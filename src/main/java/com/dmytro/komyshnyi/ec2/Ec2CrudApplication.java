package com.dmytro.komyshnyi.ec2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ec2CrudApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Ec2CrudApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world");
    }
}
