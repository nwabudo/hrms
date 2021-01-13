package io.neoOkpara.ws.hr.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.neoOkpara.ws.hr.entiy.user.ERole;
import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.entiy.user.Role;
import io.neoOkpara.ws.hr.exception.UserServiceException;
import io.neoOkpara.ws.hr.repository.user.UserRepository;
import io.neoOkpara.ws.hr.security.UserPrincipal;
import io.neoOkpara.ws.hr.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public Employee getEmployeeById(String empId) {
		log.info("Method getEmployeeById({}) reached", empId);
		return this.userRepository.findByEmpId(empId)
				.orElseThrow(() -> new UserServiceException("No user found for the Id: " + empId));
	}

	@Override
	public Set<Employee> getEmployeeByManagerId(String managerId) {
		log.info("Method getEmployeeByManagerId({}) reached", managerId);
		return this.userRepository.findByManagerId(managerId);
	}

	@Override
	public Set<Employee> getAllEmployees() throws Exception {
		log.info("Method getAllEmployees() reached");
		try {
			Optional<Employee> employee = ((UserPrincipal) (SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal())).getUser();

			if (employee.isPresent()) {
				Collection<Role> roles = employee.get().getRoles();
				if (roles.contains(new Role(ERole.ROLE_HR))) {
					return this.userRepository.findAll().stream()
						.filter(emp -> !emp.getRoles().contains(new Role(ERole.ROLE_HRMANAGER)))
						.filter(emp -> !emp.getRoles().contains(new Role(ERole.ROLE_HR)) && emp != employee.get())
						.collect(Collectors.toSet());
				} else {
					return new HashSet<>(this.userRepository.findAll());
				}
			}
		}catch(Exception ex) {
			throw new UserServiceException(ex.getMessage());
		}
		return Collections.emptySet();
	}

}
