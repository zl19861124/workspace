package com.micro.controller;

import com.micro.bean.wsForNode.WsForNodeServicesOutVo;
import com.micro.client.WsForNodeServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WsForNodeServicesController {

	@Autowired
	private WsForNodeServicesClient wsForNodeServicesClient;

	@RequestMapping(value = "/wsForNodejson", produces = "application/json;charset=utf-8")
	// http://localhost:8081/wsForNodejson?typename=124
	// http://www.test101.com:8081/wsForNodejson?typename=124
	public Object wsForNodejson(String typename) {
		System.out.println(typename);
		WsForNodeServicesOutVo response = wsForNodeServicesClient.getUserInfo("hello");
		return response;
	}

	// http://localhost:8081/wsForNodexml/123?typename=124
	//http://www.test101.com:8081/wsForNodexml/123?typename=124
	@RequestMapping(value = "/wsForNodexml/{id}", produces = "application/xml;charset=utf-8")
	public Object wsForNodexml(@PathVariable(value = "id") String id,
			@RequestParam(name = "typename", required = true) String typename) {
		WsForNodeServicesOutVo response = wsForNodeServicesClient.getUserInfo(typename);
		System.out.println("rest:"+response);

		return response;
	}

	@RequestMapping(value = "/wsForNodehtml", produces = { MediaType.TEXT_HTML_VALUE })
	public Object wsForNodehtml() {
		WsForNodeServicesOutVo response = wsForNodeServicesClient.getUserInfo("hello");
		return response;
	}
}