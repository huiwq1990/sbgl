package com.sbgl.app.actions.orderadmin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.order.OrderMainAction;
import com.sbgl.app.actions.teach.TeachActionUtil;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Ordercourserule;
import com.sbgl.app.entity.Ordercourseruledetail;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.services.equipment.EquipGroupService;
import com.sbgl.app.services.order.OrderMainService;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.app.services.teach.CourseService;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.util.DateUtil;
import com.sbgl.util.Page;


@Scope("prototype") 
@Controller("OrderAdminAction")
public class OrderAdminAction  extends ActionSupport  implements SessionAware {
	private static final Log log = LogFactory.getLog(OrderMainAction.class);
	private Map<String, Object> session;
	
	@Resource
	private OrderAdminService orderAdminService;
	
	@Resource
	private CourseService courseService;
	
	@Resource
	private EquipGroupService equipGroupService;
	
	@Resource
	private CourseconfigService courseconfigService;	
	
	
	private String dealtype;  //0:全部，1：待审核，2：待出库，3：已出库,4：已完成
	private String ordertype;  //0:全部， 1：个人预约，2：课程预约
	private String pageNo;
	private Page page;
	private Integer borrowId;
	private Integer courseId;
	private String ruleName;
	private String tag;
	private String message;
	private String ids;
	private Integer courseruleid;
	private Ordercourserule ordercourserule;
	private List<Equipmentclassification> classification1List;
	
	private List<EquipmentgroupFull> equipmentgroupFullList;
	
	private Map<Integer,List<EquipmentFull>> classequipmap;
	
	private List<EquipmenborrowFull> equipmenborrowFullList;
	
	private List<CourseFull> courseFullList; 
	
	private OrderCountFull orderCountFull;
	
	private List<OrdercourseruleFull> ordercourseruleFullList;
	
	private List<Clazz> claszzList;


	private HashMap<Integer, ArrayList<Ordercourserule>> courseruleMap;
	

	private Courseconfig courseconfig = new Courseconfig();
	private Courseconfig currentCourseconfig = new Courseconfig();
	List<Courseconfig> courseconfigList = new ArrayList<Courseconfig>();
	
	
	private int selsemesterweek;
	private int selsemesterid;
	private int totalweek;

	private List<String> showDate = new ArrayList<String>();
	private List<String> showDayList = new ArrayList<String>();
	

	private Map<Integer,ArrayList<EquipmenborrowFull>> orderEquipmenborrowMap;
	
	private String inputSendTo;
	private String inputEquipRule;
	private String inputMsgTitle;
	private String inputContent;
	private String inputDataRange;
	private String inputDataRange2;
	
