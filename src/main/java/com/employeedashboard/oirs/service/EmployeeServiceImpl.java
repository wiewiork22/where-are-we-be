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
                .isEnabled(true)
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
                .id(employeeRepository.getAddressId(id))
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
                .isEnabled(true)
                .build();
        return addressRepository.updateAddress(address) && employeeRepository.updateEmployee(employee);
    }

    public void addAdminIfAbsent() {
        if (employeeRepository.findByEmail("admin@admin.com").isEmpty()) {

            var address = Address.builder()
                    .street(Role.ADMIN.name())
                    .city(Role.ADMIN.name())
                    .state(Role.ADMIN.name())
                    .postCode(Role.ADMIN.name())
                    .country(Role.ADMIN.name())
                    .build();

            addressRepository.save(address);
            var employee = Employee.builder()
                    .name(Role.ADMIN.name())
                    .email("admin@admin.com")
                    .position(Role.ADMIN.name())
                    .squad(Role.ADMIN.name())
                    .department(Role.ADMIN.name())
                    .addressId(address.getId())
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .isEnabled(true)
                    .build();
            employeeRepository.add(employee);
        }
    }


    public void addEmployeeIfAbsent() {
        if (employeeRepository.findByEmail("employee@employee.com").isEmpty()) {

            var address = Address.builder()
                    .street(Role.USER.name())
                    .city(Role.USER.name())
                    .state(Role.USER.name())
                    .postCode(Role.USER.name())
                    .country(Role.USER.name())
                    .build();

            addressRepository.save(address);
            var employee = Employee.builder()
                    .name(Role.USER.name())
                    .email("employee@employee.com")
                    .position(Role.USER.name())
                    .squad(Role.USER.name())
                    .department(Role.USER.name())
                    .addressId(address.getId())
                    .password(passwordEncoder.encode("employee"))
                    .role(Role.USER)
                    .isEnabled(true)
                    .build();
            employeeRepository.add(employee);
        }
    }

    public void addDefaultUsers() {
        addAdminIfAbsent();
        addEmployeeIfAbsent();
    }

    @Override
    @Transactional
    public void disableEmployeeById(final Integer id) {
        Employee employee = employeeRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", id)));

        Integer addressId = employee.getAddressId();

        employee.setDepartment("NA");
        employee.setPosition("NA");
        employee.setSquad("NA");
        employee.setEnabled(false);
        employee.setAddressId(null);

        employeeRepository.updateEmployee(employee);
        addressRepository.deleteById(addressId);
    }
}
