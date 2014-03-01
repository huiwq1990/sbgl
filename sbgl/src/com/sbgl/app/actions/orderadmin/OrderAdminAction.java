package com.sbgl.app.actions.orderadmin;

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
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.order.OrderMainAction;
import com.sbgl.app.entity.CourseFull;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Ordercourserule;
import com.sbgl.app.services.order.OrderMainService;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.app.services.teach.CourseService;
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
	
	private String dealtype;  //0:全部，1：待审核，2：待出库，3：已出库	
	private String ordertype;
	private String pageNo;
	private Page page;
	private Integer borrowId;
	private Integer courseId;
	private List<Equipmentclassification> classification1List;
	
	private Map<Integer,List<EquipmentFull>> classequipmap;
	
	private List<EquipmenborrowFull> equipmenborrowFullList;
	
	private List<CourseFull> courseFullList; 
	
	private OrderCountFull orderCountFull;
	
	private List<OrdercourseruleFull> ordercourseruleFullList;
	
	
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
		classification1List = orderAdminService.findTopEquipmentclass();
		classequipmap = orderAdminService.fingclassequipMap(classification1List);
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
	
}
