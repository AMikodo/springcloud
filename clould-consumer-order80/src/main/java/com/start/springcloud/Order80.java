package com.start.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.start.springcloud.dao")
public class Order80 {
    public static void main(String[] args) {
        SpringApplication.run(Order80.class);
    }
}
