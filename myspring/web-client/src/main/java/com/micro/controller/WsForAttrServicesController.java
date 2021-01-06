package com.micro.controller;

import com.micro.bean.wsForAttr.WsForAttrServicesOutVo;
import com.micro.client.WsForAttrServicesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WsForAttrServicesController {

	@Autowired
	private WsForAttrServicesClient wsForAttrServicesClient;

	@RequestMapping(value = "/wsForAttrjson", produces = "application/json;charset=utf-8")
	// http://localhost:8081/wsForAttrjson?typename=124
	// http://www.test101.com:8081/wsForAttrjson?typename=124
	public Object wsForAttrjson(String typename) {
		System.out.println(typename);
		WsForAttrServicesOutVo response = wsForAttrServicesClient.getUserInfo("hello");
		return response;
	}

	// http://localhost:8081/wsForAttrxml/123?typename=124
	//http://www.test101.com:8081/wsForAttrxml/123?typename=124
	@RequestMapping(value = "/wsForAttrxml/{id}", produces = "application/xml;charset=utf-8")
	public Object wsForAttrxml(@PathVariable(value = "id") String id,
			@RequestParam(name = "typename", required = true) String typename) {
		WsForAttrServicesOutVo response = wsForAttrServicesClient.getUserInfo(typename);
		System.out.println("rest:"+response);

		return response;
	}

	@RequestMapping(value = "/wsForAttrhtml", produces = { MediaType.TEXT_HTML_VALUE })
	public Object wsForAttrhtml() {
		WsForAttrServicesOutVo response = wsForAttrServicesClient.getUserInfo("hello");
		return response;
	}
}