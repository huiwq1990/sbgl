package com.sbgl.common;

import org.apache.poi.ss.formula.functions.T;

public class HQLOption<T> {
	private String propertyName;
	private T value;
	private int option;
	private int type = 1;  //0为数字，1为字符串，2为日期
	private int joinType;
	
	public int getJoinType() {
		return joinType;
	}

	public void setJoinType(int joinType) {
		this.joinType = joinType;
	}

	public HQLOption() {
		
	}

	public HQLOption(String propertyName, T value, int option, int type,
			int joinType) {
		super();
		this.propertyName = propertyName;
		this.value = value;
		this.option = option;
		this.type = type;
		this.joinType = joinType;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
	
	
}
