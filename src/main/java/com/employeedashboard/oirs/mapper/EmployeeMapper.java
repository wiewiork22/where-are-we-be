package com.employeedashboard.oirs.mapper;

import com.employeedashboard.oirs.domain.Employees;
import com.employeedashboard.oirs.dto.EmployeesDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {
    public Employees mapToEmployee(final EmployeesDTO employeesDTO) {
        return new Employees(
                employeesDTO.id(),
                employeesDTO.name(),
                employeesDTO.position(),
                employeesDTO.squad(),
                employeesDTO.department(),
                employeesDTO.address()
        );
    }

    public EmployeesDTO mapToEmployeesDTO(final Employees employees) {
        return new EmployeesDTO(
                employees.getId(),
                employees.getName(),
                employees.getPosition(),
                employees.getSquad(),
                employees.getDepartment(),
                employees.getAddress()
        );
    }

    public List<EmployeesDTO> mapToEmployeesList(final List<Employees> employeesList) {
        return employeesList.stream()
                .map(this::mapToEmployeesDTO)
                .toList();
    }
}

