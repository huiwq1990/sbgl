package com.sbgl.app.entity;

/**
 * Worker entity. @author MyEclipse Persistence Tools
 */

public class Worker extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String workId;
	private String name;
	private String gender;
	private String telephone;
	private String email;
	private String photo;
	private String password;

	// Constructors

	/** default constructor */
	public Worker() {
	}

	/** minimal constructor */
	public Worker(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Worker(Integer id, String workId, String name, String gender,
			String telephone, String email, String photo, String password) {
		this.id = id;
		this.workId = workId;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.photo = photo;
		this.password = password;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}