package com.hrapp.hr_payroll.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollRecordDTO {
    private Long id;
    private Long employeeId;
    private BigDecimal salary;
    private LocalDate payDate;
}