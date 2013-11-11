package com.sbgl.app.entity;

import java.util.Date;

/**
 * Equipmentnum entity. @author MyEclipse Persistence Tools
 */

public class Equipmentnum extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipmentnumid;
	private Date enumdate;
	private Integer equipmentid;
	private Integer borrownum;

	// Constructors

	/** default constructor */
	public Equipmentnum() {
	}

	/** minimal constructor */
	public Equipmentnum(Integer equipmentnumid) {
		this.equipmentnumid = equipmentnumid;
	}

	/** full constructor */
	public Equipmentnum(Integer equipmentnumid, Date enumdate,
			Integer equipmentid, Integer borrownum) {
		this.equipmentnumid = equipmentnumid;
		this.enumdate = enumdate;
		this.equipmentid = equipmentid;
		this.borrownum = borrownum;
	}

	// Property accessors

	public Integer getEquipmentnumid() {
		return this.equipmentnumid;
	}

	public void setEquipmentnumid(Integer equipmentnumid) {
		this.equipmentnumid = equipmentnumid;
	}

	public Date getEnumdate() {
		return this.enumdate;
	}

	public void setEnumdate(Date enumdate) {
		this.enumdate = enumdate;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public Integer getBorrownum() {
		return this.borrownum;
	}

	public void setBorrownum(Integer borrownum) {
		this.borrownum = borrownum;
	}

}
