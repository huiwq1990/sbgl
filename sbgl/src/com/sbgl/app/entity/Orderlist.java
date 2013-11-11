package com.sbgl.app.entity;

import java.util.Date;

/**
 * Orderlist entity. @author MyEclipse Persistence Tools
 */

public class Orderlist extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String userid;
	private String audituserid;
	private Integer ordertype;
	private Date applytime;
	private Integer systemstatus;
	private Integer auditstatus;
	private Date applystarttime;
	private Date applyendtime;
	private String applyordernum;
	private Integer status;

	// Constructors

	/** default constructor */
	public Orderlist() {
	}

	/** minimal constructor */
	public Orderlist(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Orderlist(Integer id, String userid, String audituserid,
			Integer ordertype, Date applytime, Integer systemstatus,
			Integer auditstatus, Date applystarttime,
			Date applyendtime, String applyordernum, Integer status) {
		this.id = id;
		this.userid = userid;
		this.audituserid = audituserid;
		this.ordertype = ordertype;
		this.applytime = applytime;
		this.systemstatus = systemstatus;
		this.auditstatus = auditstatus;
		this.applystarttime = applystarttime;
		this.applyendtime = applyendtime;
		this.applyordernum = applyordernum;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAudituserid() {
		return this.audituserid;
	}

	public void setAudituserid(String audituserid) {
		this.audituserid = audituserid;
	}

	public Integer getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Date getApplytime() {
		return this.applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public Integer getSystemstatus() {
		return this.systemstatus;
	}

	public void setSystemstatus(Integer systemstatus) {
		this.systemstatus = systemstatus;
	}

	public Integer getAuditstatus() {
		return this.auditstatus;
	}

	public void setAuditstatus(Integer auditstatus) {
		this.auditstatus = auditstatus;
	}

	public Date getApplystarttime() {
		return this.applystarttime;
	}

	public void setApplystarttime(Date applystarttime) {
		this.applystarttime = applystarttime;
	}

	public Date getApplyendtime() {
		return this.applyendtime;
	}

	public void setApplyendtime(Date applyendtime) {
		this.applyendtime = applyendtime;
	}

	public String getApplyordernum() {
		return this.applyordernum;
	}

	public void setApplyordernum(String applyordernum) {
		this.applyordernum = applyordernum;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
