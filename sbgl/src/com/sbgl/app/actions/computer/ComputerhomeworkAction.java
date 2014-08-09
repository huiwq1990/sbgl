package com.sbgl.app.actions.computer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;

import net.sf.json.JSONObject;


import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.apache.commons.beanutils.BeanUtils;


import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.actions.util.PageActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.*;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.util.*;



@Scope("prototype") 
@Controller("ComputerhomeworkAction")
public class ComputerhomeworkAction extends BaseAction implements ModelDriven<Computerhomework>{
	
	private static final Log log = LogFactory.getLog(ComputerhomeworkAction.class);

	
	
	//Service	
	@Resource
	private ComputerhomeworkService computerhomeworkService;
	
	private Computerhomework computerhomework = new Computerhomework();//实例化一个模型
	private Computerhomework computerhomeworkModel = new Computerhomework();//实例化一个模型
	private ComputerhomeworkFull computerhomeworkFull = new ComputerhomeworkFull();//实例化一个模型
	List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	private Integer computerhomeworkid; //entity full 的id属性名称		
	
	
	@Resource
	private CourseService courseService;
	private Integer courseid; //entity full 的id属性名称		
	private Course course = new Course();//实例化一个模型
	private Course courseModel = new Course();//实例化一个模型
	private CourseFull courseFull = new CourseFull();//实例化一个模型
	private List<Course> courseList = new ArrayList<Course>();
	private List<CourseFull> courseFullList = new ArrayList<CourseFull>();
	private List<CourseFull> courseFullListCh = new ArrayList<CourseFull>();
	private List<CourseFull> courseFullListEn = new ArrayList<CourseFull>();
	
	


