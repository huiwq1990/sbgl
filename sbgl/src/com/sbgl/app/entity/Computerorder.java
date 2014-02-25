package com.sbgl.app.entity;

import java.util.Date;

/**
 * Computerorder entity. @author MyEclipse Persistence Tools
 */

public class Computerorder extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String serialnumber;
	private Integer createuserid;
	private String title;
	private Integer ordertype;
	private Date createtime;
	private String remark;
	private String rejectreason;
	private Integer computerhomeworkid;
	private Integer audituserid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Computerorder() {
	}

	/** minimal constructor */
	public Computerorder(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Computerorder(Integer id, String serialnumber, Integer createuserid,
			String title, Integer ordertype, Date createtime,
			String remark, String rejectreason, Integer computerhomeworkid,
			Integer audituserid, Integer status) {
		this.id = id;
		this.serialnumber = serialnumber;
		this.createuserid = createuserid;
		this.title = title;
		this.ordertype = ordertype;
		this.createtime = createtime;
		this.remark = remark;
		this.rejectreason = rejectreason;
		this.computerhomeworkid = computerhomeworkid;
		this.audituserid = audituserid;
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

	public Integer getCreateuserid() {
		return this.createuserid;
	}

	public void setCreateuserid(Integer createuserid) {
		this.createuserid = createuserid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRejectreason() {
		return this.rejectreason;
	}

	public void setRejectreason(String rejectreason) {
		this.rejectreason = rejectreason;
	}

	public Integer getComputerhomeworkid() {
		return this.computerhomeworkid;
	}

	public void setComputerhomeworkid(Integer computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
	}

	public Integer getAudituserid() {
		return this.audituserid;
	}

	public void setAudituserid(Integer audituserid) {
		this.audituserid = audituserid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
