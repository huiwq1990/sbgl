package com.sbgl.app.actions.order;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.services.order.OrderMainService;
import com.sbgl.common.DataError;
import com.sbgl.util.DateUtil;

@Scope("prototype") 
@Controller("OrderMainAction")
public class OrderMainAction  extends ActionSupport  {
	private static final Log log = LogFactory.getLog(OrderMainAction.class);
	
	@Resource
	private OrderMainService orderMainService;
	
	private List<Equipmentclassification> classification1List;
	private List<Equipmentclassification> classification2List;
	private List<EquipmentFull> equipmentList;
	private Integer equipmentId;
	private EquipmentFull equipmentFull;
	private List<Integer> equipmentNums;
	private List<Long> equipmentIds;
	private String fromDate;
	private String endDate;
	private Integer parentClassId;
	private Integer class1Id;
	private String class1Name;
	private Integer classificationId;
	private String serach;
	private String daynum;
	private String equIds;
	private String equNums;
	private String message;
	private String tag;
	private Integer borrowId;
	
	//进入设备管理页面
	public String equipmentBooking(){
		if(fromDate==null||fromDate.equals("")){
			fromDate = DateUtil.date.format(new Date());		
			if(endDate==null||endDate.equals("")){
				endDate = DateUtil.addDay2(fromDate, 1);
			}
		}else{
			if(endDate==null||endDate.equals("")){
				endDate = DateUtil.addDay2(fromDate, 1);
			}
		}
		classification1List = orderMainService.findTopEquipmentclass();
		if(classification1List!=null){
			class1Id = classification1List.get(0).getClassificationid();
			class1Name = classification1List.get(0).getName();
		}
		classification2List = orderMainService.findSecondEquipmentclass();
		equipmentList = orderMainService.findEquipmentByClss(fromDate,endDate);
		return SUCCESS;
	}
	
	//动态刷新设备管理页面
	public String equipmentByClass(){
		if(fromDate==null||fromDate.equals("")){
			fromDate = DateUtil.date.format(new Date());		
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}else{
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}
		
		Equipmentclassification equipmentclassification = new Equipmentclassification();
		equipmentclassification = orderMainService.findEquipmentclassification(parentClassId);
		class1Name = equipmentclassification.getName();
		classification2List = orderMainService.findSecondEquipmentclass(parentClassId);
		if(serach!=null&&!serach.equals("")){
			if(classificationId==0){
				equipmentList = orderMainService.findEquipmentByClss(parentClassId,fromDate,endDate,serach);
			}else{
				equipmentList = orderMainService.findEquipmentByClss(classificationId,fromDate,endDate,serach);
			}
		}else{
			if(classificationId==0){
				equipmentList = orderMainService.findEquipmentByClss(parentClassId,fromDate,endDate);
			}else{
				equipmentList = orderMainService.findEquipmentByClss(classificationId,fromDate,endDate);
			}
		}
		return SUCCESS;
	}

	public String equipDetail(){	
		if(fromDate==null||fromDate.equals("")){
			fromDate = DateUtil.date.format(new Date());		
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}else{
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}
		daynum = orderMainService.findDayNum(equipmentId,fromDate,endDate);
		return SUCCESS;
	}
	
	//根据商品id刷新页面详情
	public String equipOrdDetail(){		
		if(fromDate==null||fromDate.equals("")){
			fromDate = DateUtil.date.format(new Date());		
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}else{
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}
		equipmentFull = orderMainService.findEquipmentById(equipmentId,fromDate,endDate);
		return SUCCESS;
	}
	
	
	//提交订单
	public String subOrder(){
		if(fromDate==null||fromDate.equals("")){
			fromDate = DateUtil.date.format(new Date());		
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}else{
			if(endDate==null||endDate.equals("")){
				endDate = fromDate;
			}
		}
		try{	
			borrowId = orderMainService.subOrder(equIds, equNums, fromDate, endDate);
			tag = "1";
		}catch(DataError e){		
			tag = "2";
			message = e.getMessage();
		}catch(Exception e){
			tag = "2";
			message = "提交订单失败";
			log.error(e);
		}
		return SUCCESS;
	}
	
	//进入设备管理中心
	public String equipmentBookingMain(){
		return SUCCESS;
	}
	
	public List<Equipmentclassification> getClassification1List() {
		return classification1List;
	}

	public void setClassification1List(
			List<Equipmentclassification> classification1List) {
		this.classification1List = classification1List;
	}

	public List<Equipmentclassification> getClassification2List() {
		return classification2List;
	}

	public void setClassification2List(
			List<Equipmentclassification> classification2List) {
		this.classification2List = classification2List;
	}

	public List<EquipmentFull> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentFull> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public EquipmentFull getEquipmentFull() {
		return equipmentFull;
	}

	public void setEquipmentFull(EquipmentFull equipmentFull) {
		this.equipmentFull = equipmentFull;
	}

	public List<Integer> getEquipmentNums() {
		return equipmentNums;
	}

	public void setEquipmentNums(List<Integer> equipmentNums) {
		this.equipmentNums = equipmentNums;
	}

	public List<Long> getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(List<Long> equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getParentClassId() {
		return parentClassId;
	}

	public void setParentClassId(Integer parentClassId) {
		this.parentClassId = parentClassId;
	}

	public Integer getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(Integer classificationId) {
		this.classificationId = classificationId;
	}

	public Integer getClass1Id() {
		return class1Id;
	}

	public void setClass1Id(Integer class1Id) {
		this.class1Id = class1Id;
	}

	public String getClass1Name() {
		return class1Name;
	}

	public void setClass1Name(String class1Name) {
		this.class1Name = class1Name;
	}

	public String getSerach() {
		return serach;
	}

	public void setSerach(String serach) {
		this.serach = serach;
	}

	public String getDaynum() {
		return daynum;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

	public String getEquIds() {
		return equIds;
	}

	public void setEquIds(String equIds) {
		this.equIds = equIds;
	}

	public String getEquNums() {
		return equNums;
	}

	public void setEquNums(String equNums) {
		this.equNums = equNums;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}


	
}
