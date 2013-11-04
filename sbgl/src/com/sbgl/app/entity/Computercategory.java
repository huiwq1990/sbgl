package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computercategory entity. @author MyEclipse Persistence Tools
 */

public class Computercategory extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer parentcomputercategoryid;
	private String name;
	private Date createtime;
	private Integer createuserid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computercategory() {
	}

	/** minimal constructor */
	public Computercategory(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computercategory(Integer id, Integer parentcomputercategoryid,
			String name, Date createtime, Integer createuserid,
			Integer status) {
		this.id = id;
		this.parentcomputercategoryid = parentcomputercategoryid;
		this.name = name;
		this.createtime = createtime;
		this.createuserid = createuserid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentcomputercategoryid() {
		return this.parentcomputercategoryid;
	}

	public void setParentcomputercategoryid(Integer parentcomputercategoryid) {
		this.parentcomputercategoryid = parentcomputercategoryid;
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

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
