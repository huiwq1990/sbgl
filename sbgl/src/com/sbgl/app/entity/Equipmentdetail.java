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
	private Date makedate;
	private Date modifydate;
	private String sysremark;
	private String usermark;
	private Date manufactureDate;
	private Date acquireDate;
	private String manufacturer;
	private String supplyer;
	private Float worth;
	private String useManageDept;
	private String manager;
	private String storagePlace;
	private String storagePosition;
	private String lanType;
	private Integer comId;
	private Integer storenumber;
	private String equipserial;
	private Integer assetNumber;

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
			String supplyer, Float worth, String useManageDept, String manager,
			String storagePlace, String storagePosition, String lanType,
			Integer comId, Integer storenumber, String equipserial,
			Integer assetNumber) {
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
		this.useManageDept = useManageDept;
		this.manager = manager;
		this.storagePlace = storagePlace;
		this.storagePosition = storagePosition;
		this.lanType = lanType;
		this.comId = comId;
		this.storenumber = storenumber;
		this.equipserial = equipserial;
		this.assetNumber = assetNumber;
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

	public String getUseManageDept() {
		return this.useManageDept;
	}

	public void setUseManageDept(String useManageDept) {
		this.useManageDept = useManageDept;
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

	public String getStoragePosition() {
		return this.storagePosition;
	}

	public void setStoragePosition(String storagePosition) {
		this.storagePosition = storagePosition;
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

	public Integer getStorenumber() {
		return this.storenumber;
	}

	public void setStorenumber(Integer storenumber) {
		this.storenumber = storenumber;
	}

	public String getEquipserial() {
		return this.equipserial;
	}

	public void setEquipserial(String equipserial) {
		this.equipserial = equipserial;
	}

	public Integer getAssetNumber() {
		return this.assetNumber;
	}

	public void setAssetNumber(Integer assetNumber) {
		this.assetNumber = assetNumber;
	}

}