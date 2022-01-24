package com.test.testpro;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TestProApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestProApplication.class, args);
    }
        @Bean
    PrettyTime prettyTime(){

            return new PrettyTime(); /// but how we get this bean instance into our entity ==> By using services
        }


        }
