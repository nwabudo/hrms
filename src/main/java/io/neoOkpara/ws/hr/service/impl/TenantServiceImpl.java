package io.neoOkpara.ws.hr.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.neoOkpara.ws.hr.entiy.tenant.Tenancy;
import io.neoOkpara.ws.hr.service.TenantService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TenantServiceImpl implements TenantService {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	//private Tenancy tenant = new Tenancy();

	static {
		config.setPoolName("infinit_db" + "_pool");
		config.setJdbcUrl("jdbc:mysql://localhost:6603/testDB?allowMultiQueries=true&allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDaten");
		config.setUsername("root");
		config.setPassword("emmoxbobo");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setMaximumPoolSize(40);
		// Set timeout to 5 seconds
		config.setConnectionTimeout(TimeUnit.SECONDS.toMillis(5));
		config.setConnectionTestQuery("SELECT 1");
		config.setValidationTimeout(30000);
		config.setAutoCommit(true);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	public TenantServiceImpl() {
	}

	@Override
	public JdbcTemplate connectUser(Tenancy tenant) {
		log.info("Tenancy Connect Service to connect {}", tenant.getUserId());

		final DriverManagerDataSource datasource = new DriverManagerDataSource();
		try {
			Class.forName(tenant.getHostDBdriver());
			log.info("Connection Strings: " + tenant.getUserId());
			datasource.setDriverClassName(tenant.getHostDBdriver());
			datasource.setUrl(tenant.getHostDBUrl());
			datasource.setUsername(tenant.getUserId());
			datasource.setPassword(tenant.getUserPwd());

			log.info("JDBC Database Connection: " + datasource.getConnection().getSchema());
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}
		return new JdbcTemplate(datasource);
	}

	@Override
	public Statement connectUserViaDriverManager(Tenancy tenant) {
		try {
			Class.forName(tenant.getHostDBdriver());
			Connection con = DriverManager.getConnection(tenant.getHostDBUrl(), tenant.getUserId(),
					tenant.getUserPwd());
			return con.createStatement();
		} catch (Exception ex) {
			log.error("Error Fetched is {}", ex.getMessage());
		}
		return null;
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
