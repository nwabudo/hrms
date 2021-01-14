package io.neoOkpara.ws.hr.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(this.employee.getRoles().getName().getRole()));
		authorities.addAll(
			this.employee.getRoles().getPrivileges()
			.stream()
			.map(privilege -> privilege.getName().getPermission())
			.map(SimpleGrantedAuthority::new)
	        .collect(Collectors.toSet()));
			//.forEach(privilege -> {
			//authorities.add(new SimpleGrantedAuthority(privilege.getName().getPermission()));
			//});
		return authorities;
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

	public Optional<Employee> getUser() {
		return Optional.of(this.employee);
	}

}
