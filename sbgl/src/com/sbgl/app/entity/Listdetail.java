package com.sbgl.app.entity;

import java.util.Date;

/**
 * Listdetail entity. @author MyEclipse Persistence Tools
 */

public class Listdetail extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer listdetailid;
	private Integer borrowlistid;
	private Integer equipmentid;
	private Integer applynumber;
	private Integer borrownumber;
	private Date borrowtime;
	private Date returntime;
	private String ifdelay;
	private Integer comid;
	private String lantype;
	private String equipdetailids;

	// Constructors

	/** default constructor */
	public Listdetail() {
	}

	/** minimal constructor */
	public Listdetail(Integer listdetailid) {
		this.listdetailid = listdetailid;
	}

	/** full constructor */
	public Listdetail(Integer listdetailid, Integer borrowlistid,
			Integer equipmentid, Integer applynumber, Integer borrownumber,
			Date borrowtime, Date returntime,String ifdelay,Integer comid,String lantype,String equipdetailids) {
		this.listdetailid = listdetailid;
		this.borrowlistid = borrowlistid;
		this.equipmentid = equipmentid;
		this.applynumber = applynumber;
		this.borrownumber = borrownumber;
		this.borrowtime = borrowtime;
		this.returntime = returntime;
		this.ifdelay = ifdelay;
		this.comid = comid;
		this.lantype = lantype;
		this.equipdetailids = equipdetailids;
	}

	// Property accessors

	public Integer getListdetailid() {
		return this.listdetailid;
	}

	public void setListdetailid(Integer listdetailid) {
		this.listdetailid = listdetailid;
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

	public Integer getBorrownumber() {
		return this.borrownumber;
	}

	public void setBorrownumber(Integer borrownumber) {
		this.borrownumber = borrownumber;
	}

	public Date getBorrowtime() {
		return this.borrowtime;
	}

	public void setBorrowtime(Date borrowtime) {
		this.borrowtime = borrowtime;
	}

	public Date getReturntime() {
		return this.returntime;
	}

	public void setReturntime(Date returntime) {
		this.returntime = returntime;
	}

	public String getIfdelay() {
		return ifdelay;
	}

	public void setIfdelay(String ifdelay) {
		this.ifdelay = ifdelay;
	}

	public Integer getComid() {
		return comid;
	}

	public void setComid(Integer comid) {
		this.comid = comid;
	}

	public String getLantype() {
		return lantype;
	}

	public void setLantype(String lantype) {
		this.lantype = lantype;
	}

	public String getEquipdetailids() {
		return equipdetailids;
	}

	public void setEquipdetailids(String equipdetailids) {
		this.equipdetailids = equipdetailids;
	}

}
