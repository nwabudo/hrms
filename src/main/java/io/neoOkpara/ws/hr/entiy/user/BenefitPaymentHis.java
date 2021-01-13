package io.neoOkpara.ws.hr.entiy.user;

import java.math.BigDecimal;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.neoOkpara.ws.hr.entity.audit.AuditModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "benefit_his_tbl")
@Builder
@Getter
@Setter
@ToString
public class BenefitPaymentHis extends AuditModel {

	private static final long serialVersionUID = -911265061874866421L;
	
	@Id
	private ObjectId _id;
	
	@DBRef
	@Field(name = "emp_id")
	private Employee employee;
	
	@Field(name = "type")
	private BenefitType type;
	
	@Field(name = "disbursed_amount")
	private BigDecimal disbursedamount;
	
	@Field(name = "disbursed_date")
	@Builder.Default
	private Date disbursedDate = new Date();
}
