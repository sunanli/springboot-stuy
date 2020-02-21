package com.tangwh.springbootactuatorservcer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootActuatorServcerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActuatorServcerApplication.class, args);
    }

}
