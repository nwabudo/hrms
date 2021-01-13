package io.neoOkpara.ws.hr.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	public void commence(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException authException) throws IOException {
		// Authentication failed, send error response.
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Permission: Unauthorized");
		log.error("Unauthorized error: {}", authException.getMessage());
	}
	
	@Override
    public void afterPropertiesSet() {
        setRealmName("neoOkpara.io");
        super.afterPropertiesSet();
    }
}
