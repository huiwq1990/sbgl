package com.sbgl.app.entity;

/**
 * Messagereceiver entity. @author MyEclipse Persistence Tools
 */

public class Messagereceiver extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private MessagereceiverId id;

	// Constructors

	/** default constructor */
	public Messagereceiver() {
	}

	/** full constructor */
	public Messagereceiver(MessagereceiverId id) {
		this.id = id;
	}

	// Property accessors

	public MessagereceiverId getId() {
		return this.id;
	}

	public void setId(MessagereceiverId id) {
		this.id = id;
	}

}
