package com.sbgl.app.actions.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.computer.ComputerAction;
import com.sbgl.app.actions.computer.ComputerorderActionUtil;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.app.services.order.OrderMainService;


@Scope("prototype") 
@Controller("IndexAction")
public class IndexAction extends BaseAction{
	
	private static final Log log = LogFactory.getLog(IndexAction.class);

	
	@Resource
	private ComputerorderService computerorderService;	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();
	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();
	private int computerorderid = 0; //entity full 的id属性名称		
	int computerorderId;//订单的id
	
	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	private Integer computerorderdetailid; //entity full 的id属性名称		
	
	@Resource
	private OrderMainService orderMainService;
	
	@Resource	
	private ComputerhomeworkService computerhomeworkService;	
	private List<ComputerhomeworkFull> newComputerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;	
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	
	private List<EquipmenborrowFull> equipmenborrowFullList;
	
	HashMap<Integer, ArrayList<Computerorderdetail>> computerorderdetailMapByComputermodelId = new HashMap<Integer,ArrayList<Computerorderdetail>>();
	HashMap<Integer, ArrayList<ComputerorderdetailFull>> computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
	
	List<ComputerorderFull> computerorderFullUnderwayList = new ArrayList<ComputerorderFull>();//进行中的预约
	

	public String index(){
		
		System.out.println("sss");
		Integer userid = this.getCurrentUserId();

		if(userid < 0){		
			actionMsg = "用户未登录";
			return ComputerConfig.usernotloginreturnstr;
		}
		

//		进行中的预约是：状态未审核的预约
		String selunderwayordersql = "  where a.createuserid="+userid + " and a.status in("+ComputerorderInfo.ComputerorderStatusAduitWait+","+ComputerorderInfo.ComputerorderStatusAduitReject+") order by a.createtime desc";
		computerorderFullUnderwayList = computerorderService.selectComputerorderFullByCondition(selunderwayordersql);
		
		equipmenborrowFullList = orderMainService.findUnderWayOrder(userid);
		log.info(computerorderFullUnderwayList.size());
		return SUCCESS;
	}

//	查询进行中的预约
	public void setUnderwayComputerorder(int userid){
//		进行中的预约是：状态未审核的预约
		String selunderwayordersql = "  where a.createuserid="+userid + " and a.status in("+ComputerorderInfo.ComputerorderStatusAduitWait+","+ComputerorderInfo.ComputerorderStatusAduitReject+") order by a.createtime desc";
		computerorderFullUnderwayList = computerorderService.selectComputerorderFullByCondition(selunderwayordersql);
		
//		根据作业接收者，查询新的课程预约
		computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverByUserAndOrder(userid, ComputerConfig.computerhomeworknotorder);
		String newhomeworksql="";
		for(int i=0; i<computerhomeworkreceiverList.size();i++){
			if( (computerhomeworkreceiverList.get(i).getHasorder()==null) || (computerhomeworkreceiverList.get(i).getHasorder() != ComputerConfig.computerhomeworkhasorder)){
					newhomeworksql += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}
		}
		if(newhomeworksql.length() > 1){
			newhomeworksql = newhomeworksql.substring(0,newhomeworksql.length()-1);
			newhomeworksql = " where a.id in (" +newhomeworksql+") "  + " order by computerhomeworkcreatetime desc ";
			newComputerhomeworkFullList = computerhomeworkService.selectComputerhomeworkFullByCondition(newhomeworksql);
		}
		ComputerorderActionUtil.setUnderwayComputerorder(computerorderFullUnderwayList, newComputerhomeworkFullList);
	}

	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}


	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}


	public Computerorder getComputerorder() {
		return computerorder;
	}


	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}


	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}


	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
	}


	public List<Computerorder> getComputerorderList() {
		return computerorderList;
	}


	public void setComputerorderList(List<Computerorder> computerorderList) {
		this.computerorderList = computerorderList;
	}


	public List<ComputerorderFull> getComputerorderFullList() {
		return computerorderFullList;
	}


	public void setComputerorderFullList(
			List<ComputerorderFull> computerorderFullList) {
		this.computerorderFullList = computerorderFullList;
	}


	public int getComputerorderid() {
		return computerorderid;
	}


	public void setComputerorderid(int computerorderid) {
		this.computerorderid = computerorderid;
	}


	public int getComputerorderId() {
		return computerorderId;
	}


	public void setComputerorderId(int computerorderId) {
		this.computerorderId = computerorderId;
	}


	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}


	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
	}


	public Computerorderdetail getComputerorderdetail() {
		return computerorderdetail;
	}


	public void setComputerorderdetail(Computerorderdetail computerorderdetail) {
		this.computerorderdetail = computerorderdetail;
	}


	public ComputerorderdetailFull getComputerorderdetailFull() {
		return computerorderdetailFull;
	}


	public void setComputerorderdetailFull(
			ComputerorderdetailFull computerorderdetailFull) {
		this.computerorderdetailFull = computerorderdetailFull;
	}


	public List<Computerorderdetail> getComputerorderdetailList() {
		return computerorderdetailList;
	}


	public void setComputerorderdetailList(
			List<Computerorderdetail> computerorderdetailList) {
		this.computerorderdetailList = computerorderdetailList;
	}


	public List<ComputerorderdetailFull> getComputerorderdetailFullList() {
		return computerorderdetailFullList;
	}


	public void setComputerorderdetailFullList(
			List<ComputerorderdetailFull> computerorderdetailFullList) {
		this.computerorderdetailFullList = computerorderdetailFullList;
	}


	public Integer getComputerorderdetailid() {
		return computerorderdetailid;
	}


	public void setComputerorderdetailid(Integer computerorderdetailid) {
		this.computerorderdetailid = computerorderdetailid;
	}


	public HashMap<Integer, ArrayList<Computerorderdetail>> getComputerorderdetailMapByComputermodelId() {
		return computerorderdetailMapByComputermodelId;
	}


	public void setComputerorderdetailMapByComputermodelId(
			HashMap<Integer, ArrayList<Computerorderdetail>> computerorderdetailMapByComputermodelId) {
		this.computerorderdetailMapByComputermodelId = computerorderdetailMapByComputermodelId;
	}


	public HashMap<Integer, ArrayList<ComputerorderdetailFull>> getComputerorderdetailFullMapByComputermodelId() {
		return computerorderdetailFullMapByComputermodelId;
	}


	public void setComputerorderdetailFullMapByComputermodelId(
			HashMap<Integer, ArrayList<ComputerorderdetailFull>> computerorderdetailFullMapByComputermodelId) {
		this.computerorderdetailFullMapByComputermodelId = computerorderdetailFullMapByComputermodelId;
	}


	public List<ComputerorderFull> getComputerorderFullUnderwayList() {
		return computerorderFullUnderwayList;
	}


	public void setComputerorderFullUnderwayList(
			List<ComputerorderFull> computerorderFullUnderwayList) {
		this.computerorderFullUnderwayList = computerorderFullUnderwayList;
	}


	public List<EquipmenborrowFull> getEquipmenborrowFullList() {
		return equipmenborrowFullList;
	}


	public void setEquipmenborrowFullList(
			List<EquipmenborrowFull> equipmenborrowFullList) {
		this.equipmenborrowFullList = equipmenborrowFullList;
	}

	public OrderMainService getOrderMainService() {
		return orderMainService;
	}

	public void setOrderMainService(OrderMainService orderMainService) {
		this.orderMainService = orderMainService;
	}

	public ComputerhomeworkService getComputerhomeworkService() {
		return computerhomeworkService;
	}

	public void setComputerhomeworkService(
			ComputerhomeworkService computerhomeworkService) {
		this.computerhomeworkService = computerhomeworkService;
	}

	public List<ComputerhomeworkFull> getNewComputerhomeworkFullList() {
		return newComputerhomeworkFullList;
	}

	public void setNewComputerhomeworkFullList(
			List<ComputerhomeworkFull> newComputerhomeworkFullList) {
		this.newComputerhomeworkFullList = newComputerhomeworkFullList;
	}

	public ComputerhomeworkreceiverService getComputerhomeworkreceiverService() {
		return computerhomeworkreceiverService;
	}

	public void setComputerhomeworkreceiverService(
			ComputerhomeworkreceiverService computerhomeworkreceiverService) {
		this.computerhomeworkreceiverService = computerhomeworkreceiverService;
	}

	public List<Computerhomeworkreceiver> getComputerhomeworkreceiverList() {
		return computerhomeworkreceiverList;
	}

	public void setComputerhomeworkreceiverList(
			List<Computerhomeworkreceiver> computerhomeworkreceiverList) {
		this.computerhomeworkreceiverList = computerhomeworkreceiverList;
	}

	public static Log getLog() {
		return log;
	}
	
	
	
	
}
