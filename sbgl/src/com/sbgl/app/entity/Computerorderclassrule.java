package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorderclassrule entity. @author MyEclipse Persistence Tools
 */

public class Computerorderclassrule extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String classname;
	private Integer classid;
	private Date orderstarttime;
	private Date orderendtime;
	private Integer availableordertime;
	private Integer createuserid;
	private Date createtime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computerorderclassrule() {
	}

	/** minimal constructor */
	public Computerorderclassrule(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorderclassrule(Integer id, String name, String classname,
			Integer classid, Date orderstarttime, Date orderendtime,
			Integer availableordertime, Integer createuserid,
			Date createtime, Integer status) {
		this.id = id;
		this.name = name;
		this.classname = classname;
		this.classid = classid;
		this.orderstarttime = orderstarttime;
		this.orderendtime = orderendtime;
		this.availableordertime = availableordertime;
		this.createuserid = createuserid;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public Date getOrderstarttime() {
		return this.orderstarttime;
	}

	public void setOrderstarttime(Date orderstarttime) {
		this.orderstarttime = orderstarttime;
	}

	public Date getOrderendtime() {
		return this.orderendtime;
	}

	public void setOrderendtime(Date orderendtime) {
		this.orderendtime = orderendtime;
	}

	public Integer getAvailableordertime() {
		return this.availableordertime;
	}

	public void setAvailableordertime(Integer availableordertime) {
		this.availableordertime = availableordertime;
	}

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
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
