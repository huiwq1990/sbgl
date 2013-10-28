package com.sbgl.app.entity;

import java.util.Date;

/**
 * Orderaudit entity. @author MyEclipse Persistence Tools
 */

public class Orderaudit extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer orderid;
	private String audituserid;
	private Integer allowpass;
	private String reason;
	private Date audittime;
	private Integer status;

	// Constructors

	/** default constructor */
	public Orderaudit() {
	}

	/** minimal constructor */
	public Orderaudit(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Orderaudit(Integer id, Integer orderid, String audituserid,
			Integer allowpass, String reason, Date audittime,
			Integer status) {
		this.id = id;
		this.orderid = orderid;
		this.audituserid = audituserid;
		this.allowpass = allowpass;
		this.reason = reason;
		this.audittime = audittime;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getAudituserid() {
		return this.audituserid;
	}

	public void setAudituserid(String audituserid) {
		this.audituserid = audituserid;
	}

	public Integer getAllowpass() {
		return this.allowpass;
	}

	public void setAllowpass(Integer allowpass) {
		this.allowpass = allowpass;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getAudittime() {
		return this.audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
