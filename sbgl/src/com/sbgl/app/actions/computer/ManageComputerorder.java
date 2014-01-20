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
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.DateUtil;
import com.sbgl.util.ReturnJson;

@Scope("prototype") 
@Controller("ManageComputerorder")
public class ManageComputerorder extends ActionSupport implements SessionAware,CookiesAware,ModelDriven<Computerorder> {
	private static final Log log = LogFactory.getLog(ManageComputerorder.class);
	private Map<String, Object> session;
	private Map<String, String> cookiesMap;

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
	
	
	
	private int ComputerorderStatusAduitAll = ComputerorderInfo.ComputerorderStatusAduitAll;
	private int ComputerorderStatusAduitPass = ComputerorderInfo.ComputerorderStatusAduitPass;
	private int ComputerorderStatusAduitReject = ComputerorderInfo.ComputerorderStatusAduitReject;
	private int ComputerorderStatusAduitDel = ComputerorderInfo.ComputerorderStatusAduitDel;
	private int ComputerorderStatusAduitWait = ComputerorderInfo.ComputerorderStatusAduitWait;
	private int IndividualOrder = ComputerorderInfo.IndividualOrder;
	private int ClassOrder = ComputerorderInfo.IndividualOrder;
	
	
//	全局参数
	private String returnStr;//声明一个变量，用来在页面上显示提示信息。只有在Ajax中才用到
	private String userid;
	
	
//	提交预约表单的参数
	private String orderInfoStr;
	private Object computerorderSerialnumber;
	
	
	/**
	 * 跳转到订单审核界面
	 * @return
	 */
	public String toAuditComputerorderPage(){
		System.out.println("toAuditComputerorderPage "+ computerorderId);
		computerorderFull = computerorderService.selectComputerorderFullById(computerorderId);
		//如果找不到相应的预约单，返回错误
		if(computerorderFull == null){
//			Log.info("");
			System.out.println("wrong");
			return "PageNotFound";
		}
		
		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		computerorderdetailFullList = computerorderdetailService.selectComputerorderdetailFullByCondition(sql);
//		System.out.println("computerorderdetailFullList size:"+computerorderdetailFullList.size());
		
		if(computerorderdetailFullList==null){
			computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
		}
		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));
			}else{
				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);
			}
		}
		
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
	public String computerorderList(){
		
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		String uidStr = ComputerUtil.getCookieValue(cookies, ComputerConfig.cookieuserid);
		//根据用户查询预约单
//		if(cookiesMap==null){
//			System.out.println("null");
//		}
//		String uidStr = cookiesMap.get(ComputerConfig.cookieuserid);
		if(uidStr == null || !ComputerUtil.isNumber(uidStr)){
			return "error";
		}
		int userid = Integer.valueOf(uidStr);
		
		String selordersql = "  where a.userid="+userid;
		computerorderFullList = computerorderService.selectComputerorderFullByCondition(selordersql);
		
//		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		if(computerorderFullList==null){
			computerorderFullList = new ArrayList<ComputerorderFull>();
		}
		
		return SUCCESS;
	}
	
	
	
	/**
	 * 查看订单详情
	 * @return
	 */
	public String viewComputerorder(){
		System.out.println("viewComputerorder "+ computerorderId);
		computerorderFull = computerorderService.selectComputerorderFullById(computerorderId);
		//如果找不到相应的预约单，返回错误
		if(computerorderFull == null){
//			Log.info("");
			System.out.println("wrong");
			return "PageNotFound";
		}
		
		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		computerorderdetailFullList = computerorderdetailService.selectComputerorderdetailFullByCondition(sql);
//		System.out.println("computerorderdetailFullList size:"+computerorderdetailFullList.size());
		
		if(computerorderdetailFullList==null){
			computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
		}
		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));
			}else{
				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);
			}
		}
		
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
		log.info("computerorderFormConfirm");
		ReturnJson returnJson = new ReturnJson();		
		
		if(confirmOrderInfo(orderInfoStr) == false){
			returnJson.setFlag(0);
			returnJson.setReason("提交数据错误");
			
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			return SUCCESS;
		}else{
			returnJson.setFlag(1);
			returnJson.setReason(orderInfoStr);
			
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			session.put("computerorderdetailList", computerorderdetailList);
			session.put("computerorderdetailFullList", computerorderdetailFullList);
//			returnJson.setReason("提交数据错误");
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

	
	
//	跳转到预约确认界面
	public String toComputerorderConfirmPage(){	
		log.info("toComputerorderConfirmPage"+session);
		ReturnJson returnJson = new ReturnJson();		
		
		if(session==null || !session.containsKey("computerorderdetailFullList")){
			return "error";
		}
		computerorderdetailFullList = (ArrayList<ComputerorderdetailFull>)session.get("computerorderdetailFullList");
		if(computerorderdetailFullList == null ){
			return "error";
		}
		
		for (int i = 0; i < computerorderdetailFullList.size(); i++) {
			int tempComputermodelId = computerorderdetailFullList.get(i).getComputerorderdetailcomputermodelid();
			if(computerorderdetailFullMapByComputermodelId.containsKey(tempComputermodelId)){
				computerorderdetailFullMapByComputermodelId.get(tempComputermodelId).add(computerorderdetailFullList.get(i));
			}else{
				ArrayList<ComputerorderdetailFull> tempComputerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
				tempComputerorderdetailFullList.add(computerorderdetailFullList.get(i));
				computerorderdetailFullMapByComputermodelId.put(tempComputermodelId,tempComputerorderdetailFullList);
			}
		}
		
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
	
	
//  提交预约表单	
	public String addComputerorderAjax(){	
		log.info("Add Entity Ajax Manner");
		ReturnJson returnJson = new ReturnJson();
					
		try {
			Computerorder temp = new Computerorder();
			// 将model里的属性值赋给temp
			BeanUtils.copyProperties(temp, computerorder);
			System.out.println("computerordertitle"+computerorder.getTitle());
			String uuid = ComputerUtil.genSerialnumber("");
			temp.setSerialnumber(uuid);
			session.put("computerorderSerialnumber", uuid);
//			temp.setUserid(Integer.valueOf(userid));
			temp.setUserid(Integer.valueOf("1"));
			temp.setCreatetime(DateUtil.currentDate());
			temp.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
			computerorderService.addComputerorder(temp);
			
			computerorderdetailList = (ArrayList<Computerorderdetail>)session.get("computerorderdetailList");
			for(int i=0 ; i < computerorderdetailList.size();i++){
				Computerorderdetail cd = computerorderdetailList.get(i);
				cd.setComputerorderid(temp.getId());
				cd.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
				computerorderdetailService.addComputerorderdetail(cd);
			}
			
			returnJson.setFlag(1);		
			JSONObject jo = JSONObject.fromObject(returnJson);
			this.returnStr = jo.toString();
			
			return SUCCESS;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			log.error("类ComputerorderAction的方法：addBbstagfavourite错误"+e);
		}
		
		returnJson.setFlag(0);		
		JSONObject jo = JSONObject.fromObject(returnJson);
		this.returnStr = jo.toString();
		return SUCCESS;
	}
	
	
	

	public String toComputerorderSuccessPage(){
		computerorderSerialnumber = session.get("computerorderSerialnumber");
		return SUCCESS;
	}
	

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}



	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}



	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}



	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
	}



	public int getComputerorderId() {
		return computerorderId;
	}



	public void setComputerorderId(int computerorderId) {
		this.computerorderId = computerorderId;
	}



	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}



	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
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



	public HashMap<Integer, ArrayList<ComputerorderdetailFull>> getComputerorderdetailFullMapByComputermodelId() {
		return computerorderdetailFullMapByComputermodelId;
	}



	public void setComputerorderdetailFullMapByComputermodelId(
			HashMap<Integer, ArrayList<ComputerorderdetailFull>> computerorderdetailFullMapByComputermodelId) {
		this.computerorderdetailFullMapByComputermodelId = computerorderdetailFullMapByComputermodelId;
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


	public HashMap<Integer, ArrayList<Computerorderdetail>> getComputerorderdetailMapByComputermodelId() {
		return computerorderdetailMapByComputermodelId;
	}


	public void setComputerorderdetailMapByComputermodelId(
			HashMap<Integer, ArrayList<Computerorderdetail>> computerorderdetailMapByComputermodelId) {
		this.computerorderdetailMapByComputermodelId = computerorderdetailMapByComputermodelId;
	}


	public static Log getLog() {
		return log;
	}


	public Map<String, Object> getSession() {
		return session;
	}


	public Computerorder getComputerorder() {
		return computerorder;
	}


	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}


	public int getComputerorderid() {
		return computerorderid;
	}


	public void setComputerorderid(int computerorderid) {
		this.computerorderid = computerorderid;
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


	public Integer getComputerorderdetailid() {
		return computerorderdetailid;
	}


	public void setComputerorderdetailid(Integer computerorderdetailid) {
		this.computerorderdetailid = computerorderdetailid;
	}


	public String getReturnStr() {
		return returnStr;
	}


	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
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


	@Override
	public Computerorder getModel() {
		// TODO Auto-generated method stub
		return computerorder;
	}


	public Object getComputerorderSerialnumber() {
		return computerorderSerialnumber;
	}


	public void setComputerorderSerialnumber(Object computerorderSerialnumber) {
		this.computerorderSerialnumber = computerorderSerialnumber;
	}


	public Map<String, String> getCookiesMap() {
		return cookiesMap;
	}


	public void setCookiesMap(Map<String, String> cookiesMap) {
		this.cookiesMap = cookiesMap;
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


	
}
