package com.sbgl.app.actions.util;

import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.util.Page;

public class PageActionUtil {
	
	public static Page getPage(int totalcount,int pageNo){
		Page page = new Page();
		
		if(pageNo ==0){
			pageNo =1;
		}
		
		//设置总数量
		page.setTotalCount(totalcount);
		page.calTotalpagenum();
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		
		page.setPageNo(pageNo);
		if(totalcount==0){
			page.setTotalpagenum(1);
			page.setPageNo(1);			
		}
		
		
		return page;
	}
	
	public static void getPage(Page page,int totalcount,int pageNo){
//		Page page = new Page();
		
		if(pageNo ==0){
			pageNo =1;
		}
		
		//设置总数量
		page.setTotalCount(totalcount);
		page.calTotalpagenum();
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		
		page.setPageNo(pageNo);
		if(totalcount==0){
			page.setTotalpagenum(1);
			page.setPageNo(1);			
		}
		
		
//		return page;
	}

	public static void main(String[] args) {
		Page p = getPage(0,0);
		System.out.println(p.getPageNo());
		System.out.println(p.getTotalCount());
		System.out.println(p.getTotalpagenum());
		
		Page p2 = new Page();
		getPage(p2,0,0);
		System.out.println(p2.getPageNo());
		
	}
}
