package com.hrapp.hr_payroll.service;

import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.mapper.PayrollMapper;
import com.hrapp.hr_payroll.model.PayrollRecord;
import com.hrapp.hr_payroll.repository.PayrollRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollRepository payrollRepository;

//    public PayrollRecordDTO save(PayrollRecordDTO dto) {
//        PayrollRecord entity = PayrollMapper.toEntity(dto);
//        return PayrollMapper.toDTO(payrollRepository.save(entity));
//    }

    public PayrollRecordDTO save(PayrollRecordDTO dto) {
        PayrollRecord entity = PayrollMapper.toEntity(dto);

        BigDecimal base = baseSalaryForLevel(dto.getJobPositionLevel());
        BigDecimal bonus = seniorityBonus(dto.getHireDate());
        BigDecimal titleAdj = titleBonus(dto.getJobPositionTitle());

        BigDecimal totalSalary = base.add(bonus).add(titleAdj);
        entity.setSalary(totalSalary);
        entity.setPayDate(LocalDate.now());

        log.info("Payroll saved for {} with payDate {}", dto.getName(), entity.getPayDate());
        return PayrollMapper.toDTO(payrollRepository.save(entity));
    }


    public List<PayrollRecordDTO> getByEmployeeId(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId)
                .stream()
                .map(PayrollMapper::toDTO)
                .toList();
    }

    public List<PayrollRecordDTO> getAll() {
        return payrollRepository.findAll()
                .stream()
                .map(PayrollMapper::toDTO)
                .toList();
    }

    private BigDecimal baseSalaryForLevel(int level) {
        return switch (level) {
            case 1 -> BigDecimal.valueOf(50000);
            case 2 -> BigDecimal.valueOf(70000);
            case 3 -> BigDecimal.valueOf(90000);
            default -> BigDecimal.valueOf(40000);
        };
    }

    private BigDecimal seniorityBonus(LocalDate hireDate) {
        long years = ChronoUnit.YEARS.between(hireDate, LocalDate.now());
        return BigDecimal.valueOf(years * 1000);
    }

    private BigDecimal titleBonus(String title) {
        if (title == null) return BigDecimal.ZERO;
        return switch (title.toLowerCase()) {
            case "tech lead", "principal engineer" -> BigDecimal.valueOf(10000);
            case "senior engineer" -> BigDecimal.valueOf(5000);
            default -> BigDecimal.ZERO;
        };
    }


}