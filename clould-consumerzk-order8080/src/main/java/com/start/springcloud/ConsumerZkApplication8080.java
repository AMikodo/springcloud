package com.start.springcloud;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.start.springcloud.*")
@MapperScan(basePackages = "com.start.springcloud.dao")
@EnableDiscoveryClient
public class ConsumerZkApplication8080 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerZkApplication8080.class, args);
    }
}
