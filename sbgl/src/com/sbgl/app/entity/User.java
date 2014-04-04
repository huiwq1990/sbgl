package com.sbgl.app.entity;

import java.util.Date;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String name;
	private String gender;
	private String password;
	private Integer usertype;
	private Integer classid;
	private String email;
	private String telephone;
	private String cardnumber;
	private Integer initlan;
	private String photo;
	private Date createdate;
	private Date modifydate;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public User(Integer id, Integer userid, String name, String gender,
			String password, Integer usertype, Integer classid, String email,
			String telephone, String cardnumber, Integer initlan, String photo,
			Date createdate, Date modifydate) {
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.usertype = usertype;
		this.classid = classid;
		this.email = email;
		this.telephone = telephone;
		this.cardnumber = cardnumber;
		this.initlan = initlan;
		this.photo = photo;
		this.createdate = createdate;
		this.modifydate = modifydate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUsertype() {
		return this.usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCardnumber() {
		return this.cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public Integer getInitlan() {
		return this.initlan;
	}

	public void setInitlan(Integer initlan) {
		this.initlan = initlan;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

}