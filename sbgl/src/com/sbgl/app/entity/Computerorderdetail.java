package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorderdetail entity. @author MyEclipse Persistence Tools
 */

public class Computerorderdetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer computerorderid;
	private Integer computermodelid;
	private Integer borrownumber;
	private Date createtime;
	private Date borrowday;
	private Integer borrowperiod;
	private String computerid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computerorderdetail() {
	}

	/** minimal constructor */
	public Computerorderdetail(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorderdetail(Integer id, Integer computerorderid,
			Integer computermodelid, Integer borrownumber,
			Date createtime, Date borrowday, Integer borrowperiod,
			String computerid, Integer status) {
		this.id = id;
		this.computerorderid = computerorderid;
		this.computermodelid = computermodelid;
		this.borrownumber = borrownumber;
		this.createtime = createtime;
		this.borrowday = borrowday;
		this.borrowperiod = borrowperiod;
		this.computerid = computerid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComputerorderid() {
		return this.computerorderid;
	}

	public void setComputerorderid(Integer computerorderid) {
		this.computerorderid = computerorderid;
	}

	public Integer getComputermodelid() {
		return this.computermodelid;
	}

	public void setComputermodelid(Integer computermodelid) {
		this.computermodelid = computermodelid;
	}

	public Integer getBorrownumber() {
		return this.borrownumber;
	}

	public void setBorrownumber(Integer borrownumber) {
		this.borrownumber = borrownumber;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getBorrowday() {
		return this.borrowday;
	}

	public void setBorrowday(Date borrowday) {
		this.borrowday = borrowday;
	}

	public Integer getBorrowperiod() {
		return this.borrowperiod;
	}

	public void setBorrowperiod(Integer borrowperiod) {
		this.borrowperiod = borrowperiod;
	}

	public String getComputerid() {
		return this.computerid;
	}

	public void setComputerid(String computerid) {
		this.computerid = computerid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
