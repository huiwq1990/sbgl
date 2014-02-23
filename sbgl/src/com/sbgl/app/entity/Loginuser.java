package com.sbgl.app.entity;

public class Loginuser extends com.sbgl.app.dao.DaoAbs {
	private String userId;
	private String name;
	private String roletype;
	private Integer privilege;
	private String password;
	
	

	public Loginuser() {
		
	}
	
	public Loginuser(String userId, String name, String roletype,
			Integer privilege, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.roletype = roletype;
		this.privilege = privilege;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
