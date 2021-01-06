package com.micro;

import com.micro.bean.wsForAttr.WsForAttrServicesOutVo;
import com.micro.utils.io.MicroIoUtil;
import com.micro.utils.log.LoggerUtils;
import com.micro.utils.xml.BeanToXmlParser;
import com.micro.utils.xml.XmlToBeanUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
public class XmlToBeanTest {
    private static final Logger log = LoggerUtils.getLog(XmlToBeanTest.class);
    @Test
    public void testXmlToBean() throws Exception {
        List<WsForAttrServicesOutVo> lst = new ArrayList<WsForAttrServicesOutVo>();
        String outvo =  MicroIoUtil.loadFromFileAsString("micro/wsForAttrServicesoutvo.xml");
        String gkRsp = outvo.toString().replaceAll("\\<\\w+\\:", "<").replaceAll("\\</\\w+\\:", "</");
        log.info("返回xml" + gkRsp);
        lst = XmlToBeanUtil.getInstance().parseXml(WsForAttrServicesOutVo.class, gkRsp, "UTF-8");
        if (lst.size() > 0) {
            log.info(lst.get(0));
        }else
        {
            log.info("数据为空");
        }
    }
}
