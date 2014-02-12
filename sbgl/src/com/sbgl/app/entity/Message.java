package com.sbgl.app.entity;

import java.util.Date;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Integer senderid;
	private Date sendtime;
	private Integer replyid;
	private Integer readstatus;
	private String filepath;
	private Integer isbigfile;
	private Integer type;
	private Integer status;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Message(Integer id, String title, String content, Integer senderid,
			Date sendtime, Integer replyid, Integer readstatus,
			String filepath, Integer isbigfile, Integer type, Integer status) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.senderid = senderid;
		this.sendtime = sendtime;
		this.replyid = replyid;
		this.readstatus = readstatus;
		this.filepath = filepath;
		this.isbigfile = isbigfile;
		this.type = type;
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

	public Integer getSenderid() {
		return this.senderid;
	}

	public void setSenderid(Integer senderid) {
		this.senderid = senderid;
	}

	public Date getSendtime() {
		return this.sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

	public Integer getReplyid() {
		return this.replyid;
	}

	public void setReplyid(Integer replyid) {
		this.replyid = replyid;
	}

	public Integer getReadstatus() {
		return this.readstatus;
	}

	public void setReadstatus(Integer readstatus) {
		this.readstatus = readstatus;
	}

	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getIsbigfile() {
		return this.isbigfile;
	}

	public void setIsbigfile(Integer isbigfile) {
		this.isbigfile = isbigfile;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
