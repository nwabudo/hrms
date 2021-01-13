package io.neoOkpara.ws.hr.repository.user;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.neoOkpara.ws.hr.entiy.user.Employee;

public interface UserRepository extends MongoRepository<Employee, String> {
	
	Optional<Employee> findByUserName(String username);

	Optional<Employee> findByEmpId(String empId);
	
	Boolean existsByUserName(String username);
	
	Set<Employee> findByManagerId(String managerId);
}
