package com.qk.cms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: CmsUser
 *
 */
@Entity
@XmlRootElement(name = "user")
@Path("/user")
public class CmsUser implements Serializable {

	private static final long serialVersionUID = -5309476787818180282L;
	@Id
	private String userName;
	private String password;
	private String email;

	public CmsUser() {
		super();
	}

	public CmsUser(String userName, String password, String email,
			String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public String getUserName() {
		return this.userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "CmsUser [userName=" + userName + ", password=" + password
				+ ", email=" + email + "]";
	}

}
