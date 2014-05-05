package com.sbgl.app.entity;

import java.sql.Timestamp;

/**
 * Ordercourseruledetail entity. @author MyEclipse Persistence Tools
 */

public class Ordercourseruledetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer courseruledetailid;
	private Integer courseruleid;
	private Integer borrowlistid;
	private Integer equipmentid;
	private Integer applynumber;
	private Timestamp starttime;
	private Timestamp endtime;
	private Integer comid;
	private String lantype;
	private Integer isgroup;

	// Constructors

	/** default constructor */
	public Ordercourseruledetail() {
	}

	/** minimal constructor */
	public Ordercourseruledetail(Integer courseruledetailid,
			Integer courseruleid) {
		this.courseruledetailid = courseruledetailid;
		this.courseruleid = courseruleid;
	}

	/** full constructor */
	public Ordercourseruledetail(Integer courseruledetailid,
			Integer courseruleid, Integer borrowlistid, Integer equipmentid,
			Integer applynumber, Timestamp starttime, Timestamp endtime,
			Integer comid, String lantype, Integer isgroup) {
		this.courseruledetailid = courseruledetailid;
		this.courseruleid = courseruleid;
		this.borrowlistid = borrowlistid;
		this.equipmentid = equipmentid;
		this.applynumber = applynumber;
		this.starttime = starttime;
		this.endtime = endtime;
		this.comid = comid;
		this.lantype = lantype;
		this.isgroup = isgroup;
	}

	// Property accessors

	public Integer getCourseruledetailid() {
		return this.courseruledetailid;
	}

	public void setCourseruledetailid(Integer courseruledetailid) {
		this.courseruledetailid = courseruledetailid;
	}

	public Integer getCourseruleid() {
		return this.courseruleid;
	}

	public void setCourseruleid(Integer courseruleid) {
		this.courseruleid = courseruleid;
	}

	public Integer getBorrowlistid() {
		return this.borrowlistid;
	}

	public void setBorrowlistid(Integer borrowlistid) {
		this.borrowlistid = borrowlistid;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public Integer getApplynumber() {
		return this.applynumber;
	}

	public void setApplynumber(Integer applynumber) {
		this.applynumber = applynumber;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Integer getComid() {
		return this.comid;
	}

	public void setComid(Integer comid) {
		this.comid = comid;
	}

	public String getLantype() {
		return this.lantype;
	}

	public void setLantype(String lantype) {
		this.lantype = lantype;
	}

	public Integer getIsgroup() {
		return this.isgroup;
	}

	public void setIsgroup(Integer isgroup) {
		this.isgroup = isgroup;
	}

}