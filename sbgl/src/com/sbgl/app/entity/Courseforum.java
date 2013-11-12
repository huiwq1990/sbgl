package com.sbgl.app.entity;

import java.util.Date;

/**
 * Courseforum entity. @author MyEclipse Persistence Tools
 */

public class Courseforum extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private String content;
	private Date time;
	private Integer panelId;
	private Integer chapter;

	// Constructors

	/** default constructor */
	public Courseforum() {
	}

	/** minimal constructor */
	public Courseforum(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Courseforum(Integer id, String title, String content,
			Date time, Integer panelId, Integer chapter) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.panelId = panelId;
		this.chapter = chapter;
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

	public Integer getPanelId() {
		return this.panelId;
	}

	public void setPanelId(Integer panelId) {
		this.panelId = panelId;
	}

	public Integer getChapter() {
		return this.chapter;
	}

	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}

}
