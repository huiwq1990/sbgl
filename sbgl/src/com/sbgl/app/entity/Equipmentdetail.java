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
	private Integer equipmentid;
	private String status;
	private Integer administrationid;
	private Timestamp makedate;
	private Timestamp modifydate;
	private String sysremark;
	private String usermark;
	private Date manufactureDate;
	private Date acquireDate;
	private String manufacturer;
	private String supplyer;
	private Float worth;
	private String manageDept;
	private String manager;
	private String storagePlace;
	private String lanType;
	private Integer comId;
	private Integer equipserial;

	// Constructors

	/** default constructor */
	public Equipmentdetail() {
	}

	/** minimal constructor */
	public Equipmentdetail(Integer equipDetailid) {
		this.equipDetailid = equipDetailid;
	}

	/** full constructor */
	public Equipmentdetail(Integer equipDetailid, Integer equipmentid,
			String status, Integer administrationid, Timestamp makedate,
			Timestamp modifydate, String sysremark, String usermark,
			Date manufactureDate, Date acquireDate, String manufacturer,
			String supplyer, Float worth, String manageDept, String manager,
			String storagePlace, String lanType, Integer comId,
			Integer equipserial) {
		this.equipDetailid = equipDetailid;
		this.equipmentid = equipmentid;
		this.status = status;
		this.administrationid = administrationid;
		this.makedate = makedate;
		this.modifydate = modifydate;
		this.sysremark = sysremark;
		this.usermark = usermark;
		this.manufactureDate = manufactureDate;
		this.acquireDate = acquireDate;
		this.manufacturer = manufacturer;
		this.supplyer = supplyer;
		this.worth = worth;
		this.manageDept = manageDept;
		this.manager = manager;
		this.storagePlace = storagePlace;
		this.lanType = lanType;
		this.comId = comId;
		this.equipserial = equipserial;
	}

	// Property accessors

	public Integer getEquipDetailid() {
		return this.equipDetailid;
	}

	public void setEquipDetailid(Integer equipDetailid) {
		this.equipDetailid = equipDetailid;
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

	public Timestamp getMakedate() {
		return this.makedate;
	}

	public void setMakedate(Timestamp makedate) {
		this.makedate = makedate;
	}

	public Timestamp getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Timestamp modifydate) {
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

	public Date getManufactureDate() {
		return this.manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

	public Date getAcquireDate() {
		return this.acquireDate;
	}

	public void setAcquireDate(Date acquireDate) {
		this.acquireDate = acquireDate;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSupplyer() {
		return this.supplyer;
	}

	public void setSupplyer(String supplyer) {
		this.supplyer = supplyer;
	}

	public Float getWorth() {
		return this.worth;
	}

	public void setWorth(Float worth) {
		this.worth = worth;
	}

	public String getManageDept() {
		return this.manageDept;
	}

	public void setManageDept(String manageDept) {
		this.manageDept = manageDept;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStoragePlace() {
		return this.storagePlace;
	}

	public void setStoragePlace(String storagePlace) {
		this.storagePlace = storagePlace;
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

	public Integer getEquipserial() {
		return this.equipserial;
	}

	public void setEquipserial(Integer equipserial) {
		this.equipserial = equipserial;
	}

}