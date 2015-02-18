package com.qk.cms.auth;

import com.qk.cms.vo.LoginVo;

public class UserSession {

	String sessionId;
	LoginVo loginVo;

	public UserSession(String sessionId, LoginVo loginVo) {
		super();
		this.sessionId = sessionId;
		this.loginVo = loginVo;
	}

	public String getSessionId() {
		return sessionId;
	}

	public LoginVo getLoginVo() {
		return loginVo;
	}

}
