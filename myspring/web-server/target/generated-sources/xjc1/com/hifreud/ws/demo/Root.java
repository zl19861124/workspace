//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2020.04.11 时间 02:56:50 PM CST 
//


package com.hifreud.ws.demo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="User1Response">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="birthday" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="rsSet" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="topic" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user1Response"
})
@XmlRootElement(name = "Root")
public class Root {

    @XmlElement(name = "User1Response", required = true)
    protected Root.User1Response user1Response;
    @XmlAttribute(name = "topic", required = true)
    protected String topic;

    /**
     * 获取user1Response属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Root.User1Response }
     *     
     */
    public Root.User1Response getUser1Response() {
        return user1Response;
    }

    /**
     * 设置user1Response属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Root.User1Response }
     *     
     */
    public void setUser1Response(Root.User1Response value) {
        this.user1Response = value;
    }

    /**
     * 获取topic属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopic() {
        return topic;
    }

    /**
     * 设置topic属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopic(String value) {
        this.topic = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="birthday" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="rsSet" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "username",
        "gender",
        "birthday",
        "location",
        "rsSet"
    })
    public static class User1Response {

        @XmlElement(required = true)
        protected String username;
        @XmlElement(required = true)
        protected String gender;
        @XmlElement(required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar birthday;
        @XmlElement(required = true)
        protected String location;
        @XmlElement(required = true)
        protected List<Root.User1Response.RsSet> rsSet;

        /**
         * 获取username属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUsername() {
            return username;
        }

        /**
         * 设置username属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUsername(String value) {
            this.username = value;
        }

        /**
         * 获取gender属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGender() {
            return gender;
        }

        /**
         * 设置gender属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGender(String value) {
            this.gender = value;
        }

        /**
         * 获取birthday属性的值。
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getBirthday() {
            return birthday;
        }

        /**
         * 设置birthday属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setBirthday(XMLGregorianCalendar value) {
            this.birthday = value;
        }

        /**
         * 获取location属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLocation() {
            return location;
        }

        /**
         * 设置location属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLocation(String value) {
            this.location = value;
        }

        /**
         * Gets the value of the rsSet property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rsSet property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRsSet().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Root.User1Response.RsSet }
         * 
         * 
         */
        public List<Root.User1Response.RsSet> getRsSet() {
            if (rsSet == null) {
                rsSet = new ArrayList<Root.User1Response.RsSet>();
            }
            return this.rsSet;
        }


        /**
         * <p>anonymous complex type的 Java 类。
         * 
         * <p>以下模式片段指定包含在此类中的预期内容。
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="acc" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "acc"
        })
        public static class RsSet {

            @XmlElement(required = true)
            protected String acc;

            /**
             * 获取acc属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAcc() {
                return acc;
            }

            /**
             * 设置acc属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAcc(String value) {
                this.acc = value;
            }

        }

    }

}
