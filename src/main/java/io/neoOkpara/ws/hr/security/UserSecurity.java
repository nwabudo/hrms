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
public class UserSecurity {

	private UserRepository userRepo;

	public UserSecurity(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public boolean hasUserId(Authentication authentication, String empId) {
		Employee emp = this.userRepo.findByEmpId(empId).orElse(null);
		if (emp != null)
			return authentication.getName().equals(emp.getUserName());
		return false;
	}

	public boolean isNotMemberofHR(Employee returnObj, Authentication authentication, String empId) {
		Employee emp = ((UserPrincipal) authentication.getPrincipal()).getUser()
				.orElseThrow(() -> new UserServiceException("Authentication is not Valid"));
		
		if (emp.getEmpId().equals(empId) && emp.equals(returnObj))
			return true;
		
		if(emp.getRoles().getName().equals(ERole.ROLE_HR) && returnObj.getRoles().getName().equals(ERole.ROLE_HRMANAGER))
			return false;

		return !(returnObj.getRoles().equals(emp.getRoles()));
	}

	public boolean limitByLevelofHR(Authentication authentication) {
		boolean isHRManager = authentication.getAuthorities().stream()
				.anyMatch(p -> p.equals(new SimpleGrantedAuthority(ERole.ROLE_HRMANAGER.name())));
		log.info("isHRManager returned {}", isHRManager);
		return isHRManager;
	}
}
