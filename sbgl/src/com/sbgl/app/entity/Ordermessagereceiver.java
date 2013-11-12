package com.sbgl.app.entity;

/**
 * Ordermessagereceiver entity. @author MyEclipse Persistence Tools
 */

public class Ordermessagereceiver extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer ordermessageid;
	private Integer receiverid;
	private Integer status;

	// Constructors

	/** default constructor */
	public Ordermessagereceiver() {
	}

	/** minimal constructor */
	public Ordermessagereceiver(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Ordermessagereceiver(Integer id, Integer ordermessageid,
			Integer receiverid, Integer status) {
		this.id = id;
		this.ordermessageid = ordermessageid;
		this.receiverid = receiverid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrdermessageid() {
		return this.ordermessageid;
	}

	public void setOrdermessageid(Integer ordermessageid) {
		this.ordermessageid = ordermessageid;
	}

	public Integer getReceiverid() {
		return this.receiverid;
	}

	public void setReceiverid(Integer receiverid) {
		this.receiverid = receiverid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
