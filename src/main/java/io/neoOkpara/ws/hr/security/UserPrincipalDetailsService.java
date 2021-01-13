package io.neoOkpara.ws.hr.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.neoOkpara.ws.hr.entiy.user.Employee;
import io.neoOkpara.ws.hr.repository.user.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
	private UserRepository userRepository;

	public UserPrincipalDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = this.userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user Found with userName: " + username));

		UserPrincipal user = new UserPrincipal(employee);
		return user;
	}

}
