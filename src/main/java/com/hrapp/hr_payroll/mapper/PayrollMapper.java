package com.hrapp.hr_payroll.mapper;

import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.model.PayrollRecord;

public class PayrollMapper {

    public static PayrollRecordDTO toDTO(PayrollRecord entity) {
        return PayrollRecordDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .name(entity.getName())
                .email(entity.getEmail())
                .jobTitle(entity.getJobTitle())
                .phoneNumber(entity.getPhoneNumber())
                .hireDate(entity.getHireDate())
                .departmentId(entity.getDepartmentId())
                .roleIds(entity.getRoleIds())
                .jobPositionTitle(entity.getJobPositionTitle())
                .jobPositionLevel(entity.getJobPositionLevel())
                .salary(entity.getSalary())
                .payDate(entity.getPayDate())
                .build();
    }

    public static PayrollRecord toEntity(PayrollRecordDTO dto) {
        return PayrollRecord.builder()
                .employeeId(dto.getEmployeeId())
                .name(dto.getName())
                .email(dto.getEmail())
                .jobTitle(dto.getJobTitle())
                .phoneNumber(dto.getPhoneNumber())
                .hireDate(dto.getHireDate())
                .departmentId(dto.getDepartmentId())
                .roleIds(dto.getRoleIds())
                .jobPositionTitle(dto.getJobPositionTitle())
                .jobPositionLevel(dto.getJobPositionLevel())
                .payDate(dto.getPayDate())
                .build();
    }
}