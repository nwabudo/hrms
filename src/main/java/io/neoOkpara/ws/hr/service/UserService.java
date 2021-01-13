package io.neoOkpara.ws.hr.service;

import java.util.Set;

import io.neoOkpara.ws.hr.entiy.user.Employee;

public interface UserService {
	
	Employee getEmployeeById(String empId);
	
	Set<Employee> getEmployeeByManagerId(String managerId);
	
	Set<Employee> getAllEmployees();

}
