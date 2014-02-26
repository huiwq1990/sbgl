package com.sbgl.app.entity;

/**
 * ComputerorderconfigId entity. @author MyEclipse Persistence Tools
 */

public class ComputerorderconfigId extends com.sbgl.app.dao.DaoAbs implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private String orderperiod;
	private Integer orderperiodnum;
	private Integer maxorderday;

	// Constructors

	/** default constructor */
	public ComputerorderconfigId() {
	}

	/** full constructor */
	public ComputerorderconfigId(Integer id, String orderperiod,
			Integer orderperiodnum, Integer maxorderday) {
		this.id = id;
		this.orderperiod = orderperiod;
		this.orderperiodnum = orderperiodnum;
		this.maxorderday = maxorderday;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderperiod() {
		return this.orderperiod;
	}

	public void setOrderperiod(String orderperiod) {
		this.orderperiod = orderperiod;
	}

	public Integer getOrderperiodnum() {
		return this.orderperiodnum;
	}

	public void setOrderperiodnum(Integer orderperiodnum) {
		this.orderperiodnum = orderperiodnum;
	}

	public Integer getMaxorderday() {
		return this.maxorderday;
	}

	public void setMaxorderday(Integer maxorderday) {
		this.maxorderday = maxorderday;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ComputerorderconfigId))
			return false;
		ComputerorderconfigId castOther = (ComputerorderconfigId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getOrderperiod() == castOther.getOrderperiod()) || (this
						.getOrderperiod() != null
						&& castOther.getOrderperiod() != null && this
						.getOrderperiod().equals(castOther.getOrderperiod())))
				&& ((this.getOrderperiodnum() == castOther.getOrderperiodnum()) || (this
						.getOrderperiodnum() != null
						&& castOther.getOrderperiodnum() != null && this
						.getOrderperiodnum().equals(
								castOther.getOrderperiodnum())))
				&& ((this.getMaxorderday() == castOther.getMaxorderday()) || (this
						.getMaxorderday() != null
						&& castOther.getMaxorderday() != null && this
						.getMaxorderday().equals(castOther.getMaxorderday())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37
				* result
				+ (getOrderperiod() == null ? 0 : this.getOrderperiod()
						.hashCode());
		result = 37
				* result
				+ (getOrderperiodnum() == null ? 0 : this.getOrderperiodnum()
						.hashCode());
		result = 37
				* result
				+ (getMaxorderday() == null ? 0 : this.getMaxorderday()
						.hashCode());
		return result;
	}

}