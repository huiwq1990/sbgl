package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorder entity. @author MyEclipse Persistence Tools
 */

public class Computerorder extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Date createtime;
	private Integer status;
	private Date starttime;
	private Date endtime;

	// Constructors

	/** default constructor */
	public Computerorder() {
	}

	/** minimal constructor */
	public Computerorder(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorder(Integer id, Integer userid, Date createtime,
			Integer status, Date starttime, Date endtime) {
		this.id = id;
		this.userid = userid;
		this.createtime = createtime;
		this.status = status;
		this.starttime = starttime;
		this.endtime = endtime;
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

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

}
