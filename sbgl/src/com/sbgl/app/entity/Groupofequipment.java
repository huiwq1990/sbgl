package com.sbgl.app.entity;

/**
 * Groupofequipment entity. @author MyEclipse Persistence Tools
 */

public class Groupofequipment extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private GroupofequipmentId id;
	private Integer num;

	// Constructors

	/** default constructor */
	public Groupofequipment() {
	}

	/** minimal constructor */
	public Groupofequipment(GroupofequipmentId id) {
		this.id = id;
	}

	/** full constructor */
	public Groupofequipment(GroupofequipmentId id, Integer num) {
		this.id = id;
		this.num = num;
	}

	// Property accessors

	public GroupofequipmentId getId() {
		return this.id;
	}

	public void setId(GroupofequipmentId id) {
		this.id = id;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}