package com.sbgl.app.entity;

import java.util.Date;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer categoryid;
	private String categoryname;
	private Date maketime;
	private Date modifytime;
	private Integer userid;

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(Integer categoryid) {
		this.categoryid = categoryid;
	}

	/** full constructor */
	public Category(Integer categoryid, String categoryname,
			Date maketime, Date modifytime, Integer userid) {
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.maketime = maketime;
		this.modifytime = modifytime;
		this.userid = userid;
	}

	// Property accessors

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return this.categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public Date getMaketime() {
		return this.maketime;
	}

	public void setMaketime(Date maketime) {
		this.maketime = maketime;
	}

	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}
