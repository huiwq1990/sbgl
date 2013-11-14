package com.sbgl.app.entity;

import java.util.Date;

/**
 * Loginuser entity. @author MyEclipse Persistence Tools
 */

public class Loginuser extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer userid;
	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String name;
	private Date createtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Loginuser() {
	}

	/** minimal constructor */
	public Loginuser(Integer userid) {
		this.userid = userid;
	}

	/** full constructor */
	public Loginuser(Integer userid, String name, Date createtime,
			Integer status) {
		this.userid = userid;
		this.name = name;
		this.createtime = createtime;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.userid;
	}

	public void setId(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
