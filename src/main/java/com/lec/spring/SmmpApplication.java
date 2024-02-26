package com.lec.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmmpApplication.class, args);
    }

}
