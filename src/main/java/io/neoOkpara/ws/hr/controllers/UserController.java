package io.neoOkpara.ws.hr.controllers;

import java.util.Set;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.service.impl.UserServiceImpl;

@RestController
@RequestMapping("api/v1/employees")
public class UserController {
	
	private UserServiceImpl userServiceImpl;
	
	public UserController(UserServiceImpl userServiceIpl) {
		this.userServiceImpl = userServiceIpl;
	}
	
	@GetMapping(path = "")
	@PreAuthorize(value = "hasAnyAuthority('hr:read')")
	public Set<Employee> getAllEmployees() {
		return this.userServiceImpl.getAllEmployees();
	}

	@PostAuthorize(value = "@userSecurity.isMemberofHR(returnObject, #empId)")
	@GetMapping(path = "{empId}")
	@PreAuthorize(value = "hasAnyAuthority('user:read') or @userSecurity.hasUserId(authentication, #empId)")
	public Employee getEmployee(@PathVariable("empId") String studentId) {
		return this.userServiceImpl.getEmployeeById(studentId);
	}

	@GetMapping(path = "/manager/{managerId}")
	//Prevent other HR from calling this endpont for HR
	@PreAuthorize(value = "hasAnyAuthority('hr:read') or @userSecurity.hasUserId(authentication, #managerId)")
	public Set<Employee> getEmployeeListByManagerId(@PathVariable("managerId") String managerId) {
		return this.userServiceImpl.getEmployeeByManagerId(managerId);
	}
}
