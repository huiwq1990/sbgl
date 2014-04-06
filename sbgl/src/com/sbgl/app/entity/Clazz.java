package com.sbgl.app.entity;

/**
 * Clazz entity. @author MyEclipse Persistence Tools
 */

public class Clazz extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer classid;
	private String classname;
	private Integer classtype;

	// Constructors

	/** default constructor */
	public Clazz() {
	}

	/** minimal constructor */
	public Clazz(Integer classid) {
		this.classid = classid;
	}

	/** full constructor */
	public Clazz(Integer classid, String classname, Integer classtype) {
		this.classid = classid;
		this.classname = classname;
		this.classtype = classtype;
	}

	// Property accessors

	public Integer getClassid() {
		return this.classid;
	}

	public void setClassid(Integer classid) {
		this.classid = classid;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getClasstype() {
		return this.classtype;
	}

	public void setClasstype(Integer classtype) {
		this.classtype = classtype;
	}

}