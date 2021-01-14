package io.neoOkpara.ws.hr.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import io.neoOkpara.ws.hr.entiy.user.BenefitPaymentHis;
import io.neoOkpara.ws.hr.entiy.user.Benefits;
import io.neoOkpara.ws.hr.entiy.user.Employee;

public interface UserService {
	
	@PreAuthorize(value = "@userSecurity.useHeirachy(#empId, authentication)")
	Employee getEmployeeById(@Param("empId") String empId);
	
	@PreAuthorize(value = "hasAnyAuthority('admin:read') or @userSecurity.useHeirachy(#managerId, authentication)")
	Set<Employee> getEmployeeByManagerId(@Param("managerId") String managerId);
	
	Set<Employee> getAllEmployees() throws Exception;

	@PreAuthorize(value = "hasAnyAuthority('admin:read') or @userSecurity.useHeirachy(#empId, authentication)")
	Employee maintainBenefit(@Param("empId") String empId, Benefits benefits);
	
	@PreAuthorize(value = "hasAnyAuthority('admin:read') or @userSecurity.useHeirachy(#empId, authentication)")
	List<BenefitPaymentHis> fetchSalaryHistory(@Param("empId") String empId);
}
