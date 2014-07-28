package com.sbgl.app.entity;

import java.util.Date;

/**
 * Userlogininfo entity. @author MyEclipse Persistence Tools
 */

public class Userlogininfo extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private String isfirstlogin;
	private Integer logincount;
	private Date lastlogintime;
	private String remark;

	// Constructors

	/** default constructor */
	public Userlogininfo() {
	}

	/** minimal constructor */
	public Userlogininfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Userlogininfo(Integer id, Integer userid, String isfirstlogin,
			Integer logincount, Date lastlogintime, String remark) {
		this.id = id;
		this.userid = userid;
		this.isfirstlogin = isfirstlogin;
		this.logincount = logincount;
		this.lastlogintime = lastlogintime;
		this.remark = remark;
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

	public String getIsfirstlogin() {
		return this.isfirstlogin;
	}

	public void setIsfirstlogin(String isfirstlogin) {
		this.isfirstlogin = isfirstlogin;
	}

	public Integer getLogincount() {
		return this.logincount;
	}

	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}

	public Date getLastlogintime() {
		return this.lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}