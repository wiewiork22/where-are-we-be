package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(final int Id);

    boolean updateEmployee(final EmployeeRequestDTO employeeRequestDTO, final int id);

    EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO);
}
