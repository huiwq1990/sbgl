package com.sbgl.app.actions.order;

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
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.order.OrderExamService;
import com.sbgl.app.services.order.OrderFinishService;
import com.sbgl.common.DataError;

@Scope("prototype") 
@Controller("OrderExamAction")
public class OrderExamAction  extends ActionSupport  implements SessionAware{
	private static final Log log = LogFactory.getLog(OrderExamAction.class);
	private Map<String, Object> session;
	
	private List<EquipmentFull> equipmentList;
	private List<Equipmentclassification> equipmentclassificationList;
	private Map<Integer,List<EquipmentFull>> equipmentMap;
	private Integer borrowId;
	private EquipmenborrowFull equipmenborrowFull;
	private Integer examstate;
	private String tag;
	private String message;
	private String examcontent;
	private String inputEquipContent;
	private String ids;
	private Loginuser userdetail;
	private Loginuser userdetail2;
	
	private Integer userId;

	
	@Resource
	private OrderExamService orderExamService;
	
	@Resource
	private OrderFinishService orderFinishService;
	
//	进入查看订单确认页面
	public String equipmentConfirm(){
		String lantype = (String) session.get(CommonConfig.sessionLanguagetype);
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		equipmentList = orderFinishService.findListBorrow(borrowId,lantype);
		equipmenborrowFull = orderFinishService.findEquipmenborrow(borrowId);
		return SUCCESS;
	}
	
	//	进入查看订单审核页面
	public String equipmentExaming(){
		String lantype = CommonConfig.languagechStr;
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		equipmentList = orderFinishService.findListBorrow(borrowId,lantype);
		equipmenborrowFull = orderFinishService.findEquipmenborrow(borrowId);
		userdetail = orderExamService.userdetail(equipmenborrowFull.getTeacherid());
		userdetail2 = orderExamService.userdetail(equipmenborrowFull.getUserid());
		return SUCCESS;
	}
	
	//进入设备出库页面
	public String orderalibrary(){
		String lantype = CommonConfig.languagechStr;
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		equipmentclassificationList = orderExamService.findclassList(borrowId,lantype);
		equipmentMap = orderExamService.findMapBorrow(borrowId,1,lantype);
		equipmenborrowFull = orderFinishService.findEquipmenborrow(borrowId);
		return SUCCESS;
	}
	
	//进入设备入库页面
	public String orderstorage(){
		String lantype = CommonConfig.languagechStr;
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		equipmentclassificationList = orderExamService.findclassList(borrowId,lantype);
		equipmentMap = orderExamService.findMapBorrow(borrowId,2,lantype);
		equipmenborrowFull = orderFinishService.findEquipmenborrow(borrowId);
		return SUCCESS;
	}
	
	
	
	//审核订单
	public String examorder(){
		try{
			boolean flag = false;
			Loginuser loginuser = (Loginuser) session.get("loginUser");
			flag = orderExamService.examorder(borrowId,examstate,examcontent,loginuser);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "审核订单失败";
			}
		}catch(Exception e){
			tag = "2";
			message = "审核订单失败";
			e.printStackTrace();
			log.error(e);
		}
		return SUCCESS;
	}
	
	//设备出库
	public String alibraryorder(){
		try{
			boolean flag = false;
			Loginuser loginuser = (Loginuser) session.get("loginUser");
			flag = orderExamService.alibraryorder(borrowId,ids,loginuser);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "订单出库失败";
			}
		}catch(Exception e){
			tag = "2";
			message = "订单出库失败";
			e.printStackTrace();
			log.error(e);
		}
		return SUCCESS;
	}
	
	//用户详情
	public String userdetail(){
		userdetail = orderExamService.userdetail(userId);
		return SUCCESS;
	}
	
	
	
	
	//设备出库
	public String storageorder(){
		try{
			boolean flag = false;
			Loginuser loginuser = (Loginuser) session.get("loginUser");
			flag = orderExamService.storageorder(borrowId,ids,loginuser);
			if(flag){
				tag = "1";
			}else{
				tag = "2";
				message = "订单入库失败";
			}
		}catch(DataError e){
			tag = "2";
			message = e.getMessage();
			e.printStackTrace();
			log.error(e);
		}catch(Exception e){
			tag = "2";
			message = "订单入库失败";
			e.printStackTrace();
			log.error(e);
		}
		return SUCCESS;
	}

	
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	    this.session = session;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public EquipmenborrowFull getEquipmenborrowFull() {
		return equipmenborrowFull;
	}

	public void setEquipmenborrowFull(EquipmenborrowFull equipmenborrowFull) {
		this.equipmenborrowFull = equipmenborrowFull;
	}

	public List<EquipmentFull> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<EquipmentFull> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public Integer getExamstate() {
		return examstate;
	}

	public void setExamstate(Integer examstate) {
		this.examstate = examstate;
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

	public String getExamcontent() {
		return examcontent;
	}

	public List<Equipmentclassification> getEquipmentclassificationList() {
		return equipmentclassificationList;
	}

	public void setEquipmentclassificationList(
			List<Equipmentclassification> equipmentclassificationList) {
		this.equipmentclassificationList = equipmentclassificationList;
	}

	public void setExamcontent(String examcontent) {
		this.examcontent = examcontent;
	}

	public Map<Integer, List<EquipmentFull>> getEquipmentMap() {
		return equipmentMap;
	}

	public void setEquipmentMap(Map<Integer, List<EquipmentFull>> equipmentMap) {
		this.equipmentMap = equipmentMap;
	}

	public String getInputEquipContent() {
		return inputEquipContent;
	}

	public void setInputEquipContent(String inputEquipContent) {
		this.inputEquipContent = inputEquipContent;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Loginuser getUserdetail() {
		return userdetail;
	}

	public void setUserdetail(Loginuser userdetail) {
		this.userdetail = userdetail;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Loginuser getUserdetail2() {
		return userdetail2;
	}

	public void setUserdetail2(Loginuser userdetail2) {
		this.userdetail2 = userdetail2;
	}

}
