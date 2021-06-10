package com.projectssm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@MapperScan("com.projectssm.mapper")
@SpringBootApplication
public class WebApplication {
    private static Logger logger = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication.run(WebApplication.class, args);

        String port ="8080";
        String externalAPI = InetAddress.getLocalHost().getHostAddress();
        logger.info("\n===============================================================\n\t" +
                "login-page:http://{}:{}\n" +
                "===============================================================\n\t",externalAPI,port);
    }

}
