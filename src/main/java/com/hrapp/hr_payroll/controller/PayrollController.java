package com.hrapp.hr_payroll.controller;

import com.hrapp.hr_payroll.dto.PayrollRecordDTO;
import com.hrapp.hr_payroll.service.PayrollService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {
    private final PayrollService payrollService;

    @Operation(summary = "Create a payroll record")
    @ApiResponse(responseCode = "201", description = "Payroll record created")
    @PostMapping
    public ResponseEntity<PayrollRecordDTO> createPayroll(@RequestBody PayrollRecordDTO dto) {
        PayrollRecordDTO saved = payrollService.save(dto);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Get payroll records by employee ID")
    @ApiResponse(responseCode = "200", description = "Payroll records retrieved")
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollRecordDTO>> getByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getByEmployeeId(employeeId));
    }

    @Operation(summary = "Get all payroll records")
    @ApiResponse(responseCode = "200", description = "All payroll records retrieved")
    @GetMapping
    public ResponseEntity<List<PayrollRecordDTO>> getAll() {
        return ResponseEntity.ok(payrollService.getAll());
    }

}
