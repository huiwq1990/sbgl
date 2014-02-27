package com.sbgl.app.entity;

public class Loginuser extends com.sbgl.app.dao.DaoAbs {
	private Integer id;
	private String userid;
	private String name;
	private String gender;
	private String telephone;
	private String email;
	private String roletype;
	private String privilege;
	private String password;
	private String photo;
	
	public Loginuser() {
		super();
	}

	public Loginuser(Integer id, String userid, String name, String gender,
			String telephone, String email, String roletype, String privilege,
			String password, String photo) {
		super();
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.roletype = roletype;
		this.privilege = privilege;
		this.password = password;
		this.photo = photo;
	}





	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
