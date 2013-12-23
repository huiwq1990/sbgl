package com.sbgl.app.entity;

import java.util.Date;

/**
 * Usergroup entity. @author MyEclipse Persistence Tools
 */

public class Usergroup extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String ownerId;
	private Date createTime;
	private Integer status;
	private Integer type;

	// Constructors

	/** default constructor */
	public Usergroup() {
	}

	/** minimal constructor */
	public Usergroup(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Usergroup(Integer id, String name, String ownerId,
			Date createTime, Integer status, Integer type) {
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
		this.createTime = createTime;
		this.status = status;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}