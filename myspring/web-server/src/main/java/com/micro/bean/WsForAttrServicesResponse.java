//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2020.04.11 时间 01:43:24 PM CST 
//


package com.micro.bean;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(namespace = "http://www.micro.com/ws/demo", name = "wsForAttrServicesResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class WsForAttrServicesResponse {

    @XmlElement(name = "wsForAttrServices", required = true)
    protected WsForAttrServicesResponse.WsForAttrServices WsForAttrServices;
    @XmlAttribute(name = "topic", required = true)
    protected String topic;

    public WsForAttrServicesResponse.WsForAttrServices getWsForAttrServicesResponse() {
        return WsForAttrServices;
    }


    public void setWsForAttrServicesResponse(WsForAttrServicesResponse.WsForAttrServices value) {
        this.WsForAttrServices = value;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String value) {
        this.topic = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "username",
        "gender",
        "location",
        "rsSet"
    })
    public static class WsForAttrServices {

        @XmlElement(required = true)
        protected String username;
        @XmlElement(required = true)
        protected String gender;
        @XmlElement(required = true)
        protected String location;
        @XmlElement(required = true,name = "rsSet")
        @XmlElementWrapper(name="list")
        protected List<WsForAttrServicesResponse.WsForAttrServices.RsSet> rsSet;

        public String getUsername() {
            return username;
        }

        public void setUsername(String value) {
            this.username = value;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String value) {
            this.gender = value;
        }



        public String getLocation() {
            return location;
        }

        public void setLocation(String value) {
            this.location = value;
        }

        public List<WsForAttrServicesResponse.WsForAttrServices.RsSet> getRsSet() {
            if (rsSet == null) {
                rsSet = new ArrayList<WsForAttrServicesResponse.WsForAttrServices.RsSet>();
            }
            return this.rsSet;
        }

        public void setRsSet(List<RsSet> rsSet) {
            this.rsSet = rsSet;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "acc"
        })
        public static class RsSet {

            @XmlElement(required = true)
            protected String acc;

            public String getAcc() {
                return acc;
            }

            public void setAcc(String value) {
                this.acc = value;
            }
            @Override
            public String toString() {
                return ReflectionToStringBuilder.reflectionToString(this);
            }
        }
        @Override
        public String toString() {
            return ReflectionToStringBuilder.reflectionToString(this);
        }
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
