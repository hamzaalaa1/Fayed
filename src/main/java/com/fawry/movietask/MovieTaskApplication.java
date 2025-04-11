package com.fawry.movietask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MovieTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieTaskApplication.class, args);
    }

}
