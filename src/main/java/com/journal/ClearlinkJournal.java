package com.journal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
public class ClearlinkJournal extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(ClearlinkJournal.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClearlinkJournal.class, args);
    }
}
