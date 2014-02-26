package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorderconfig entity. @author MyEclipse Persistence Tools
 */

public class Computerorderconfig extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer openorder;
	private String orderperiod;
	private Integer orderperiodnum;
	private Integer maxorderday;
	private String orderintroduction;
	private Integer createuserid;
	private Date createtime;
	private Integer currentconfig;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computerorderconfig() {
	}

	/** minimal constructor */
	public Computerorderconfig(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorderconfig(Integer id, Integer openorder,
			String orderperiod, Integer orderperiodnum, Integer maxorderday,
			String orderintroduction, Integer createuserid, Date createtime,
			Integer currentconfig, Integer status) {
		this.id = id;
		this.openorder = openorder;
		this.orderperiod = orderperiod;
		this.orderperiodnum = orderperiodnum;
		this.maxorderday = maxorderday;
		this.orderintroduction = orderintroduction;
		this.createuserid = createuserid;
		this.createtime = createtime;
		this.currentconfig = currentconfig;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOpenorder() {
		return this.openorder;
	}

	public void setOpenorder(Integer openorder) {
		this.openorder = openorder;
	}

	public String getOrderperiod() {
		return this.orderperiod;
	}

	public void setOrderperiod(String orderperiod) {
		this.orderperiod = orderperiod;
	}

	public Integer getOrderperiodnum() {
		return this.orderperiodnum;
	}

	public void setOrderperiodnum(Integer orderperiodnum) {
		this.orderperiodnum = orderperiodnum;
	}

	public Integer getMaxorderday() {
		return this.maxorderday;
	}

	public void setMaxorderday(Integer maxorderday) {
		this.maxorderday = maxorderday;
	}

	public String getOrderintroduction() {
		return this.orderintroduction;
	}

	public void setOrderintroduction(String orderintroduction) {
		this.orderintroduction = orderintroduction;
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

	public Integer getCurrentconfig() {
		return this.currentconfig;
	}

	public void setCurrentconfig(Integer currentconfig) {
		this.currentconfig = currentconfig;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
