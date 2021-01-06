package com.micro.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://www.micro.com/ws/demo", name = "wsForNodeServicesRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class WsForNodeServicesInVo {

	@XmlElement(namespace = "http://www.micor.com/ws/demo")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
