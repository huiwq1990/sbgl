package com.sbgl.app.actions.common;

import java.util.Map;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.Page;

public class BaseAction extends ActionSupport implements SessionAware,CookiesAware{

	protected Map<String, Object> session;
	protected Map<String, String> cookiesMap;

	
	protected int pageNo;
	protected int totalcount = 0;
	protected int totalpage = 0;
	protected Page page = new Page();
	
	protected String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	protected String returnInfo;
	protected String actionMsg; // Action间传递的消息参数
	
	protected String callType;
	
	
	
	public void setPageInfo(int totalcount){
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
//			System.out.println(pageNo);
		}
	}
	
	public boolean checkUserLogin(){
//		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
//		String uidStr = ComputerActionUtil.getUserIdFromCookie(cookies);
//		if(uidStr==null || uidStr.trim().equals("0") || uidStr.trim().equals("")){
//			return -1;
//		}
//		return Integer.valueOf(uidStr);
		if(getCurrentUserId() < 0 ){
			return false;
		}
		return true;		
	}
	
	public int getCurrentUserId(){
		if(session.containsKey(CommonConfig.sessionuser)){
			Loginuser lu =  (Loginuser) session.get(CommonConfig.sessionuser);
			return lu.getId();
		}
		return -100;
	}
	
	public Loginuser getCurrentUser(){
		if(session.containsKey(CommonConfig.sessionuser)){
			Loginuser lu =  (Loginuser) session.get(CommonConfig.sessionuser);
			return lu;
		}
		
		return null;
	}
	
	public int getCurrentLanguage(){
		return CommonActionUtil.getLanguagetype((String) session.get(ComputerConfig.sessionLanguagetype));		
	}
	
	
	
	
	
	

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setCookiesMap(Map<String, String> arg0) {
		// TODO Auto-generated method stub
		
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}

	public String getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}

	public String getActionMsg() {
		return actionMsg;
	}

	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}

	public Map<String, String> getCookiesMap() {
		return cookiesMap;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	

}
