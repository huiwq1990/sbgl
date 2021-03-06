package com.sbgl.app.actions.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.Page;

public class BaseAction extends ActionSupport implements SessionAware,CookiesAware{

	
	
	public int ComputerorderStatusAduitAll = ComputerorderInfo.ComputerorderStatusAduitAll;
	public int ComputerorderStatusAduitPass = ComputerorderInfo.ComputerorderStatusAduitPass;
	public int ComputerorderStatusAduitReject = ComputerorderInfo.ComputerorderStatusAduitReject;
	public int ComputerorderStatusAduitDel = ComputerorderInfo.ComputerorderStatusAduitDel;
	public int ComputerorderStatusAduitWait = ComputerorderInfo.ComputerorderStatusAduitWait;
	public int  ComputerorderStatusWaitApply = ComputerorderInfo.ComputerorderStatusWaitApply;
	public int IndividualOrder = ComputerorderInfo.IndividualOrder;
	public int ClassOrder = ComputerorderInfo.ClassOrder;
	
	protected Map<String, Object> session;
	protected Map<String, String> cookiesMap;

	
	protected int pageNo;
	protected int totalcount = 0;
	protected int totalpage = 0;
	protected Page page = new Page();
	
	protected String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	protected Map returnMap=  new HashMap();
	protected String returnInfo;
	protected String actionMsg; // Action间传递的消息参数
	
	protected String callType;
	
	protected String forwardurl; // 重定向url
	
	protected int language; // 语言
	
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
	
	
	public int getCurrentAdminId(){
		if(session.containsKey(CommonConfig.sessionuser)){
			Loginuser lu =  (Loginuser) session.get(CommonConfig.sessionuser);
			return lu.getId();
		}
		return -100;
	}
	
	public boolean isAdmin(){
		Loginuser lu = getCurrentUser();
		if(Integer.valueOf(lu.getPrivilege())==1){
			return true;
		}
		return false;
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
	
	/**
	 * 获取国际化信息
	 * @param name
	 * @return
	 */
	public String getMsg(String name){
		
		Map<String,String> textmap = (Map<String, String>) ServletActionContext.getRequest().getAttribute(CommonConfig.resourcetextmap);
		
		if(textmap ==null){
			return "";
		}
		if(textmap.containsKey(name)){
			return textmap.get(name);
		}else{
			return "";
		}
		
		
	}
	
	public String getUrl(){
		HttpServletRequest request = ServletActionContext.getRequest();

		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
		String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);  
		   
		if(request.getQueryString()!=null) 
		{   
		    url+="?"+request.getQueryString();           
		} 
		return url;
//		System.out.println("path："+path); 
//		System.out.println("basePath："+basePath);    
//		System.out.println("URL："+url);    
//		System.out.println("URL参数："+request.getQueryString());  
	}
	
	public String getActionUrl(){
		HttpServletRequest request = ServletActionContext.getRequest();

		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
		String  url  =  "http://"  +  request.getServerName()  +  ":"  +  request.getServerPort()  +  request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1);  
		   
		if(request.getQueryString()!=null) 
		{   
			path+="?"+request.getQueryString();           
		} 
		
		path = path.substring(1,path.length());
		return path;
//		System.out.println("path："+path); 
//		System.out.println("basePath："+basePath);    
//		System.out.println("URL："+url);    
//		System.out.println("URL参数："+request.getQueryString());  
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

	public String getForwardurl() {
		return forwardurl;
	}

	public void setForwardurl(String forwardurl) {
		this.forwardurl = forwardurl;
	}

	public int getComputerorderStatusAduitAll() {
		return ComputerorderStatusAduitAll;
	}

	public void setComputerorderStatusAduitAll(int computerorderStatusAduitAll) {
		ComputerorderStatusAduitAll = computerorderStatusAduitAll;
	}

	public int getComputerorderStatusAduitPass() {
		return ComputerorderStatusAduitPass;
	}

	public void setComputerorderStatusAduitPass(int computerorderStatusAduitPass) {
		ComputerorderStatusAduitPass = computerorderStatusAduitPass;
	}

	public int getComputerorderStatusAduitReject() {
		return ComputerorderStatusAduitReject;
	}

	public void setComputerorderStatusAduitReject(int computerorderStatusAduitReject) {
		ComputerorderStatusAduitReject = computerorderStatusAduitReject;
	}

	public int getComputerorderStatusAduitDel() {
		return ComputerorderStatusAduitDel;
	}

	public void setComputerorderStatusAduitDel(int computerorderStatusAduitDel) {
		ComputerorderStatusAduitDel = computerorderStatusAduitDel;
	}

	public int getComputerorderStatusAduitWait() {
		return ComputerorderStatusAduitWait;
	}

	public void setComputerorderStatusAduitWait(int computerorderStatusAduitWait) {
		ComputerorderStatusAduitWait = computerorderStatusAduitWait;
	}

	public int getComputerorderStatusWaitApply() {
		return ComputerorderStatusWaitApply;
	}

	public void setComputerorderStatusWaitApply(int computerorderStatusWaitApply) {
		ComputerorderStatusWaitApply = computerorderStatusWaitApply;
	}

	public int getIndividualOrder() {
		return IndividualOrder;
	}

	public void setIndividualOrder(int individualOrder) {
		IndividualOrder = individualOrder;
	}

	public int getClassOrder() {
		return ClassOrder;
	}

	public void setClassOrder(int classOrder) {
		ClassOrder = classOrder;
	}
	
	
	public int getLanguage() {
		return language;
	}



	public void setLanguage(int language) {
		this.language = language;
	}





	public Map getReturnMap() {
		return returnMap;
	}





	public void setReturnMap(Map returnMap) {
		this.returnMap = returnMap;
	}






	

}
