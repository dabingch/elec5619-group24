package com.elec5619.hdhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class HdhelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(HdhelperApplication.class, args);
    }

}
