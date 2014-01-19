package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorderrule entity. @author MyEclipse Persistence Tools
 */

public class Computerorderrule extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String classname;
	private Integer classid;
	private Date orderstarttime;
	private Date orderendtime;
	private Integer totaltime;

	// Constructors

	/** default constructor */
	public Computerorderrule() {
	}

	/** minimal constructor */
	public Computerorderrule(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorderrule(Integer id, String name, String classname,
			Integer classid, Date orderstarttime, Date orderendtime,
			Integer totaltime) {
		this.id = id;
		this.name = name;
		this.classname = classname;
		this.classid = classid;
		this.orderstarttime = orderstarttime;
		this.orderendtime = orderendtime;
		this.totaltime = totaltime;
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

	public Integer getTotaltime() {
		return this.totaltime;
	}

	public void setTotaltime(Integer totaltime) {
		this.totaltime = totaltime;
	}

}
