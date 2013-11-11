package com.sbgl.app.entity;

/**
 * Orderdetail entity. @author MyEclipse Persistence Tools
 */

public class Orderdetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer equipmentid;
	private Integer applynumber;

	// Constructors

	/** default constructor */
	public Orderdetail() {
	}

	/** minimal constructor */
	public Orderdetail(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Orderdetail(Integer id, Integer equipmentid, Integer applynumber) {
		this.id = id;
		this.equipmentid = equipmentid;
		this.applynumber = applynumber;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public Integer getApplynumber() {
		return this.applynumber;
	}

	public void setApplynumber(Integer applynumber) {
		this.applynumber = applynumber;
	}

}
