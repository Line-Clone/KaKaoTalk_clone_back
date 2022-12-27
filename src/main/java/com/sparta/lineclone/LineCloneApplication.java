package com.sparta.lineclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LineCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(LineCloneApplication.class, args);
    }

}
