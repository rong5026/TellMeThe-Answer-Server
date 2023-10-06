package com.capstone.answer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//Auditing을 사용하면 꼭 설정해줘야함 -> basetimeEntity
@SpringBootApplication
public class AnswerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnswerApplication.class, args);
	}

}
