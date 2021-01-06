package com.micro.utils.log;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.io.IOException;


public class DayMaxRollingFileAppenderProxy extends AppenderSkeleton {
    // private static Map<String, DayMaxRollingFileAppender> fileAppenders = new
    // ConcurrentHashMap<String, DayMaxRollingFileAppender>();

    private String file;
    private int maxBackupIndex;

    private DayMaxRollingFileAppender appender = null;

    public DayMaxRollingFileAppender getDayMaxRollingFileAppender() {

        if (appender != null) {
            return appender;
        }

        /**
         * 解决集群情况下的日志分包问题
         */
        String weblogicName = System.getProperty("weblogic.Name");

        if (StringUtils.isEmpty(weblogicName)) {
            weblogicName = System.getProperty("com.cvicse.las.instanceName");
        }

        String domain = "domain";
        if (weblogicName != null) {
            int key = file.indexOf(weblogicName);
            if (key == -1) {
                String saLogs = "saLogs/";
                StringBuffer sb = new StringBuffer();
                sb.append(saLogs);
                sb.append(domain);
                sb.append("/");
                sb.append(weblogicName);
                sb.append("/");
                String assembleString = sb.toString();
                file = StringUtils.replace(file, saLogs, assembleString);
            }
        }
        try {
            appender = new DayMaxRollingFileAppender(this.layout, this.file, this.maxBackupIndex);
            appender.setName(this.getName());
            appender.setSoa3(isSoa3());
            LogHolder.addAppender(appender);
            return appender;
        } catch (IOException e) {
            throw new RuntimeException("初始化日志失败", e);
        }

    }
    public static boolean isSoa3() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            Class.forName("com.micro.utils.log.LoggerUtils", false, classLoader);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }
    public DayMaxRollingFileAppenderProxy() {

    }

    @Override
    protected void append(LoggingEvent event) {
        getDayMaxRollingFileAppender().append(event);
    }

    public void close() {
        getDayMaxRollingFileAppender().requiresLayout();
    }

    public boolean requiresLayout() {
        return getDayMaxRollingFileAppender().requiresLayout();
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public String getFile(){
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setMaxBackupIndex(int maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }



}
