package io.neoOkpara.ws.hr.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.neoOkpara.ws.hr.entiy.user.ApplicationUserPermission;
import io.neoOkpara.ws.hr.entiy.user.Privilege;

public interface PrivilegeRepository extends MongoRepository<Privilege, String>  {

	Privilege findByName(ApplicationUserPermission name);

}
