package com.sbgl.app.entity;

public class Loginuser extends com.sbgl.app.dao.DaoAbs {
	private Integer id;
	private String userId;
	private String name;
	private String roletype;
	private String privilege;
	private String password;
	
	public Loginuser() {
		super();
	}

	public Loginuser(Integer id, String userId, String name, String roletype,
			String privilege, String password) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.roletype = roletype;
		this.privilege = privilege;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
