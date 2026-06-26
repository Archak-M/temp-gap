package org.zeroapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZeroapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeroapiApplication.class, args);
    }
}
