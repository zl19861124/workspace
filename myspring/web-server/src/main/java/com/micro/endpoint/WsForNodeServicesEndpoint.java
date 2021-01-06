package com.micro.endpoint;

import com.micro.bean.WsForNodeServicesInVo;
import com.micro.bean.WsForNodeServicesOutVo;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

 
@Endpoint
public class WsForNodeServicesEndpoint {

	@PayloadRoot(namespace = "http://www.micro.com/ws/demo", localPart = "wsForNodeServicesRequest")
	@ResponsePayload
	public WsForNodeServicesOutVo findUserById(@RequestPayload WsForNodeServicesInVo request) throws Exception {


		WsForNodeServicesOutVo WsForNodeServicesResponse =new WsForNodeServicesOutVo();
		//root.setTopic("aa");
		WsForNodeServicesOutVo.WsForNodeServices response = new WsForNodeServicesOutVo.WsForNodeServices();
		response.setUsername("Freud");
		response.setGender("Male");
		response.setLocation("Dalian");
		WsForNodeServicesOutVo.WsForNodeServices.RsSet rs=new WsForNodeServicesOutVo.WsForNodeServices.RsSet();
		rs.setAcc(System.currentTimeMillis()+"");
		WsForNodeServicesOutVo.WsForNodeServices.RsSet rs2=new WsForNodeServicesOutVo.WsForNodeServices.RsSet();
		rs2.setAcc(System.currentTimeMillis()+"");
		List<WsForNodeServicesOutVo.WsForNodeServices.RsSet> rss=new ArrayList<>();
		rss.add(rs);
		rss.add(rs2);
		response.setRsSet(rss);
		WsForNodeServicesResponse.setWsForNodeServices(response);
		System.out.println(WsForNodeServicesResponse);
		return WsForNodeServicesResponse;
	}

}