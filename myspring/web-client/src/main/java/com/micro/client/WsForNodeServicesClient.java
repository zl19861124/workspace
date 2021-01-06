package com.micro.client;

import com.micro.bean.wsForNode.WsForNodeServicesInVo;
import com.micro.bean.wsForNode.WsForNodeServicesOutVo;
import com.micro.utils.log.LoggerUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


public class WsForNodeServicesClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerUtils.getLog(WsForNodeServicesClient.class);

     @Value("${spring.application.wsForNodeServicesWsdl}")
    private String epr;
    public WsForNodeServicesOutVo getUserInfo(String userid) {

        WsForNodeServicesInVo request = new WsForNodeServicesInVo();
        request.setUserId(userid);
        log.info("通过spring的方式调用： "+request.getUserId());
          return  sendSvrForSpring(request);

    }


    //springboot方式获取不到topic的值
    private WsForNodeServicesOutVo sendSvrForSpring(WsForNodeServicesInVo request) {
        Object obj = (Object)getWebServiceTemplate().marshalSendAndReceive(
                epr, request);
        System.out.println(obj.toString());
        WsForNodeServicesOutVo response = (WsForNodeServicesOutVo)obj;
        System.out.println("通过spring的方式调用返回： "+response);
        return response;
    }

}
