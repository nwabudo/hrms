package io.neoOkpara.ws.hr.repository.user;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.neoOkpara.ws.hr.entiy.user.ERole;
import io.neoOkpara.ws.hr.entiy.user.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
