package com.micro.utils.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class ClassResourceLoader extends DefaultResourceLoader {
    private static final Log log = LogFactory.getLog(ClassResourceLoader.class);

    public Resource getResourceByPath(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            log.info("从类路径下读取文件");
            return new ClassPathResource
                    (location.substring(CLASSPATH_URL_PREFIX.length()), cl);
        }
        else {
            log.info("从文件系统中读取文件");
            return new FileSystemResource(location);
        }

    }

    public Resource getResourceByPath(String location,ClassLoader loader) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            log.info("从类路径下读取文件");
            return new ClassPathResource
                    (location.substring(CLASSPATH_URL_PREFIX.length()), loader);
        }
        else {
            log.info("从文件系统中读取文件");
            return new FileSystemResource(location);
        }

    }


}