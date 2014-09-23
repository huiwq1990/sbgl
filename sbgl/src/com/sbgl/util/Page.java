package com.sbgl.util;

import java.util.ArrayList;
import java.util.List;


/**
 * 分页类
 * @author wm
 *
 */
public class Page {

	public static int SIZE = 20;
	public static int PAGE = 1;
	private int pageNo = PAGE;
	/** 每页显示记录数 **/
	private int pageSize = SIZE;
	/**
	 * 总数量
	 */
	private int totalCount = -1;
	/*** 总页数 */
    private int totalpage=1;
    /**页面显示的开始数,与分页无关**/
    private int startindex;
    /**页面显示的结束数,与分页无关**/
	private int endindex;
	
	private List<Integer> pageList;
	/** 页码数量 **/
	private int pageviewcount = 10;
	
	private int totalpagenum;
	
	public Page() {

	}

	public Page(int pageNo) {
		this.pageNo = pageNo;
	}

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
/**
 * 
 * @param pageNo 当前页码
 * @param pageSize 每页显示的数量
 * @param pageviewcount 页面显示页码的数量
 */
	public Page(int pageNo, int pageSize,int pageviewcount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.pageviewcount=pageviewcount;
	}
	public int getStartNum() {
		return getStartCount();
	}

	public int getStartCount() {
		if (pageNo < 0 || pageSize < 0) {
			return -1;
		} else {
			return (pageNo - 1) * pageSize;
		}
	}


	public boolean nextPage() {
		return (pageNo + 1 <= getTotalpage());
	}

	public int getNextPage() {
		if (nextPage())
			return pageNo + 1;
		else
			return pageNo;
	}

	public boolean prePage() {
		return (pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (prePage()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int page) {
		this.pageNo = page;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalpage=getTotalpage();
		getPageIndex(pageNo,totalpage);
	}
	
	public int getTotalpage() {
		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getStartindex() {
		return startindex;
	}


	public long getEndindex() {
		return endindex;
	}


	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	public void setEndindex(int endindex) {
		this.endindex = endindex;
	}

	public List<Integer> getPageList() {
		return pageList;
	}

	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}

	public int getPageviewcount() {
		return pageviewcount;
	}

	public void setPageviewcount(int pageviewcount) {
		this.pageviewcount = pageviewcount;
	}

	public void getPageIndex(int currentPage, int totalpage){
		int startpage = currentPage-(pageviewcount%2==0? pageviewcount/2-1 : pageviewcount/2);
		int endpage = currentPage+pageviewcount/2;
		if(startpage<1){
			startpage = 1;
			if(totalpage>=pageviewcount) {}
			else endpage = totalpage;
		}
		if(endpage>totalpage){
			endpage = totalpage;
			if((endpage-pageviewcount)>0) startpage = endpage-pageviewcount+1;
			else startpage = 1;
		}
		if(endpage<10&&totalpage>=10){
			endpage=10;
		}
		this.startindex=startpage;
		this.endindex=endpage;	
		pageList=new ArrayList<Integer>();
		for (int i = startindex; i < endindex; i++) {
			pageList.add(i);
		}
	}
	
	
	public int getPageNoIncrement1(){
	    return this.pageNo+1;
	}
	public int getPageNoIncrement2(){
	    return this.pageNo+2;
	}
	public int getPageNoDecreasing1(){
	    return this.pageNo-1;
	}
	public int getPageNoDecreasing2(){
	    return this.pageNo-2;
	}
	public int getTotalPageDecreasing1(){
	    return this.totalpage-1;
	}

	
	public void calTotalpagenum(){
		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		
		totalpagenum = count;
	}
	public int getTotalpagenum() {
		return totalpagenum;
	}

	public void setTotalpagenum(int totalpagenum) {
		this.totalpagenum = totalpagenum;
	}
	
	
	
	
}
