package com.sbgl.app.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Equipmentdetail entity. @author MyEclipse Persistence Tools
 */

public class Equipmentdetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipDetailid;
	private String equipserial;
	private Integer equipmentid;
	private String status;
	private Integer administrationid;
	private Date makedate;
	private Date modifydate;
	private String sysremark;
	private String usermark;

	// Constructors

	/** default constructor */
	public Equipmentdetail() {
	}

	/** minimal constructor */
	public Equipmentdetail(Integer equipDetailid) {
		this.equipDetailid = equipDetailid;
	}

	/** full constructor */
	public Equipmentdetail(Integer equipDetailid, String equipserial,
			Integer equipmentid, String status, Integer administrationid,
			Timestamp makedate, Timestamp modifydate, String sysremark,
			String usermark) {
		this.equipDetailid = equipDetailid;
		this.equipserial = equipserial;
		this.equipmentid = equipmentid;
		this.status = status;
		this.administrationid = administrationid;
		this.makedate = makedate;
		this.modifydate = modifydate;
		this.sysremark = sysremark;
		this.usermark = usermark;
	}

	// Property accessors

	public Integer getEquipDetailid() {
		return this.equipDetailid;
	}

	public void setEquipDetailid(Integer equipDetailid) {
		this.equipDetailid = equipDetailid;
	}

	public String getEquipserial() {
		return this.equipserial;
	}

	public void setEquipserial(String equipserial) {
		this.equipserial = equipserial;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAdministrationid() {
		return this.administrationid;
	}

	public void setAdministrationid(Integer administrationid) {
		this.administrationid = administrationid;
	}

	public Date getMakedate() {
		return this.makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getSysremark() {
		return this.sysremark;
	}

	public void setSysremark(String sysremark) {
		this.sysremark = sysremark;
	}

	public String getUsermark() {
		return this.usermark;
	}

	public void setUsermark(String usermark) {
		this.usermark = usermark;
	}

}