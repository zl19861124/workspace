//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.3.0 生成的
// 请访问 <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2020.04.11 时间 11:04:40 AM CST 
//


package com.micro.bean.wsForAttr;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "userId"
})
@XmlRootElement(name = "wsForAttrServicesRequest")
public class WsForAttrServicesInVo {

    @XmlElement(required = true)
    protected String userId;


    public String getUserId() {
        return userId;
    }


    public void setUserId(String value) {
        this.userId = value;
    }

}
