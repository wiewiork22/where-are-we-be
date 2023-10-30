package com.employeedashboard.oirs.mapper;

import com.employeedashboard.oirs.domain.Employee;
import com.employeedashboard.oirs.dto.EmployeeDTO;
import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public Employee mapToEmployee(final EmployeeRequestDTO employeeRequestDTO) {
        return Employee
                .builder()
                .name(employeeRequestDTO.name())
                .department(employeeRequestDTO.department())
                .position(employeeRequestDTO.position())
                .squad(employeeRequestDTO.squad())
                .build();
    }

    public EmployeeDTO mapToEmployeesDTO(final Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getSquad(),
                employee.getDepartment(),
                employee.getAddressId()
        );
    }

    public EmployeeResponseDTO mapToEmployeeResponseDTO(final Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getSquad(),
                employee.getDepartment(),
                employee.getAddressId()
        );
    }

    public List<EmployeeResponseDTO> mapToEmployeeResponseDTOList(final List<Employee> employeeList) {

        return employeeList.stream()
                .map(this::mapToEmployeeResponseDTO)
                .toList();

    }
}

