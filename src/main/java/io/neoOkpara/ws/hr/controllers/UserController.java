package io.neoOkpara.ws.hr.controllers;

import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.service.UserService;

@RestController
@RequestMapping("api/v1/employees")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "")
	@PreAuthorize(value = "hasAnyAuthority('hr:read')")
	public Set<Employee> getAllEmployees() throws Exception {
		return this.userService.getAllEmployees();
	}

	@GetMapping(path = "/{empId}")
	public Employee getEmployee(@PathVariable("empId") String studentId) {
		return this.userService.getEmployeeById(studentId);
	}

	@GetMapping(path = "/manager/{managerId}")
	//Prevent other HR from calling this endpont for HR
	public Set<Employee> getEmployeeListByManagerId(@PathVariable("managerId") String managerId) {
		return this.userService.getEmployeeByManagerId(managerId);
	}
}
