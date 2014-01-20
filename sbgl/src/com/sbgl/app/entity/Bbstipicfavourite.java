package com.sbgl.app.entity;

import java.util.Date;

/**
 * Bbstipicfavourite entity. @author MyEclipse Persistence Tools
 */

public class Bbstipicfavourite extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userid;
	private Integer topicid;
	private Date createtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Bbstipicfavourite() {
	}

	/** minimal constructor */
	public Bbstipicfavourite(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Bbstipicfavourite(Integer id, Integer userid, Integer topicid,
			Date createtime, Integer status) {
		this.id = id;
		this.userid = userid;
		this.topicid = topicid;
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

	public Integer getTopicid() {
		return this.topicid;
	}

	public void setTopicid(Integer topicid) {
		this.topicid = topicid;
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
