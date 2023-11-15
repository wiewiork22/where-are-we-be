package com.employeedashboard.oirs.auth;

import com.employeedashboard.oirs.authConfig.JwtService;
import com.employeedashboard.oirs.domain.Employee;
import com.employeedashboard.oirs.domain.Role;
import com.employeedashboard.oirs.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final EmployeeRepository employeeRepository;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		Employee user = getEmployeeByEmail(request.email());
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
		var jwtToken = jwtService.generateToken(user);
		return new AuthenticationResponse(jwtToken);
	}

	public boolean isUserOwnerOfId(UserDetails userDetails, int id) {
		Employee employee = getEmployeeByEmail(userDetails.getUsername());

		return employee.getId().equals(id);
	}

	private Employee getEmployeeByEmail(String email) {
		return employeeRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
	}

	public boolean isAdmin(UserDetails userDetails) {
		Employee employee = getEmployeeByEmail(userDetails.getUsername());

		return employee.getRole().equals(Role.ADMIN);
	}
}
