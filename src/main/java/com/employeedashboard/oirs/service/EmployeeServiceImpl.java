package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.domain.Address;
import com.employeedashboard.oirs.domain.Employee;
import com.employeedashboard.oirs.domain.Role;
import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import com.employeedashboard.oirs.mapper.EmployeeMapper;
import com.employeedashboard.oirs.repository.AddressRepository;
import com.employeedashboard.oirs.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;


    @Transactional
    public EmployeeResponseDTO addEmployee(EmployeeRequestDTO request) {

        if (employeeRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException(String.format("User with email: %s already exists.", request.email()));
        }

        var address = Address.builder()
                .street(request.street())
                .city(request.city())
                .state(request.state())
                .postCode(request.postCode())
                .country(request.country())
                .build();

        addressRepository.save(address);
        var employee = Employee.builder()
                .name(request.name())
                .email(request.email())
                .position(request.position())
                .squad(request.squad())
                .department(request.department())
                .addressId(address.getId())
                .password(passwordEncoder.encode("pass"))
                .role(Role.USER)
                .build();
        employeeRepository.add(employee);
        return mapper.mapToEmployeeResponseDTO(employee);
    }

    public List<EmployeeResponseDTO> getAllEmployees() {
        return mapper.mapToEmployeeResponseDTOList(employeeRepository.getAll());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(int id) {
        return mapper.mapToEmployeeResponseDTO(
                employeeRepository.getById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", id)))
        );
    }


    public boolean updateEmployee(final EmployeeRequestDTO employeeRequestDTO, final int id) {

        Address address = Address.builder()
                .id(employeeRepository.getAddresId(id))
                .street(employeeRequestDTO.street())
                .city(employeeRequestDTO.city())
                .state(employeeRequestDTO.state())
                .postCode(employeeRequestDTO.postCode())
                .country(employeeRequestDTO.country())
                .build();

        Employee employee = Employee.builder()
                .id(id)
                .name(employeeRequestDTO.name())
                .position(employeeRequestDTO.position())
                .squad(employeeRequestDTO.squad())
                .department(employeeRequestDTO.department())
                .build();
        return addressRepository.updateAddress(address) && employeeRepository.updateEmployee(employee);
    }
}
