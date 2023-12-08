package org.example.REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EntityScan("org.example.REST")
@EnableJpaRepositories(basePackages = "org.example.REST")
public class REST {
    public static void main(String[] args) {
        ArrayList<Float> arrayPagamentos = new ArrayList<>();
        SpringApplication.run(REST.class, args);
        System.out.println("Executing");
    }
}



