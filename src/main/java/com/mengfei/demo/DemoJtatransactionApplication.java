package com.mengfei.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//关闭自动配置
@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class
        }
)
//开启手动配置
@EnableTransactionManagement
public class DemoJtatransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoJtatransactionApplication.class, args);
    }

}
