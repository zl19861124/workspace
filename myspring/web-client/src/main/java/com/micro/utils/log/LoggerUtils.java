package com.micro.utils.log;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

public class LoggerUtils {


    public static Logger getLog(Class c) {
        String logName = LogHolder.getLogName();
        LoggerRepository h = LogHolder.getCurrentLogRepository(logName);
        if (h == null) {
            h = LogHolder.addLogRepository(logName);
        }
        h.getLogger(c.getName());
        return h.getLogger(c.getName());
    }

}