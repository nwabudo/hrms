package io.neoOkpara.ws.hr.entiy.user;

import java.math.BigDecimal;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Document(collection = "benefit_tbl")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Benefits {

	@Id
	private ObjectId _id;
	
	@Field(name = "salary_amount")
	private BigDecimal salaryAmount;
	
	@Field(name = "vacation_amount")
	private BigDecimal vacationAmount;
	
	@Field(name = "annual_bonus")
	private BigDecimal annualBonus;
}
