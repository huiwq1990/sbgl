package com.sbgl.app.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Equipment entity. @author MyEclipse Persistence Tools
 */

public class Equipment extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipmentid;
	private String equipmentname;
	private Integer brandid;
	private Integer classificationid;
	private Integer administrationid;
	private Date makedate;
	private Date modifydate;
	private Integer equipmentnum;
	private Integer activenum;
	private Integer maintainnum;
	private Integer repairnum;
	private Integer losednum;
	private Integer recyclingnum;
	private String equipmentdetail;
	private Integer category;
	private String remark;
	private String lanType;
	private Integer comId;
	private String imgNameSaved;
	private String imgName;

	// Constructors

	/** default constructor */
	public Equipment() {
	}

	/** minimal constructor */
	public Equipment(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	/** full constructor */
	public Equipment(Integer equipmentid, String equipmentname,
			Integer brandid, Integer classificationid,
			Integer administrationid, Timestamp makedate, Timestamp modifydate,
			Integer equipmentnum, Integer activenum, Integer maintainnum,
			Integer repairnum, Integer losednum, Integer recyclingnum,
			String equipmentdetail, Integer category, String remark,
			String lanType, Integer comId, String imgNameSaved, String imgName) {
		this.equipmentid = equipmentid;
		this.equipmentname = equipmentname;
		this.brandid = brandid;
		this.classificationid = classificationid;
		this.administrationid = administrationid;
		this.makedate = makedate;
		this.modifydate = modifydate;
		this.equipmentnum = equipmentnum;
		this.activenum = activenum;
		this.maintainnum = maintainnum;
		this.repairnum = repairnum;
		this.losednum = losednum;
		this.recyclingnum = recyclingnum;
		this.equipmentdetail = equipmentdetail;
		this.category = category;
		this.remark = remark;
		this.lanType = lanType;
		this.comId = comId;
		this.imgNameSaved = imgNameSaved;
		this.imgName = imgName;
	}

	// Property accessors

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
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

	public Integer getEquipmentnum() {
		return this.equipmentnum;
	}

	public void setEquipmentnum(Integer equipmentnum) {
		this.equipmentnum = equipmentnum;
	}

	public Integer getActivenum() {
		return this.activenum;
	}

	public void setActivenum(Integer activenum) {
		this.activenum = activenum;
	}

	public Integer getMaintainnum() {
		return this.maintainnum;
	}

	public void setMaintainnum(Integer maintainnum) {
		this.maintainnum = maintainnum;
	}

	public Integer getRepairnum() {
		return this.repairnum;
	}

	public void setRepairnum(Integer repairnum) {
		this.repairnum = repairnum;
	}

	public Integer getLosednum() {
		return this.losednum;
	}

	public void setLosednum(Integer losednum) {
		this.losednum = losednum;
	}

	public Integer getRecyclingnum() {
		return this.recyclingnum;
	}

	public void setRecyclingnum(Integer recyclingnum) {
		this.recyclingnum = recyclingnum;
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

	public String getImgNameSaved() {
		return this.imgNameSaved;
	}

	public void setImgNameSaved(String imgNameSaved) {
		this.imgNameSaved = imgNameSaved;
	}

	public String getImgName() {
		return this.imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

}