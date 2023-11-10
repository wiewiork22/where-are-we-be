package com.employeedashboard.oirs.service;

import com.employeedashboard.oirs.domain.Address;
import com.employeedashboard.oirs.domain.Employee;
import com.employeedashboard.oirs.domain.Role;
import com.employeedashboard.oirs.dto.EmployeeRequestDTO;
import com.employeedashboard.oirs.dto.EmployeeResponseDTO;
import com.employeedashboard.oirs.mapper.AddressMapper;
import com.employeedashboard.oirs.mapper.EmployeeMapper;
import com.employeedashboard.oirs.repository.AddressRepository;
import com.employeedashboard.oirs.repository.EmployeeRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

	@Transactional
	public EmployeeResponseDTO addEmployee(EmployeeRequestDTO request) {

        employeeRepository.findByEmail(request.email())
                .ifPresent(employee -> {
                    throw new IllegalArgumentException(String.format("User with email: %s already exists.", employee.getEmail()));
                });

        var address = Address.builder()
                .street(request.address().street())
                .city(request.address().city())
                .state(request.address().state())
                .postCode(request.address().postCode())
                .country(request.address().country())
                .build();

        addressRepository.save(address);
        var employee = Employee.builder()
                .name(request.fullName())
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
        return employeeMapper.mapToEmployeeResponseDTO(employee, addressMapper.mapToAddressDTO(address));
    }

	public List<EmployeeResponseDTO> getAllEmployees() {
		return employeeRepository.getAll().stream()
				.map(employee -> employeeMapper.mapToEmployeeResponseDTO(employee,
						addressMapper.mapToAddressDTO(
								addressRepository.getById(employee.getAddressId()).orElse(new Address()))))
				.toList();

    }

    @Override
    public EmployeeResponseDTO getEmployeeById(int id) {
        var employee = employeeRepository.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id: %d not found.", id)));
        var address = addressRepository.getById(employee.getAddressId()).orElse(new Address());
        return employeeMapper.mapToEmployeeResponseDTO(employee, addressMapper.mapToAddressDTO(address));
    }

	public boolean updateEmployee(final EmployeeRequestDTO employeeRequestDTO, final int id) {

        Address address = Address.builder()
                .id(employeeRepository.getAddressId(id))
                .street(employeeRequestDTO.address().street())
                .city(employeeRequestDTO.address().city())
                .state(employeeRequestDTO.address().state())
                .postCode(employeeRequestDTO.address().postCode())
                .country(employeeRequestDTO.address().country())
                .build();

		Employee employee = Employee.builder()
				.id(id)
				.name(employeeRequestDTO.fullName())
				.position(employeeRequestDTO.position())
				.squad(employeeRequestDTO.squad())
				.department(employeeRequestDTO.department())
				.isEnabled(true)
				.addressId(address.getId())
				.build();
		return addressRepository.updateAddress(address) && employeeRepository.updateEmployee(employee);
	}

	public void addAdminIfAbsent() {
		if (employeeRepository.findByEmail("admin@admin.com").isEmpty()) {

			var address = Address.builder().street(Role.ADMIN.name()).city(Role.ADMIN.name()).state(Role.ADMIN.name())
					.postCode(Role.ADMIN.name()).country(Role.ADMIN.name()).build();

			addressRepository.save(address);
			var employee = Employee.builder().name(Role.ADMIN.name()).email("admin@admin.com")
					.position(Role.ADMIN.name()).squad(Role.ADMIN.name()).department(Role.ADMIN.name())
					.addressId(address.getId()).password(passwordEncoder.encode("admin")).role(Role.ADMIN)
					.isEnabled(true).build();
			employeeRepository.add(employee);
		}
	}

	public void addEmployeeIfAbsent() {
		if (employeeRepository.findByEmail("employee@employee.com").isEmpty()) {

			var address = Address.builder().street(Role.USER.name()).city(Role.USER.name()).state(Role.USER.name())
					.postCode(Role.USER.name()).country(Role.USER.name()).build();

			addressRepository.save(address);
			var employee = Employee.builder().name(Role.USER.name()).email("employee@employee.com")
					.position(Role.USER.name()).squad(Role.USER.name()).department(Role.USER.name())
					.addressId(address.getId()).password(passwordEncoder.encode("employee")).role(Role.USER)
					.isEnabled(true).build();
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
