package com.hrapp.hr_payroll.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "payroll_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayrollRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String name;
    private String email;
    private String jobTitle;
    private String phoneNumber;
    private LocalDate hireDate;
    private Long departmentId;

    @ElementCollection
    private Set<Long> roleIds;

    private String jobPositionTitle;
    private BigDecimal salary;
    private Integer jobPositionLevel;
    private LocalDate payDate;

}
