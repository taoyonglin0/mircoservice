package com.microservice.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Configuration
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public class MyPropertySourceLocator implements PropertySourceLocator {

        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String,Object> locatePropertyConfig = new HashMap<>();
            locatePropertyConfig.put("server.port","9091");
            return new MapPropertySource("my-property-source", locatePropertyConfig);
        }
    }
}
