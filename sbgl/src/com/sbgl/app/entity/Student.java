package com.sbgl.app.entity;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String studentId;
	private String name;
	private String gender;
	private String telephone;
	private String email;
	private String photo;
	private String password;
	private Integer classid;
	private String couldBorrow;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Student(Integer id, String studentId, String name, String gender,
			String telephone, String email, String photo, String password,
			Integer classid, String couldBorrow) {
		this.id = id;
		this.studentId = studentId;
		this.name = name;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.photo = photo;
		this.password = password;
		this.classid = classid;
		this.couldBorrow = couldBorrow;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentId() {
		return this.studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getCouldBorrow() {
		return this.couldBorrow;
	}

	public void setCouldBorrow(String couldBorrow) {
		this.couldBorrow = couldBorrow;
	}

}