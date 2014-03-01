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
	private String ownerid;
	private Date createtime;
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
	public Usergroup(Integer id, String name, String ownerid,
			Date createtime, Integer status, Integer type) {
		this.id = id;
		this.name = name;
		this.ownerid = ownerid;
		this.createtime = createtime;
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

	public String getOwnerid() {
		return this.ownerid;
	}

	public void setOwnerid(String ownerid) {
		this.ownerid = ownerid;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}