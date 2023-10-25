package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.domain.Employee;
import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import com.employeedashboard.oirs.mapper.EmployeeMapper;
import com.employeedashboard.oirs.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public List<EmployeeResponseDTO> getAllEmployees() {
        return mapper.mapToEmployeeResponseDTOList(repository.getAll());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(int id) {
        return mapper.mapToEmployeeResponseDTO(
                repository.getById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", id)))
        );
    }

    public boolean updateEmployee(final EmployeeRequestDTO employeeRequestDTO, final int id) {

        Employee employee = new Employee(
                id,
                employeeRequestDTO.name(),
                employeeRequestDTO.position(),
                employeeRequestDTO.squad(),
                employeeRequestDTO.department(),
                employeeRequestDTO.address()
        );

        return repository.updateEmployee(employee);
    }

    @Transactional
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = mapper.mapToEmployee(employeeRequestDTO);
        repository.add(employee);
        return mapper.mapToEmployeeResponseDTO(employee);
    }

}
