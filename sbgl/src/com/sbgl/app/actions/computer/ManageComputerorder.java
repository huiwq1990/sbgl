package com.sbgl.app.actions.computer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.actions.util.SnActionUtil;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.app.services.computer.ComputerstatusService;
import com.sbgl.util.DateUtil;
import com.sbgl.util.ReturnJson;

@Scope("prototype") 
@Controller("ManageComputerorder")
public class ManageComputerorder extends BaseAction implements ModelDriven<Computerorder> {
	private static final Log log = LogFactory.getLog(ManageComputerorder.class);


	@Resource
	private ComputerorderService computerorderService;	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();
	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();
	private int computerorderid; //entity full 的id属性名称		
	int computerorderId;//订单的id
	
	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	private Integer computerorderdetailid; //entity full 的id属性名称		
	
	
	HashMap<Integer, ArrayList<Computerorderdetail>> computerorderdetailMapByComputermodelId = new HashMap<Integer,ArrayList<Computerorderdetail>>();
	HashMap<Integer, ArrayList<ComputerorderdetailFull>> computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
	
	
	
	@Resource
	private ComputerstatusService computerstatusService;
	int computerstatusid = 0;
	List<Computerstatus> computerstatusList = new ArrayList<Computerstatus>();
	List<ComputerstatusFull> computerstatusFullList = new ArrayList<ComputerstatusFull>();
	
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;	
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();//实例化一个模型
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();//实例化一个模型
	
	
	private int ComputerorderStatusAduitAll = ComputerorderInfo.ComputerorderStatusAduitAll;
	private int ComputerorderStatusAduitPass = ComputerorderInfo.ComputerorderStatusAduitPass;
	private int ComputerorderStatusAduitReject = ComputerorderInfo.ComputerorderStatusAduitReject;
	private int ComputerorderStatusAduitDel = ComputerorderInfo.ComputerorderStatusAduitDel;
	private int ComputerorderStatusAduitWait = ComputerorderInfo.ComputerorderStatusAduitWait;
	private int IndividualOrder = ComputerorderInfo.IndividualOrder;
	private int ClassOrder = ComputerorderInfo.ClassOrder;
	
	
//	查看自己的预约
	List<ComputerorderFull> computerorderFullUnderwayList = new ArrayList<ComputerorderFull>();//进行中的预约
	List<ComputerorderFull> computerorderFullFinishList = new ArrayList<ComputerorderFull>();//进行中的预约
	
//	全局参数	
	private String userid;
	
	
//	提交预约表单的参数
	private String orderInfoStr;
	private Object computerorderSerialnumber;
	
//	统计预约单的参数
	int orderpccaregorynum = 0;//订单模型数量
	int orderpctotalnum = 0;//订单机器数量
	
