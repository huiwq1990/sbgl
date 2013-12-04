package com.sbgl.app.dao;

import java.util.List;

public class QueryResult {
	private List<?> resultList;
	private int totalResultNum;
	
	public QueryResult(List<?> resultList, int totalResultNum) {
		this.resultList = resultList;
		this.totalResultNum = totalResultNum;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public int getTotalResultNum() {
		return totalResultNum;
	}

	public void setTotalResultNum(int totalResultNum) {
		this.totalResultNum = totalResultNum;
	}
	
	
}
