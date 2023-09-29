package org.orion.avaya.servicetwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ServicetwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicetwoApplication.class, args);
    }

}
