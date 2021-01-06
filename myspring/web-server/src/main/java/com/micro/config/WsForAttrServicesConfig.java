package com.micro.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.jws.soap.SOAPBinding;

/**
 * @author Freud
 */
@EnableWs
@Configuration
public class WsForAttrServicesConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}
    //http://www.test101.com:8080/ws/WsForAttrServices.wsdl
	//http://127.0.0.1:8080/ws/WsForAttrServices.wsdl
	// 以前写过基于JAXB的传统Java WebService服务端,通过IBM WebSphere Message
	// Broker生成过WebService服务端，最近项目要在Spring
	// MVC搭建的Web项目中添加WebService服务。研究之后发现主流的实现无外乎Apache CXF，Axis 2，Spring
	// WebService。通过对比发现，CXF需要引入的jar依赖太多，Axis 2太重量级，最后选择Spring
	// WebService,主要原因是原有的Web项目是基于Spring MVC的，Spring WebSercice可以与之无缝整合，Spring
	// WebService完全可以支撑现有的业务，并且相对比较轻量级。
	//其实WSDL就是Web Service Definition Lanauage，即WebService定义语言
	@Bean(name = "WsForAttrServices")
	@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
	public DefaultWsdl11Definition defaultWsdlWsForAttr(XsdSchema wsForAttrSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		//可以将一个请求和一个响应消息组合成单个请求/响应操作。 这在SOAP服务中最常用。 portType可以定义多个操作。
		wsdl11Definition.setPortTypeName("WsForAttrPortType");
		wsdl11Definition.setLocationUri("/ws/WsForAttrServices.wsdl");
		wsdl11Definition.setTargetNamespace("http://www.micro.com/ws/WsForAttrServices");
		wsdl11Definition.setSchema(wsForAttrSchema);
		return wsdl11Definition;
	} 

	// cd C:\Program Files\Java\jdk1.8.0_181\bin
	// xsd_to_java xjc -d c:\aa -p com.ailk.upc.inter.epc.bean  C:\1.xsd
	// wsdl_to_java
	// wsimport -keep -p com.demo.client -d c:\aa http://127.0.0.1:8080/ws/WsForAtrrServices.wsdl
	// wsimport c:/MyUserServices.wsdl -keep -p wy.soap.order -s c:/aa
	@Bean
	public XsdSchema wsForAttrSchema() {
		return new SimpleXsdSchema(new ClassPathResource("/xsd/wsForAttrServices.xsd"));

	} 
}