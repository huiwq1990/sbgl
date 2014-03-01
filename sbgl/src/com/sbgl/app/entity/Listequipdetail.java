package com.sbgl.app.entity;

/**
 * Listequipdetail entity. @author MyEclipse Persistence Tools
 */

public class Listequipdetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer listequipdetailid;
	private Integer listdetailid;
	private Integer borrowlistid;
	private Integer equipmentid;
	private String equipstatus;
	private Integer equipdetailid;

	// Constructors

	/** default constructor */
	public Listequipdetail() {
	}

	/** minimal constructor */
	public Listequipdetail(Integer listequipdetailid) {
		this.listequipdetailid = listequipdetailid;
	}

	/** full constructor */
	public Listequipdetail(Integer listequipdetailid, Integer listdetailid,
			Integer borrowlistid, Integer equipmentid, String equipstatus,
			Integer equipdetailid) {
		this.listequipdetailid = listequipdetailid;
		this.listdetailid = listdetailid;
		this.borrowlistid = borrowlistid;
		this.equipmentid = equipmentid;
		this.equipstatus = equipstatus;
		this.equipdetailid = equipdetailid;
	}

	// Property accessors

	public Integer getListequipdetailid() {
		return this.listequipdetailid;
	}

	public void setListequipdetailid(Integer listequipdetailid) {
		this.listequipdetailid = listequipdetailid;
	}

	public Integer getListdetailid() {
		return this.listdetailid;
	}

	public void setListdetailid(Integer listdetailid) {
		this.listdetailid = listdetailid;
	}

	public Integer getBorrowlistid() {
		return this.borrowlistid;
	}

	public void setBorrowlistid(Integer borrowlistid) {
		this.borrowlistid = borrowlistid;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getEquipstatus() {
		return this.equipstatus;
	}

	public void setEquipstatus(String equipstatus) {
		this.equipstatus = equipstatus;
	}

	public Integer getEquipdetailid() {
		return this.equipdetailid;
	}

	public void setEquipdetailid(Integer equipdetailid) {
		this.equipdetailid = equipdetailid;
	}

}