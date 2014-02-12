package com.sbgl.app.entity;

import java.util.Date;

/**
 * Messagereceiver entity. @author MyEclipse Persistence Tools
 */

public class Messagereceiver extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer messageid;
	private Integer receiverid;
	private Integer hasview;
	private Date viewdate;
	private Integer status;

	// Constructors

	/** default constructor */
	public Messagereceiver() {
	}

	/** minimal constructor */
	public Messagereceiver(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Messagereceiver(Integer id, Integer messageid, Integer receiverid,
			Integer hasview, Date viewdate, Integer status) {
		this.id = id;
		this.messageid = messageid;
		this.receiverid = receiverid;
		this.hasview = hasview;
		this.viewdate = viewdate;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMessageid() {
		return this.messageid;
	}

	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}

	public Integer getReceiverid() {
		return this.receiverid;
	}

	public void setReceiverid(Integer receiverid) {
		this.receiverid = receiverid;
	}

	public Integer getHasview() {
		return this.hasview;
	}

	public void setHasview(Integer hasview) {
		this.hasview = hasview;
	}

	public Date getViewdate() {
		return this.viewdate;
	}

	public void setViewdate(Date viewdate) {
		this.viewdate = viewdate;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
