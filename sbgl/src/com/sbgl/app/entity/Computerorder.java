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
	private Integer userid;
	private String title;
	private Integer ordertype;
	private Date createtime;
	private String remark;
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
	public Computerorder(Integer id, String serialnumber, Integer userid,
			String title, Integer ordertype, Date createtime,
			String remark, Integer status) {
		this.id = id;
		this.serialnumber = serialnumber;
		this.userid = userid;
		this.title = title;
		this.ordertype = ordertype;
		this.createtime = createtime;
		this.remark = remark;
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

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
