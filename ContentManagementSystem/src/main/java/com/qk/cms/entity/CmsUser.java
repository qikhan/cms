package com.qk.cms.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: CmsUser
 *
 */
@Entity
public class CmsUser implements Serializable {

	private static final long serialVersionUID = -5309476787818180282L;
	@Id
	private String userName;
	@NotEmpty
	private String password;
	@NotEmpty
	private String email;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;

	public CmsUser() {
		super();
	}

	public CmsUser(String userName, String password, String email,
			String firstName, String lastName) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
