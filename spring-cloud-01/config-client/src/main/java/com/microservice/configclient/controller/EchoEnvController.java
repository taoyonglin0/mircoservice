package com.microservice.configclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author taoyonglin
 * @version 1.0.0
 * @ClassName EchoEnvController.java
 * @Description TODO 小陶写点注释吧
 * @createTime 2020年01月23日 17:30:00
 */
@RestController
public class EchoEnvController {

    /**
     * 注意此处的环境变量类型为：org.springframework.core.env.Environment
     */
    private Environment environment;

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private EchoEnvController(Environment environment) {
        this.environment = environment;
    }

    /**
     * http://localhost:8080/env/server.port/
     * @param property
     * @return
     */
    @GetMapping("/env/{property}")
    public Object getEnvProperty(@PathVariable("property") String property) {
        HashMap resultMap = new HashMap();
        resultMap.put(property,environment.getProperty(property));
        return resultMap;
    }
}
