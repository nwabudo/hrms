package io.neoOkpara.ws.hr.entiy.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "privileges")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Privilege {

	@Id
	private ObjectId _id;

	@Field(name = "privilege_name")
	private ApplicationUserPermission name;
	
	public Privilege(ApplicationUserPermission name) {
		this.name = name;
	}
}
