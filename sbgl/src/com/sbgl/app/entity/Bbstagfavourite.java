package com.sbgl.app.entity;

import java.util.Date;

/**
 * Bbstagfavourite entity. @author MyEclipse Persistence Tools
 */

public class Bbstagfavourite extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer tagid;
	private Date createtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Bbstagfavourite() {
	}

	/** minimal constructor */
	public Bbstagfavourite(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Bbstagfavourite(Integer id, Integer userid, Integer tagid,
			Date createtime, Integer status) {
		this.id = id;
		this.userid = userid;
		this.tagid = tagid;
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

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getTagid() {
		return this.tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
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
