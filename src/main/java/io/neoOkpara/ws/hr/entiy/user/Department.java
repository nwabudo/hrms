package io.neoOkpara.ws.hr.entiy.user;

public enum Department {
	HUMAN_RESOURCES("HR"),
	INFORMATION_TECHNOLOGY("IT"),
	ADMINISTRATION("Admin");
	
	private final String department;
	
	private Department(String name) {
		this.department = name;
	}
	
	public String getDepartment() {
		return department;
	}
}
