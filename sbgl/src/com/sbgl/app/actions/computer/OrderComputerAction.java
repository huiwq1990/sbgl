package com.sbgl.app.actions.computer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputerconfigService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderconfigService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.ComputerDirective;
import com.sbgl.util.DateUtil;
import com.sbgl.util.Page;
import com.sbgl.util.ReturnJson;
import com.sbgl.util.SpringUtil;


@Scope("prototype") 
@Controller("OrderComputerAction")
public class OrderComputerAction  extends BaseAction {

	private static final Log log = LogFactory.getLog(OrderComputerAction.class);

	// Service
	@Resource
	private ComputerService computerService;
	List<Computer> computerList = new ArrayList<Computer>();
	List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	private Integer computerid; //entity full 的id属性名称		
	
	@Resource
	private ComputercategoryService computercategoryService;

	//父级分类的list
	List<Computercategory> parentcomputercategoryList = new ArrayList<Computercategory>();
	List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
	List<ComputercategoryFull> computercategoryFullList = new ArrayList<ComputercategoryFull>();
	
	
	@Resource
	private ComputermodelService computermodelService;
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	
	
	@Resource
	private ComputerorderService computerorderService;	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();
	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();
	private Integer computerorderid=0; //entity full 的id属性名称		
	
	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	private Integer computerorderdetailid; //entity full 的id属性名称		
	
	
//	@Resource
//	private ComputerconfigService computerconfigService;	
//	private Computerconfig computerconfig = new Computerconfig();//实例化一个模型
//	private List<Computerconfig> computerconfigList = new ArrayList<Computerconfig>();
//	
	
	@Resource
	private ComputerorderconfigService computerorderconfigService;	
	private Computerorderconfig computerorderconfig = new Computerorderconfig();//实例化一个模型
	private List<Computerorderconfig> computerorderconfigList = new ArrayList<Computerorderconfig>();
	
	List<String> ordernum = new ArrayList<String>();

	private String logprefix = "exec method";


	private String userid;
	
	int currentPeriod ;
//	int computerorderTotalOrderDay ;
	int computeroderadvanceorderday;
	private int showComputeroderadvanceorderday = 0;
	int computerodertablercolumn;
	List<Borrowperiod> borrowperiodList   = new ArrayList<Borrowperiod>();
	HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();
	
	List<Borrowperiod> showtimeList   = new ArrayList<Borrowperiod>();
	
	HashMap<Integer,ArrayList<String>> showDateMap = new HashMap<Integer,ArrayList<String>>();
	HashMap<Integer,ArrayList<String>> showWeekdayMap = new HashMap<Integer,ArrayList<String>>();
	HashMap<Integer,ArrayList<String>> dateMap = new HashMap<Integer,ArrayList<String>>();

	
//	预约类型
	int computerordertype;
	int computerhomeworkid;
	
//	提交预约表单的参数
	private String orderInfoStr;
	
	
//	判断是否已经预约
	int reorder = 0;//默认为0或者没有，如果为1，则为重新预约
	
	
	public String toComputerIndividualorderPage(){
		try{
		computerordertype = ComputerorderInfo.IndividualOrder;
//		如果是个人预约，默认作业id
		if(computerordertype == ComputerorderInfo.IndividualOrder){
			computerhomeworkid = 0;
		}

//		获取预约配置信息
		computerorderconfig = computerorderconfigService.selectCurrentComputerorderconfig();
		if(computerorderconfig == null){
			computerorderconfig = ComputerActionUtil.getDefaultComputerorderconfig();
		}
		
		if(computerorderconfig.getOpenorder() == 0){
//			JsonActionUtil.buildReturnStr(JsonActionUtil., errorStr)
			this.actionMsg = "预约功能暂时关闭。";
			return "orderclose";
		}
		
		//设置提前预约的天数
		computeroderadvanceorderday = computerorderconfig.getMaxorderday();
//		if()
		log.info("提前预约最大天数" + computeroderadvanceorderday);
//		设备预约表格显示的列数，默认是7
		computerodertablercolumn = ComputerConfig.computerodertablercolumn;

		
//		设置当前时间
		Date currentDate = DateUtil.currentDate();
		//设置假的时间
//		Date currentDate = DateUtil.parseDate("2013-10-01 18:00:00");
		String currentDateStr = DateUtil.dateFormat(currentDate, DateUtil.dateformatstr1);
		
//		获取语言
		int currentlanguagetype = this.getCurrentLanguage();
		
		String getAllComputermodelTypeSql = " where a.languagetype="+currentlanguagetype+" ";
		computermodelList = computermodelService.selectComputermodelByCondition(getAllComputermodelTypeSql);
		log.info("computermodelList" + computermodelList.size());
		calculate(currentDate,currentDateStr );
		
		System.out.println(borrowperiodList.size());
		return SUCCESS;
		
		
		}catch(Exception e){
			e.printStackTrace();
//			log.error("addComputerorderAjax错误"+e);
		
		}
		
		return "innererror";
	}

	
	public void buildShowDate(Date orderStartDate,int computeroderadvanceorderday,int computerodertablercolumn){
		int weeknum = computeroderadvanceorderday/computerodertablercolumn ;
		for(int i=0; i< weeknum;i++){
			ArrayList<String> weekday = new ArrayList<String>();
			ArrayList<String> time = new ArrayList<String>();
			ArrayList<String> weekStr = new ArrayList<String>();
			for(int col =0; col <computerodertablercolumn; col++ ){
				Date date = DateUtil.addDay(orderStartDate, i*computerodertablercolumn + col);
				String dayStr = DateUtil.dateFormat( date , "MM/dd");
				String timeStr = DateUtil.dateFormat( date , "yyyy-MM-dd");
				weekStr.add(dayStr);
				time.add(timeStr);
				weekday.add(DateUtil.getWeekOfDate(date));
				
			}
			showDateMap.put(i, weekStr);
			dateMap.put(i, time);
			showWeekdayMap.put(i, weekday);
		}
	}
	
