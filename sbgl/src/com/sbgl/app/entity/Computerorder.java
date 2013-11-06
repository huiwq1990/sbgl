package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorder entity. @author MyEclipse Persistence Tools
 */

public class Computerorder extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String serialnumber;
	private Integer userid;
	private Date createtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computerorder() {
	}

	/** minimal constructor */
	public Computerorder(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorder(Integer id, String serialnumber, Integer userid,
			Date createtime, Integer status) {
		this.id = id;
		this.serialnumber = serialnumber;
		this.userid = userid;
		this.createtime = createtime;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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
