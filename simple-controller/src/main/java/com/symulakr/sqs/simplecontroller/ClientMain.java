package com.symulakr.sqs.simplecontroller;

import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Stream;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/map");
        URLConnection connection = url.openConnection();
        try (ObjectInputStream inputStream = new ObjectInputStream(connection.getInputStream())) {
            Object o = inputStream.readObject();
            Stream<Integer> stream = (Stream<Integer>) o;
            stream.forEach(System.out::println);
        }
    }
}
