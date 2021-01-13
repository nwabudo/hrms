package io.neoOkpara.ws.hr.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.neoOkpara.ws.hr.entiy.user.Employee;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private Employee employee;

	public UserPrincipal(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.employee.getRole().getGrantedAuthorities();
	}

	@Override
	public String getPassword() {
		return this.employee.getPassword();
	}

	@Override
	public String getUsername() {
		return this.employee.getUserName();
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
		return true;
	}

}
