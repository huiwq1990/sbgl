package com.sbgl.app.actions.equipment.template;

public class ParentClassIdName {
	private long pId;
	private String pName;
	
	public ParentClassIdName(long pId, String pName) {
		super();
		this.pId = pId;
		this.pName = pName;
	}
	
	public long getpId() {
		return pId;
	}
	public void setpId(long pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
}
