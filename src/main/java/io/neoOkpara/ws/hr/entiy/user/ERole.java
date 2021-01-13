package io.neoOkpara.ws.hr.entiy.user;

import static io.neoOkpara.ws.hr.entiy.user.ApplicationUserPermission.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum ERole {
	ROLE_USER(Sets.newHashSet()), 
	ROLE_MANAGER(Sets.newHashSet(USER_READ, USER_WRITE)),
	ROLE_HR(Sets.newHashSet(USER_READ, USER_WRITE, HR_READ)),
	ROLE_HRMANAGER(Sets.newHashSet(USER_READ, USER_WRITE, HR_READ, HR_WRITE)), 
	ROLE_ADMIN(Sets.newHashSet());

	private final Set<ApplicationUserPermission> permissions;

	ERole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());

		permissions.add(new SimpleGrantedAuthority(this.name()));
		return permissions;
	}
}
