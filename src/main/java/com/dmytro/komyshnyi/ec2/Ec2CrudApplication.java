package com.dmytro.komyshnyi.ec2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Ec2CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ec2CrudApplication.class, args);
    }
}
