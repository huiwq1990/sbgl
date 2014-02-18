package com.sbgl.app.actions.util;

import com.sbgl.util.Page;

public class PageActionUtil {
	
	public static Page getPage(int totalcount,int pageNo){
		Page page = new Page();
		
		if(pageNo ==0){
			pageNo =1;
		}
		
		//设置总数量
		page.setTotalCount(totalcount);
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		
		page.setPageNo(pageNo);
		if(page.getTotalCount()==0){
			page.setPageNo(0);
			page.setTotalpage(0);
			pageNo = 0;
		}
		
		return page;
	}

}
