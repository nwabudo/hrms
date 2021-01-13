package io.neoOkpara.ws.hr.entiy.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "roles")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

	@Id
	private String id;

	private ERole name;
	
	/*
	 * @DBRef private Collection<Privilege> privileges;
	 */

	public Role(ERole name) {
		this.name = name;
	}
}
