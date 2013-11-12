package com.sbgl.app.entity;

import java.util.Date;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Date time;
	private Integer userId;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Notice(Integer id, String title, String content, Date time,
			Integer userId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.userId = userId;
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

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
