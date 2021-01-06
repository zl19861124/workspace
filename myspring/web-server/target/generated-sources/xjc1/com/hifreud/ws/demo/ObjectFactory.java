//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.7 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2020.04.11 时间 02:56:50 PM CST 
//


package com.hifreud.ws.demo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hifreud.ws.demo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hifreud.ws.demo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Root }
     * 
     */
    public Root createRoot() {
        return new Root();
    }

    /**
     * Create an instance of {@link Root.User1Response }
     * 
     */
    public Root.User1Response createRootUser1Response() {
        return new Root.User1Response();
    }

    /**
     * Create an instance of {@link User1Request }
     * 
     */
    public User1Request createUser1Request() {
        return new User1Request();
    }

    /**
     * Create an instance of {@link Root.User1Response.RsSet }
     * 
     */
    public Root.User1Response.RsSet createRootUser1ResponseRsSet() {
        return new Root.User1Response.RsSet();
    }

}
