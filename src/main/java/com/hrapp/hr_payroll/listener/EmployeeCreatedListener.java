package com.hrapp.hr_payroll.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.service.PayrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmployeeCreatedListener {

    private final PayrollService payrollService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "employee.created", groupId = "hr-payroll-group-dto")
    public void handleEmployeeCreated(String message) {
        try {
            PayrollRecordDTO dto = objectMapper.readValue(message, PayrollRecordDTO.class);
            payrollService.save(dto);
            log.info("Payroll record created for employeeId: {}", dto.getEmployeeId());
        } catch (Exception e) {
            log.error("Failed to process employee.created event: {}", message, e);
        }
    }

    @KafkaListener(topics = "employee.created", groupId = "hr-payroll-group-raw")
    public void listenRaw(String message) {
        log.debug("Raw employee.created event received: {}", message);
    }

}