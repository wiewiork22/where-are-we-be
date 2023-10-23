package com.employeedashboard.oirs.controller;

import com.employeedashboard.oirs.dto.EmployeesDTO;
import com.employeedashboard.oirs.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService service;

    @GetMapping("")
    public List<EmployeesDTO> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeesDTO getById(@PathVariable("id") Integer id) {
        return service.getEmployee(id);
    }
}
