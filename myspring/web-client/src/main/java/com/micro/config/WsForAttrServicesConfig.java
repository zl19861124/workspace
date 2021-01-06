package com.micro.config;

import com.micro.client.WsForAttrServicesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WsForAttrServicesConfig {
    @Value("${spring.application.wsForAttrServicesWsdl}")
    private String epr;
    @Value("${spring.application.marshallerAttrContextPath}")
    private String marshallerAttrContextPath;
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(marshallerAttrContextPath);
        return marshaller;
    }

    @Bean
    public WsForAttrServicesClient wsForAttrServicesClient(Jaxb2Marshaller marshaller) {
        WsForAttrServicesClient client = new WsForAttrServicesClient();
        client.setDefaultUri(epr);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}