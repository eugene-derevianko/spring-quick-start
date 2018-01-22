package com.symulakr.sqs.spring.cloud.config.client.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
public class Properties {

    private HashMap<Environment, Value> value;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Value {

        private String first;
        private String second;
        private String third;

    }

}
