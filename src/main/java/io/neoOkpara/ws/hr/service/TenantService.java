package io.neoOkpara.ws.hr.service;

import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;

import io.neoOkpara.ws.hr.entiy.tenant.Tenancy;

public interface TenantService {

	JdbcTemplate connectUser(Tenancy tenant);
	
	Statement connectUserViaDriverManager(Tenancy tenant);
}
