

package com.micro.bean.wsForAttr;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;
import org.apache.commons.digester3.annotations.rules.SetProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@ObjectCreate(pattern = "wsForAttrServicesResponse")
@XmlRootElement(name = "wsForAttrServicesResponse") //必须加这个参数，否则解析不出来
public class WsForAttrServicesOutVo {

    @SetProperty(pattern = "wsForAttrServicesResponse", attributeName = "topic")
    protected String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    private List<WsForAttrServices> wsForAttrServices = new ArrayList<>();

    @SetNext
    public void addRowData(WsForAttrServices data) {
        wsForAttrServices.add(data);
    }

    public List<WsForAttrServices> getWsForAttrServices() {
        return wsForAttrServices;
    }

    public void setWsForAttrServices(List<WsForAttrServices> wsForAttrServices) {
        this.wsForAttrServices = wsForAttrServices;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }

    @ObjectCreate(pattern = "wsForAttrServicesResponse/wsForAttrServices")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class WsForAttrServices {

        @BeanPropertySetter(pattern = "wsForAttrServicesResponse/wsForAttrServices/username")
        protected String username;
        @BeanPropertySetter(pattern = "wsForAttrServicesResponse/wsForAttrServices/gender")
        protected String gender;
        @BeanPropertySetter(pattern = "wsForAttrServicesResponse/wsForAttrServices/birthday")
        protected String birthday;
        @BeanPropertySetter(pattern = "wsForAttrServicesResponse/wsForAttrServices/location")
        protected String location;
        @XmlElementWrapper(name = "list")
        protected List<RsSet> rsSet = new ArrayList<>();


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


        public String getGender() {
            return gender;
        }


        public void setGender(String value) {
            this.gender = value;
        }


        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String value) {
            this.birthday = value;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String value) {
            this.location = value;
        }



        @Override
        public String toString() {
            return ReflectionToStringBuilder.reflectionToString(this);
        }
        @ObjectCreate(pattern = "wsForAttrServicesResponse/wsForAttrServices/list/rsSet")
        public static class RsSet {

            @BeanPropertySetter(pattern = "wsForAttrServicesResponse/wsForAttrServices/list/rsSet/acc")
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
    }
}