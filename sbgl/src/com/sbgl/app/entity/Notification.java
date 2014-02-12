package com.sbgl.app.entity;

import java.util.Date;

/**
 * Notification entity. @author MyEclipse Persistence Tools
 */

public class Notification extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Integer senderrid;
	private Integer receiverid;
	private Date sendtime;
	private Integer readstatus;
	private Integer modeltype;
	private Integer status;

	// Constructors

	/** default constructor */
	public Notification() {
	}

	/** minimal constructor */
	public Notification(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Notification(Integer id, String title, String content,
			Integer senderrid, Integer receiverid, Date sendtime,
			Integer readstatus, Integer modeltype, Integer status) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.senderrid = senderrid;
		this.receiverid = receiverid;
		this.sendtime = sendtime;
		this.readstatus = readstatus;
		this.modeltype = modeltype;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSenderrid() {
		return this.senderrid;
	}

	public void setSenderrid(Integer senderrid) {
		this.senderrid = senderrid;
	}

	public Integer getReceiverid() {
		return this.receiverid;
	}

	public void setReceiverid(Integer receiverid) {
		this.receiverid = receiverid;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getReadstatus() {
		return this.readstatus;
	}

	public void setReadstatus(Integer readstatus) {
		this.readstatus = readstatus;
	}

	public Integer getModeltype() {
		return this.modeltype;
	}

	public void setModeltype(Integer modeltype) {
		this.modeltype = modeltype;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
