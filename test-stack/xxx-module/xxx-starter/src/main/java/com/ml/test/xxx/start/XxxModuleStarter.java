package com.ml.test.xxx.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by mal on 2019/5/23.
 */
@SpringBootApplication(scanBasePackages = "com.ml.test.xxx")
@MapperScan({"com.ml.test.xxx.mapper.simple", "com.ml.test.xxx.mapper.complex"})
@EnableTransactionManagement
@EnableEurekaClient
public class XxxModuleStarter {

    public static void main(String[] args) {
        SpringApplication.run(XxxModuleStarter.class, args);
    }

}
