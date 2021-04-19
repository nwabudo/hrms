package io.neoOkpara.ws.hr.entiy.tenant;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DepartmentMapper implements RowMapper<Department>  {

	@Override
	public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
		int dNo = rs.getInt("deptno");
		String dName = rs.getString("dname");
		String location = rs.getString("loc");
		
		return new Department(dNo, dName, location);
	}

}
