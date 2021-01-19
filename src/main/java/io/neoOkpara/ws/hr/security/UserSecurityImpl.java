package io.neoOkpara.ws.hr.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.neoOkpara.ws.hr.entiy.user.ERole;
import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.exception.UserServiceException;
import io.neoOkpara.ws.hr.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Component("userSecurity")
@Slf4j
public class UserSecurityImpl implements UserSecurity {

	private UserRepository userRepo;

	public UserSecurityImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public boolean limitByLevelofHR(Authentication authentication) {
		boolean isHRManager = authentication.getAuthorities().stream()
				.anyMatch(p -> p.equals(new SimpleGrantedAuthority(ERole.ROLE_HRMANAGER.name())));
		log.info("isHRManager returned {}", isHRManager);
		return isHRManager;
	}

	@Override
	public boolean useHeirachy(String empId, Authentication authentication) {
		Employee emp = ((UserPrincipal) authentication.getPrincipal()).getUser()
				.orElseThrow(() -> new UserServiceException("Authentication is not Valid"));

		if (emp.getEmpId().equals(empId))
			return true;

		Employee returnObj = this.userRepo.findByEmpId(empId)
				.orElseThrow(() -> new UserServiceException("No User exist with the empId " + empId));

		boolean isOga = compareRoles(returnObj, emp) > 0;
		log.info("isOga returned {}", isOga);
		return isOga;
	}

	private int compareRoles(Employee targetEmp, Employee authEmp) {
		String[] roles = ERole.getRoleHierarchy().split(">");
		// Map<Integer, String> mappedRole = new HashMap<>();
		Integer authEmpLevel = 0;
		Integer returnEmp = 0;
		for (int i = 0; i < roles.length; i++) {
			// mappedRole.put(roles.length - i, roles[i].trim());
			if (authEmp.getRoles().getName().getRole().equals(roles[i].trim()))
				authEmpLevel = roles.length - i;
			if (targetEmp.getRoles().getName().getRole().equals(roles[i].trim()))
				returnEmp = roles.length - i;
		}
		if(authEmpLevel < 3) {
			String managerId = authEmp.getEmpId(), employeeMngId = targetEmp.getManagerId() == null ? "" : targetEmp.getManagerId();
			if(!managerId.equals(employeeMngId))
				return -1;
		}
		log.info("Authenticating Employee level is {}, Target Employee level is {}", authEmpLevel, returnEmp);
		return authEmpLevel.compareTo(returnEmp);
	}

}
