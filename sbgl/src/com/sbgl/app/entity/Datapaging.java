package com.sbgl.app.entity;

/**
 * Datapaging entity. @author MyEclipse Persistence Tools
 */

public class Datapaging extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer datapagingid;
	private Integer tableid;
	private Integer recordnum;

	// Constructors

	/** default constructor */
	public Datapaging() {
	}

	/** minimal constructor */
	public Datapaging(Integer datapagingid) {
		this.datapagingid = datapagingid;
	}

	/** full constructor */
	public Datapaging(Integer datapagingid, Integer tableid, Integer recordnum) {
		this.datapagingid = datapagingid;
		this.tableid = tableid;
		this.recordnum = recordnum;
	}

	// Property accessors

	public Integer getDatapagingid() {
		return this.datapagingid;
	}

	public void setDatapagingid(Integer datapagingid) {
		this.datapagingid = datapagingid;
	}

	public Integer getTableid() {
		return this.tableid;
	}

	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}

	public Integer getRecordnum() {
		return this.recordnum;
	}

	public void setRecordnum(Integer recordnum) {
		this.recordnum = recordnum;
	}

}
