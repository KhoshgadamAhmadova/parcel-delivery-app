package com.guava.courierms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CourierMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourierMsApplication.class, args);
    }

}
