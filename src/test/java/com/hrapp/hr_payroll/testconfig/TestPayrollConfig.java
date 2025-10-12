package com.hrapp.hr_payroll.testconfig;

import com.hrapp.hr_payroll.service.PayrollService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestPayrollConfig {
    @Bean
    @Primary
    public PayrollService payrollService() {
        return mock(PayrollService.class);
    }
}