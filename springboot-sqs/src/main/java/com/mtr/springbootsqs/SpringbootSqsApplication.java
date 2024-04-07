package com.mtr.springbootsqs;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mtr.springbootsqs.service.Publisher;

@SpringBootApplication
public class SpringbootSqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSqsApplication.class, args);
	}

	@Bean
    public ApplicationRunner runner(Publisher publisher) {
        return args -> {
            Thread.sleep(3000);
            for (int i = 0; i < 10; i++) {
                publisher.publishMessage(String.valueOf(i));
            }
        };
    }
}
