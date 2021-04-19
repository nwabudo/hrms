package io.neoOkpara.ws.hr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import io.neoOkpara.ws.hr.entiy.tenant.Department;
import io.neoOkpara.ws.hr.entiy.tenant.Tenancy;
import io.neoOkpara.ws.hr.service.TenantService;
import io.neoOkpara.ws.hr.service.impl.TenantServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyRunner implements CommandLineRunner {

	private String userId = "root";

	private String userPwd = "emmoxbobo";

	private String hostDBUrl = "jdbc:mysql://localhost:6603/testDB?allowMultiQueries=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	private String hostDBdriver = "com.mysql.cj.jdbc.Driver";

	@Autowired
	TenantService tenantService;

	private final Tenancy tenancy = new Tenancy(userId, userPwd, hostDBUrl, hostDBdriver);

	@Override
	public void run(String... args) throws Exception {

		String sql = "select * from dept";
		String query = "select dname from dept where deptno = 10";
		String insertQuery = "INSERT INTO dept(deptno, dname, loc) VALUES(?, ?, ?)";

		try {
			JdbcTemplate jdbc = tenantService.connectUser(tenancy);
			System.out.println(jdbc);
			List<Department> departments = jdbc.query(sql, new BeanPropertyRowMapper<Department>(Department.class));
			departments.stream().forEach(System.out::println);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		try (Statement stm = tenantService.connectUserViaDriverManager(tenancy);
				ResultSet rs = stm.executeQuery(query)) {
			if (rs.next()) {
				log.info("Department Name for deptno 10 is {}", rs.getString(1));
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

		try (Connection conn = TenantServiceImpl.getConnection();
				PreparedStatement pst = conn.prepareStatement(insertQuery)) {
			pst.setInt(1, 50);
			pst.setString(2, "Cybersecurity");
			pst.setString(3, "Israel");
			int returnValue = pst.executeUpdate();
			log.info("Update on the Department is {}", returnValue);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}

	}

}