	public String orderAdmin(){
		if(dealtype==null||dealtype.equals("")){
			dealtype = "0";
		}
		if(ordertype==null||ordertype.equals("")){
			ordertype= "0";
		}
		if(pageNo==null||pageNo.equals("")){
			pageNo = "1";
		}
		
		page = new Page(Integer.parseInt(pageNo),10);
		equipmenborrowFullList = orderAdminService.findEquipmenborrow(dealtype, ordertype, page);
		orderCountFull = orderAdminService.findOrderCount(ordertype);
		if(dealtype.equals("0")){
			page.setTotalCount(orderCountFull.getOrderCount5().intValue());
			int temp = orderCountFull.getOrderCount5().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount5().intValue()/page.getPageSize();
			if(temp==0){
				if(temp2==0){
					temp2=1;
				}
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}else if(dealtype.equals("1")){
			page.setTotalCount(orderCountFull.getOrderCount1().intValue());
			int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
			if(temp==0){
				if(temp2==0){
					temp2=1;
				}
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}else if(dealtype.equals("2")){
			page.setTotalCount(orderCountFull.getOrderCount2().intValue());
			int temp = orderCountFull.getOrderCount2().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount2().intValue()/page.getPageSize();
			if(temp==0){
				if(temp2==0){
					temp2=1;
				}
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}else if(dealtype.equals("3")){
			page.setTotalCount(orderCountFull.getOrderCount3().intValue());
			int temp = orderCountFull.getOrderCount3().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount3().intValue()/page.getPageSize();
			if(temp==0){
				if(temp2==0){
					temp2=1;
				}
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}
		return SUCCESS;
	}

	
	public String orderclassrule(){
		if(courseId==null||courseId.equals("")){
			courseId = 0;
		}
		if(pageNo==null||pageNo.equals("")){
			pageNo = "1";
		}
		
		page = new Page(Integer.parseInt(pageNo),10);
		ordercourseruleFullList = orderAdminService.findOrderClassRule(courseId, page);
		orderCountFull = orderAdminService.findOrderCountRule(courseId);
		page.setTotalCount(orderCountFull.getOrderCount1().intValue());
		int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
		int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
		if(temp==0){
			if(temp2==0){
				temp2=1;
			}
			page.setTotalpage(temp2);
		}else{
			page.setTotalpage(temp2+1);
		}
		String lantype = (String) session.get(CommonConfig.sessionLanguagetype);
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+lantype);
		return SUCCESS;
	}
	
	public String orderclassrule3(){
		String lantype = (String) session.get(CommonConfig.sessionLanguagetype);
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		classification1List = orderAdminService.findTopEquipmentclass(lantype);
		classequipmap = orderAdminService.fingclassequipMap(classification1List,courseruleid);
		courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+lantype);
		equipmentgroupFullList = equipGroupService.findEquipmentGroup(courseruleid);
		if(courseruleid!=null){
			ordercourserule = orderAdminService.findOrdercourserule(courseruleid);
		}
		return SUCCESS;
	}
	
	//创建规则
	public String addorderclassrule(){
		try{
			boolean flag = false;
			Loginuser loginuser = (Loginuser) session.get("loginUser");
			flag = orderAdminService.addorderclassrule(courseId,ruleName,ids,loginuser,courseruleid);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "订单入库失败";
			}
		}catch(Exception e){
			tag = "2";
			message = "订单入库失败";
			e.printStackTrace();
			log.error(e);
		}
		return SUCCESS;
	}
	
	//发送规则
	public String sendRule(){
		try{
			boolean flag = false;
			Loginuser loginuser = (Loginuser) session.get("loginUser");
			flag = orderAdminService.sendRule(inputSendTo,inputEquipRule,inputMsgTitle,inputContent,inputDataRange,inputDataRange2,loginuser);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "发送规则失败";
			}
		}catch(Exception e){
			tag = "2";
			message = "发送规则失败";
			e.printStackTrace();
			log.error(e);
		}
		return SUCCESS;
	}
	
	//删除规则 
	public String delequipclassrule(){
		
		boolean flag = false;
		Loginuser loginuser = (Loginuser) session.get("loginUser");
		flag = orderAdminService.delequipclassrule(courseruleid);
		if(flag){
			if(courseId==null||courseId.equals("")){
				courseId = 0;
			}
			if(pageNo==null||pageNo.equals("")){
				pageNo = "1";
			}
			
			page = new Page(Integer.parseInt(pageNo),10);
			ordercourseruleFullList = orderAdminService.findOrderClassRule(courseId, page);
			orderCountFull = orderAdminService.findOrderCountRule(courseId);
			page.setTotalCount(orderCountFull.getOrderCount1().intValue());
			int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
			if(temp==0){
				if(temp2==0){
					temp2=1;
				}
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
			String lantype = (String) session.get(CommonConfig.sessionLanguagetype);
			if(lantype==null||lantype.equals("")){
				lantype = "0";
			}
			courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+lantype);
		}

		return SUCCESS;
	}
	
