package com.itheima.reggie_takeout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author purpoas
 */
@Slf4j
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
public class ReggieApplication {
    public static void main(String[] args) {
        log.info("项目启动成功");
        SpringApplication.run(ReggieApplication.class, args);
    }

}
