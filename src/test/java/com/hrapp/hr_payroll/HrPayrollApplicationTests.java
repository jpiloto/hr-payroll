package com.hrapp.hr_payroll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.cloud.stream.default.producer.enabled=false",
		"spring.cloud.stream.default.consumer.enabled=false",
		"spring.cloud.function.scan.enabled=false"
})
class HrPayrollApplicationTests {

	@Test
	void contextLoads() {
	}

}
