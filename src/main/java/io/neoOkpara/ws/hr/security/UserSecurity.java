package io.neoOkpara.ws.hr.security;

import org.springframework.security.core.Authentication;

public interface UserSecurity {

	boolean limitByLevelofHR(Authentication authentication);

	boolean useHeirachy(String empId, Authentication authentication);

}
