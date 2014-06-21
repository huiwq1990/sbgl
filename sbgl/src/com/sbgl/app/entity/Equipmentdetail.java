package com.sbgl.app.entity;

import java.util.Date;

/**
 * Equipmentdetail entity. @author MyEclipse Persistence Tools
 */

public class Equipmentdetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipdetailid;
	private Integer equipmentid;
	private String status;
	private Integer administrationid;
	private Date makedate;
	private Date modifydate;
	private String sysremark;
	private String usermark;
	private Date manufacturedate;
	private Date acquiredate;
	private String manufacturer;
	private String supplyer;
	private Float worth;
	private String usemanagedept;
	private String manager;
	private String storageplace;
	private String storageposition;
	private String lantype;
	private Integer comid;
	private Integer storenumber;
	private String equipserial;
	private Integer assetnumber;
	private Integer classificationid;
	private String rent;

	// Constructors

	/** default constructor */
	public Equipmentdetail() {
	}

	/** minimal constructor */
	public Equipmentdetail(Integer equipdetailid) {
		this.equipdetailid = equipdetailid;
	}

	/** full constructor */
	public Equipmentdetail(Integer equipdetailid, Integer equipmentid,
			String status, Integer administrationid, Date makedate,
			Date modifydate, String sysremark, String usermark,
			Date manufacturedate, Date acquiredate, String manufacturer,
			String supplyer, Float worth, String usemanagedept, String manager,
			String storageplace, String storageposition, String lantype,
			Integer comid, Integer storenumber, String equipserial,
			Integer assetnumber, Integer classificationid, String rent) {
		this.equipdetailid = equipdetailid;
		this.equipmentid = equipmentid;
		this.status = status;
		this.administrationid = administrationid;
		this.makedate = makedate;
		this.modifydate = modifydate;
		this.sysremark = sysremark;
		this.usermark = usermark;
		this.manufacturedate = manufacturedate;
		this.acquiredate = acquiredate;
		this.manufacturer = manufacturer;
		this.supplyer = supplyer;
		this.worth = worth;
		this.usemanagedept = usemanagedept;
		this.manager = manager;
		this.storageplace = storageplace;
		this.storageposition = storageposition;
		this.lantype = lantype;
		this.comid = comid;
		this.storenumber = storenumber;
		this.equipserial = equipserial;
		this.assetnumber = assetnumber;
		this.classificationid = classificationid;
		this.rent = rent;
	}

	// Property accessors

	public Integer getEquipdetailid() {
		return this.equipdetailid;
	}

	public void setEquipdetailid(Integer equipdetailid) {
		this.equipdetailid = equipdetailid;
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

	public Date getManufacturedate() {
		return this.manufacturedate;
	}

	public void setManufacturedate(Date manufacturedate) {
		this.manufacturedate = manufacturedate;
	}

	public Date getAcquiredate() {
		return this.acquiredate;
	}

	public void setAcquiredate(Date acquiredate) {
		this.acquiredate = acquiredate;
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

	public String getUsemanagedept() {
		return this.usemanagedept;
	}

	public void setUsemanagedept(String usemanagedept) {
		this.usemanagedept = usemanagedept;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getStorageplace() {
		return this.storageplace;
	}

	public void setStorageplace(String storageplace) {
		this.storageplace = storageplace;
	}

	public String getStorageposition() {
		return this.storageposition;
	}

	public void setStorageposition(String storageposition) {
		this.storageposition = storageposition;
	}

	public String getLantype() {
		return this.lantype;
	}

	public void setLantype(String lantype) {
		this.lantype = lantype;
	}

	public Integer getComid() {
		return this.comid;
	}

	public void setComid(Integer comid) {
		this.comid = comid;
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

	public Integer getAssetnumber() {
		return this.assetnumber;
	}

	public void setAssetnumber(Integer assetnumber) {
		this.assetnumber = assetnumber;
	}

	public Integer getClassificationid() {
		return this.classificationid;
	}

	public void setClassificationid(Integer classificationid) {
		this.classificationid = classificationid;
	}

	public String getRent() {
		return this.rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}

}