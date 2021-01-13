package io.neoOkpara.ws.hr.entity.audit;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Setter
@Getter
public abstract class AuditModel implements Serializable {

	private static final long serialVersionUID = 3249193964152480108L;

	@NotNull 
    @Field(name = "created_at")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;

	@NotNull 
    @Field(name = "updated_at")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updatedAt;
    
}
