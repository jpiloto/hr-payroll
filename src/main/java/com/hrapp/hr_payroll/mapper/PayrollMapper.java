package com.hrapp.hr_payroll.mapper;

import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.model.PayrollRecord;

public class PayrollMapper {

    public static PayrollRecordDTO toDTO(PayrollRecord entity) {
        return PayrollRecordDTO.builder()
                .id(entity.getId())
                .employeeId(entity.getEmployeeId())
                .salary(entity.getSalary())
                .payDate(entity.getPayDate())
                .build();
    }

    public static PayrollRecord toEntity(PayrollRecordDTO dto) {
        return PayrollRecord.builder()
                .id(dto.getId())
                .employeeId(dto.getEmployeeId())
                .salary(dto.getSalary())
                .payDate(dto.getPayDate())
                .build();
    }
}