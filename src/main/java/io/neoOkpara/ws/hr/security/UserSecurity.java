package io.neoOkpara.ws.hr.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.repository.user.UserRepository;

@Component("userSecurity")
public class UserSecurity {

	private UserRepository userRepo;

	public UserSecurity(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public boolean hasUserId(Authentication authentication, String userId) {
		Employee emp = this.userRepo.findByEmpId(userId).orElse(null);
		if(emp != null)
			return authentication.getName().equals(emp.getUserName());
		return false;
    }
	
	public boolean hasId(Authentication authentication, String userId) {
		Employee emp = this.userRepo.findByEmpId(userId).orElse(null);
		if(emp != null)
			return authentication.getName().equals(emp.getUserName());
		return false;
    }
	
    public boolean isMemberofHR(Employee returnObj,  String empId) {
    	Employee emp = this.userRepo.findByEmpId(empId).orElse(null);
		if(emp != null)
			//return !(returnObj.getDepartment().equals(emp.getDepartment()) && !emp.isManager());
			return !(returnObj.getRole().equals(emp.getRole()));
		return true;
    }
}
