package io.neoOkpara.ws.hr.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.neoOkpara.ws.hr.entiy.user.BenefitPaymentHis;
import io.neoOkpara.ws.hr.entiy.user.Benefits;
import io.neoOkpara.ws.hr.entiy.user.ERole;
import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.exception.UserServiceException;
import io.neoOkpara.ws.hr.repository.benefit.BenefitRepository;
import io.neoOkpara.ws.hr.repository.user.UserRepository;
import io.neoOkpara.ws.hr.security.UserPrincipal;
import io.neoOkpara.ws.hr.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private BenefitRepository benefitRepository;

	public UserServiceImpl(UserRepository userRepository, BenefitRepository benefitRepository) {
		this.userRepository = userRepository;
		this.benefitRepository = benefitRepository;
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
				if (employee.get().getRoles().getName().equals(ERole.ROLE_HR)) {
					return this.userRepository.findAll().stream()
							.filter(emp -> !emp.getRoles().getName().equals(ERole.ROLE_HRMANAGER))
							.filter(emp -> !emp.getRoles().getName().equals(ERole.ROLE_HR) && emp != employee.get())
							.collect(Collectors.toSet());
				} else return new HashSet<>(this.userRepository.findAll());
			}
		} catch (Exception ex) {
			throw new UserServiceException(ex.getMessage());
		}
		return Collections.emptySet();
	}

	@Override
	public Employee maintainBenefit(String empId, Benefits benefits) {
		Employee emp = this.userRepository.findByEmpId(empId)
				.orElseThrow(() -> new UserServiceException("No user found for the Id: " + empId));
		emp.setAnnualBonus(benefits.getAnnualBonus());
		emp.setSalaryAmount(benefits.getSalaryAmount());
		emp.setVacationAmount(benefits.getVacationAmount());
		return this.userRepository.save(emp);
	}
	
	@Override
	public List<BenefitPaymentHis> fetchSalaryHistory(String empId) {
		Employee emp = this.userRepository.findByEmpId(empId)
		.orElseThrow(() -> new UserServiceException("No user found for the Id: " + empId));
		return this.benefitRepository.findAllByEmployee(emp);
	}
	
}
