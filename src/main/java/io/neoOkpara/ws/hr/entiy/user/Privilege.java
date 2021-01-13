package io.neoOkpara.ws.hr.entiy.user;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "privileges")
@Builder
@Getter
@Setter
@ToString(exclude = { "employee" })
public class Privilege {

	@Id
	private Long id;

	private String name;

	@DBRef
	private Collection<Employee> employee;
}
