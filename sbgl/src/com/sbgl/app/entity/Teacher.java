package com.sbgl.app.entity;

import java.util.Date;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String teacherid;
	private String name;
	private String gender;
	private String telephone;
	private String email;
	private String photo;
	private String password;
	private Date makedate;
	private Date modifydate;
	private String couldborrow;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Teacher(Integer id, String teacherid, String name, String gender,
			String telephone, String email, String photo, String password,
			Date makedate, Date modifydate) {
		this.id = id;
		this.teacherid = teacherid;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.photo = photo;
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

	public String getTeacherid() {
		return this.teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
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

	public String getCouldborrow() {
		return couldborrow;
	}

	public void setCouldborrow(String couldborrow) {
		this.couldborrow = couldborrow;
	}

}