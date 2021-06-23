package com.zjz.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.zjz.code.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class GarbageSortingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarbageSortingApplication.class, args);
    }

}