	private String logprefix = "exec action method:";		

	
	private List<ComputerhomeworkFull> newComputerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	private List<ComputerhomeworkFull> finishComputerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();

	
//	add homework	



// del 
	String computerhomeworkIdsForDel;
	
	
	@Resource
	private ComputerorderclassruleService computerorderclassruleService;	
	private Computerorderclassrule computerorderclassrule = new Computerorderclassrule();//实例化一个模型
	private Computerorderclassrule computerorderclassruleModel = new Computerorderclassrule();//实例化一个模型
	private ComputerorderclassruleFull computerorderclassruleFull = new ComputerorderclassruleFull();//实例化一个模型
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
	
	
	
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private Computerorderclassruledetail computerorderclassruledetailModel = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	


//	改进的Homework添加
	private String receiverids;//接收者
	private Integer classid;
	private Date orderstarttime;
	private Date orderendtime;
	private Integer availableordertime;
//	规则允许借的pc
	private String allowborrowpcids;
	
	
//	作业默认是等待提交，课程预约
	private Integer  currentcomputerorderauditstatus=0;
	private Integer   currentcomputerorderordertype = 0;
	
//	学生查看作业收件箱
	public String toComputerhomeworkInboxPage(){
		log.info(logprefix +" toComputerhomeworkInboxPage");
		
		Integer uid = getCurrentUserId();
		log.info("login user id "+ uid);
		if(uid < 0){
			returnInfo = "用户未登录";
			this.returnStr = JsonActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		String receivesql = " where a.userid ="+uid ;
		computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverByCondition(receivesql);
		
		String newhomeworksql =  "";
		String finishehomeworksql = "";
		if(computerhomeworkreceiverList == null || computerhomeworkreceiverList.size() == 0){
			if(newComputerhomeworkFullList == null){
				newComputerhomeworkFullList =  new ArrayList<ComputerhomeworkFull>();
			}
			if(finishComputerhomeworkFullList == null){
				finishComputerhomeworkFullList= new ArrayList<ComputerhomeworkFull>();
			}
			
			return SUCCESS;
		}
			
		for(int i=0; i<computerhomeworkreceiverList.size();i++){
			System.out.println(computerhomeworkreceiverList.get(i).getHasorder());
			if( (computerhomeworkreceiverList.get(i).getHasorder()==null) || (computerhomeworkreceiverList.get(i).getHasorder() != ComputerConfig.computerhomeworkhasorder)){
					newhomeworksql += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}else{
				finishehomeworksql += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}
		}
		
		log.info("new :"+newhomeworksql);
		log.info("new :"+finishehomeworksql);
			if(finishehomeworksql.length() > 1){
				finishehomeworksql = finishehomeworksql.substring(0,finishehomeworksql.length()-1);
				finishehomeworksql = " where a.id in (" +finishehomeworksql+") "  + " order by computerhomeworkcreatetime desc ";
				finishComputerhomeworkFullList = computerhomeworkService.selectComputerhomeworkFullByCondition(finishehomeworksql);
			}
			
			if(newhomeworksql.length() > 1){
				newhomeworksql = newhomeworksql.substring(0,newhomeworksql.length()-1);
				newhomeworksql = " where a.id in (" +newhomeworksql+") "  + " order by computerhomeworkcreatetime desc ";
				newComputerhomeworkFullList = computerhomeworkService.selectComputerhomeworkFullByCondition(newhomeworksql);
			}
			
			
			if(newComputerhomeworkFullList == null){
				newComputerhomeworkFullList =  new ArrayList<ComputerhomeworkFull>();
			}
			if(finishComputerhomeworkFullList == null){
				finishComputerhomeworkFullList= new ArrayList<ComputerhomeworkFull>();
			}
		
		return SUCCESS;
	}
	
	
	//管理作业
	public String manageComputerhomeworkFull(){
		log.info("exec action method:manageComputerhomeworkFull");
		
		int totalcount = computerhomeworkService.countComputerhomeworkRow();
		page = PageActionUtil.getPage(totalcount, pageNo);
		this.pageNo = page.getPageNo();
		System.out.println(page.getTotalpage());
		
		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullByConditionAndPage("", page);
		System.out.println("computerhomeworkFullList.size"+computerhomeworkFullList.size());
//		查询全部
//		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullAll();

		if(computerhomeworkFullList == null){
			computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
		}
//		for(int i = 0; i < computerhomeworkFullList.size(); i++){
//			System.out.println("id="+computerhomeworkFullList.get(i).getLoginusername());
//		}
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
			
		

			

//  ajax add
	/*
	public String addComputerhomeworkAjax(){	
		log.info("Add Entity Ajax Manner");
		
	
		try {
		
			Computerhomework temp = new Computerhomework();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerhomework);
			temp.setCreateuserid(this.getCurrentUserId());
			
			List<Computerhomeworkreceiver> chrList = new ArrayList<Computerhomeworkreceiver>();
			String[] userIds = receiverids.split(";");
			for (int i = 0; i < userIds.length; i++) {
				Computerhomeworkreceiver chr = new Computerhomeworkreceiver();
			
				chr.setComputerhomeworkid(temp.getId());
				chr.setUserid(Integer.valueOf(userIds[i]));
				chr.setHasorder(0);
				chr.setStatus(0);
				chr.setHasview(0);
				
				chrList.add(chr);
			}
			
			computerhomeworkService.sendComputerhomework(computerhomework, chrList);
			
			returnInfo = "添加成功";
			this.returnStr = JsonActionUtil.buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
			return SUCCESS;
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnInfo = "添加失败";
		this.returnStr = JsonActionUtil.buildReturnStr(ComputerConfig.ajaxsuccessreturn,returnInfo);
		return SUCCESS;
	}

	*/
	public boolean checkAddForm(){
		if(receiverids == null || receiverids.length() == 0){
			returnInfo = "接收人不能为空";
			return false;
		}
		
		if(allowborrowpcids == null || allowborrowpcids.length() == 0){
			returnInfo = "预约器材不能为空";
			return false;
		}
		
		return true;
	}

//  ajax add	
	public String addComputerhomeworkAjaxNew(){	
		log.info("Add Entity Ajax Manner");
		
		
		
		try {
			
			if(!checkAddForm()){
				this.returnStr = JsonActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn,returnInfo);
				return SUCCESS;
			}
			
//			添加规则
			Computerorderclassrule tempRule = new Computerorderclassrule();
			tempRule.setClassid(classid);
			tempRule.setAvailableordertime(availableordertime);
			tempRule.setOrderstarttime(orderstarttime);
			tempRule.setOrderendtime(orderendtime);
			
//			规则详情
			List<Computerorderclassruledetail> codList = new ArrayList<Computerorderclassruledetail>();
			String[] pcid = allowborrowpcids.split(";");			
			for (int i = 0; i < pcid.length; i++) {
				Computerorderclassruledetail c = new Computerorderclassruledetail();
				c.setAllowedcomputermodelid(Integer.valueOf(pcid[i]));
				codList.add(c);
			}
			
			
			Computerhomework temp = new Computerhomework();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerhomework);
			temp.setCreateuserid(this.getCurrentUserId());
			
			List<Computerhomeworkreceiver> chrList = new ArrayList<Computerhomeworkreceiver>();
			
			String[] userIds = receiverids.split(";");
			Map<Integer,Integer> receiverMap = new HashMap<Integer,Integer>();
			log.info("作业发送对象"+ receiverids);
			for (int i = 0; i < userIds.length; i++) {
				
				if(receiverMap.containsKey(Integer.valueOf(userIds[i]))){
					continue;
				}
				
				receiverMap.put(Integer.valueOf(userIds[i]), Integer.valueOf(userIds[i]));
				
				Computerhomeworkreceiver chr = new Computerhomeworkreceiver();			
				chr.setComputerhomeworkid(temp.getId());
				chr.setUserid(Integer.valueOf(userIds[i]));
				chr.setHasorder(0);
				chr.setStatus(0);
				chr.setHasview(0);
				
				chrList.add(chr);
			}
			
			computerhomeworkService.sendComputerhomeworkNew(tempRule,codList,computerhomework, chrList);

			
			
			
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn,"作业发送成功");
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：addBbstagfavourite错误"+e);
		}
		
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,"内部错误");		
		return SUCCESS;
	}


	
	//del Ajax
	public String deleteComputerhomeworkAjax( ){
		
		log.info(logprefix + "deleteComputercategoryFullAjax");
		
		if(computerhomeworkIdsForDel == null || computerhomeworkIdsForDel.trim().equals("")){
			this.returnInfo = "没有选择要删除的数据";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		
		String ids[] = computerhomeworkIdsForDel.split(";");
		Integer delIdArray[] = new Integer[ids.length];
//		List<Integer> delIdList = new ArrayList<Integer>();
		
		for(int i=0; i < ids.length;i++){
			
			if(!NumberUtils.isNumber(ids[i])){					
				this.returnInfo = "删除数据格式不正确";
				log.info(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			Integer tempDelId = Integer.valueOf(ids[i]);
			
			delIdArray[i]=tempDelId;
		}
	
		try{
			
			
			computerhomeworkService.deleteComputerhomework(delIdArray);
			this.returnInfo = "删除成功";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
                        
            } catch (Exception e) {
                        e.printStackTrace();
            }
            
            
            this.returnInfo = "删除的内部错误";
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
        
	}


	
	//ajax 修改
	public String updateComputerhomeworkAjax(){
		log.info(logprefix + "updateComputerhomeworkAjax,id="+computerhomework.getId());
		ReturnJson returnJson = new ReturnJson();
		try {
			if(computerhomework.getId() != null && computerhomework.getId() > 0){				
				Computerhomework tempComputerhomework = computerhomeworkService.selectComputerhomeworkById(computerhomework.getId());
				
				
//              选择能更改的属性，与界面一致	
  				tempComputerhomework.setName(computerhomework.getName());
  				tempComputerhomework.setComputerorderclassruleid(computerhomework.getComputerorderclassruleid());
  				tempComputerhomework.setContent(computerhomework.getContent());
  				tempComputerhomework.setCreateuserid(computerhomework.getCreateuserid());
  				tempComputerhomework.setAttachment(computerhomework.getAttachment());
  				tempComputerhomework.setStatus(computerhomework.getStatus());
  				tempComputerhomework.setCreatetime(computerhomework.getCreatetime());
 
				
				computerhomeworkService.updateComputerhomework(tempComputerhomework);		
				returnJson.setFlag(1);	
				returnJson.setReason("修改成功");
				JSONObject jo = JSONObject.fromObject(returnJson);
				this.returnStr = jo.toString();
				//actionMsg = getText("viewComputerhomeworkSuccess");
				return SUCCESS;
				
			}else{
				actionMsg = getText("viewComputerhomeworkFail");
				log.info(logprefix + "updateComputerhomeworkAjax fail");
			}			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerhomeworkAction的方法：viewComputerhomework错误"+e);
		}

			returnJson.setFlag(0);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
	}
	
	


	/**
	 * 学生查看作业信息
	 * view ComputerhomeworkFull
	 * need give parmeter id
	 * get id from modle,
	 * @return
	 */
		
	public String checkComputerhomework() {
					
			try {
				
//				默认是等待审核
				currentcomputerorderauditstatus = this.getComputerorderStatusWaitApply();
//				课程预约
				currentcomputerorderordertype = this.getClassOrder();
				
				computerhomeworkFull = computerhomeworkService.selectComputerhomeworkFullById(computerhomeworkid);
				if(computerhomeworkFull==null){				
					return "404";
				}
				
								
//				查询作业可以借的PC
				int ruleId = computerhomeworkFull.getComputerorderclassruleid();
				if(ruleId > 0){
//					String borrowPcSql  = " where a.computerorderclassruleid = "+ ruleId+ " and b.languagetype = "+this.getCurrentLanguage();
					computerorderclassruledetailFullList = computerorderclassruledetailService.selByComputerorderclassruleId(ruleId, this.getCurrentLanguage());				
				}else{
					return "404";
				}
				
//				查询课程信息
				courseFull = courseService.selectCourseFullByCoursetype(computerhomeworkFull.getComputerorderclassruleclassid(),this.getCurrentLanguage());
				
//				System.out.println(courseFull.getCoursename());
				
				if(computerorderclassruledetailFullList == null){
					computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
				}
				
				if(courseFull==null){
					return "404";
				}
				
				return SUCCESS;

			} catch (Exception e) {
				e.printStackTrace();			
			}
			return "404";
		}	


	/**
	 * 教师查看作业内容
	 * view ComputerhomeworkFull
	 * need give parmeter id
	 * get id from modle,
	 * @return
	 */
	public String viewComputerhomeworkFull() {
				
		try {
			String condition = " where a.id = "+computerhomeworkid ;
			List<ComputerhomeworkFull> tempList = computerhomeworkService.selectComputerhomeworkFullByCondition(condition );
			
			if(tempList!=null && tempList.size() >0){
				
			}else{
				actionMsg = "访问的PC作业不存在";
				return ComputerConfig.pagenotfound;
			}
			
			computerhomeworkFull = tempList.get(0);
			
			courseFull = courseService.selectCourseFullByCoursetype(computerhomeworkFull.getComputerorderclassruleclassid(),this.getCurrentLanguage());
			
//			System.out.println(tempList.size() + " "+computerhomeworkFull.getComputerorderclassruleid());
			
//			查询作业可以借的PC
			int ruleId = computerhomeworkFull.getComputerorderclassruleid();
			String borrowPcSql  = " where a.computerorderclassruleid = "+ ruleId + " and b.languagetype = "+this.getCurrentLanguage();
			computerorderclassruledetailFullList = computerorderclassruledetailService.selectComputerorderclassruledetailFullByCondition(borrowPcSql);				

			if(computerorderclassruledetailFullList == null){
				actionMsg = "获取作业规则错误";
				return ComputerConfig.pagenotfound;
			}
			
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		actionMsg = "系统内部发生错误";
		return ComputerConfig.innererror;
	}
/*
	//根据computerorderclassruleid 查询实体
	public String selectComputerhomeworkByComputerorderclassruleId() {
			//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerhomeworkList  = computerhomeworkService.selectComputerhomeworkAll();
		for(int i = 0; i < computerhomeworkList.size(); i++){
			System.out.println("id="+computerhomeworkList.get(i).getId());
		}
		return SUCCESS;
	}
	//根据computerorderclassruleid 查询实体full
	public String selectComputerhomeworkFullByComputerorderclassruleId() {
				//检查用户登录
		Loginuser lu = (Loginuser)session.get("Loginuser");
		if(lu==null || lu.getId()==null){
			return "toLogin";
		}
		Integer userId = lu.getId();
		
		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullByComputerorderclassruleId(userId);
		for(int i = 0; i < computerhomeworkFullList.size(); i++){
			//System.out.println("id="+computerhomeworkFullList.get(i).getLoginusername());
		}
		return SUCCESS;
	}
*/

	
	@Override
	public Computerhomework getModel() {
		// TODO Auto-generated method stub
		return computerhomework;
	}



	public ComputerhomeworkService getComputerhomeworkService() {
		return computerhomeworkService;
	}



	public void setComputerhomeworkService(
			ComputerhomeworkService computerhomeworkService) {
		this.computerhomeworkService = computerhomeworkService;
	}



	public Computerhomework getComputerhomework() {
		return computerhomework;
	}



	public void setComputerhomework(Computerhomework computerhomework) {
		this.computerhomework = computerhomework;
	}



	public Computerhomework getComputerhomeworkModel() {
		return computerhomeworkModel;
	}



	public void setComputerhomeworkModel(Computerhomework computerhomeworkModel) {
		this.computerhomeworkModel = computerhomeworkModel;
	}



	public ComputerhomeworkFull getComputerhomeworkFull() {
		return computerhomeworkFull;
	}



	public void setComputerhomeworkFull(ComputerhomeworkFull computerhomeworkFull) {
		this.computerhomeworkFull = computerhomeworkFull;
	}



	public List<Computerhomework> getComputerhomeworkList() {
		return computerhomeworkList;
	}



	public void setComputerhomeworkList(List<Computerhomework> computerhomeworkList) {
		this.computerhomeworkList = computerhomeworkList;
	}



	public List<ComputerhomeworkFull> getComputerhomeworkFullList() {
		return computerhomeworkFullList;
	}



	public void setComputerhomeworkFullList(
			List<ComputerhomeworkFull> computerhomeworkFullList) {
		this.computerhomeworkFullList = computerhomeworkFullList;
	}



	public Integer getComputerhomeworkid() {
		return computerhomeworkid;
	}



	public void setComputerhomeworkid(Integer computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
	}



	public CourseService getCourseService() {
		return courseService;
	}



	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}



	public Integer getCourseid() {
		return courseid;
	}



	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}



	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}



	public Course getCourseModel() {
		return courseModel;
	}



	public void setCourseModel(Course courseModel) {
		this.courseModel = courseModel;
	}



	public CourseFull getCourseFull() {
		return courseFull;
	}



	public void setCourseFull(CourseFull courseFull) {
		this.courseFull = courseFull;
	}



	public List<Course> getCourseList() {
		return courseList;
	}



	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}



	public List<CourseFull> getCourseFullList() {
		return courseFullList;
	}



	public void setCourseFullList(List<CourseFull> courseFullList) {
		this.courseFullList = courseFullList;
	}



	public List<CourseFull> getCourseFullListCh() {
		return courseFullListCh;
	}



	public void setCourseFullListCh(List<CourseFull> courseFullListCh) {
		this.courseFullListCh = courseFullListCh;
	}



	public List<CourseFull> getCourseFullListEn() {
		return courseFullListEn;
	}



	public void setCourseFullListEn(List<CourseFull> courseFullListEn) {
		this.courseFullListEn = courseFullListEn;
	}



	public String getLogprefix() {
		return logprefix;
	}



	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}



	public List<ComputerhomeworkFull> getNewComputerhomeworkFullList() {
		return newComputerhomeworkFullList;
	}



	public void setNewComputerhomeworkFullList(
			List<ComputerhomeworkFull> newComputerhomeworkFullList) {
		this.newComputerhomeworkFullList = newComputerhomeworkFullList;
	}



	public List<ComputerhomeworkFull> getFinishComputerhomeworkFullList() {
		return finishComputerhomeworkFullList;
	}



	public void setFinishComputerhomeworkFullList(
			List<ComputerhomeworkFull> finishComputerhomeworkFullList) {
		this.finishComputerhomeworkFullList = finishComputerhomeworkFullList;
	}



	public String getComputerhomeworkIdsForDel() {
		return computerhomeworkIdsForDel;
	}



	public void setComputerhomeworkIdsForDel(String computerhomeworkIdsForDel) {
		this.computerhomeworkIdsForDel = computerhomeworkIdsForDel;
	}



	public ComputerorderclassruleService getComputerorderclassruleService() {
		return computerorderclassruleService;
	}



	public void setComputerorderclassruleService(
			ComputerorderclassruleService computerorderclassruleService) {
		this.computerorderclassruleService = computerorderclassruleService;
	}



	public Computerorderclassrule getComputerorderclassrule() {
		return computerorderclassrule;
	}



	public void setComputerorderclassrule(
			Computerorderclassrule computerorderclassrule) {
		this.computerorderclassrule = computerorderclassrule;
	}



	public Computerorderclassrule getComputerorderclassruleModel() {
		return computerorderclassruleModel;
	}



	public void setComputerorderclassruleModel(
			Computerorderclassrule computerorderclassruleModel) {
		this.computerorderclassruleModel = computerorderclassruleModel;
	}



	public ComputerorderclassruleFull getComputerorderclassruleFull() {
		return computerorderclassruleFull;
	}



	public void setComputerorderclassruleFull(
			ComputerorderclassruleFull computerorderclassruleFull) {
		this.computerorderclassruleFull = computerorderclassruleFull;
	}



	public List<Computerorderclassrule> getComputerorderclassruleList() {
		return computerorderclassruleList;
	}



	public void setComputerorderclassruleList(
			List<Computerorderclassrule> computerorderclassruleList) {
		this.computerorderclassruleList = computerorderclassruleList;
	}



	public List<ComputerorderclassruleFull> getComputerorderclassruleFullList() {
		return computerorderclassruleFullList;
	}



	public void setComputerorderclassruleFullList(
			List<ComputerorderclassruleFull> computerorderclassruleFullList) {
		this.computerorderclassruleFullList = computerorderclassruleFullList;
	}



	public ComputerhomeworkreceiverService getComputerhomeworkreceiverService() {
		return computerhomeworkreceiverService;
	}



	public void setComputerhomeworkreceiverService(
			ComputerhomeworkreceiverService computerhomeworkreceiverService) {
		this.computerhomeworkreceiverService = computerhomeworkreceiverService;
	}



	public Computerhomeworkreceiver getComputerhomeworkreceiver() {
		return computerhomeworkreceiver;
	}



	public void setComputerhomeworkreceiver(
			Computerhomeworkreceiver computerhomeworkreceiver) {
		this.computerhomeworkreceiver = computerhomeworkreceiver;
	}



	public ComputerhomeworkreceiverFull getComputerhomeworkreceiverFull() {
		return computerhomeworkreceiverFull;
	}



	public void setComputerhomeworkreceiverFull(
			ComputerhomeworkreceiverFull computerhomeworkreceiverFull) {
		this.computerhomeworkreceiverFull = computerhomeworkreceiverFull;
	}



	public List<Computerhomeworkreceiver> getComputerhomeworkreceiverList() {
		return computerhomeworkreceiverList;
	}



	public void setComputerhomeworkreceiverList(
			List<Computerhomeworkreceiver> computerhomeworkreceiverList) {
		this.computerhomeworkreceiverList = computerhomeworkreceiverList;
	}



	public List<ComputerhomeworkreceiverFull> getComputerhomeworkreceiverFullList() {
		return computerhomeworkreceiverFullList;
	}



	public void setComputerhomeworkreceiverFullList(
			List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList) {
		this.computerhomeworkreceiverFullList = computerhomeworkreceiverFullList;
	}



	public ComputerorderclassruledetailService getComputerorderclassruledetailService() {
		return computerorderclassruledetailService;
	}



	public void setComputerorderclassruledetailService(
			ComputerorderclassruledetailService computerorderclassruledetailService) {
		this.computerorderclassruledetailService = computerorderclassruledetailService;
	}



	public Computerorderclassruledetail getComputerorderclassruledetail() {
		return computerorderclassruledetail;
	}



	public void setComputerorderclassruledetail(
			Computerorderclassruledetail computerorderclassruledetail) {
		this.computerorderclassruledetail = computerorderclassruledetail;
	}



	public Computerorderclassruledetail getComputerorderclassruledetailModel() {
		return computerorderclassruledetailModel;
	}



	public void setComputerorderclassruledetailModel(
			Computerorderclassruledetail computerorderclassruledetailModel) {
		this.computerorderclassruledetailModel = computerorderclassruledetailModel;
	}



	public ComputerorderclassruledetailFull getComputerorderclassruledetailFull() {
		return computerorderclassruledetailFull;
	}



	public void setComputerorderclassruledetailFull(
			ComputerorderclassruledetailFull computerorderclassruledetailFull) {
		this.computerorderclassruledetailFull = computerorderclassruledetailFull;
	}



	public List<Computerorderclassruledetail> getComputerorderclassruledetailList() {
		return computerorderclassruledetailList;
	}



	public void setComputerorderclassruledetailList(
			List<Computerorderclassruledetail> computerorderclassruledetailList) {
		this.computerorderclassruledetailList = computerorderclassruledetailList;
	}



	public List<ComputerorderclassruledetailFull> getComputerorderclassruledetailFullList() {
		return computerorderclassruledetailFullList;
	}



	public void setComputerorderclassruledetailFullList(
			List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList) {
		this.computerorderclassruledetailFullList = computerorderclassruledetailFullList;
	}



	public String getReceiverids() {
		return receiverids;
	}



	public void setReceiverids(String receiverids) {
		this.receiverids = receiverids;
	}



	public Integer getClassid() {
		return classid;
	}



	public void setClassid(Integer classid) {
		this.classid = classid;
	}



	public Date getOrderstarttime() {
		return orderstarttime;
	}



	public void setOrderstarttime(Date orderstarttime) {
		this.orderstarttime = orderstarttime;
	}



	public Date getOrderendtime() {
		return orderendtime;
	}



	public void setOrderendtime(Date orderendtime) {
		this.orderendtime = orderendtime;
	}



	public Integer getAvailableordertime() {
		return availableordertime;
	}



	public void setAvailableordertime(Integer availableordertime) {
		this.availableordertime = availableordertime;
	}



	public String getAllowborrowpcids() {
		return allowborrowpcids;
	}



	public void setAllowborrowpcids(String allowborrowpcids) {
		this.allowborrowpcids = allowborrowpcids;
	}



	public static Log getLog() {
		return log;
	}



	public Integer getCurrentcomputerorderauditstatus() {
		return currentcomputerorderauditstatus;
	}


	public void setCurrentcomputerorderauditstatus(
			Integer currentcomputerorderauditstatus) {
		this.currentcomputerorderauditstatus = currentcomputerorderauditstatus;
	}


	public Integer getCurrentcomputerorderordertype() {
		return currentcomputerorderordertype;
	}


	public void setCurrentcomputerorderordertype(
			Integer currentcomputerorderordertype) {
		this.currentcomputerorderordertype = currentcomputerorderordertype;
	}


	
	
}
