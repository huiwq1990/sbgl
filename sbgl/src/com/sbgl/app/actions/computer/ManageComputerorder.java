package com.sbgl.app.actions.computer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.actions.util.SnActionUtil;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.app.services.computer.ComputerorderconfigService;
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
	
	int computerorderid = 0; //entity full 的id属性名称		
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
	private ComputerorderclassruledetailService computerorderclassruledetailService;	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	private Integer computerorderclassruledetailid; //entity full 的id属性名称		
	
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;	
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();//实例化一个模型
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();//实例化一个模型
	
	@Resource
	ComputerorderconfigService computerorderconfigService;
	
	@Resource
	private ComputerorderclassruleService computerorderclassruleService;	
	private Computerorderclassrule computerorderclassrule = new Computerorderclassrule();//实例化一个模型
	private ComputerorderclassruleFull computerorderclassruleFull = new ComputerorderclassruleFull();//实例化一个模型
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	private Integer computerorderclassruleid; //entity full 的id属性名称		
	
	
	

	@Resource	
	private ComputerhomeworkService computerhomeworkService;	
	private Computerhomework computerhomework = new Computerhomework();//实例化一个模型
	private ComputerhomeworkFull computerhomeworkFull = new ComputerhomeworkFull();//实例化一个模型
	private List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	private List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	private List<ComputerhomeworkFull> newComputerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	

	
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
	
	List<ComputerorderEntity> computerorderEntityList = new ArrayList<ComputerorderEntity>();//进行中的预约
	
	
//	全局参数	
	private String userid;
	
	
//	提交预约表单的参数
	private String orderInfoStr;
	private Object computerorderSerialnumber;
	
//	统计预约单的参数
	int orderpccaregorynum = 0;//订单模型数量
	int orderpctotalnum = 0;//订单机器数量
	int ordertimecount = 0;
	
	private	int computerordertype = 0;
	private int computerhomeworkid = 0;
	private int curcomputerhomeworkid = 0;

//重新预约
	int reorder = 0;
//	int computerorderid;
	
	//用户角色信息
	private String userRoleName = "";
	private  Map<Integer,String> userRoleMap = new HashMap<Integer,String>();
	
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
		
//		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		computerorderdetailFullList = computerorderdetailService.selFullByOrderId(computerorderId, this.getCurrentLanguage());
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
		
//		对订单排序
		if(computerorderdetailFullMapByComputermodelId == null){
			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
		}else{
			computerorderdetailFullMapByComputermodelId = ComputerActionUtil.sortComputerorderMap(computerorderdetailFullMapByComputermodelId);
		}
		System.out.println("computerorderdetailFullMapByComputermodelId size:"+computerorderdetailFullMapByComputermodelId.size());
		
		return SUCCESS;
	}
	
	
	/**
	 * 查看借用清单列表
	 * @return
	 */
	public String viewMineComputerorderList(){
		try{
		
		Integer userid = this.getCurrentUserId();

		if(userid < 0){		
			actionMsg = "用户未登录";
			return ComputerConfig.usernotloginreturnstr;
		}
		
/*
//		进行中的预约是：状态未审核的预约
		String selunderwayordersql = "  where a.createuserid="+userid + " and a.status in("+ComputerorderInfo.ComputerorderStatusAduitWait+","+ComputerorderInfo.ComputerorderStatusAduitReject+") order by a.createtime desc";
		computerorderFullUnderwayList = computerorderService.selectComputerorderFullByCondition(selunderwayordersql);
		
//		根据作业接收者，查询新的课程预约
		computerhomeworkreceiverList = computerhomeworkreceiverService.selectComputerhomeworkreceiverByUserAndOrder(userid, ComputerConfig.computerhomeworknotorder);
		String newhomeworksql="";
		for(int i=0; i<computerhomeworkreceiverList.size();i++){
			if( (computerhomeworkreceiverList.get(i).getHavefinish()==null) || (computerhomeworkreceiverList.get(i).getHavefinish() !=1)){
					newhomeworksql += computerhomeworkreceiverList.get(i).getComputerhomeworkid()+",";
			}
		}
		if(newhomeworksql.length() > 1){
			newhomeworksql = newhomeworksql.substring(0,newhomeworksql.length()-1);
			newhomeworksql = " where a.id in (" +newhomeworksql+") "  + " order by computerhomeworkcreatetime desc ";
			newComputerhomeworkFullList = computerhomeworkService.selectComputerhomeworkFullByCondition(newhomeworksql);
		}
		
		computerorderEntityList = ComputerorderActionUtil.setUnderwayComputerorder(computerorderFullUnderwayList, newComputerhomeworkFullList);
*/
		
		computerorderEntityList = computerorderService.getUnderwayComputerorder(userid);
		
//		预约完成的订单
//		String selfinordersql = "  where a.createuserid="+userid + " and a.status ="+ComputerorderInfo.ComputerorderStatusAduitPass+" order by a.createtime desc";
//		computerorderFullFinishList = computerorderService.selectComputerorderFullByCondition(selfinordersql);
		computerorderFullFinishList = computerorderService.selFullByStatus(userid, ComputerorderInfo.ComputerorderStatusAduitPass);
//		System.out.println(computerorderFullUnderwayList.size());
//		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		if(computerorderFullUnderwayList==null){
			computerorderFullUnderwayList = new ArrayList<ComputerorderFull>();
		}
		
//		对预约排序
		if(computerorderEntityList == null){
			computerorderEntityList = new ArrayList<ComputerorderEntity>();;
		}else{
			ComputerorderEntityComparator comparator=new ComputerorderEntityComparator();
			Collections.sort(computerorderEntityList, comparator);
		}
		if(computerorderFullFinishList==null){
			computerorderFullFinishList = new ArrayList<ComputerorderFull>();
		}
		
		return SUCCESS;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "404";
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
			return "404";
		}
		
