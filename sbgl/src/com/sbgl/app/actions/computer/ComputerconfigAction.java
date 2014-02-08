package com.sbgl.app.actions.computer;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.beanutils.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerconfigService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerconfigAction")
public class ComputerconfigAction extends ActionSupport implements SessionAware,ModelDriven<Computerconfig>{
	
	private static final Log log = LogFactory.getLog(ComputerconfigAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private ComputerconfigService computerconfigService;
	
	private Computerconfig computerconfig = new Computerconfig();//实例化一个模型
	private Computerconfig computerconfigModel = new Computerconfig();//实例化一个模型
	private ComputerconfigFull computerconfigFull = new ComputerconfigFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Computerconfig> computerconfigList = new ArrayList<Computerconfig>();
	List<ComputerconfigFull> computerconfigFullList = new ArrayList<ComputerconfigFull>();
	private Integer computerconfigid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";

	private ReturnJson returnJson = new ReturnJson();
	
	
	
	 int openbook;
	 String bookperiod ;
	 int bookperiodnum;
	 int maxorderday;
	
//  manage Computerconfig
	public String manageComputerconfig(){
		log.info(logprefix+"manageComputerconfigFull");
	
		Computerconfig temp = new Computerconfig();
		
		temp = computerconfigService.selectComputerconfigByCondition(" where name = 'openbook' ").get(0);		
		openbook = Integer.valueOf(temp.getValue());
		
		temp = computerconfigService.selectComputerconfigByCondition(" where name = 'bookperiod' ").get(0);		
		bookperiod = temp.getValue();
		
		temp = computerconfigService.selectComputerconfigByCondition(" where name = 'bookperiodnum' ").get(0);		
		bookperiodnum = Integer.valueOf(temp.getValue());
		
		temp = computerconfigService.selectComputerconfigByCondition(" where name = 'maxorderday' ").get(0);		
		maxorderday = Integer.valueOf(temp.getValue());
		
		return SUCCESS;
	}		
			

			

	
	//ajax 修改
	public String updateComputerconfigAjax(){
		log.info(logprefix + "updateComputerconfigAjax,id="+computerconfig.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			
			
			computerconfigService.execSql(" update Computerconfig set value = '"+openbook+"' where name = 'openbook'" );
			
			bookperiod = bookperiod.trim();
			computerconfigService.execSql(" update Computerconfig set value = '"+bookperiod+"' where name = 'bookperiod'" );
			bookperiodnum = 0;
			for (int i = 0; i < bookperiod.length(); i++) {
				char c = bookperiod.charAt(i);
				if(c=='1'){
					bookperiodnum++;
				}				
			}
			computerconfigService.execSql(" update Computerconfig set value = '"+bookperiodnum+"' where name = 'bookperiodnum'" );
			computerconfigService.execSql(" update Computerconfig set value = '"+maxorderday+"' where name = 'maxorderday'" );
			
			
			returnJson.setFlag(1);	
			returnJson.setReason("修改成功");
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			//actionMsg = getText("viewComputerconfigSuccess");
			return SUCCESS;
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerconfigAction的方法：viewComputerconfig错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	



	

	//get set
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}
	
	@Override
	public Computerconfig getModel() {
		// TODO Auto-generated method stub
		return computerconfig;
	}

//  
	public Computerconfig getComputerconfig() {
		return computerconfig;
	}
	
	public void setComputerconfig(Computerconfig computerconfig) {
		this.computerconfig = computerconfig;
	}
//  entityModel
	public Computerconfig getComputerconfigModel() {
		return computerconfigModel;
	}
	
	public void setComputerconfigModel(Computerconfig computerconfigModel) {
		this.computerconfigModel = computerconfigModel;
	}
	
	public ComputerconfigFull getComputerconfigFull() {
		return computerconfigFull;
	}
	
	public void setComputerconfigFull(ComputerconfigFull computerconfigFull) {
		this.computerconfigFull = computerconfigFull;
	}
	
	public List<Computerconfig> getComputerconfigList() {
		return computerconfigList;
	}


	public void setComputerconfigList(List<Computerconfig> computerconfigList) {
		this.computerconfigList = computerconfigList;
	}

	public List<ComputerconfigFull> getComputerconfigFullList() {
		return computerconfigFullList;
	}


	public void setComputerconfigFullList(List<ComputerconfigFull> computerconfigFullList) {
		this.computerconfigFullList = computerconfigFullList;
	}

	public String getReturnStr() {
		return returnStr;
	}


	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}





	public ComputerconfigService getComputerconfigService() {
		return computerconfigService;
	}





	public void setComputerconfigService(ComputerconfigService computerconfigService) {
		this.computerconfigService = computerconfigService;
	}





	public String getActionMsg() {
		return actionMsg;
	}





	public void setActionMsg(String actionMsg) {
		this.actionMsg = actionMsg;
	}





	public Integer getComputerconfigid() {
		return computerconfigid;
	}





	public void setComputerconfigid(Integer computerconfigid) {
		this.computerconfigid = computerconfigid;
	}





	public String getLogprefix() {
		return logprefix;
	}





	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}





	public ReturnJson getReturnJson() {
		return returnJson;
	}





	public void setReturnJson(ReturnJson returnJson) {
		this.returnJson = returnJson;
	}





	public int getOpenbook() {
		return openbook;
	}





	public void setOpenbook(int openbook) {
		this.openbook = openbook;
	}





	public String getBookperiod() {
		return bookperiod;
	}





	public void setBookperiod(String bookperiod) {
		this.bookperiod = bookperiod;
	}





	public int getBookperiodnum() {
		return bookperiodnum;
	}





	public void setBookperiodnum(int bookperiodnum) {
		this.bookperiodnum = bookperiodnum;
	}





	public int getMaxorderday() {
		return maxorderday;
	}





	public void setMaxorderday(int maxorderday) {
		this.maxorderday = maxorderday;
	}





	public static Log getLog() {
		return log;
	}





	public Map<String, Object> getSession() {
		return session;
	}
	
	
	
}
