package com.symulakr.sqs.spring.cloud.config.client.web;

import com.symulakr.sqs.spring.cloud.config.client.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/example")
public class ExampleController {

    @Autowired
    private Properties properties;

    @GetMapping
    public String value() {
        StringBuilder sb = new StringBuilder();
        properties.getValue().forEach((key, value) -> sb.append(key).append(" -> ").append(value).append('\n'));
        return sb.toString();
    }

}