	//发送规则 
	public String equClassSend(){
			
		//	课程信息
		courseFullList  = courseService.selectCourseFullByCondition(" where a.languagetype = "+CommonConfig.languagech);
		courseruleMap = orderAdminService.findCourseRule(courseFullList);
		
		//班级信息
		claszzList = orderAdminService.findAllClazz();
		

		return SUCCESS;
	}
	
	//批量删除预约
	public String deleteBorrow(){
		boolean flag = false;
		if(ids!=null){
			ids = ids.substring(0,ids.length()-1);
			String[] temp1 = ids.split("_");
			flag = orderAdminService.deleteBorrow(temp1);
			if(flag){
				if(dealtype==null||dealtype.equals("")){
					dealtype = "0";
				}
				if(ordertype==null||ordertype.equals("")){
					ordertype= "0";
				}
				if(pageNo==null||pageNo.equals("")){
					pageNo = "1";
				}
				
				page = new Page(Integer.parseInt(pageNo),10);
				equipmenborrowFullList = orderAdminService.findEquipmenborrow(dealtype, ordertype, page);
				orderCountFull = orderAdminService.findOrderCount(ordertype);
				if(dealtype.equals("0")){
					page.setTotalCount(orderCountFull.getOrderCount5().intValue());
					int temp = orderCountFull.getOrderCount5().intValue()%page.getPageSize();
					int temp2 = orderCountFull.getOrderCount5().intValue()/page.getPageSize();
					if(temp==0){
						if(temp2==0){
							temp2=1;
						}
						page.setTotalpage(temp2);
					}else{
						page.setTotalpage(temp2+1);
					}
				}else if(dealtype.equals("1")){
					page.setTotalCount(orderCountFull.getOrderCount1().intValue());
					int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
					int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
					if(temp==0){
						if(temp2==0){
							temp2=1;
						}
						page.setTotalpage(temp2);
					}else{
						page.setTotalpage(temp2+1);
					}
				}else if(dealtype.equals("2")){
					page.setTotalCount(orderCountFull.getOrderCount2().intValue());
					int temp = orderCountFull.getOrderCount2().intValue()%page.getPageSize();
					int temp2 = orderCountFull.getOrderCount2().intValue()/page.getPageSize();
					if(temp==0){
						if(temp2==0){
							temp2=1;
						}
						page.setTotalpage(temp2);
					}else{
						page.setTotalpage(temp2+1);
					}
				}else if(dealtype.equals("3")){
					page.setTotalCount(orderCountFull.getOrderCount3().intValue());
					int temp = orderCountFull.getOrderCount3().intValue()%page.getPageSize();
					int temp2 = orderCountFull.getOrderCount3().intValue()/page.getPageSize();
					if(temp==0){
						if(temp2==0){
							temp2=1;
						}
						page.setTotalpage(temp2);
					}else{
						page.setTotalpage(temp2+1);
					}
				}
			}
		}
			
		return SUCCESS;
	}
	
