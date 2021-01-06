package com.micro.config;

import com.micro.client.WsForAttrServicesClient;
import com.micro.client.WsForNodeServicesClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Configuration
//@ConfigurationProperties(prefix = "spring")
//@Component
public class WsForNodeServicesConfig {
   @Value("${spring.application.wsForNodeServicesWsdl}")
    private String epr;
   @Value("${spring.application.marshallerNodeContextPath}")
    private String marshallerNodeContextPath;
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(marshallerNodeContextPath);
        return marshaller;
    }

    @Bean
    public WsForNodeServicesClient wsForNodeServicesClient(Jaxb2Marshaller marshaller) {
        WsForNodeServicesClient wsForNodeServicesClient = new WsForNodeServicesClient();
        wsForNodeServicesClient.setDefaultUri(epr);
        wsForNodeServicesClient.setMarshaller(marshaller);
        wsForNodeServicesClient.setUnmarshaller(marshaller);
        return wsForNodeServicesClient;
    }
}