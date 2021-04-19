package io.neoOkpara.ws.hr.entiy.tenant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tenancy {

	private String userId;
	
	private String userPwd;
	
	private String hostDBUrl;
	
	private String hostDBdriver;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getHostDBUrl() {
		return hostDBUrl;
	}

	public void setHostDBUrl(String hostDBUrl) {
		this.hostDBUrl = hostDBUrl;
	}

	public String getHostDBdriver() {
		return hostDBdriver;
	}

	public void setHostDBdriver(String hostDBdriver) {
		this.hostDBdriver = hostDBdriver;
	}
}
