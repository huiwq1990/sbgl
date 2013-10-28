package com.sbgl.app.entity;

/**
 * EquipmenborrowId entity. @author MyEclipse Persistence Tools
 */

public class EquipmenborrowId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer borrowid;
	private Integer category;

	// Constructors

	/** default constructor */
	public EquipmenborrowId() {
	}

	/** full constructor */
	public EquipmenborrowId(Integer borrowid, Integer category) {
		this.borrowid = borrowid;
		this.category = category;
	}

	// Property accessors

	public Integer getBorrowid() {
		return this.borrowid;
	}

	public void setBorrowid(Integer borrowid) {
		this.borrowid = borrowid;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EquipmenborrowId))
			return false;
		EquipmenborrowId castOther = (EquipmenborrowId) other;

		return ((this.getBorrowid() == castOther.getBorrowid()) || (this
				.getBorrowid() != null && castOther.getBorrowid() != null && this
				.getBorrowid().equals(castOther.getBorrowid())))
				&& ((this.getCategory() == castOther.getCategory()) || (this
						.getCategory() != null
						&& castOther.getCategory() != null && this
						.getCategory().equals(castOther.getCategory())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBorrowid() == null ? 0 : this.getBorrowid().hashCode());
		result = 37 * result
				+ (getCategory() == null ? 0 : this.getCategory().hashCode());
		return result;
	}

}
