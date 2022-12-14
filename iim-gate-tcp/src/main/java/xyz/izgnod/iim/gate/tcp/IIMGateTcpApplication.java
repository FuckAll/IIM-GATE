package xyz.izgnod.iim.gate.tcp;

//import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EnableDubbo
//@EnableConfigurationProperties
@ComponentScan(value = "xyz.izgnod.iim.gate")
public class IIMGateTcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(IIMGateTcpApplication.class, args);
    }
}

