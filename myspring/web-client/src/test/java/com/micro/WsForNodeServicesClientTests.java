package com.micro;

import com.micro.client.WsForAttrServicesClient;
import com.micro.client.WsForNodeServicesClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes={HelloService.class})
//@TestPropertySource("/appTest.properties")
public class WsForNodeServicesClientTests {
    @Value("${spring.application.marshallerNodeContextPath}")
    private String marshallerNodeContextPath;
    @Autowired
    private WsForNodeServicesClient wsForNodeServicesClient;
    @Test
   public void wsForNodeServicesClientTests() {
        System.out.println(marshallerNodeContextPath);
        wsForNodeServicesClient.getUserInfo("aa");
    }


}