package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import java.util.List;

public interface EmployeeService {

	EmployeeResponseDTO addEmployee(EmployeeRequestDTO request);

	List<EmployeeResponseDTO> getAllEmployees();

	EmployeeResponseDTO getEmployeeById(final int Id);

	boolean updateEmployee(final EmployeeRequestDTO employeeRequestDTO, final int id);

	void disableEmployeeById(Integer id);
}
