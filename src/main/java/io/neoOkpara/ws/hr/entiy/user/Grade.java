package io.neoOkpara.ws.hr.entiy.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "grade_tbl")
@Builder
@Getter
@Setter
@ToString
public class Grade {
	
	@Id
	private ObjectId _id;
	
	@Field(name = "grade")
	private GradeLevel gradeLevel;
	
	@DBRef
	@Field(name = "benefits")
	private Benefits benefit;

}