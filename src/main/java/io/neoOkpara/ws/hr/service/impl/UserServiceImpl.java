package io.neoOkpara.ws.hr.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.repository.user.UserRepository;
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
				.orElseThrow(() -> new IllegalArgumentException("No user found for the Id: " + empId));
	}

	@Override
	public Set<Employee> getEmployeeByManagerId(String managerId) {
		log.info("Method getEmployeeByManagerId({}) reached", managerId);
		return this.userRepository.findByManagerId(managerId);
	}

	@Override
	public Set<Employee> getAllEmployees() {
		log.info("Method getAllEmployees() reached");
		return new HashSet<>(this.userRepository.findAll());
	}

}
