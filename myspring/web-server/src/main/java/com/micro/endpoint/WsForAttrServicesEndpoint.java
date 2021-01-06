package com.micro.endpoint;

import com.micro.bean.WsForAttrServicesResponse;
import com.micro.bean.WsForAttrServicesRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Freud
 */
@Endpoint
public class WsForAttrServicesEndpoint {

	@PayloadRoot(namespace = "http://www.micro.com/ws/demo", localPart = "wsForAttrServicesRequest")
	@ResponsePayload
	public WsForAttrServicesResponse findUserById(@RequestPayload WsForAttrServicesRequest request) throws Exception {


		WsForAttrServicesResponse WsForAttrServicesResponse =new WsForAttrServicesResponse();
		//root.setTopic("aa");
		WsForAttrServicesResponse.WsForAttrServices response = new WsForAttrServicesResponse.WsForAttrServices();
		response.setUsername("Freud");
		response.setGender("Male");
		response.setLocation("Dalian");
		WsForAttrServicesResponse.WsForAttrServices.RsSet rs=new WsForAttrServicesResponse.WsForAttrServices.RsSet();
		rs.setAcc(System.currentTimeMillis()+"");
		WsForAttrServicesResponse.WsForAttrServices.RsSet rs2=new WsForAttrServicesResponse.WsForAttrServices.RsSet();
		rs2.setAcc(System.currentTimeMillis()+"");
		List<WsForAttrServicesResponse.WsForAttrServices.RsSet> rss=new ArrayList<>();
		rss.add(rs);
		rss.add(rs2);
		response.setRsSet(rss);
		WsForAttrServicesResponse.setWsForAttrServicesResponse(response);
		System.out.println(WsForAttrServicesResponse);
		return WsForAttrServicesResponse;
	}

}