	int computerordertype;
	int computerhomeworkid;


	
	private List<Borrowperiod> periodList = new ArrayList<Borrowperiod>();

	
	/**
	 * 跳转到订单审核界面
	 * @return
	 */
	public String toAuditComputerorderPage(){
		log.info("toAuditComputerorderPage "+ computerorderId);
		
//		装载时间段的信息,界面上用于显示
		periodList = BorrowperiodUtil.getBorrowperiodList();
		
		computerorderFull = computerorderService.selectComputerorderFullById(computerorderId);
		if(computerorderFull == null){
			actionMsg = "访问的订单不存在";
			return ComputerConfig.usernotloginreturnstr;
		}
		
		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		computerorderdetailFullList = computerorderdetailService.selectComputerorderdetailFullByCondition(sql);
//		System.out.println("computerorderdetailFullList size:"+computerorderdetailFullList.size());
		
//		computerstatusList  = computerstatusService.selectComputerstatusByCondition("");
		computerstatusFullList = computerstatusService.selectComputerstatusFullByCondition(" ");
		if(computerorderdetailFullList==null){
			computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
		}
//		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
//			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
//			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
//				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));
//			}else{
//				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
//				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
//				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);
//			}
//		}
		this.buildOrderInfo(computerorderdetailFullList);
		
		if(computerorderdetailFullMapByComputermodelId == null){
			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
		}
		System.out.println("computerorderdetailFullMapByComputermodelId size:"+computerorderdetailFullMapByComputermodelId.size());
		
		return SUCCESS;
	}
	
	
	/**
	 * 查看借用清单列表
	 * @return
	 */
	public String viewMineComputerorderList(){
		
		Integer userid = checkUserLogin();
//		log.info("login user id "+ uid);
		if(userid < 0){		
			actionMsg = "用户未登录";
			return ComputerConfig.usernotloginreturnstr;
		}
		

//		进行中的预约是：状态未审核的预约
		String selunderwayordersql = "  where a.userid="+userid + " and a.status="+ComputerorderInfo.ComputerorderStatusAduitWait+" order by a.createtime desc";
		computerorderFullUnderwayList = computerorderService.selectComputerorderFullByCondition(selunderwayordersql);
		
		String selfinordersql = "  where a.userid="+userid + " and a.status !="+ComputerorderInfo.ComputerorderStatusAduitWait+" order by a.createtime desc";
		computerorderFullFinishList = computerorderService.selectComputerorderFullByCondition(selfinordersql);
		
		
//		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		if(computerorderFullUnderwayList==null){
			computerorderFullUnderwayList = new ArrayList<ComputerorderFull>();
		}
		if(computerorderFullFinishList==null){
			computerorderFullFinishList = new ArrayList<ComputerorderFull>();
		}
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 用户查看订单详情
	 * @return
	 */
	public String viewComputerorder(){
		log.info("viewComputerorder "+ computerorderId);
		
//		装载时间段的信息,界面上用于显示
		periodList = BorrowperiodUtil.getBorrowperiodList();
		
		computerorderFull = computerorderService.selectComputerorderFullById(computerorderId);
		//如果找不到相应的预约单，返回错误
		if(computerorderFull == null){
			actionMsg = "访问的页面不存在";
			log.info(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		
		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		computerorderdetailFullList = computerorderdetailService.selectComputerorderdetailFullByCondition(sql);
//		System.out.println("computerorderdetailFullList size:"+computerorderdetailFullList.size());
		
		if(computerorderdetailFullList==null){
			computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
		}
		
//		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
//			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
//			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
//				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));
//			}else{
//				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
//				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
//				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);
//			}
//		}
		
		buildOrderInfo(computerorderdetailFullList);
		
		if(computerorderdetailFullMapByComputermodelId == null){
			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
		}
		System.out.println("computerorderdetailFullMapByComputermodelId size:"+computerorderdetailFullMapByComputermodelId.size());
		
		return SUCCESS;
	}
	
	
	
	

	
	/**
	 * 在预约界面按提交按钮提交表单,将详细信息放到session中
	 * @return
	 */
	public String computerorderFormConfirm(){	
		log.info("computerorderFormConfirm"+computerordertype);
		ReturnJson returnJson = new ReturnJson();		
		
		if(confirmOrderInfo(orderInfoStr) == false){

			returnInfo = "预约表单数据错误";
			log.error(returnInfo);
			this.returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}else{
			returnJson.setFlag(1);
			returnJson.setReason(orderInfoStr);
			
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			session.put("computerhomeworkid", computerhomeworkid);
			session.put("computerordertype", computerordertype);
			session.put("computerorderdetailList", computerorderdetailList);
			session.put("computerorderdetailFullList", computerorderdetailFullList);


			returnInfo = "成功";
			log.info(returnInfo);
			this.returnStr = ComputerActionUtil.buildReturnStr(ComputerConfig.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
		}
	}

	/**
	 * 检查预约表单是否规范，并且给Order赋值
	 * @param str
	 * @return
	 */
	public boolean confirmOrderInfo(String str){
//		str无效
		if(str ==null || str.length() ==0){
			return false;
		}
		String[] orderDetailStrArray = orderInfoStr.split(";");
//		List<Computerorderdetail> computerorderdetailList
		for (int i = 0; i < orderDetailStrArray.length; i++) {
			String temp = orderDetailStrArray[i];
			if(temp==null || temp.length()==0 || temp.split(",").length != 5){
				continue;
			}
			String[] info = temp.split(",");
			
			ComputerorderdetailFull f = new ComputerorderdetailFull();
			f.setComputerorderdetailcomputermodelid(Integer.valueOf(info[0]));
			f.setComputerorderdetailborrownumber(Integer.valueOf(info[1]));
			f.setComputerorderdetailborrowday(DateUtil.parseDate(info[2]));
			f.setComputerorderdetailborrowperiod(Integer.valueOf(info[3]));
			f.setComputermodelname(info[4]);
					
			computerorderdetailFullList.add(f);	
			
//			用于save时调用
			Computerorderdetail te = new Computerorderdetail();
			te.setComputermodelid(Integer.valueOf(info[0]));
			te.setBorrownumber(Integer.valueOf(info[1]));
			te.setBorrowday(DateUtil.parseDate(info[2]));
			te.setBorrowperiod(Integer.valueOf(info[3]));
			computerorderdetailList.add(te);	
		}
		
		if(computerorderdetailFullList != null && computerorderdetailFullList.size() != 0){
			return true;
		}else{
			return false;
		}
	}

	
	
//	课程预约界面点击提交按钮后，如果computerorderFormConfirm指向成功，跳转到预约确认界面
	public String toComputerorderConfirmPage(){	
		log.info("toComputerorderConfirmPage computerordertype:"+computerordertype);
		
//		装载时间段的信息,界面上用于显示
		periodList = BorrowperiodUtil.getBorrowperiodList();
		

		
		
		if(session==null || !session.containsKey("computerordertype") || !session.containsKey("computerorderdetailFullList")){
			actionMsg = "预约信息不完整";
			return ComputerConfig.pagenotfound;
		}
//		获取预约类型
		computerordertype = (Integer) session.get("computerordertype");
		computerorderdetailFullList = (ArrayList<ComputerorderdetailFull>)session.get("computerorderdetailFullList");
		if(computerorderdetailFullList == null ){
			return "error";
		}
		
//		设置统计信息
		buildOrderInfo(computerorderdetailFullList);
		
		
		if(computerorderdetailFullMapByComputermodelId == null){
			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
		}
//		System.out.println("computerorderdetailFullMapByComputermodelId"+computerorderdetailFullMapByComputermodelId.get(1).size());
		
//		for (int i = 0; i < computerorderdetailList.size(); i++) {
//			int tempComputermodelId = computerorderdetailList.get(i).getComputermodelid();
//			if(computerorderdetailMapByComputermodelId.containsKey(tempComputermodelId)){
//				computerorderdetailMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailList.get(i));
//			}else{
//				ArrayList<Computerorderdetail> tempComputerorderdetailList = new ArrayList<Computerorderdetail>();
//				tempComputerorderdetailList.add(computerorderdetailList.get(i));
//				computerorderdetailMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailList);
//			}
//		}
//		
//		if(computerorderdetailMapByComputermodelId == null){
//			computerorderdetailMapByComputermodelId = new HashMap<Integer,ArrayList<Computerorderdetail>>();
//		}
		
		if(computerorderdetailFullList == null){
//			returnJson.setFlag(0);
//			returnJson.setReason("提交数据错误");
			return "error";
		}else{
//			returnJson.setFlag(1);
//			returnJson.setReason(orderInfoStr);
//			returnJson.setReason("提交数据错误");
			return SUCCESS;
		}
	}
	
	/**
	 * 统计订单的模型数量，借用个数，设置查看订单返回值
	 * @param computerorderdetailFullList
	 */
	public void buildOrderInfo(List<ComputerorderdetailFull> computerorderdetailFullList){
		orderpctotalnum = 0;
		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));				
			}else{
				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);				
			}
			orderpctotalnum =orderpctotalnum + computerorderdetailFullList.get(i).getComputerorderdetailborrownumber();
		}		
		orderpccaregorynum = computerorderdetailFullMapByComputermodelId.size();
		
		log.info(orderpctotalnum);
	}
	
