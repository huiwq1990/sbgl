package com.sbgl.app.entity;

import java.util.Date;

/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */

public class Administrator extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String administratorid;
	private String name;
	private String gender;
	private String telephone;
	private String email;
	private String photo;
	private Integer privilege;
	private String password;
	private Date makedate;
	private Date modifydate;

	// Constructors

	/** default constructor */
	public Administrator() {
	}

	/** minimal constructor */
	public Administrator(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Administrator(Integer id, String administratorid, String name,
			String gender, String telephone, String email, String photo,
			Integer privilege, String password, Date makedate,
			Date modifydate) {
		this.id = id;
		this.administratorid = administratorid;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.photo = photo;
		this.privilege = privilege;
		this.password = password;
		this.makedate = makedate;
		this.modifydate = modifydate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdministratorid() {
		return this.administratorid;
	}

	public void setAdministratorid(String administratorid) {
		this.administratorid = administratorid;
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

	public Integer getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getMakedate() {
		return this.makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

}