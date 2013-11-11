package com.sbgl.app.actions.order;

import java.util.Date;

import com.sbgl.app.dao.DaoAbs;

public class EquipmentFull extends DaoAbs {
	private Long equipmentid;
	private String equipmentname;
	private Long brandid;
	private Long classificationid;
	private Long administrationid;
	private Date makedate;
	private Date modifydate;
	private Long equipmentnum;
	private Long activenum;
	private Long maintainnum;
	private Long repairnum;
	private Long losednum;
	private Long recyclingnum;
	private String equipmentdetail;
	private Long category;
	private String remark;
	
	private String borrownum;  //可借数量
	private String  categoryName; //分类名称

	public Long getEquipmentid() {
		return equipmentid;
	}

	public void setEquipmentid(Long equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public Long getBrandid() {
		return brandid;
	}

	public void setBrandid(Long brandid) {
		this.brandid = brandid;
	}

	public Long getClassificationid() {
		return classificationid;
	}

	public void setClassificationid(Long classificationid) {
		this.classificationid = classificationid;
	}

	public Long getAdministrationid() {
		return administrationid;
	}

	public void setAdministrationid(Long administrationid) {
		this.administrationid = administrationid;
	}

	public Date getMakedate() {
		return makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public Long getEquipmentnum() {
		return equipmentnum;
	}

	public void setEquipmentnum(Long equipmentnum) {
		this.equipmentnum = equipmentnum;
	}

	public Long getActivenum() {
		return activenum;
	}

	public void setActivenum(Long activenum) {
		this.activenum = activenum;
	}

	public Long getMaintainnum() {
		return maintainnum;
	}

	public void setMaintainnum(Long maintainnum) {
		this.maintainnum = maintainnum;
	}

	public Long getRepairnum() {
		return repairnum;
	}

	public void setRepairnum(Long repairnum) {
		this.repairnum = repairnum;
	}

	public Long getLosednum() {
		return losednum;
	}

	public void setLosednum(Long losednum) {
		this.losednum = losednum;
	}

	public Long getRecyclingnum() {
		return recyclingnum;
	}

	public void setRecyclingnum(Long recyclingnum) {
		this.recyclingnum = recyclingnum;
	}

	public String getEquipmentdetail() {
		return equipmentdetail;
	}

	public void setEquipmentdetail(String equipmentdetail) {
		this.equipmentdetail = equipmentdetail;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBorrownum() {
		return borrownum;
	}

	public void setBorrownum(String borrownum) {
		this.borrownum = borrownum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
