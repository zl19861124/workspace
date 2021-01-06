package com.micro.webpage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//@MapperScan("com.micro.webpage.mapper")
public class WebPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebPageApplication.class, args);
    }
}
