package com.symulakr.sqs.simplecontroller.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class StreamController {

    private Stream<String> generate(long limit) {
        AtomicInteger integer = new AtomicInteger(0);
        return Stream.generate(integer::incrementAndGet)
                .map(i -> String.format("%s - %s", i, UUID.randomUUID()))
                .limit(limit);
    }

    @RequestMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/stream")
    public StreamingResponseBody handleRequest(@RequestParam(defaultValue = "100000") int limit) {
        return out -> {
            SomeConsumer consumer = new SomeConsumer<String>(out);
            generate(limit).forEach(consumer);
        };
    }

    @RequestMapping(produces = MediaType.TEXT_PLAIN_VALUE, value = "/batch")
    public ResponseEntity<String> handleRequest2(@RequestParam(defaultValue = "100000") int limit) {
        return ResponseEntity.ok(generate(limit).collect(Collectors.joining("\n")));
    }

    public static class SomeConsumer<T> implements Consumer<T> {

        private OutputStream outputStream;

        public SomeConsumer(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        @Override
        public void accept(T t) {
            try {
                outputStream.write((String.format("%s\n", t)).getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
