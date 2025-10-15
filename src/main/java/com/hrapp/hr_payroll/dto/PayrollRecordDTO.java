package com.hrapp.hr_payroll.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollRecordDTO {
    private Long id;
    private Long employeeId;
    private String name;
    private String email;
    private String jobTitle;
    private String phoneNumber;
    private LocalDate hireDate;
    private Long departmentId;
    private Set<Long> roleIds;
    private String jobPositionTitle;
    private BigDecimal salary;
    private LocalDate payDate;
}