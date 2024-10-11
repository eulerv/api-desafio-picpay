package com.github.eulerv.picpaydesafiobackend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableScheduling
@EnableJdbcAuditing
@SpringBootApplication
public class PicpayDesafioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicpayDesafioBackendApplication.class, args);
	}
	@Bean
	ApplicationRunner runner(PasswordEncoder passwordEncoder) {
		return args -> System.out.println(">>>>>>>>Application is running!<<<<<<<<");
		//return args -> System.out.println(passwordEncoder.encode("123456"));
	}
}