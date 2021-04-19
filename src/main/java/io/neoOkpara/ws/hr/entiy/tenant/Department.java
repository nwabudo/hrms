package io.neoOkpara.ws.hr.entiy.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	private int deptno;
	private String dname;
	private String loc;
}
