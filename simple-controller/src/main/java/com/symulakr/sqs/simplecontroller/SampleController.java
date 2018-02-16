package com.symulakr.sqs.simplecontroller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@EnableAutoConfiguration
public class SampleController {

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<String> callback(HttpServletRequest request,
                                           @RequestParam Map<String, Object> params) throws IOException {
        log.info("POST:");
        log.info("Headers: {}", getHeadersInfo(request));
        log.info("Params: {}", params);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<String> get(HttpServletRequest request,
                                      HttpServletResponse response, @RequestParam Map<String, String> params) throws IOException {
        String body = request.getReader().lines().collect(Collectors.joining());
        log.info("GET:");
        log.info("Request: {}", body);
        log.info("Params: {}", params);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}
