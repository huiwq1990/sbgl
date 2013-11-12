package com.sbgl.app.entity;

/**
 * Bbsshare entity. @author MyEclipse Persistence Tools
 */

public class Bbsshare extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String userId;
	private Integer bbsId;
	private Integer status;

	// Constructors

	/** default constructor */
	public Bbsshare() {
	}

	/** minimal constructor */
	public Bbsshare(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Bbsshare(Integer id, String userId, Integer bbsId, Integer status) {
		this.id = id;
		this.userId = userId;
		this.bbsId = bbsId;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getBbsId() {
		return this.bbsId;
	}

	public void setBbsId(Integer bbsId) {
		this.bbsId = bbsId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
