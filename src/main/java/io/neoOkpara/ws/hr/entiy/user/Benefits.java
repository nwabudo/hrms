package io.neoOkpara.ws.hr.entiy.user;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Benefits {
	
	private BigDecimal salaryAmount;
	
	private BigDecimal vacationAmount;
	
	private BigDecimal annualBonus;
}