//		String sql = " where a.computerorderid = "+computerorderId  + " and c.languagetype="+ComputerConfig.languagech ;
		computerorderdetailFullList = computerorderdetailService.selFullByOrderId(computerorderId, this.getCurrentLanguage());
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
		
//		对订单排序
		if(computerorderdetailFullMapByComputermodelId == null){
			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
		}else{
			computerorderdetailFullMapByComputermodelId = ComputerActionUtil.sortComputerorderMap(computerorderdetailFullMapByComputermodelId);
		}
		
//		System.out.println("computerorderdetailFullMapByComputermodelId size:"+computerorderdetailFullMapByComputermodelId.size());
		Loginuser user = (Loginuser) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
//		System.out.println(user.getName() + user.getTelephone());
		userRoleName = CommonConfig.userRoleMap.get(Integer.valueOf(user.getRoletype()));
		System.out.println(user.getRoletype() +userRoleName);
		return SUCCESS;
	}
	
	
	/**
	 * 在预约界面按提交按钮提交表单,将详细信息放到session中
	 * @return
	 */
	public String computerorderFormConfirm(){
		log.info("computerorderFormConfirm,预约类型："+computerordertype + " 作业id:"+ computerorder.getComputerhomeworkid());

		try{		
			if(getCurrentUser()==null){
//				returnInfo = "用户未登录";
				returnInfo = getMsg("computerorderconfirm_usernotlogin");
				log.error(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
		
		log.info("预约信息："+orderInfoStr);
		if(confirmOrderInfo(orderInfoStr) == false){
			
//			returnInfo = "预约表单数据错误";
			returnInfo = getMsg("computerorderconfirm_formerror");
			log.error(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
			
			Computerorderconfig config = computerorderconfigService.getCurrentComputerorderconfig();
			if(config == null || config.getMaxorderday() == 0){
//				returnInfo = "获取预约配置信息出错";
				returnInfo = getMsg("computerorderconfirm_orderconfignotfound");
				log.error(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
			
			if(!checkOrderDateAndPeriod()){
//				returnInfo = "不能预约过去的PC";
				returnInfo = getMsg("computerorderconfirm_orderpassedpcerror");
				log.error(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}

//			管理员进行预约
			if(computerordertype == ComputerorderInfo.IndividualOrder && this.isAdmin()){
				boolean pass = validOrderForm(config);

//				if(!pass){
//					returnInfo = "预约数量不能满足";
//					log.error(returnInfo);
//					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
//					return SUCCESS;
//				}
			}else if(computerordertype == ComputerorderInfo.IndividualOrder && !this.isAdmin()){
				boolean pass = validOrderForm(config);

				if(!pass){
//					returnInfo = "预约数量不能满足";
					returnInfo = getMsg("computerorderconfirm_ordernumexceed");
					log.error(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
			}else if(computerordertype == ComputerorderInfo.ClassOrder ){ //课程预约
				computerhomeworkreceiver =  computerhomeworkreceiverService.sel(computerorder.getComputerhomeworkid(), this.getCurrentUserId());
				log.info("homeworkid:"+computerorder.getComputerhomeworkid()+"  userid:"+this.getCurrentUserId());
				if(computerhomeworkreceiver == null){
//					returnInfo = "查不到相关作业信息";
					returnInfo = getMsg("computerorderconfirm_orderhomeworknotfound");
					log.error(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
				
				
				log.info("课程预约验证,预约数量："+ordertimecount+"  允许借用数量："+computerhomeworkreceiver.getLeftordertime());
				if(ordertimecount > computerhomeworkreceiver.getLeftordertime()){
					returnInfo = getMsg("computerorderconfirm_ordertimeexceed1")+computerhomeworkreceiver.getLeftordertime()+getMsg("computerorderconfirm_ordertimeexceed2");
					log.error(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
				
				boolean pass = validClassOrderForm(computerorder.getComputerhomeworkid());

				if(!pass){
//					returnInfo = "预约数量不能满足";
					returnInfo = getMsg("computerorderconfirm_ordernumexceed");
					log.error(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
			}else{
				boolean pass = validClassOrderForm(computerorder.getComputerhomeworkid());

				if(!pass){
//					returnInfo = "预约数量不能满足";
					returnInfo = getMsg("computerorderconfirm_ordernumexceed");
					log.error(returnInfo);
					this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
					return SUCCESS;
				}
			}
			
			//			session.put("computerhomeworkid", curcomputerhomeworkid);
//			System.out.println("computerhomeworkid"+curcomputerhomeworkid);
//			session.put("computerordertype", computerordertype);
//			session.put("computerorderdetailList", computerorderdetailList);
//			session.put("computerorderdetailFullList", computerorderdetailFullList);
			
			
			
//			returnInfo = "成功";
			returnInfo = getMsg("computerorderconfirm_ordersuccess");
			log.info(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxsuccessreturn, returnInfo);
			return SUCCESS;
			
			
		}catch(Exception e){
			e.printStackTrace();
			log.error("addComputerorderAjax错误"+e);
		
		}
		
//		returnInfo = "内部错误";
		returnInfo = getMsg("computerorderconfirm_ordererror");
		log.info(returnInfo);
		this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
		return SUCCESS;
	}

	/**
	 * 检查预约表单是否规范，并且给Order赋值
	 * 统计数量
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
			if(temp==null || temp.length()==0 || temp.split(",").length != 6){
				continue;
			}
			String[] info = temp.split(",");
			
			if(Integer.valueOf(info[0]) > 0){
				
				
				ComputerorderdetailFull f = new ComputerorderdetailFull();
				f.setComputerorderdetailcomputermodelid(Integer.valueOf(info[0]));
				f.setComputerorderdetailborrownumber(Integer.valueOf(info[1]));
				f.setComputerorderdetailborrowday(DateUtil.parseDate(info[2]));
				f.setComputerorderdetailborrowperiod(Integer.valueOf(info[3]));
				f.setComputermodelname(info[4]);
				f.setComputermodelpicpath(info[5]);	
				computerorderdetailFullList.add(f);	
				
//				用于save时调用
				Computerorderdetail te = new Computerorderdetail();
				te.setComputermodelid(Integer.valueOf(info[0]));
				te.setBorrownumber(Integer.valueOf(info[1]));
				te.setBorrowday(DateUtil.parseDate(info[2]));
				te.setBorrowperiod(Integer.valueOf(info[3]));
				
//				设置数量
				ordertimecount += te.getBorrownumber();
				
				computerorderdetailList.add(te);	
			}
			
			
		}
		
		if(computerorderdetailFullList != null && computerorderdetailFullList.size() != 0){
			return true;
		}else{
			return false;
		}
	}

//	void set
	public boolean checkOrderDateAndPeriod(){
		Date currentDate = DateUtil.currentDate();
		Date currentDayDate = DateUtil.getDateDayDate(currentDate);
//		String startDateStr= DateUtil.dateFormat(, DateUtil.dateformatstr1);
		int currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(currentDate);
		for(Computerorderdetail od : computerorderdetailList){
//			if(startDateStr)
			Date odDate = od.getBorrowday();
			int odP = od.getBorrowperiod();
//			int pnum = BorrowperiodUtil.getBorrowperiodMap().get(odP).getPeriodnum();
//			不能预约今天之前的Pc
			if(odDate.before(currentDayDate)){
				return false;
			}
//			不能预约今天已经过去时间段的PC
			if( odDate.equals(currentDayDate) && currentPeriod > odP ){
				return false;
			}
		}
		
		return true;
	}
	
	
//	课程预约界面点击提交按钮后，如果computerorderFormConfirm指向成功，跳转到预约确认界面
	public String toComputerorderConfirmPage(){	
		log.info("toComputerorderConfirmPage computerordertype:"+computerordertype+"  "+reorder);
//		System.out.println(session.get("computerordertype"));
		
//		装载时间段的信息,界面上用于显示
		periodList = BorrowperiodUtil.getBorrowperiodList();
		

		if(confirmOrderInfo(orderInfoStr) == false){
//		if(session==null || !session.containsKey("computerordertype") || !session.containsKey("computerorderdetailFullList")){
			actionMsg = "预约信息不完整";
			return ComputerConfig.pagenotfound;
		}
//		获取预约类型
//		computerordertype = (Integer) session.get("computerordertype");
//		computerorderdetailFullList = (ArrayList<ComputerorderdetailFull>)session.get("computerorderdetailFullList");
//		if(computerorderdetailFullList == null ){
//			return "error";
//		}
		
//		如果是个人预约，默认作业id
		if(computerordertype == ComputerorderInfo.IndividualOrder){
			computerhomeworkid = 0;
		}
		
//		设置统计信息
		buildOrderInfo(computerorderdetailFullList);
		
//		如果是重新预约，调用表单id获取相关信息，在确认界面显示
		if(reorder == 1){
			computerorder = computerorderService.selectComputerorderById(computerorderid);
		}else{
			computerorder = new Computerorder();
		}
		
//		对订单排序
		if(computerorderdetailFullMapByComputermodelId == null){
			computerorderdetailFullMapByComputermodelId = new HashMap<Integer,ArrayList<ComputerorderdetailFull>>();
		}else{
			computerorderdetailFullMapByComputermodelId = ComputerActionUtil.sortComputerorderMap(computerorderdetailFullMapByComputermodelId);
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
	
//	验证表单数据是否满足
	public boolean validOrderForm(Computerorderconfig config){
		Date currentDate = DateUtil.currentDate();
		int currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(currentDate);
//		可以预约 n天之内的PC,结束日期是最大预约天数减一
		log.info("验证数量能否满足");
		Date endDate = DateUtil.addDay(currentDate, config.getMaxorderday()-1);
		int endPeriod = BorrowperiodUtil.getMaxPeriod();
		int currentLanguage = this.getCurrentLanguage();
		List<Borrowperiod> borrowperiodList = BorrowperiodUtil.getBorrowperiodList();
		int computeroderadvanceorderday = config.getMaxorderday();
		boolean pass = computerorderService.vaildComputerorderForm(computerorderdetailList, currentDate , currentPeriod, endDate, endPeriod, currentLanguage , borrowperiodList, computeroderadvanceorderday);
//		
		return pass;
	}
	
//	验证表单数据是否满足
	public boolean validClassOrderForm(int computerhomeworkid){
		
		System.out.println("curcomputerhomeworkid"+curcomputerhomeworkid);
		
		computerhomeworkList = computerhomeworkService.selectComputerhomeworkByCondition(" where id= "+ computerhomeworkid+"  ");
		
		int ruleid = computerhomeworkList.get(0).getComputerorderclassruleid();
		computerorderclassruleList = computerorderclassruleService.selectComputerorderclassruleByCondition( " where a.id = "+ruleid+" " );
		
		computerorderclassrule = computerorderclassruleList.get(0);
		
		
		Date currentDate = DateUtil.currentDate();
//		if(currentDate.after(orderstartDate)){
//			orderstartDate = currentDate;
//			log.info("orderstartDate" + DateUtil.dateFormat(orderstartDate, DateUtil.dateformatstr1));
//		}
		
		currentDate = computerorderclassrule.getOrderstarttime();
		
		int currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(currentDate);
//		可以预约 n天之内的PC,结束日期是最大预约天数减一
		log.info("验证数量能否满足");
		Date endDate = computerorderclassrule.getOrderendtime();
		int endPeriod = BorrowperiodUtil.getMaxPeriod();
		int currentLanguage = this.getCurrentLanguage();
		List<Borrowperiod> borrowperiodList = BorrowperiodUtil.getBorrowperiodList();
		int computeroderadvanceorderday = DateUtil.daysBetween(currentDate, endDate)+1;
		boolean pass = computerorderService.vaildComputerorderForm(computerorderdetailList, currentDate , currentPeriod, endDate, endPeriod, currentLanguage , borrowperiodList, computeroderadvanceorderday);
//		
		return pass;
	}
	
	
//  提交预约表单	
	public String addComputerorderAjax(){	
		log.info("Add Entity Ajax Manner" + computerorder.getComputerhomeworkid());
		
//		computerhomeworkid=computerorder.getComputerhomeworkid();
		
		try {
		
		Integer uid = getCurrentUserId();
		if(uid < 0){			
//			returnInfo = "用户未登录";
			returnInfo = getMsg("computerordersubmit_usernotlogin");
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
		if(confirmOrderInfo(orderInfoStr) == false){
//			returnInfo = "表单不正确";
			returnInfo = getMsg("computerordersubmit_formerror");
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
			return SUCCESS;
		}
		
//		设置默认标题
		if(computerorder.getTitle()==null || computerorder.getTitle().trim().length()==0){
//			computerorder.setTitle(DateUtil.dateFormat(DateUtil.currentDate(), "MM-dd")+"机房预约");
//			returnInfo = "请输入标题";
			returnInfo = getMsg("computerordersubmit_notitle");
			log.error(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		
		
		Computerorderconfig config = computerorderconfigService.getCurrentComputerorderconfig();
		if(config == null || config.getMaxorderday() == 0){
//			returnInfo = "获取预约配置信息出错";
			returnInfo = getMsg("computerordersubmit_orderconfigerror");
			log.error(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
	
		boolean adminNeedForce = false;
//		管理员预约
		if(computerordertype == ComputerorderInfo.IndividualOrder && this.isAdmin()){
//			如果数量不能满足，需要强制抢占
			boolean pass = validOrderForm(config);
			if(!pass){
				adminNeedForce = true;
			}
//			if(!pass){
//				returnInfo = "预约数量不能满足";
//				log.error(returnInfo);
//				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
//				return SUCCESS;
//			}
		}else if(computerordertype == ComputerorderInfo.IndividualOrder && !this.isAdmin()){
			boolean pass = validOrderForm(config);

			if(!pass){
//				returnInfo = "预约数量不能满足";
				returnInfo = getMsg("computerordersubmit_ordernumexceed");
				log.error(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
		}else if(computerordertype == ComputerorderInfo.ClassOrder){
			boolean pass = validClassOrderForm(computerorder.getComputerhomeworkid());

			if(!pass){
//				returnInfo = "预约数量不能满足";
				returnInfo = getMsg("computerordersubmit_ordernumexceed");
				log.error(returnInfo);
				this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
				return SUCCESS;
			}
		}else{
//			returnInfo = "预约类型不对";
			returnInfo = getMsg("computerordersubmit_ordertypeerror");
			log.error(returnInfo);
			this.returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn, returnInfo);
			return SUCCESS;
		}
		

//		如果是重新预约，则是知道预约id的
		if(reorder == 1){
			computerorder.setId(computerorderid);
			
		}

	
		System.out.println(computerorder.getTitle());
		
//		Admin需要抢占别人预约
		if(adminNeedForce){
			adminForceGet(config);
			computerorderService.addComputerorder(computerorder, computerordertype, reorder, uid, computerorderdetailList);			
		}else{
			computerorderService.addComputerorder(computerorder, computerordertype, reorder, uid, computerorderdetailList);			
		}
		
		session.put("computerorderSerialnumber", computerorder.getSerialnumber());
//		System.out.println( computerorder.getSerialnumber());
//		returnInfo = "预约成功";
		returnInfo = getMsg("computerordersubmit_ordersuccess");
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
		
//		returnInfo = "系统发生内部错误";
		returnInfo = getMsg("computerordersubmit_ordererror");
		returnStr = JsonActionUtil.buildReturnStr(JsonActionUtil.ajaxerrorreturn,returnInfo);
		return SUCCESS;
	}
	
	
//管理员抢占机房
	public void adminForceGet(Computerorderconfig config){
		Date currentDate = DateUtil.currentDate();
		int currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(currentDate);
//		可以预约 n天之内的PC,结束日期是最大预约天数减一
		log.info("验证数量能否满足");
		Date endDate = DateUtil.addDay(currentDate, config.getMaxorderday()-1);
		int endPeriod = BorrowperiodUtil.getMaxPeriod();
		int currentLanguage = this.getCurrentLanguage();
		List<Borrowperiod> borrowperiodList = BorrowperiodUtil.getBorrowperiodList();
		int computeroderadvanceorderday = config.getMaxorderday();
		computerorderService.adminForceGetComputer(computerorderdetailList, currentDate , currentPeriod, endDate, endPeriod, currentLanguage , borrowperiodList, computeroderadvanceorderday);

	}
	

	public String toComputerorderSuccessPage(){
		computerorderSerialnumber = session.get("computerorderSerialnumber");
		return SUCCESS;
	}

	

	@Override
	public Computerorder getModel() {
		// TODO Auto-generated method stub
		return computerorder;
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




	public int getReorder() {
		return reorder;
	}


	public void setReorder(int reorder) {
		this.reorder = reorder;
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


	public Integer getComputerorderclassruledetailid() {
		return computerorderclassruledetailid;
	}


	public void setComputerorderclassruledetailid(
			Integer computerorderclassruledetailid) {
		this.computerorderclassruledetailid = computerorderclassruledetailid;
	}


	public ComputerorderconfigService getComputerorderconfigService() {
		return computerorderconfigService;
	}


	public void setComputerorderconfigService(
			ComputerorderconfigService computerorderconfigService) {
		this.computerorderconfigService = computerorderconfigService;
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


	public Integer getComputerorderclassruleid() {
		return computerorderclassruleid;
	}


	public void setComputerorderclassruleid(Integer computerorderclassruleid) {
		this.computerorderclassruleid = computerorderclassruleid;
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


	public int getCurcomputerhomeworkid() {
		return curcomputerhomeworkid;
	}


	public void setCurcomputerhomeworkid(int curcomputerhomeworkid) {
		this.curcomputerhomeworkid = curcomputerhomeworkid;
	}


	public List<ComputerhomeworkFull> getNewComputerhomeworkFullList() {
		return newComputerhomeworkFullList;
	}


	public void setNewComputerhomeworkFullList(
			List<ComputerhomeworkFull> newComputerhomeworkFullList) {
		this.newComputerhomeworkFullList = newComputerhomeworkFullList;
	}


	public List<ComputerorderEntity> getComputerorderEntityList() {
		return computerorderEntityList;
	}


	public void setComputerorderEntityList(
			List<ComputerorderEntity> computerorderEntityList) {
		this.computerorderEntityList = computerorderEntityList;
	}


	public int getOrdertimecount() {
		return ordertimecount;
	}


	public void setOrdertimecount(int ordertimecount) {
		this.ordertimecount = ordertimecount;
	}


	public String getUserRoleName() {
		return userRoleName;
	}


	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}


	public Map<Integer, String> getUserRoleMap() {
		return userRoleMap;
	}


	public void setUserRoleMap(Map<Integer, String> userRoleMap) {
		this.userRoleMap = userRoleMap;
	}


	



	
	
	
	
	
	
	
	
	
	
	
	
	
}
