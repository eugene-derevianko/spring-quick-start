package com.symulakr.sqs.spring.cloud.config.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTest {

    @Test
    public void loadContext() {

    }

    @Test
    public void startApplication() {
        ClientApplication.main(new String[]{});
    }

}