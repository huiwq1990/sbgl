package com.sbgl.app.entity;

import java.util.Date;

/**
 * Equipmentclassification entity. @author MyEclipse Persistence Tools
 */

public class Equipmentclassification extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer classificationid;
	private Integer parentid;
	private String name;
	private Date maketime;
	private Date modifytime;
	private Integer userid;

	// Constructors

	/** default constructor */
	public Equipmentclassification() {
	}

	/** minimal constructor */
	public Equipmentclassification(Integer classificationid) {
		this.classificationid = classificationid;
	}

	/** full constructor */
	public Equipmentclassification(Integer classificationid, Integer parentid,
			String name, Date maketime, Date modifytime,
			Integer userid) {
		this.classificationid = classificationid;
		this.parentid = parentid;
		this.name = name;
		this.maketime = maketime;
		this.modifytime = modifytime;
		this.userid = userid;
	}

	// Property accessors

	public Integer getClassificationid() {
		return this.classificationid;
	}

	public void setClassificationid(Integer classificationid) {
		this.classificationid = classificationid;
	}

	public Integer getParentid() {
		return this.parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
