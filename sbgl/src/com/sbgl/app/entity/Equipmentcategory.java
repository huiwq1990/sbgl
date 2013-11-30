package com.sbgl.app.entity;

/**
 * Equipmentcategory entity. @author MyEclipse Persistence Tools
 */

public class Equipmentcategory extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipcategoryid;
	private Integer equipmentid;
	private Integer categoryid;

	// Constructors

	/** default constructor */
	public Equipmentcategory() {
	}

	/** minimal constructor */
	public Equipmentcategory(Integer equipcategoryid) {
		this.equipcategoryid = equipcategoryid;
	}

	/** full constructor */
	public Equipmentcategory(Integer equipcategoryid, Integer equipmentid,
			Integer categoryid) {
		this.equipcategoryid = equipcategoryid;
		this.equipmentid = equipmentid;
		this.categoryid = categoryid;
	}

	// Property accessors

	public Integer getEquipcategoryid() {
		return this.equipcategoryid;
	}

	public void setEquipcategoryid(Integer equipcategoryid) {
		this.equipcategoryid = equipcategoryid;
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

}
