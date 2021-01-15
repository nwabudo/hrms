package io.neoOkpara.ws.hr.entiy.user;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import io.neoOkpara.ws.hr.entity.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "employee")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AuditModel {

	private static final long serialVersionUID = -7521158852319446040L;

	@Id
	private BigInteger _id;

	@Field(name = "emp_id")
	public String empId;

	@Field(name = "first_name")
	private String firstName;

	@Field(name = "last_name")
	private String lastName;

	@Field(name = "user_name")
	@Indexed(unique=true)
	private String userName;

	@Field(name = "pwd")
	private String password;

	// @Field(name = "enabled")
	// private boolean enabled;

	@Field(name = "salary_amount")
	@Builder.Default
	private BigDecimal salaryAmount = new BigDecimal(1000);

	@Field(name = "vacation_amount")
	@Builder.Default
	private BigDecimal vacationAmount = new BigDecimal(1000);

	@Field(name = "annual_bonus")
	@Builder.Default
	private BigDecimal annualBonus = new BigDecimal(1000);

	@Field(name = "department_id")
	private Department department;

	@Field(name = "manager_id")
	@Builder.Default
	private String managerId = null;

	// @Field(name = "is_manager")
	// @Builder.Default private boolean isManager = false;
	//	@DBRef
	//	private UserGroup group;

	@DBRef
	private Role roles;

	public void assignAManger(String id) {
		this.managerId = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	
}
