package com.micro.utils.log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DayMaxRollingFileAppender extends FileAppender {
    /** 不允许改写的datepattern */
    private String datePattern = "'.'MMdd";

    /** 最多文件增长个数 */
    private int maxBackupIndex = 30;

    private int maxBackupFileIndex = 30;

    RollingCalendar rc = new RollingCalendar();

    static final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");

    private String scheduledFilename;

    /**
     * The next time we estimate a rollover should occur.
     */
    private long nextCheck = System.currentTimeMillis() - 1;

    Date now = new Date();

    SimpleDateFormat sdf;

    /**
     * The default maximum file size is 10MB.
     */
    protected long maxFileSize = 1024 * 1024 * 10;

    private long nextRollover = 0;

    private static final ThreadLocal<StringBuffer> logBufferT = new ThreadLocal<StringBuffer>();

    private boolean isSoa3 = false;

    /**
     * The default constructor does nothing.
     */
    public DayMaxRollingFileAppender() {
    }

    /**
     * 改造过的构造器
     */
    public DayMaxRollingFileAppender(Layout layout, String filename, int maxBackupIndex) throws IOException {
        super(layout, filename, true);
        this.maxBackupIndex = maxBackupIndex;
        activateOptions();
    }

    /**
     * 初始化本Appender对象的时候调用一次
     */
    public void activateOptions() {
        super.activateOptions();
        // if(fileName != null) { //perf.log
        // now.setTime(System.currentTimeMillis());
        // sdf = new SimpleDateFormat(datePattern);
        // File file = new File(fileName);
        // //获取最后更新时间拼成的文件名
        // scheduledFilename = fileName+sdf.format(new
        // Date(file.lastModified()));
        // } else {
        // LogLog.error("File is not set for appender ["+name+"].");
        // }
        if ((this.datePattern != null) && (this.fileName != null)) {
            this.now.setTime(System.currentTimeMillis());
            this.sdf = new SimpleDateFormat(this.datePattern);
            int type = computeCheckPeriod();
            this.rc.setType(type);
            File file = new File(this.fileName);
            this.scheduledFilename = this.fileName + this.sdf.format(new Date(file.lastModified()));

        } else {
            LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
        }
        if (maxBackupIndex <= 0) {
            LogLog.error("maxBackupIndex reset to default value[2],orignal value is:" + maxBackupIndex);
            maxBackupIndex = 2;
        }
    }

    int computeCheckPeriod() {
        RollingCalendar rollingCalendar = new RollingCalendar(gmtTimeZone, Locale.ENGLISH);

        Date epoch = new Date(0L);
        if (this.datePattern != null) {
            for (int i = 0; i <= 5; ++i) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.datePattern);
                simpleDateFormat.setTimeZone(gmtTimeZone);
                String r0 = simpleDateFormat.format(epoch);
                rollingCalendar.setType(i);
                Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
                String r1 = simpleDateFormat.format(next);

                if ((r0 != null) && (r1 != null) && (!(r0.equals(r1)))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 滚动文件的函数：
     */
    void rollOver() throws IOException {

        // 预期文件的名称 fileName+时间
        String datedFilename = this.fileName + this.sdf.format(this.now);

        File directoryFile = new File(this.fileName).getParentFile();
        // 如果正在执行的文件名和预期的文件名相同，则不回滚
        if (!this.scheduledFilename.equals(datedFilename)) {
            this.rollOverByDate(directoryFile, datedFilename);
        }
    }

    private void rollOverByDate(File directoryFile, String datedFilename) throws IOException {

        // 重命名是否成功
        boolean renameSucceeded = true;

        // 关闭文件
        this.closeFile();

        if (this.maxBackupIndex > 0) {
            // 发生回滚先将回大版本号删除
            Collection<File> files = getFile(directoryFile, this.fileName, this.maxBackupIndex);
            if (files.size() > 0) {
                for (File f : files) {
                    renameSucceeded = f.delete();
                }
            }
            renameSucceeded = fileMoveBack(directoryFile, renameSucceeded);
            renameSucceeded = renameSucceedProcess(renameSucceeded);
        }

        // 如果重命名不成功，使用原来的
        if (!renameSucceeded) {
            return;
        }

        try {
            setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
            this.nextRollover = 0;
        } catch (IOException e) {
            LogLog.error("setFile(" + this.fileName + ", false) call failed.", e);
        }
        scheduledFilename = datedFilename;
    }

    private boolean fileMoveBack(File directoryFile, boolean renameSucceeded) {
        Collection<File> files;
        File target;

        // 将文件的序号逐个向后移
        for (int i = this.maxBackupIndex - 1; (i >= 1) && (renameSucceeded); --i) {
            files = getFile(directoryFile, this.fileName, i);
            if (files.size() > 0) {

                for (File f : files) {
                    String targetFileDate = this.getFileData(f, i);
                    target = new File(this.fileName + '.' + (i + 1) + "." + targetFileDate + ".zip");
                    LogLog.debug("Renaming file " + f + " to " + target);
                    renameSucceeded = this.copy(f, target);

                    // 如果文件移动成功，删除原有文件（让地方）
                    if (renameSucceeded)
                        f.delete();
                }

            }
        }
        return renameSucceeded;
    }

    private boolean renameSucceedProcess(boolean renameSucceeded) {
        if (renameSucceeded) {
            String data = this.scheduledFilename.substring((this.fileName + ".").length());
            super.closeFile();
            List<File> zipFiles = new ArrayList<File>();
            File file = new File(this.fileName);
            zipFiles.add(file);
            String zipFileName = this.fileName + "." + 1 + "." + data + ".zip";

            // 添加日志文件
            for (int i = 1; i < this.maxBackupFileIndex + 1 && renameSucceeded; i++) {
                File[] indexFiles = FileDirectorySearch.getFiles(fileName + i + ".*");
                if (indexFiles.length > 0) {
                    zipFiles.add(indexFiles[0]);
                }
            }
            // 压缩日志文件
            renameSucceeded = this.zipFile(zipFileName, zipFiles);
            // 如果压缩成功，删除日志文件
            compressSucceedDF(renameSucceeded, zipFiles);
            // 如果重命名不成功，使用原来的
            renameSucceeded(renameSucceeded);

        }
        return renameSucceeded;
    }

    private void compressSucceedDF(boolean renameSucceeded, List<File> zipFiles) {
        if (renameSucceeded) {
            for (File deletefile : zipFiles) {
                deletefile.delete();
            }

        }
    }

    private void renameSucceeded(boolean renameSucceeded) {
        if (!(renameSucceeded)) {
            try {
                setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
            } catch (IOException e) {
                LogLog.error("setFile(" + this.fileName + ", true) call failed.", e);
            }
        }
    }

    private void rollOverBySize() {
        File target;
        File file;

        if (qw != null) {
            long size = ((CountingQuietWriter) qw).getCount();
            nextRollover = size + maxFileSize;
        }

        boolean renameSucceeded = true;
        // If maxBackups <= 0, then there is no file renaming to be done.
        if (maxBackupFileIndex > 0) {
            // Delete the oldest file, to keep Windows happy.
            File[] files = FileDirectorySearch.getFiles(fileName + maxBackupFileIndex + ".*");
            if (files != null && files.length > 0) {
                file = files[0];
                renameSucceeded = file.delete();
            }

            for (int i = maxBackupFileIndex - 1; i >= 1 && renameSucceeded; i--) {
                File[] indexFiles = FileDirectorySearch.getFiles(fileName + i + ".*");
                if (indexFiles != null && indexFiles.length > 0) {
                    file = indexFiles[0];
                    String dataRegion = this.getDataRegion(file.getName());
                    String targetFileName = fileName + String.valueOf(i + 1);
                    if (StringUtils.isNotEmpty(dataRegion)) {
                        targetFileName = targetFileName + "." + dataRegion;
                    }
                    target = new File(targetFileName);
                    LogLog.debug("Renaming file " + file + " to " + target);
                    renameSucceeded = this.copy(file, target);
                    file.delete();
                }
            }

            renameSucceeded = renameFile(renameSucceeded);
        }

        //
        // if all renames were successful, then
        //
        if (renameSucceeded) {
            try {
                // This will also close the file. This is OK since multiple
                // close operations are safe.
                this.setFile(fileName, false, bufferedIO, bufferSize);
                nextRollover = 0;
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("setFile(" + fileName + ", false) call failed.", e);
            }
        }
    }

    private boolean renameFile(boolean renameSucceeded) {
        File target;
        File file;
        if (renameSucceeded) {
            file = new File(fileName);
            String endTime = getNowTime();
            String startTime = getLogStartTime(file);
            // Rename fileName to fileName.1
            target = new File(fileName + 1 + "." + startTime + " To " + endTime);

            this.closeFile(); // keep windows happy.
            LogLog.debug("Renaming file " + file + " to " + target);
            renameSucceeded = this.copy(file, target);
            //
            // if file rename failed, reopen file with append = true
            //
            if (!renameSucceeded) {
                try {
                    this.setFile(fileName, true, bufferedIO, bufferSize);
                } catch (IOException e) {
                    if (e instanceof InterruptedIOException) {
                        Thread.currentThread().interrupt();
                    }
                    LogLog.error("setFile(" + fileName + ", true) call failed.", e);
                }
            }
        }
        return renameSucceeded;
    }

    private static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        return sdf.format(new Date());
    }

    /**
     * 日志名称类似于loggerTest.log.2.2012-03-27,
     * 通过loggerTest.log.2查找日志目录中所有以loggerTest.log.2开头中的第一个。
     *
     * @param directoryFile
     *            日志目录
     * @param name
     *            日志名称
     * @return
     */
    @SuppressWarnings("unchecked")
    private Collection<File> getFile(File directoryFile, String name, int index) {

        String fileName = new File(name).getName();
        String fileNameRegex = fileName + "[0-9]{0,2}" + "\\." + index + "\\.[\\s\\S]*\\.zip";

        RegexFileFilter fileFilter = new RegexFileFilter(fileNameRegex);
        Collection<File> c = FileUtils.listFiles(directoryFile, fileFilter, null);

        return c;
    }

    /**
     * 通过文件名和备份号，得到这个日志文件名称中的日期
     *
     * @param file
     *            文件名 exp:loggerTest.log.2.2012-03-27
     * @param index
     *            备份号：exp:2
     * @return
     */
    private String getFileData(File file, int index) {
        String data = "";

        if (file.exists()) {
            String fileName = file.getName();
            int beginLeg = ("log." + index + ".").length();
            int begin = fileName.lastIndexOf("log." + index + ".");
            int end = fileName.lastIndexOf(".zip");
            data = fileName.substring(begin + beginLeg, end);
        }
        return data;
    }

    /**
     * Actual writing occurs here. 这个方法是写操作真正的执行过程！
     * 在Category中callAppenders是线程安全的，所以在此关于文件的操作，也是线程安全的。
     * */
    protected void subAppend(LoggingEvent event) {
        long n = System.currentTimeMillis();
        if (n >= this.nextCheck) {
            this.now.setTime(n);
            this.nextCheck = this.rc.getNextCheckMillis(this.now);
            try {
                rollOver();
            } catch (IOException ioe) {
                LogLog.error("rollOver() failed.", ioe);
            }
        }

        long size = ((CountingQuietWriter) qw).getCount();
        if (size >= maxFileSize && size >= nextRollover) {
            rollOverBySize();
        }
        super.subAppend(event);

        // 非调试信息才输出

        if (this.getName().equals("INFO_FILE") && this.isSoa3) {
            StringBuffer logSb = getThreadLogSb();
            logSb.append("#sa#").append(this.getLayout().format(event));

            String[] s = event.getThrowableStrRep();
            if (s != null) {
                int len = s.length;
                for (int i = 0; i < len; ++i) {
                    logSb.append(s[i]);
                    logSb.append(Layout.LINE_SEP);
                }
            }
        }
    }


    private static StringBuffer getThreadLogSb() {
        StringBuffer logSb = logBufferT.get();
        if (logSb == null) {
            logSb = new StringBuffer();
            logBufferT.set(logSb);
        }

        if (logSb.length() > 1024 * 1024) {
            reSetThreadLogSb();
            logSb = logBufferT.get();
        }
        return logSb;
    }

    public static void reSetThreadLogSb() {
        StringBuffer logSb = logBufferT.get();

        if (logSb != null) {
            logSb.setLength(0);
            logSb = new StringBuffer();
            logBufferT.remove();
            logBufferT.set(logSb);
        }
    }

    static String getThreadLog() {
        return getThreadLogSb().toString();
    }

    public int getMaxBackupIndex() {
        return maxBackupIndex;
    }

    public void setMaxBackupIndex(int maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }

    public void closeFile() {
        super.closeFile();
    }

    protected void setQWForFiles(Writer writer) {
        this.qw = new CountingQuietWriter(writer, errorHandler);
    }

    boolean copy(File src, File dst) {
        try {
            InputStream in = new FileInputStream(src);

            OutputStream out = new FileOutputStream(dst);

            // Transfer bytes from in to out
            byte[] buf = new byte[8192];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            LogLog.error("源文件不存在,或者目标文件无法被识别.", e);
            return false;
        } catch (IOException e) {
            LogLog.error("文件读写错误.");
            return false;
        }
    }

    private static String getLogStartTime(File file) {

        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            inputReader = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(inputReader);
            // 读取一行
            String line = bufferReader.readLine();
            return line.substring(11, 19).replace(":", "-");
        }

        catch (IOException e)

        {
            e.printStackTrace();
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
                if (inputReader != null) {
                    inputReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return "";

    }

    private boolean zipFile(String zipFileName, List<File> inputFiles) {
        boolean test = true;
        try {

            BufferedInputStream origin = null;

            FileOutputStream dest = new FileOutputStream(zipFileName);

            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

            byte data[] = new byte[2048];

            for (int i = 0; i < inputFiles.size(); i++) {

                File file = inputFiles.get(i);

                FileInputStream fi = new FileInputStream(file);

                origin = new BufferedInputStream(fi, 2048);

                ZipEntry entry = new ZipEntry(file.getName());

                out.putNextEntry(entry);

                int count;

                while ((count = origin.read(data, 0, 2048)) != -1) {

                    out.write(data, 0, count);

                }

                origin.close();

            }

            out.close();

        } catch (Exception e) {

            test = false;

            e.printStackTrace();

        }

        return test;

    }

    private String getDataRegion(String fileName) {
        String dataRegion = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if (!dataRegion.contains("To")) {
            return "";
        }
        return dataRegion;
    }

    public boolean isSoa3() {
        return isSoa3;
    }

    public void setSoa3(boolean isSoa3) {
        this.isSoa3 = isSoa3;
    }

}