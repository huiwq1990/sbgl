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
	private Integer computerid;
	private Integer computernumber;
	private Date createtime;
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
			Integer computerid, Integer computernumber, Date createtime,
			Integer status) {
		this.id = id;
		this.computerorderid = computerorderid;
		this.computerid = computerid;
		this.computernumber = computernumber;
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

	public Integer getComputerorderid() {
		return this.computerorderid;
	}

	public void setComputerorderid(Integer computerorderid) {
		this.computerorderid = computerorderid;
	}

	public Integer getComputerid() {
		return this.computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public Integer getComputernumber() {
		return this.computernumber;
	}

	public void setComputernumber(Integer computernumber) {
		this.computernumber = computernumber;
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
