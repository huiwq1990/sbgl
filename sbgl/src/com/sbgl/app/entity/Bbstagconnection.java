package com.sbgl.app.entity;

/**
 * Bbstagconnection entity. @author MyEclipse Persistence Tools
 */

public class Bbstagconnection extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer tagid;
	private Integer bbsid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Bbstagconnection() {
	}

	/** minimal constructor */
	public Bbstagconnection(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Bbstagconnection(Integer id, Integer tagid, Integer bbsid,
			Integer status) {
		this.id = id;
		this.tagid = tagid;
		this.bbsid = bbsid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTagid() {
		return this.tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	public Integer getBbsid() {
		return this.bbsid;
	}

	public void setBbsid(Integer bbsid) {
		this.bbsid = bbsid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