	public  void calculate( Date currentDate,String currentDateStr ) {
		// TODO Auto-generated method stub
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
//		

//		调整预约时间，使时间是7的倍数
		if(computeroderadvanceorderday%computerodertablercolumn !=0){
			
			showComputeroderadvanceorderday = (computeroderadvanceorderday/computerodertablercolumn + 1) * computerodertablercolumn ;			
		}else{
			showComputeroderadvanceorderday = computeroderadvanceorderday;
		}
		
//		log.info("")
		System.out.println("computeroderadvanceorderday "+computeroderadvanceorderday +"showComputeroderadvanceorderday "+showComputeroderadvanceorderday);
//		int computerorderTotalOrderPeriod = ComputerConfig.computerorderTotalOrderPeriod;
		
//			设置当前时间
		String currentDay = DateUtil.dateFormat(currentDate, DateUtil.dateformatstr1);//"2013-10-01 18:00:00";
		currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(currentDate);
		System.out.println("currentDay:"+currentDay+"   currentPeriod: "+currentPeriod);
		 
			 
		buildShowDate(currentDate,showComputeroderadvanceorderday,computerodertablercolumn);	 
			 

		borrowperiodList =  BorrowperiodUtil.getBorrowperiodList();
		
		for(Computermodel model : computermodelList){
			HashMap<Integer,ArrayList<Integer>> periodDay = new HashMap<Integer,ArrayList<Integer>>();
			availableBorrowModelMap.put(model.getComputermodeltype(),periodDay);
			
			for(Borrowperiod bp : borrowperiodList){
			
				ArrayList<Integer> dayList = new ArrayList<Integer>();
				for(int tempday=0; tempday < computeroderadvanceorderday; tempday++){				
					dayList.add(0);					
				}
				
				periodDay.put(bp.getPeriodnum(), dayList);
				
			}
			System.out.println();
//			availableBorrowModelMap.put(modelList.get(tempmodel).getComputermodeltype(), periodDayAvailInfo);
		}
		
//		根据模型构建 模型、时间段、日期的map
		availableBorrowModelMap = ComputerorderActionUtil.computermodelPeriodDayInfo(computermodelList, currentPeriod, borrowperiodList, computeroderadvanceorderday);
		if(availableBorrowModelMap == null){
			 availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();			
		}
		
//		配合显示，将多出来的那些天数的数量设置为0
		if(showComputeroderadvanceorderday > computeroderadvanceorderday){
			for(Computermodel tempmodel : computermodelList){
				for(Borrowperiod tempBorrowperiod : borrowperiodList){
					for(int tempday=computeroderadvanceorderday; tempday < showComputeroderadvanceorderday; tempday++){
						availableBorrowModelMap.get(tempmodel.getComputermodeltype()).get(tempBorrowperiod.getPeriodnum()).add(0);
					}	
				}
			}
		}
		
		
		System.out.println(availableBorrowModelMap.size());
		
		/*for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
			System.out.println(tempmodel.getName()+"   ");
			for(int tempperiod=0; tempperiod < borrowperiodList.size(); tempperiod++){
				Borrowperiod tempBorrowperiod = borrowperiodList.get(tempperiod);
				System.out.println(tempBorrowperiod.getPeroidname());
				for(int tempday=0; tempday < computeroderadvanceorderday; tempday++){				
					
					System.out.print(availableBorrowModelMap.get(tempmodel.getComputermodeltype()).get(tempBorrowperiod.getId()).get(tempday)+"  ");
				}	
				System.out.println();
//				periodDayAvailInfo.put(periodList.get(tempperiod).getId(), dayInfo);
			}
			System.out.println();
//			availableBorrowModelMap.put(modelList.get(tempmodel).getComputermodeltype(), periodDayAvailInfo);
		}*/
		
			

//		查询当前时间之后，预约的清单
		Date startDate = currentDate;
		int startPeriod = BorrowperiodUtil.getBorrowTimePeriod(startDate);
		Date endDate = DateUtil.addDay(startDate, computerorderconfig.getMaxorderday()-1);
		int endPeriod = BorrowperiodUtil.getMaxPeriod();
		
//		查询已经有的预约
		List<Computerorderdetail> validHaveorderComputerorderdetailList  = computerorderdetailService.selectValidComputerorderdetailFromStartToEnd(startDate, startPeriod, endDate, endPeriod);	
		log.info("已经预约的订单："+validHaveorderComputerorderdetailList.size());
//		for(int i = 0; i <validHaveorderComputerorderdetailList.size(); i++){
//			System.out.println("id="+validHaveorderComputerorderdetailList.get(i).getId() + "  " +validHaveorderComputerorderdetailList.get(i).getComputermodelid());
//		}

		//根据预约清单计算当前可借数量			
		for(Computerorderdetail od : validHaveorderComputerorderdetailList){
				int between = DateUtil.daysBetween(DateUtil.parseDate(currentDay),od.getBorrowday());
				System.out.println("model: "+od.getComputermodelid()+"; day: "+od.getBorrowday()+"; period: "+od.getBorrowperiod());
				int newcount = availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).get(between) - od.getBorrownumber();
				availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).set(between, newcount);
		}

	
