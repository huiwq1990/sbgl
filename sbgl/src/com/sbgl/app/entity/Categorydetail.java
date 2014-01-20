package com.sbgl.app.entity;

/**
 * Categorydetail entity. @author MyEclipse Persistence Tools
 */

public class Categorydetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer categorydetailsid;
	private Integer equipmentid;
	private Integer categoryid;
	private String userid;

	// Constructors

	/** default constructor */
	public Categorydetail() {
	}

	/** minimal constructor */
	public Categorydetail(Integer categorydetailsid) {
		this.categorydetailsid = categorydetailsid;
	}

	/** full constructor */
	public Categorydetail(Integer categorydetailsid, Integer equipmentid,
			Integer categoryid, String userid) {
		this.categorydetailsid = categorydetailsid;
		this.equipmentid = equipmentid;
		this.categoryid = categoryid;
		this.userid = userid;
	}

	// Property accessors

	public Integer getCategorydetailsid() {
		return this.categorydetailsid;
	}

	public void setCategorydetailsid(Integer categorydetailsid) {
		this.categorydetailsid = categorydetailsid;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public Integer getCategoryid() {
		return this.categoryid;
	}

	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
