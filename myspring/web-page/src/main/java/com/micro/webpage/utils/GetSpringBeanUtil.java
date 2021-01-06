package com.micro.webpage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class GetSpringBeanUtil implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(GetSpringBeanUtil.class);
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(GetSpringBeanUtil.applicationContext == null) {
            GetSpringBeanUtil.applicationContext = applicationContext;
        }
        log.info("========ApplicationContext配置成功,在普通类可以通过调用GetSpringBeanUtil.getAppContext()获取applicationContext对象,applicationContext="+GetSpringBeanUtil.applicationContext+"========");

    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
