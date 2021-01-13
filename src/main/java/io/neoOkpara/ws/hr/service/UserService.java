package io.neoOkpara.ws.hr.service;

import java.util.Set;

import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import io.neoOkpara.ws.hr.entiy.user.Employee;

public interface UserService {
	
	@PostAuthorize(value = "@userSecurity.isNotMemberofHR(returnObject, authentication, #empId)")
	@PreAuthorize(value = "hasAnyAuthority('user:read') or @userSecurity.hasUserId(authentication, #empId)")
	Employee getEmployeeById(@Param("empId") String empId);
	
	@PreAuthorize(value = "hasAnyAuthority('hr:read') or @userSecurity.hasUserId(authentication, #managerId)")
	Set<Employee> getEmployeeByManagerId(@Param("managerId") String managerId);
	
	//@PostAuthorize(value = "@userSecurity.limitByLevelofHR(returnObject, authentication)")
	Set<Employee> getAllEmployees() throws Exception;

}
