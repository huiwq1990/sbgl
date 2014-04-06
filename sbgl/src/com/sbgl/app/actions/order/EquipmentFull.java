package com.sbgl.app.actions.order;

import java.util.Date;
import java.util.List;

import com.sbgl.app.dao.DaoAbs;

public class EquipmentFull extends DaoAbs {
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
	
	private Long borrownum;  //可借数量
	private String categoryName; //分类名称
	private String daynum;   
	private Integer applynumber;
	private String equipDetailids;
	private Integer listdetailid;
	private List<String> equipDetailidlist;
	private Integer borrownumber;
	
	
	public Integer getActivenum() {
		return activenum;
	}
	public void setActivenum(Integer activenum) {
		this.activenum = activenum;
	}
	public Integer getAdministrationid() {
		return administrationid;
	}
	public void setAdministrationid(Integer administrationid) {
		this.administrationid = administrationid;
	}
	
	public Integer getBrandid() {
		return brandid;
	}
	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getClassificationid() {
		return classificationid;
	}
	public void setClassificationid(Integer classificationid) {
		this.classificationid = classificationid;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public String getEquipmentdetail() {
		return equipmentdetail;
	}
	public void setEquipmentdetail(String equipmentdetail) {
		this.equipmentdetail = equipmentdetail;
	}
	public Integer getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public Integer getEquipmentnum() {
		return equipmentnum;
	}
	public void setEquipmentnum(Integer equipmentnum) {
		this.equipmentnum = equipmentnum;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgNameSaved() {
		return imgNameSaved;
	}
	public void setImgNameSaved(String imgNameSaved) {
		this.imgNameSaved = imgNameSaved;
	}
	public String getLanType() {
		return lanType;
	}
	public void setLanType(String lanType) {
		this.lanType = lanType;
	}
	public Integer getLosednum() {
		return losednum;
	}
	public void setLosednum(Integer losednum) {
		this.losednum = losednum;
	}
	public Integer getMaintainnum() {
		return maintainnum;
	}
	public void setMaintainnum(Integer maintainnum) {
		this.maintainnum = maintainnum;
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
	public Integer getRecyclingnum() {
		return recyclingnum;
	}
	public void setRecyclingnum(Integer recyclingnum) {
		this.recyclingnum = recyclingnum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRepairnum() {
		return repairnum;
	}
	public void setRepairnum(Integer repairnum) {
		this.repairnum = repairnum;
	}
	public Long getBorrownum() {
		return borrownum;
	}
	public void setBorrownum(Long borrownum) {
		this.borrownum = borrownum;
	}
	public String getDaynum() {
		return daynum;
	}
	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}
	public Integer getApplynumber() {
		return applynumber;
	}
	public void setApplynumber(Integer applynumber) {
		this.applynumber = applynumber;
	}
	public String getEquipDetailids() {
		return equipDetailids;
	}
	public void setEquipDetailids(String equipDetailids) {
		this.equipDetailids = equipDetailids;
	}
	public List<String> getEquipDetailidlist() {
		return equipDetailidlist;
	}
	public void setEquipDetailidlist(List<String> equipDetailidlist) {
		this.equipDetailidlist = equipDetailidlist;
	}
	public Integer getListdetailid() {
		return listdetailid;
	}
	public void setListdetailid(Integer listdetailid) {
		this.listdetailid = listdetailid;
	}
	public Integer getBorrownumber() {
		return borrownumber;
	}
	public void setBorrownumber(Integer borrownumber) {
		this.borrownumber = borrownumber;
	}
	

	
}
