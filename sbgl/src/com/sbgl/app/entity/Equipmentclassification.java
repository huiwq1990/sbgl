package com.sbgl.app.entity;

import java.sql.Timestamp;

/**
 * Equipmentclassification entity. @author MyEclipse Persistence Tools
 */

public class Equipmentclassification extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer classificationid;
	private Integer parentid;
	private String name;
	private Timestamp maketime;
	private Timestamp modifytime;
	private Integer userid;
	private String lanType;
	private Integer comId;

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
			String name, Timestamp maketime, Timestamp modifytime,
			Integer userid, String lanType, Integer comId) {
		this.classificationid = classificationid;
		this.parentid = parentid;
		this.name = name;
		this.maketime = maketime;
		this.modifytime = modifytime;
		this.userid = userid;
		this.lanType = lanType;
		this.comId = comId;
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

	public Timestamp getMaketime() {
		return this.maketime;
	}

	public void setMaketime(Timestamp maketime) {
		this.maketime = maketime;
	}

	public Timestamp getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getLanType() {
		return this.lanType;
	}

	public void setLanType(String lanType) {
		this.lanType = lanType;
	}

	public Integer getComId() {
		return this.comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

}