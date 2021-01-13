package io.neoOkpara.ws.hr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import io.neoOkpara.ws.hr.entiy.user.Department;
import io.neoOkpara.ws.hr.entiy.user.ERole;
import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.repository.user.UserRepository;

@Component
public class SetupLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup)
			return;

		List<Employee> employeeList = List.of(
				
				Employee.builder().empId("NO123").firstName("Emmanuel").lastName("Nwabudo").userName("okpara")
				.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
				.isManager(true).role(ERole.ROLE_MANAGER).build(),
				
				Employee.builder().empId("NO153").firstName("Onyekachi").lastName("John").userName("emmaco")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.managerId("NO123")
						.isManager(false).role(ERole.ROLE_USER).build(),
						
				Employee.builder().empId("NO167").firstName("Kehinde").lastName("Alabi").userName("alabi")
						.password(passwordEncoder.encode("password123")).department(Department.INFORMATION_TECHNOLOGY)
						.managerId("NO123")
						.isManager(false).role(ERole.ROLE_USER).build(),
						
				Employee.builder().empId("NO323").firstName("Francisca").lastName("Nnamdi").userName("nwabudo")
						.password(passwordEncoder.encode("password123")).department(Department.HUMAN_RESOURCES)
						.isManager(false).role(ERole.ROLE_HR).build(),
		
				Employee.builder().empId("NO023").firstName("Mary").lastName("James").userName("mary")
					.password(passwordEncoder.encode("password123")).department(Department.HUMAN_RESOURCES)
					.isManager(true).role(ERole.ROLE_HRMANAGER).build());

		userRepository.saveAll(employeeList);
	}

}
