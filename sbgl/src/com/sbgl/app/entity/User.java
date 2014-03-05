package com.sbgl.app.entity;

import java.util.Date;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Double usernumber;
	private String username;
	private String gender;
	private Date birthday;
	private String userpass;
	private String phonenum;
	private String email;
	private Integer classbelong;
	private String photo;
	private Date createtime;
	private Date modifytime;
	private Integer privilege;
	private Integer roletype;
	private Integer createrid;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public User(Integer id, Double usernumber, String username, String gender,
			Date birthday, String userpass, String phonenum, String email,
			Integer classbelong, String photo, Date createtime,
			Date modifytime, Integer privilege, Integer roletype,
			Integer createrid) {
		this.id = id;
		this.usernumber = usernumber;
		this.username = username;
		this.gender = gender;
		this.birthday = birthday;
		this.userpass = userpass;
		this.phonenum = phonenum;
		this.email = email;
		this.classbelong = classbelong;
		this.photo = photo;
		this.createtime = createtime;
		this.modifytime = modifytime;
		this.privilege = privilege;
		this.roletype = roletype;
		this.createrid = createrid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getUsernumber() {
		return this.usernumber;
	}

	public void setUsernumber(Double usernumber) {
		this.usernumber = usernumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getUserpass() {
		return this.userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getPhonenum() {
		return this.phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getClassbelong() {
		return this.classbelong;
	}

	public void setClassbelong(Integer classbelong) {
		this.classbelong = classbelong;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Integer getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public Integer getRoletype() {
		return this.roletype;
	}

	public void setRoletype(Integer roletype) {
		this.roletype = roletype;
	}

	public Integer getCreaterid() {
		return this.createrid;
	}

	public void setCreaterid(Integer createrid) {
		this.createrid = createrid;
	}

}