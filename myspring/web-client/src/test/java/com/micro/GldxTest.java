package com.micro;

import com.micro.utils.io.MicroIoUtil;
import com.micro.utils.log.LoggerUtils;
import com.micro.utils.xml.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
public class GldxTest {
    private static final Logger log = LoggerUtils.getLog(GldxTest.class);
    @Test
    public void testBeanToXmlFromConfig() throws Exception {
        XmlToVo xmlToVo = new XmlToVo();
        XmlVoToPo xmlVoToPo = new XmlVoToPo();
        final String gldxData = MicroIoUtil.loadFromFileAsString("gldx/data.xml");
        final String configXml =MicroIoUtil.loadFromFileAsString("gldx/config.xml");



        //  LOG.debug("管理对象报文:" + gldxData);
        // LOG.info("管理对象配置文件:" + configXml);
        //  LOG.info("systemID:" + systemID);

        // 解析配置文件
        final Config config = ParseConfig.getConfig(configXml);

        // 解析报文vo
        final List<GldxXmlVo> vos = xmlToVo.parse(gldxData, config);

        // 将报文vo转换成管理对象PO
        final List pos = xmlVoToPo.transform(vos, config);
        Map<String, Object> dataMap =new HashMap<String, Object>();
        dataMap.put("gldxPoList", pos);
        dataMap.put("config", config);
      //  final String template =MicroIoUtil.loadFromFileAsString("gldx/vm/gldx.vm");
        final String grisXml =  BeanToXmlParser.getInstance().beanToXml("gldx/gldx.vm",dataMap,"");
        log.debug("组装出来的管控管理对象xml为：" + grisXml);
    }
}