	/**
	 * 日历模式浏览预约
	 * @return
	 */
	public String orderCalendarShow(){
		try{
		
			
			Date currentDate = DateUtil.currentDate();
			int currentPeriod =  BorrowperiodUtil.getBorrowTimePeriod(currentDate);
			Date currentDay = DateUtil.getDateDayDate(currentDate);
			courseconfigList = courseconfigService.selAll();
			
			Date semesterStartDate ;
			Date selStartDate;
			Date selEndDate;
			if(selsemesterid <= 0 || selsemesterweek <=0){//设置默认为当前学期
				currentCourseconfig = courseconfigService.getCurrentCourseconfig();
				if(currentCourseconfig == null){
					return "notsetcurrentCourseconfig";
				}
				
			      
				selStartDate = DateUtil.getDateDayDate(DateUtil.getChineseWeekMondayCurrent());
				selEndDate =  DateUtil.getDateDayDate(DateUtil.addDay(selStartDate,6));
				selsemesterweek = DateUtil.daysBetween(currentCourseconfig.getFirstweekfirstday(), selStartDate)/7+1;
			}else{
				currentCourseconfig = courseconfigService.selectCourseconfigById(selsemesterid);
				if(currentCourseconfig == null){
					return "notsetcurrentCourseconfig";
				}
				selStartDate = DateUtil.getDateDayDate(DateUtil.addDay(currentCourseconfig.getFirstweekfirstday(), 7*(selsemesterweek-1)));
				selEndDate =  DateUtil.getDateDayDate(DateUtil.addDay(selStartDate,6));
				selsemesterweek = DateUtil.daysBetween(currentCourseconfig.getFirstweekfirstday(), selStartDate)/7+1;
			}

			orderEquipmenborrowMap = new HashMap<Integer, ArrayList<EquipmenborrowFull>>();
			for(int i=0; i < 7; i++){
				String date = DateUtil.dateFormat(DateUtil.addDay(selStartDate,i), "yyyy-MM-dd");
				
				List<EquipmenborrowFull> list = orderAdminService.getEquipmenborrowByDate(date);
				
				orderEquipmenborrowMap.put((i+1), new ArrayList<EquipmenborrowFull>());
				if(list!=null&&list.size()>0){
					for(EquipmenborrowFull equipmenborrowFull :list){		
						orderEquipmenborrowMap.get((i+1)).add(equipmenborrowFull);
					}
				}
				showDate.add(date);
				showDayList.add(DateUtil.getWeekOfDate(DateUtil.addDay(selStartDate,i)));
			}
	

			//默认显示周数为设置值，如果有设置下一学期第一天，这计算处理
			totalweek = currentCourseconfig.getWeeknum();
			if(currentCourseconfig.getNextsemesterdaybefore()!=null){
				totalweek = DateUtil.daysBetween(currentCourseconfig.getFirstweekfirstday(), currentCourseconfig.getNextsemesterdaybefore())/7+1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}


		return SUCCESS;
		
	}

	public List<EquipmenborrowFull> getEquipmenborrowFullList() {
		return equipmenborrowFullList;
	}


	public void setEquipmenborrowFullList(
			List<EquipmenborrowFull> equipmenborrowFullList) {
		this.equipmenborrowFullList = equipmenborrowFullList;
	}


	public String getDealtype() {
		return dealtype;
	}


	public void setDealtype(String dealtype) {
		this.dealtype = dealtype;
	}


	public String getOrdertype() {
		return ordertype;
	}


	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}


