package com.sbgl.app.entity;

/**
 * GroupofequipmentId entity. @author MyEclipse Persistence Tools
 */

public class GroupofequipmentId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer equipmentgroupid;
	private Integer equipmentid;

	// Constructors

	/** default constructor */
	public GroupofequipmentId() {
	}

	/** full constructor */
	public GroupofequipmentId(Integer equipmentgroupid, Integer equipmentid) {
		this.equipmentgroupid = equipmentgroupid;
		this.equipmentid = equipmentid;
	}

	// Property accessors

	public Integer getEquipmentgroupid() {
		return this.equipmentgroupid;
	}

	public void setEquipmentgroupid(Integer equipmentgroupid) {
		this.equipmentgroupid = equipmentgroupid;
	}

	public Integer getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GroupofequipmentId))
			return false;
		GroupofequipmentId castOther = (GroupofequipmentId) other;

		return ((this.getEquipmentgroupid() == castOther.getEquipmentgroupid()) || (this
				.getEquipmentgroupid() != null
				&& castOther.getEquipmentgroupid() != null && this
				.getEquipmentgroupid().equals(castOther.getEquipmentgroupid())))
				&& ((this.getEquipmentid() == castOther.getEquipmentid()) || (this
						.getEquipmentid() != null
						&& castOther.getEquipmentid() != null && this
						.getEquipmentid().equals(castOther.getEquipmentid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEquipmentgroupid() == null ? 0 : this
						.getEquipmentgroupid().hashCode());
		result = 37
				* result
				+ (getEquipmentid() == null ? 0 : this.getEquipmentid()
						.hashCode());
		return result;
	}

}