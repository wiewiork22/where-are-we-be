package com.employeedashboard.oirs.domain;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private String position;
	private String squad;
	private String department;
	private Integer addressId;
	private Role role;
	private boolean isEnabled;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role != null) {
			return List.of(new SimpleGrantedAuthority(this.role.name()));
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