	public String getPageNo() {
		return pageNo;
	}


	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}


	public OrderCountFull getOrderCountFull() {
		return orderCountFull;
	}


	public void setOrderCountFull(OrderCountFull orderCountFull) {
		this.orderCountFull = orderCountFull;
	}


	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}


	public Integer getBorrowId() {
		return borrowId;
	}

	
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}


	public List<OrdercourseruleFull> getOrdercourseruleFullList() {
		return ordercourseruleFullList;
	}


	public void setOrdercourseruleFullList(
			List<OrdercourseruleFull> ordercourseruleFullList) {
		this.ordercourseruleFullList = ordercourseruleFullList;
	}


	public Integer getCourseId() {
		return courseId;
	}


	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}


	public List<CourseFull> getCourseFullList() {
		return courseFullList;
	}


	public void setCourseFullList(List<CourseFull> courseFullList) {
		this.courseFullList = courseFullList;
	}


	public List<Equipmentclassification> getClassification1List() {
		return classification1List;
	}


	public void setClassification1List(
			List<Equipmentclassification> classification1List) {
		this.classification1List = classification1List;
	}


	public Map<Integer, List<EquipmentFull>> getClassequipmap() {
		return classequipmap;
	}


	public void setClassequipmap(Map<Integer, List<EquipmentFull>> classequipmap) {
		this.classequipmap = classequipmap;
	}


	public String getRuleName() {
		return ruleName;
	}


	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Integer getCourseruleid() {
		return courseruleid;
	}


	public void setCourseruleid(Integer courseruleid) {
		this.courseruleid = courseruleid;
	}


	public Ordercourserule getOrdercourserule() {
		return ordercourserule;
	}


	public void setOrdercourserule(Ordercourserule ordercourserule) {
		this.ordercourserule = ordercourserule;
	}


	public HashMap<Integer, ArrayList<Ordercourserule>> getCourseruleMap() {
		return courseruleMap;
	}


	public void setCourseruleMap(
			HashMap<Integer, ArrayList<Ordercourserule>> courseruleMap) {
		this.courseruleMap = courseruleMap;
	}


	public List<Clazz> getClaszzList() {
		return claszzList;
	}


	public void setClaszzList(List<Clazz> claszzList) {
		this.claszzList = claszzList;
	}


	public String getInputSendTo() {
		return inputSendTo;
	}


	public void setInputSendTo(String inputSendTo) {
		this.inputSendTo = inputSendTo;
	}


	public String getInputEquipRule() {
		return inputEquipRule;
	}


	public void setInputEquipRule(String inputEquipRule) {
		this.inputEquipRule = inputEquipRule;
	}


	public String getInputMsgTitle() {
		return inputMsgTitle;
	}


	public void setInputMsgTitle(String inputMsgTitle) {
		this.inputMsgTitle = inputMsgTitle;
	}


	public String getInputContent() {
		return inputContent;
	}


	public void setInputContent(String inputContent) {
		this.inputContent = inputContent;
	}


	public String getInputDataRange() {
		return inputDataRange;
	}


	public void setInputDataRange(String inputDataRange) {
		this.inputDataRange = inputDataRange;
	}


	public String getInputDataRange2() {
		return inputDataRange2;
	}


	public void setInputDataRange2(String inputDataRange2) {
		this.inputDataRange2 = inputDataRange2;
	}


	public List<EquipmentgroupFull> getEquipmentgroupFullList() {
		return equipmentgroupFullList;
	}


	public void setEquipmentgroupFullList(
			List<EquipmentgroupFull> equipmentgroupFullList) {
		this.equipmentgroupFullList = equipmentgroupFullList;
	}


	public Courseconfig getCourseconfig() {
		return courseconfig;
	}


	public void setCourseconfig(Courseconfig courseconfig) {
		this.courseconfig = courseconfig;
	}


	public Courseconfig getCurrentCourseconfig() {
		return currentCourseconfig;
	}


	public void setCurrentCourseconfig(Courseconfig currentCourseconfig) {
		this.currentCourseconfig = currentCourseconfig;
	}


	public List<Courseconfig> getCourseconfigList() {
		return courseconfigList;
	}


	public void setCourseconfigList(List<Courseconfig> courseconfigList) {
		this.courseconfigList = courseconfigList;
	}


	public int getSelsemesterweek() {
		return selsemesterweek;
	}


	public void setSelsemesterweek(int selsemesterweek) {
		this.selsemesterweek = selsemesterweek;
	}


	public int getSelsemesterid() {
		return selsemesterid;
	}


	public void setSelsemesterid(int selsemesterid) {
		this.selsemesterid = selsemesterid;
	}


	public int getTotalweek() {
		return totalweek;
	}


	public void setTotalweek(int totalweek) {
		this.totalweek = totalweek;
	}


	public List<String> getShowDate() {
		return showDate;
	}


	public void setShowDate(List<String> showDate) {
		this.showDate = showDate;
	}


	public List<String> getShowDayList() {
		return showDayList;
	}


	public void setShowDayList(List<String> showDayList) {
		this.showDayList = showDayList;
	}


	public Map<Integer, ArrayList<EquipmenborrowFull>> getOrderEquipmenborrowMap() {
		return orderEquipmenborrowMap;
	}


	public void setOrderEquipmenborrowMap(
			Map<Integer, ArrayList<EquipmenborrowFull>> orderEquipmenborrowMap) {
		this.orderEquipmenborrowMap = orderEquipmenborrowMap;
	}


	
	

	

	
	
}
