package com.sbgl.app.actions.order;

import com.sbgl.app.dao.DaoAbs;

public class EquGroupDetailFull extends DaoAbs  {
	private Integer equipmentid;
	private String equipmentname;
	private Integer comId;
	private Integer num;
	public Integer getEquipmentid() {
		return equipmentid;
	}
	public void setEquipmentid(Integer equipmentid) {
		this.equipmentid = equipmentid;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
}
