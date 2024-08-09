package com.Zpher.reggie;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * ClassName: ReggieApplication
 * Package: com.Zpher.reggie
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/6 19:48
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info("项目启动成功");
    }
}
