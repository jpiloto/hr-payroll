package com.hrapp.hr_payroll.service;

import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.mapper.PayrollMapper;
import com.hrapp.hr_payroll.model.PayrollRecord;
import com.hrapp.hr_payroll.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepository;

//    public PayrollRecord save(PayrollRecord record) {
//        return payrollRepository.save(record);
//    }

    public PayrollRecordDTO save(PayrollRecordDTO dto) {
        PayrollRecord entity = PayrollMapper.toEntity(dto);
        return PayrollMapper.toDTO(payrollRepository.save(entity));
    }


//    public List<PayrollRecord> getByEmployeeId(Long employeeId) {
//        return payrollRepository.findByEmployeeId(employeeId);
//    }

    public List<PayrollRecordDTO> getByEmployeeId(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId)
                .stream()
                .map(PayrollMapper::toDTO)
                .toList();
    }

//    public List<PayrollRecord> getAll() {
//        return payrollRepository.findAll();
//    }

    public List<PayrollRecordDTO> getAll() {
        return payrollRepository.findAll()
                .stream()
                .map(PayrollMapper::toDTO)
                .toList();
    }

}