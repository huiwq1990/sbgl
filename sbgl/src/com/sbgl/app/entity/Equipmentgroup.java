package com.sbgl.app.entity;

import java.sql.Timestamp;

/**
 * Equipmentgroup entity. @author MyEclipse Persistence Tools
 */

public class Equipmentgroup extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipmentgroupid;
	private String equipmentname;
	private Integer brandid;
	private Integer classificationid;
	private Integer administrationid;
	private Timestamp makedate;
	private Timestamp modifydate;
	private String equipmentdetail;
	private Integer category;
	private String remark;
	private String lantype;
	private Integer comid;
	private String imgnamesaved;
	private String imgname;

	// Constructors

	/** default constructor */
	public Equipmentgroup() {
	}

	/** minimal constructor */
	public Equipmentgroup(Integer equipmentgroupid) {
		this.equipmentgroupid = equipmentgroupid;
	}

	/** full constructor */
	public Equipmentgroup(Integer equipmentgroupid, String equipmentname,
			Integer brandid, Integer classificationid,
			Integer administrationid, Timestamp makedate, Timestamp modifydate,
			String equipmentdetail, Integer category, String remark,
			String lantype, Integer comid, String imgnamesaved, String imgname) {
		this.equipmentgroupid = equipmentgroupid;
		this.equipmentname = equipmentname;
		this.brandid = brandid;
		this.classificationid = classificationid;
		this.administrationid = administrationid;
		this.makedate = makedate;
		this.modifydate = modifydate;
		this.equipmentdetail = equipmentdetail;
		this.category = category;
		this.remark = remark;
		this.lantype = lantype;
		this.comid = comid;
		this.imgnamesaved = imgnamesaved;
		this.imgname = imgname;
	}

	// Property accessors

	public Integer getEquipmentgroupid() {
		return this.equipmentgroupid;
	}

	public void setEquipmentgroupid(Integer equipmentgroupid) {
		this.equipmentgroupid = equipmentgroupid;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public Integer getBrandid() {
		return this.brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	public Integer getClassificationid() {
		return this.classificationid;
	}

	public void setClassificationid(Integer classificationid) {
		this.classificationid = classificationid;
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

	public String getEquipmentdetail() {
		return this.equipmentdetail;
	}

	public void setEquipmentdetail(String equipmentdetail) {
		this.equipmentdetail = equipmentdetail;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getImgnamesaved() {
		return this.imgnamesaved;
	}

	public void setImgnamesaved(String imgnamesaved) {
		this.imgnamesaved = imgnamesaved;
	}

	public String getImgname() {
		return this.imgname;
	}

	public void setImgname(String imgname) {
		this.imgname = imgname;
	}

}