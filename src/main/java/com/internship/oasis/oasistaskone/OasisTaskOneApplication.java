package com.internship.oasis.oasistaskone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OasisTaskOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasisTaskOneApplication.class, args);
    }

}
