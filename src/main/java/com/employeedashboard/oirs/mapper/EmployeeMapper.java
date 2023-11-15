package com.employeedashboard.oirs.mapper;

import com.employeedashboard.oirs.domain.Employee;
import com.employeedashboard.oirs.dto.AddressDTO;
import com.employeedashboard.oirs.dto.EmployeeDTO;
import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
	public Employee mapToEmployee(final EmployeeRequestDTO employeeRequestDTO) {
		return Employee.builder().name(employeeRequestDTO.fullName()).department(employeeRequestDTO.department())
				.position(employeeRequestDTO.position()).squad(employeeRequestDTO.squad()).build();
	}

	public EmployeeDTO mapToEmployeesDTO(final Employee employee) {
		return new EmployeeDTO(employee.getId(), employee.getName(), employee.getEmail(), employee.getPosition(),
				employee.getSquad(), employee.getDepartment(), employee.getAddressId());
	}

	public EmployeeResponseDTO mapToEmployeeResponseDTO(final Employee employee, AddressDTO address) {
		return new EmployeeResponseDTO(employee.getId(), employee.getName(), employee.getPosition(),
				employee.getSquad(), employee.getDepartment(), address);
	}

}
