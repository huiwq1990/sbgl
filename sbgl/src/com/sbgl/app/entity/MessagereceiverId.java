package com.sbgl.app.entity;

/**
 * MessagereceiverId entity. @author MyEclipse Persistence Tools
 */

public class MessagereceiverId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer messageid;
	private Integer receiverid;
	private Integer status;

	// Constructors

	/** default constructor */
	public MessagereceiverId() {
	}

	/** full constructor */
	public MessagereceiverId(Integer id, Integer messageid, Integer receiverid,
			Integer status) {
		this.id = id;
		this.messageid = messageid;
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

	public Integer getMessageid() {
		return this.messageid;
	}

	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MessagereceiverId))
			return false;
		MessagereceiverId castOther = (MessagereceiverId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getMessageid() == castOther.getMessageid()) || (this
						.getMessageid() != null
						&& castOther.getMessageid() != null && this
						.getMessageid().equals(castOther.getMessageid())))
				&& ((this.getReceiverid() == castOther.getReceiverid()) || (this
						.getReceiverid() != null
						&& castOther.getReceiverid() != null && this
						.getReceiverid().equals(castOther.getReceiverid())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getMessageid() == null ? 0 : this.getMessageid().hashCode());
		result = 37
				* result
				+ (getReceiverid() == null ? 0 : this.getReceiverid()
						.hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		return result;
	}

}
