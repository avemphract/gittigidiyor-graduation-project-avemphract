package dev.patika.creditscoreserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class CreditScoreServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditScoreServerApplication.class, args);
    }

}
