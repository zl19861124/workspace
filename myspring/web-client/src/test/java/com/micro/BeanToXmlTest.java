package com.micro;

import com.micro.bean.wsForAttr.WsForAttrServicesInVo;
import com.micro.utils.ftl.FtlBeanToXml;
import com.micro.utils.log.LoggerUtils;
import com.micro.utils.xml.*;
import freemarker.template.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
public class BeanToXmlTest {
    private static final Logger log = LoggerUtils.getLog(BeanToXmlTest.class);
    @Test
    public void testBeanToXmlFromVelocity() throws Exception {
        Map<String, Object> dataMap =new HashMap<String, Object>();
        dataMap.put("userid", "1234");
        String tempstr=   BeanToXmlParser.getInstance().
                beanToXml("micro/wsForAttrServicesinvo.vm",dataMap,"");
        log.info(tempstr);
        log.info("==============================================");
    }
    @Test
    public void testBeanToXmlFromFreemarker() throws Exception {
        FtlBeanToXml ftl=new FtlBeanToXml();
        log.info(ftl.beanToXml("micro","wsForAttrServicesinvo.ftl"));
    }

}