//		for(int tempmodelindex=0;tempmodelindex < computermodelList.size();tempmodelindex++){
//			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
//			for(int tempperiod=0; tempperiod < borrowperiodList.size(); tempperiod++){
//				Borrowperiod tempBorrowperiod = borrowperiodList.get(tempperiod);
//				for(int tempday=0; tempday < computeroderadvanceorderday; tempday++){				
//					
//					System.out.print(availableBorrowModelMap.get(tempmodel.getComputermodeltype()).get(tempBorrowperiod.getPeriodnum()).get(tempday)+"  ");
//				}	
//				System.out.println();
////				periodDayAvailInfo.put(periodList.get(tempperiod).getId(), dayInfo);
//			}
//			System.out.println();
////			availableBorrowModelMap.put(modelList.get(tempmodel).getComputermodeltype(), periodDayAvailInfo);
//		}
	
		
	}



	public String orderComputer(){
		System.out.println("orderComputer");
		for(int i=0; i < ordernum.size(); i++){
			System.out.println(ordernum.get(i));
		}
		
		return SUCCESS;
	}


	public ComputerService getComputerService() {
		return computerService;
	}


	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}


	public List<Computer> getComputerList() {
		return computerList;
	}


	public void setComputerList(List<Computer> computerList) {
		this.computerList = computerList;
	}


	public List<ComputerFull> getComputerFullList() {
		return computerFullList;
	}


	public void setComputerFullList(List<ComputerFull> computerFullList) {
		this.computerFullList = computerFullList;
	}


	public Integer getComputerid() {
		return computerid;
	}


	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}


	public ComputercategoryService getComputercategoryService() {
		return computercategoryService;
	}


	public void setComputercategoryService(
			ComputercategoryService computercategoryService) {
		this.computercategoryService = computercategoryService;
	}


	public List<Computercategory> getParentcomputercategoryList() {
		return parentcomputercategoryList;
	}


	public void setParentcomputercategoryList(
			List<Computercategory> parentcomputercategoryList) {
		this.parentcomputercategoryList = parentcomputercategoryList;
	}


	public List<Computercategory> getComputercategoryList() {
		return computercategoryList;
	}


	public void setComputercategoryList(List<Computercategory> computercategoryList) {
		this.computercategoryList = computercategoryList;
	}


	public List<ComputercategoryFull> getComputercategoryFullList() {
		return computercategoryFullList;
	}


	public void setComputercategoryFullList(
			List<ComputercategoryFull> computercategoryFullList) {
		this.computercategoryFullList = computercategoryFullList;
	}


	public ComputermodelService getComputermodelService() {
		return computermodelService;
	}


	public void setComputermodelService(ComputermodelService computermodelService) {
		this.computermodelService = computermodelService;
	}


	public List<Computermodel> getComputermodelList() {
		return computermodelList;
	}


	public void setComputermodelList(List<Computermodel> computermodelList) {
		this.computermodelList = computermodelList;
	}


	public List<ComputermodelFull> getComputermodelFullList() {
		return computermodelFullList;
	}


	public void setComputermodelFullList(
			List<ComputermodelFull> computermodelFullList) {
		this.computermodelFullList = computermodelFullList;
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


	public Integer getComputerorderid() {
		return computerorderid;
	}


	public void setComputerorderid(Integer computerorderid) {
		this.computerorderid = computerorderid;
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


	public ComputerorderconfigService getComputerorderconfigService() {
		return computerorderconfigService;
	}


	public void setComputerorderconfigService(
			ComputerorderconfigService computerorderconfigService) {
		this.computerorderconfigService = computerorderconfigService;
	}


	public Computerorderconfig getComputerorderconfig() {
		return computerorderconfig;
	}


	public void setComputerorderconfig(Computerorderconfig computerorderconfig) {
		this.computerorderconfig = computerorderconfig;
	}


	public List<Computerorderconfig> getComputerorderconfigList() {
		return computerorderconfigList;
	}


	public void setComputerorderconfigList(
			List<Computerorderconfig> computerorderconfigList) {
		this.computerorderconfigList = computerorderconfigList;
	}


	public List<String> getOrdernum() {
		return ordernum;
	}


	public void setOrdernum(List<String> ordernum) {
		this.ordernum = ordernum;
	}


	public String getLogprefix() {
		return logprefix;
	}


	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public int getCurrentPeriod() {
		return currentPeriod;
	}


	public void setCurrentPeriod(int currentPeriod) {
		this.currentPeriod = currentPeriod;
	}


	public int getComputeroderadvanceorderday() {
		return computeroderadvanceorderday;
	}


	public void setComputeroderadvanceorderday(int computeroderadvanceorderday) {
		this.computeroderadvanceorderday = computeroderadvanceorderday;
	}


	public int getComputerodertablercolumn() {
		return computerodertablercolumn;
	}


	public void setComputerodertablercolumn(int computerodertablercolumn) {
		this.computerodertablercolumn = computerodertablercolumn;
	}


	public List<Borrowperiod> getBorrowperiodList() {
		return borrowperiodList;
	}


	public void setBorrowperiodList(List<Borrowperiod> borrowperiodList) {
		this.borrowperiodList = borrowperiodList;
	}


	public HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> getAvailableBorrowModelMap() {
		return availableBorrowModelMap;
	}


	public void setAvailableBorrowModelMap(
			HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> availableBorrowModelMap) {
		this.availableBorrowModelMap = availableBorrowModelMap;
	}


	public List<Borrowperiod> getShowtimeList() {
		return showtimeList;
	}


	public void setShowtimeList(List<Borrowperiod> showtimeList) {
		this.showtimeList = showtimeList;
	}


	public HashMap<Integer, ArrayList<String>> getShowDateMap() {
		return showDateMap;
	}


	public void setShowDateMap(HashMap<Integer, ArrayList<String>> showDateMap) {
		this.showDateMap = showDateMap;
	}


	public HashMap<Integer, ArrayList<String>> getDateMap() {
		return dateMap;
	}


	public void setDateMap(HashMap<Integer, ArrayList<String>> dateMap) {
		this.dateMap = dateMap;
	}


	public int getComputerordertype() {
		return computerordertype;
	}


	public void setComputerordertype(int computerordertype) {
		this.computerordertype = computerordertype;
	}


	public String getOrderInfoStr() {
		return orderInfoStr;
	}


	public void setOrderInfoStr(String orderInfoStr) {
		this.orderInfoStr = orderInfoStr;
	}


	public int getReorder() {
		return reorder;
	}


	public void setReorder(int reorder) {
		this.reorder = reorder;
	}


	public static Log getLog() {
		return log;
	}


	public int getComputerhomeworkid() {
		return computerhomeworkid;
	}


	public void setComputerhomeworkid(int computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
	}


	public int getShowComputeroderadvanceorderday() {
		return showComputeroderadvanceorderday;
	}


	public void setShowComputeroderadvanceorderday(
			int showComputeroderadvanceorderday) {
		this.showComputeroderadvanceorderday = showComputeroderadvanceorderday;
	}


	public HashMap<Integer, ArrayList<String>> getShowWeekdayMap() {
		return showWeekdayMap;
	}


	public void setShowWeekdayMap(HashMap<Integer, ArrayList<String>> showWeekdayMap) {
		this.showWeekdayMap = showWeekdayMap;
	}
	
	
	
	
	
}