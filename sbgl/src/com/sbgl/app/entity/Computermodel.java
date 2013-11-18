package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computermodel entity. @author MyEclipse Persistence Tools
 */

public class Computermodel extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer computercategoryid;
	private String picpath;
	private Date createtime;
	private Integer createuserid;
	private Integer computercount;
	private String description;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computermodel() {
	}

	/** minimal constructor */
	public Computermodel(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computermodel(Integer id, String name, Integer computercategoryid,
			String picpath, Date createtime, Integer createuserid,
			Integer computercount, String description, Integer status) {
		this.id = id;
		this.name = name;
		this.computercategoryid = computercategoryid;
		this.picpath = picpath;
		this.createtime = createtime;
		this.createuserid = createuserid;
		this.computercount = computercount;
		this.description = description;
		this.status = status;
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

	public Integer getComputercategoryid() {
		return this.computercategoryid;
	}

	public void setComputercategoryid(Integer computercategoryid) {
		this.computercategoryid = computercategoryid;
	}

	public String getPicpath() {
		return this.picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public Integer getComputercount() {
		return this.computercount;
	}

	public void setComputercount(Integer computercount) {
		this.computercount = computercount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
