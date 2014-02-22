package com.sbgl.app.actions.teach;

import java.util.Calendar;
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
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("CourseconfigAction")
public class CourseconfigAction extends ActionSupport implements SessionAware,ModelDriven<Courseconfig>{
	
	private static final Log log = LogFactory.getLog(CourseconfigAction.class);

	private Map<String, Object> session;
	
	//Service	
	@Resource
	private CourseconfigService courseconfigService;
	
	private Courseconfig courseconfig = new Courseconfig();//实例化一个模型
	private Courseconfig courseconfigModel = new Courseconfig();//实例化一个模型
	private CourseconfigFull courseconfigFull = new CourseconfigFull();//实例化一个模型
	private String actionMsg; // Action间传递的消息参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	List<Courseconfig> courseconfigList = new ArrayList<Courseconfig>();
	List<CourseconfigFull> courseconfigFullList = new ArrayList<CourseconfigFull>();
	private Integer courseconfigid; //entity full 的id属性名称		
	private String logprefix = "exec action method:";
	
	private int pageNo=1;
	private String callType;
	private Page page = new Page();
	private ReturnJson returnJson = new ReturnJson();
	private String courseconfigIdsForDel;
	
	public int currentyear;
	
	//管理 查询
	public String toAddCourseconfigPage(){
		
		   Calendar cal = Calendar.getInstance();
		   currentyear= cal.get(Calendar.YEAR);
		
		return SUCCESS;
	}			

		
	//管理 查询
	public String manageCourseconfigFull(){
		log.info("exec action method:manageCourseconfigFull");
                
//      分页查询        
        if(pageNo <= 0){
			pageNo =1;
        }
        page.setTotalCount(courseconfigService.countCourseconfigRow());
        if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
        }
        page.setPageNo(pageNo);
                
        courseconfigFullList  = courseconfigService.selectCourseconfigFullByConditionAndPage("", page);
               

        if(courseconfigFullList == null){
			courseconfigFullList = new ArrayList<CourseconfigFull>();
        }
//      for(int i = 0; i < computerhomeworkFullList.size(); i++){
//      	System.out.println("id=");
//      }
        if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
        }else{
           return "success1";
        }	
	}			


//  ajax add	
	public String addCourseconfigAjax(){	
		log.info("Add Entity Ajax Manner");
		
		
		
		try {
			Courseconfig temp = new Courseconfig();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, courseconfig);			
			//add your code here.
			
			//temp.setCreatetime(DateUtil.currentDate());
			
//			将所有当前学期设置不是当前学期
			courseconfigService.execSql(" update Courseconfig set currentsemester = "+TeachConstant.coursesconfigcurrentsemester+" where currentsemester = "+TeachConstant.coursesconfigcurrentsemester);
			temp.setStatus(TeachConstant.coursesconfigvalidstatus);
//			将添加的设置为当前学期
			temp.setCurrentsemester(TeachConstant.coursesconfigcurrentsemester);
			
			temp.setStatus(TeachConstant.coursesconfigvalidstatus);
			courseconfigService.addCourseconfig(temp);
			
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, "添加当前学期成功");			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类CourseconfigAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		returnJson.setReason("添加失败");
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
	public Courseconfig getModel() {
		// TODO Auto-generated method stub
		return courseconfig;
	}

//  
	public Courseconfig getCourseconfig() {
		return courseconfig;
	}
	
	public void setCourseconfig(Courseconfig courseconfig) {
		this.courseconfig = courseconfig;
	}
//  entityModel
	public Courseconfig getCourseconfigModel() {
		return courseconfigModel;
	}
	
	public void setCourseconfigModel(Courseconfig courseconfigModel) {
		this.courseconfigModel = courseconfigModel;
	}
	
	public CourseconfigFull getCourseconfigFull() {
		return courseconfigFull;
	}
	
	public void setCourseconfigFull(CourseconfigFull courseconfigFull) {
		this.courseconfigFull = courseconfigFull;
	}
	
	public List<Courseconfig> getCourseconfigList() {
		return courseconfigList;
	}


	public void setCourseconfigList(List<Courseconfig> courseconfigList) {
		this.courseconfigList = courseconfigList;
	}

	public List<CourseconfigFull> getCourseconfigFullList() {
		return courseconfigFullList;
	}


	public void setCourseconfigFullList(List<CourseconfigFull> courseconfigFullList) {
		this.courseconfigFullList = courseconfigFullList;
	}

	public String getReturnStr() {
		return returnStr;
	}


	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}
	
	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}
	
	public int getCourseconfigid() {
		return courseconfigid;
	}

	public void setCourseconfigid(int courseconfigid) {
		this.courseconfigid = courseconfigid;
	}
		public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
	public String getCourseconfigIdsForDel() {
                return courseconfigIdsForDel;
        }

        public void setCourseconfigIdsForDel(String courseconfigIdsForDel) {
                this.courseconfigIdsForDel = courseconfigIdsForDel;
        }


		public CourseconfigService getCourseconfigService() {
			return courseconfigService;
		}


		public void setCourseconfigService(CourseconfigService courseconfigService) {
			this.courseconfigService = courseconfigService;
		}


		public String getActionMsg() {
			return actionMsg;
		}


		public void setActionMsg(String actionMsg) {
			this.actionMsg = actionMsg;
		}


		public String getLogprefix() {
			return logprefix;
		}


		public void setLogprefix(String logprefix) {
			this.logprefix = logprefix;
		}


		public String getCallType() {
			return callType;
		}


		public void setCallType(String callType) {
			this.callType = callType;
		}


		public ReturnJson getReturnJson() {
			return returnJson;
		}


		public void setReturnJson(ReturnJson returnJson) {
			this.returnJson = returnJson;
		}


		public int getCurrentyear() {
			return currentyear;
		}


		public void setCurrentyear(int currentyear) {
			this.currentyear = currentyear;
		}


		public static Log getLog() {
			return log;
		}


		public Map<String, Object> getSession() {
			return session;
		}


		public void setCourseconfigid(Integer courseconfigid) {
			this.courseconfigid = courseconfigid;
		}
        
        
        
        
        
}
