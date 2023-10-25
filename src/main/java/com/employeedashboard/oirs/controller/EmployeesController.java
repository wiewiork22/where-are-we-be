package com.employeedashboard.oirs.controller;

import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import com.employeedashboard.oirs.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService service;

    @GetMapping("")
    public List<EmployeeResponseDTO> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getById(@PathVariable("id") int id) {
        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public boolean updateEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO, @PathVariable("id") int id) {
        return service.updateEmployee(employeeRequestDTO, id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        return service.addEmployee(employeeRequestDTO);
    }
}
