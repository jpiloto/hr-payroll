package com.hrapp.hr_payroll;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@Slf4j
@SpringBootApplication
@EnableKafka
public class HrPayrollApplication {

	public static void main(String[] args) {
		log.info("🔥 Startup checkpoint reached");
		SpringApplication.run(HrPayrollApplication.class, args);
	}

}
