package com.sbgl.app.actions.equipment.template;

import java.sql.Timestamp;

import com.sbgl.app.dao.DaoAbs;

public class EquipmentgroupFull extends DaoAbs  {
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
	private Integer applynumber;
	private String equipmentnameeng;
	private String intro;
	
	
	public Integer getEquipmentgroupid() {
		return equipmentgroupid;
	}
	public void setEquipmentgroupid(Integer equipmentgroupid) {
		this.equipmentgroupid = equipmentgroupid;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public Integer getBrandid() {
		return brandid;
	}
	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}
	public Integer getClassificationid() {
		return classificationid;
	}
	public void setClassificationid(Integer classificationid) {
		this.classificationid = classificationid;
	}
	public Integer getAdministrationid() {
		return administrationid;
	}
	public void setAdministrationid(Integer administrationid) {
		this.administrationid = administrationid;
	}
	public Timestamp getMakedate() {
		return makedate;
	}
	public void setMakedate(Timestamp makedate) {
		this.makedate = makedate;
	}
	public Timestamp getModifydate() {
		return modifydate;
	}
	public void setModifydate(Timestamp modifydate) {
		this.modifydate = modifydate;
	}
	public String getEquipmentdetail() {
		return equipmentdetail;
	}
	public void setEquipmentdetail(String equipmentdetail) {
		this.equipmentdetail = equipmentdetail;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLantype() {
		return lantype;
	}
	public void setLantype(String lantype) {
		this.lantype = lantype;
	}
	public Integer getComid() {
		return comid;
	}
	public void setComid(Integer comid) {
		this.comid = comid;
	}
	public String getImgnamesaved() {
		return imgnamesaved;
	}
	public void setImgnamesaved(String imgnamesaved) {
		this.imgnamesaved = imgnamesaved;
	}
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public Integer getApplynumber() {
		return applynumber;
	}
	public void setApplynumber(Integer applynumber) {
		this.applynumber = applynumber;
	}
	public String getEquipmentnameeng() {
		return equipmentnameeng;
	}
	public void setEquipmentnameeng(String equipmentnameeng) {
		this.equipmentnameeng = equipmentnameeng;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}
