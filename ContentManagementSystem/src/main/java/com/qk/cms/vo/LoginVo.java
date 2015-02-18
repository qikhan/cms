package com.qk.cms.vo;

import java.util.Map;

public class LoginVo {

	private String userName;
	private String password;
	private boolean authValid = false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAuthValid() {
		return authValid;
	}

	public void setAuthValid(boolean authValid) {
		this.authValid = authValid;
	}

	public void updateModel(Map<String, Object> model) {
		model.put("login", this);
		model.put("userName", getUserName());
		model.put("authValid", authValid);
	}
}
