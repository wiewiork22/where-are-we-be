package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.dto.EmployeesDTO;
import com.employeedashboard.oirs.mapper.EmployeeMapper;
import com.employeedashboard.oirs.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public List<EmployeesDTO> getAllEmployees() {
        return mapper.mapToEmployeesList(repository.getAll());
    }

    public EmployeesDTO getEmployee(final int Id) {
        return mapper.mapToEmployeesDTO(repository.getById(Id));
    }
}
