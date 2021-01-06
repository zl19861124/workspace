package com.micro.bean.wsForNode;


import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "wsForNodeServicesResponse",namespace = "http://www.micro.com/ws/demo")
@XmlType(namespace="http://www.micro.com/ws/demo",name="wsForNodeServicesResponse")
public class WsForNodeServicesOutVo {

    @XmlElement(required = true,namespace = "http://www.micro.com/ws/demo")
    private WsForNodeServices  wsForNodeServices =new WsForNodeServices();


    public WsForNodeServices getWsForNodeServices() {
        return wsForNodeServices;
    }

    public void setWsForNodeServices(WsForNodeServices wsForNodeServices) {
        this.wsForNodeServices = wsForNodeServices;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "username",
            "gendera",
            "location",
            "rsSet"
    })
    public static class WsForNodeServices {
        @XmlElement(required = true,namespace = "http://www.micro.com/ws/demo")
        protected String username;
        @XmlElement(required = true)
        protected String gendera;
        @XmlElement(required = true)
        protected String location;

        @XmlElementWrapper(name = "list")
        protected List<RsSet> rsSet = new ArrayList<RsSet>();


        public List<RsSet> getRsSet() {
            return rsSet;
        }

        public void setRsSet(List<RsSet> rsSet) {
            this.rsSet = rsSet;
        }

        @SetNext
        public void addItems(final RsSet items) {
            rsSet.add(items);
        }

        public String getUsername() {
            return username;
        }


        public void setUsername(String value) {
            this.username = value;
        }


        public String getGendera() {
            return gendera;
        }


        public void setGendera(String value) {
            this.gendera = value;
        }



        public String getLocation() {
            return location;
        }

        public void setLocation(String value) {
            this.location = value;
        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "acc"
        })
        public static class RsSet {

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

