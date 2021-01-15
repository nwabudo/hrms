package io.neoOkpara.ws.hr.entiy.user;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties(value = { "employee" })
public class BenefitPaymentHis extends AuditModel {

	private static final long serialVersionUID = -911265061874866421L;
	
	@Id
	private BigInteger _id;
	
	@DBRef
	@Field(name = "emp_id")
	private Employee employee;
	
	@Field(name = "type")
	private BenefitType type;
	
	@Field(name = "disbursed_amount")
	@Builder.Default
	private BigDecimal disbursedamount = new BigDecimal(0);
	
	@Field(name = "disbursed_date")
	@Builder.Default
	@JsonFormat(pattern = "yyyy-MMM-dd HH:mm")
	private Date disbursedDate = new Date();
}
