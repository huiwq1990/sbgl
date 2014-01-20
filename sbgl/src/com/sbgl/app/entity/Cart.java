package com.sbgl.app.entity;

/**
 * Cart entity. @author MyEclipse Persistence Tools
 */

public class Cart extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer equipmentId;
	private Integer equipmentNum;
	private String userid;

	// Constructors

	/** default constructor */
	public Cart() {
	}

	/** minimal constructor */
	public Cart(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Cart(Integer id, Integer equipmentId, Integer equipmentNum,
			String userid) {
		this.id = id;
		this.equipmentId = equipmentId;
		this.equipmentNum = equipmentNum;
		this.userid = userid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getEquipmentNum() {
		return this.equipmentNum;
	}

	public void setEquipmentNum(Integer equipmentNum) {
		this.equipmentNum = equipmentNum;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
