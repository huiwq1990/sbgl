package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computer entity. @author MyEclipse Persistence Tools
 */

public class Computer extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer serialnumber;
	private String name;
	private Integer computercategoryid;
	private Date createtime;
	private Integer createuserid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computer() {
	}

	/** minimal constructor */
	public Computer(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computer(Integer id, Integer serialnumber, String name,
			Integer computercategoryid, Date createtime,
			Integer createuserid, Integer status) {
		this.id = id;
		this.serialnumber = serialnumber;
		this.name = name;
		this.computercategoryid = computercategoryid;
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

	public Integer getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
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
