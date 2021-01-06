package com.micro.client;

import com.micro.bean.wsForAttr.WsForAttrServicesInVo;
import com.micro.bean.wsForAttr.WsForAttrServicesOutVo;
import com.micro.utils.io.MicroIoUtil;
import com.micro.utils.log.LoggerUtils;
import com.micro.utils.services.GKServiceInvoke;
import com.micro.utils.xml.BeanToXmlParser;
import com.micro.utils.xml.XmlToBeanUtil;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WsForAttrServicesClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerUtils.getLog(WsForAttrServicesClient.class);
    @Value("${spring.application.wsForAttrServicesWsdl}")
    private String epr;


    public WsForAttrServicesOutVo getUserInfo(String userid) {

        WsForAttrServicesInVo request = new WsForAttrServicesInVo();
        request.setUserId(userid);
        log.info("通过axis的方式调用 "+request.getUserId());
         return sendSvrForAxis(request);

    }

    private WsForAttrServicesOutVo sendSvrForAxis(WsForAttrServicesInVo request) {
        List<WsForAttrServicesOutVo> lst= new ArrayList<WsForAttrServicesOutVo>();
        WsForAttrServicesOutVo response = new WsForAttrServicesOutVo();
        try {
            Map<String, Object> dataMap =new HashMap<String, Object>();
            dataMap.put("userid",request.getUserId());
            String invo= BeanToXmlParser.getInstance().
                    beanToXml("micro/wsForAttrServicesinvo.vm",dataMap,"");
            OMElement xmlValue = new StAXOMBuilder(new ByteArrayInputStream(
                    invo.getBytes("UTF-8"))).getDocumentElement();
            OMElement retxml = GKServiceInvoke.invokeAndReturnOmeFor(xmlValue, epr, new HashMap(),
                    50000);
           String gkRsp = retxml.toString().replaceAll("\\<\\w+\\:",
                   "<").replaceAll("\\</\\w+\\:", "</");
            log.info("返回xml"+gkRsp);
            lst= XmlToBeanUtil.getInstance().parseXml(WsForAttrServicesOutVo.class, gkRsp, "UTF-8");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if(lst.size()>0) {
            log.info(lst.get(0));
            return lst.get(0);
        }
        return  null;
    }


}