//  提交预约表单	
	public String addComputerorderAjax(){	
		log.info("Add Entity Ajax Manner");
		
		
		try {
		
		Integer uid = getCurrentUserId();
		if(uid < 0){			
			returnInfo = "用户未登录";
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		
		if(session==null || !session.containsKey("computerordertype") || !session.containsKey("computerorderdetailFullList")){
			returnInfo = "预约参数错误";
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		computerhomeworkid = (Integer) session.get("computerhomeworkid");
		computerordertype = (Integer) session.get("computerordertype");
					
		
			Computerorder temp = new Computerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorder);
			System.out.println("computerordertitle"+computerorder.getTitle());
			
			String uuid = SnActionUtil.genComputerorderSn(uid, computerordertype, DateUtil.currentDate());
			temp.setSerialnumber(uuid);
			session.put("computerorderSerialnumber", uuid);

			
			
			temp.setUserid(uid);
			temp.setCreatetime(DateUtil.currentDate());
			temp.setOrdertype(computerordertype);
			temp.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
			computerorderService.addComputerorder(temp);
			
			computerorderdetailList = (ArrayList<Computerorderdetail>)session.get("computerorderdetailList");
			for(int i=0 ; i < computerorderdetailList.size();i++){
				Computerorderdetail cd = computerorderdetailList.get(i);
				cd.setComputerorderid(temp.getId());
				cd.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
				computerorderdetailService.addComputerorderdetail(cd);
			}
			
//			如果是课程预约，需要修改作业的状态，一次作业只能预约一次
			if(computerordertype == ComputerorderInfo.ClassOrder){
				String hmresql = " where computerhomeworkid = "+ computerhomeworkid +" and userid="+checkUserLogin();
//				System.out.println(hmresql);
				computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverByCondition(hmresql);
				if(computerhomeworkreceiverList == null || computerhomeworkreceiverList.size()!=1){
					returnInfo = "修改用户课程预约的预约状态失败";
					returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
					return SUCCESS;
				}
				
				String updateChrSql = " update Computerhomeworkreceiver set hasorder="+ComputerConfig.computerhomeworkhasorder+" , hasview="+ComputerConfig.computerhomeworkhasview+" where id = "+computerhomeworkreceiverList.get(0).getId();
				computerhomeworkreceiverService.execSql(updateChrSql);
			}

			
			returnInfo = "预约成功";
			returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn,returnInfo);
			return SUCCESS;
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("addComputerorderAjax错误"+e);
		}
		
		returnInfo = "系统发生内部错误";
		returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
		return SUCCESS;
	}
	
	

	

	public String toComputerorderSuccessPage(){
		computerorderSerialnumber = session.get("computerorderSerialnumber");
		return SUCCESS;
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


	public ComputerstatusService getComputerstatusService() {
		return computerstatusService;
	}


	public void setComputerstatusService(ComputerstatusService computerstatusService) {
		this.computerstatusService = computerstatusService;
	}


	public int getComputerstatusid() {
		return computerstatusid;
	}


	public void setComputerstatusid(int computerstatusid) {
		this.computerstatusid = computerstatusid;
	}


	public List<Computerstatus> getComputerstatusList() {
		return computerstatusList;
	}


	public void setComputerstatusList(List<Computerstatus> computerstatusList) {
		this.computerstatusList = computerstatusList;
	}


	public List<ComputerstatusFull> getComputerstatusFullList() {
		return computerstatusFullList;
	}


	public void setComputerstatusFullList(
			List<ComputerstatusFull> computerstatusFullList) {
		this.computerstatusFullList = computerstatusFullList;
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


	public List<Computerhomeworkreceiver> getComputerhomeworkreceiverList() {
		return computerhomeworkreceiverList;
	}


	public void setComputerhomeworkreceiverList(
			List<Computerhomeworkreceiver> computerhomeworkreceiverList) {
		this.computerhomeworkreceiverList = computerhomeworkreceiverList;
	}


	public ComputerhomeworkreceiverFull getComputerhomeworkreceiverFull() {
		return computerhomeworkreceiverFull;
	}


	public void setComputerhomeworkreceiverFull(
			ComputerhomeworkreceiverFull computerhomeworkreceiverFull) {
		this.computerhomeworkreceiverFull = computerhomeworkreceiverFull;
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


	public List<ComputerorderFull> getComputerorderFullUnderwayList() {
		return computerorderFullUnderwayList;
	}


	public void setComputerorderFullUnderwayList(
			List<ComputerorderFull> computerorderFullUnderwayList) {
		this.computerorderFullUnderwayList = computerorderFullUnderwayList;
	}


	public List<ComputerorderFull> getComputerorderFullFinishList() {
		return computerorderFullFinishList;
	}


	public void setComputerorderFullFinishList(
			List<ComputerorderFull> computerorderFullFinishList) {
		this.computerorderFullFinishList = computerorderFullFinishList;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getOrderInfoStr() {
		return orderInfoStr;
	}


	public void setOrderInfoStr(String orderInfoStr) {
		this.orderInfoStr = orderInfoStr;
	}


	public Object getComputerorderSerialnumber() {
		return computerorderSerialnumber;
	}


	public void setComputerorderSerialnumber(Object computerorderSerialnumber) {
		this.computerorderSerialnumber = computerorderSerialnumber;
	}


	public int getOrderpccaregorynum() {
		return orderpccaregorynum;
	}


	public void setOrderpccaregorynum(int orderpccaregorynum) {
		this.orderpccaregorynum = orderpccaregorynum;
	}


	public int getOrderpctotalnum() {
		return orderpctotalnum;
	}


	public void setOrderpctotalnum(int orderpctotalnum) {
		this.orderpctotalnum = orderpctotalnum;
	}


	public int getComputerordertype() {
		return computerordertype;
	}


	public void setComputerordertype(int computerordertype) {
		this.computerordertype = computerordertype;
	}


	public int getComputerhomeworkid() {
		return computerhomeworkid;
	}


	public void setComputerhomeworkid(int computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
	}


	public List<Borrowperiod> getPeriodList() {
		return periodList;
	}


	public void setPeriodList(List<Borrowperiod> periodList) {
		this.periodList = periodList;
	}


	public static Log getLog() {
		return log;
	}


	@Override
	public Computerorder getModel() {
		// TODO Auto-generated method stub
		return computerorder;
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
}
