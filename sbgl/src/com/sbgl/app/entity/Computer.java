package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computer entity. @author MyEclipse Persistence Tools
 */

public class Computer extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String serialnumber;
	private Integer computermodelid;
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
	public Computer(Integer id, String serialnumber, Integer computermodelid,
			Date createtime, Integer createuserid, Integer status) {
		this.id = id;
		this.serialnumber = serialnumber;
		this.computermodelid = computermodelid;
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

	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public Integer getComputermodelid() {
		return this.computermodelid;
	}

	public void setComputermodelid(Integer computermodelid) {
		this.computermodelid = computermodelid;
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
