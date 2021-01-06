package com.micro.utils.log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.RootLogger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.core.io.Resource;

public class LogHolder {

    private static Map<String, LoggerRepository> logRspMap = new HashMap<String, LoggerRepository>();

    private static final Log log = LogFactory.getLog(LogHolder.class);

    private static Map<String, List<DayMaxRollingFileAppender>> appenderMap = new HashMap<String, List<DayMaxRollingFileAppender>>();

    public static LoggerRepository addLogRepository(String logName) {
        try {
            if (logRspMap.get(logName) != null) {
                return logRspMap.get(logName);
            }
            LoggerRepository h = new Hierarchy(new RootLogger(Level.ALL));
            ClassResourceLoader loader = new ClassResourceLoader();
            Resource resource = loader.getResourceByPath("classpath:sulog4j.xml");
            new DOMConfigurator().doConfigure(resource.getInputStream(), h);
            logRspMap.put(logName, h);
            List<DayMaxRollingFileAppender> appenderList = new ArrayList<DayMaxRollingFileAppender>(5);
            appenderMap.put(logName, appenderList);
            return h;
        } catch (Exception ex) {
            throw new RuntimeException("初始化日志失败", ex);
        }
    }

    public static LoggerRepository getCurrentLogRepository(String logName) {
        return logRspMap.get(logName);
    }

    public static void removeLoggerRepository() {
        String logName = getLogName();
        // 关闭日志引用
        log.info("开始移除日志引用");
        log.info("logName:" + logName + " loggerRepository:" + logRspMap.get(logName));
        if (logRspMap.get(logName) != null) {
            LoggerRepository repository = logRspMap.remove(logName);
            repository.shutdown();

            log.info("logName:appenderSize" + appenderMap.get(logName).size());
            final List<DayMaxRollingFileAppender> appenders = appenderMap.remove(logName);
            if (appenders != null) {
                for (DayMaxRollingFileAppender appender : appenders) {
                    appender.close();
                }
            }

        }
    }

    public static void addAppender(DayMaxRollingFileAppender appender) {
        String logName = getLogName();
        if (appenderMap.get(logName) != null) {
            appenderMap.get(logName).add(appender);
        }
    }

    public static String getLogName() {
        String logName = "";
        try {
            Object logNameObj ="log++"; //InSaUtil.getInSa(SoaFrameContants.LOGNAME);
            if (logNameObj == null) {
                logName = UUID.randomUUID().toString();
               // log.info("新建logName:" + logName);
               // InSaUtil.putInSa(SoaFrameContants.LOGNAME, logName);
            } else {
                logName = logNameObj.toString();
            }
        } catch (Exception ex) {
            throw new RuntimeException("初始化日志失败", ex);
        }
        return logName;
    }

}
