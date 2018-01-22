package com.symulakr.sqs.spring.cloud.config.client.config;

import com.symulakr.sqs.spring.cloud.config.client.properties.Properties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfiguration {

    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "example.properties")
    public Properties properties() {
        return new Properties();
    }

}
