package io.neoOkpara.ws.hr;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.neoOkpara.ws.hr.entiy.user.ApplicationUserPermission;
import io.neoOkpara.ws.hr.entiy.user.BenefitPaymentHis;
import io.neoOkpara.ws.hr.entiy.user.BenefitType;
import io.neoOkpara.ws.hr.entiy.user.Department;
import io.neoOkpara.ws.hr.entiy.user.ERole;
import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.entiy.user.Privilege;
import io.neoOkpara.ws.hr.entiy.user.Role;
import io.neoOkpara.ws.hr.exception.UserServiceException;
import io.neoOkpara.ws.hr.repository.benefit.BenefitRepository;
import io.neoOkpara.ws.hr.repository.user.PrivilegeRepository;
import io.neoOkpara.ws.hr.repository.user.RoleRepository;
import io.neoOkpara.ws.hr.repository.user.UserRepository;

@Component
public class SetupLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BenefitRepository benefitRepo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		Privilege userReadPriviledge = createPrivilegeIfNotFound(ApplicationUserPermission.USER_READ);
		Privilege userWritePrivilege = createPrivilegeIfNotFound(ApplicationUserPermission.USER_WRITE);
		Privilege hrRreadPrivilege = createPrivilegeIfNotFound(ApplicationUserPermission.HR_READ);
		Privilege hrWritePrivilege = createPrivilegeIfNotFound(ApplicationUserPermission.HR_WRITE);
		Privilege adminReadPrivilege = createPrivilegeIfNotFound(ApplicationUserPermission.ADMIN_READ);
		Privilege adminWritePrivilege = createPrivilegeIfNotFound(ApplicationUserPermission.ADMIN_WRITE);

		List<Privilege> adminPrivileges = Arrays.asList(userReadPriviledge, userWritePrivilege, hrRreadPrivilege,
				hrWritePrivilege, adminReadPrivilege, adminWritePrivilege);

		Role adminRole = createRoleIfNotFound(ERole.ROLE_ADMIN, adminPrivileges);
		Role userRole = createRoleIfNotFound(ERole.ROLE_USER, Arrays.asList(userReadPriviledge));
		Role mngrRole = createRoleIfNotFound(ERole.ROLE_MANAGER, Arrays.asList(userReadPriviledge, userWritePrivilege));
		Role hrOfficerRole = createRoleIfNotFound(ERole.ROLE_HR,
				Arrays.asList(userReadPriviledge, userWritePrivilege, hrRreadPrivilege));
		Role hrMngrRole = createRoleIfNotFound(ERole.ROLE_HRMANAGER,
				Arrays.asList(userReadPriviledge, userWritePrivilege, hrRreadPrivilege, hrWritePrivilege));

		List<Employee> employeeList = List.of(

				Employee.builder().empId("NO123").firstName("Emmanuel").lastName("Nwabudo").userName("okpara")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.roles(mngrRole).build(),

				Employee.builder().empId("NO153").firstName("Onyekachi").lastName("John").userName("emmaco")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.managerId("NO123").roles(userRole).build(),

				Employee.builder().empId("NO167").firstName("Kehinde").lastName("Alabi").userName("alabi")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.managerId("NO123").roles(userRole).build(),

				Employee.builder().empId("NO154").firstName("Dinma").lastName("Olua").userName("dinma")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.managerId("NO123").roles(userRole).build(),

				Employee.builder().empId("NO166").firstName("Taiye").lastName("Alabi").userName("taiyo")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.managerId("NO123").roles(userRole).build(),

				Employee.builder().empId("NO323").firstName("Francisca").lastName("Nnamdi").userName("nwabudo")
						.password(passwordEncoder.encode("password123")).managerId("NO023")
						.department(Department.HUMAN_RESOURCES).roles(hrOfficerRole).build(),

				Employee.builder().empId("NO3231").firstName("Maryjane").lastName("Okpara").userName("mjane")
						.password(passwordEncoder.encode("password123")).managerId("NO023")
						.department(Department.HUMAN_RESOURCES).roles(hrOfficerRole).build(),

				Employee.builder().empId("NO3023").firstName("Ikechukwu").lastName("Onah").userName("ikay")
						.password(passwordEncoder.encode("password123")).managerId("NO023")
						.department(Department.HUMAN_RESOURCES).roles(hrOfficerRole).build(),

				Employee.builder().empId("NO023").firstName("Mary").lastName("James").userName("mary")
						.password(passwordEncoder.encode("password123")).department(Department.HUMAN_RESOURCES)
						.roles(hrMngrRole).build(),

				Employee.builder().empId("NO0231").firstName("Samuel").lastName("Babatunde").userName("baba")
						.password(passwordEncoder.encode("password123")).department(Department.ADMINISTRATION)
						.roles(adminRole).build());

		//		userRepository.saveAll(employeeList);
		for(Employee emp: employeeList) {
			if(userRepository.existsByUserName(emp.getUserName()))
				continue;
			userRepository.save(emp);
		}

		Employee emp = userRepository.findByEmpId("NO023")
				.orElseThrow(() -> new UserServiceException("Such user does not exist"));

		Employee emp1 = userRepository.findByEmpId("NO153")
				.orElseThrow(() -> new UserServiceException("Such user does not exist"));

		Employee emp2 = userRepository.findByEmpId("NO0231")
				.orElseThrow(() -> new UserServiceException("Such user does not exist"));

		Employee emp3 = userRepository.findByEmpId("NO3023")
				.orElseThrow(() -> new UserServiceException("Such user does not exist"));

		Employee emp4 = userRepository.findByEmpId("NO123")
				.orElseThrow(() -> new UserServiceException("Such user does not exist"));

		List<BenefitPaymentHis> benefitList = List.of(
				BenefitPaymentHis.builder().employee(emp4).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(500000)).build(),

				BenefitPaymentHis.builder().employee(emp4).type(BenefitType.ANNUAL_BONUS)
						.disbursedamount(new BigDecimal(300000)).build(),

				BenefitPaymentHis.builder().employee(emp4).type(BenefitType.VOCATION_AMOUNT)
						.disbursedamount(new BigDecimal(35000)).build(),

				BenefitPaymentHis.builder().employee(emp3).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(1000000)).build(),

				BenefitPaymentHis.builder().employee(emp3).type(BenefitType.ANNUAL_BONUS)
						.disbursedamount(new BigDecimal(300000)).build(),

				BenefitPaymentHis.builder().employee(emp2).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(50000)).build(),

				BenefitPaymentHis.builder().employee(emp2).type(BenefitType.VOCATION_AMOUNT)
						.disbursedamount(new BigDecimal(30000)).build(),

				BenefitPaymentHis.builder().employee(emp2).type(BenefitType.ANNUAL_BONUS)
						.disbursedamount(new BigDecimal(30000)).build(),

				BenefitPaymentHis.builder().employee(emp1).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(30000)).build(),

				BenefitPaymentHis.builder().employee(emp1).type(BenefitType.VOCATION_AMOUNT)
						.disbursedamount(new BigDecimal(30000)).build(),

				BenefitPaymentHis.builder().employee(emp).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(30000)).build(),

				BenefitPaymentHis.builder().employee(emp3).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(500000)).build(),

				BenefitPaymentHis.builder().employee(emp).type(BenefitType.SALARY)
						.disbursedamount(new BigDecimal(30000)).build());

		benefitRepo.saveAll(benefitList);
		
		alreadySetup = true;
	}

	@Transactional
	Privilege createPrivilegeIfNotFound(ApplicationUserPermission name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			//privilege.setId(UUID.randomUUID().toString());
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	Role createRoleIfNotFound(ERole name, Collection<Privilege> privileges) {
		Optional<Role> roleOp = roleRepository.findByName(name);
		Role role = null;
		if (!roleOp.isPresent()) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}

}
