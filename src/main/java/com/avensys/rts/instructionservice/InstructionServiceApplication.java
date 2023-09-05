package com.avensys.rts.instructionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InstructionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstructionServiceApplication.class, args);
	}

}
