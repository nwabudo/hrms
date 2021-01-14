package io.neoOkpara.ws.hr.repository.benefit;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import io.neoOkpara.ws.hr.entiy.user.BenefitPaymentHis;
import io.neoOkpara.ws.hr.entiy.user.Employee;

public interface BenefitRepository extends MongoRepository<BenefitPaymentHis, String> {
	
	List<BenefitPaymentHis> findAllByEmployee(Employee emp);
}
