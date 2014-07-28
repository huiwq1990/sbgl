package com.sbgl.app.actions.computer;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import javax.annotation.Resource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;

import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.entity.Computermodel;
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
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputerconfigService;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.app.services.computer.ComputerorderconfigService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.DateUtil;



@Scope("prototype") 
@Controller("ComputerClassorderAction")
public class ComputerClassorderAction  extends BaseAction  {

	private static final Log log = LogFactory.getLog(ComputerClassorderAction.class);


	// Service
	@Resource
	private ComputerService computerService;
	List<Computer> computerList = new ArrayList<Computer>();
	List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	private Integer computerid; //entity full 的id属性名称		
	
	@Resource
	private ComputercategoryService computercategoryService;
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

	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	List<ComputerorderdetailFull> computerorderdetailFullList = new ArrayList<ComputerorderdetailFull>();
	private Integer computerorderdetailid; //entity full 的id属性名称		
	
	
	
	@Resource
	private ComputerorderclassruleService computerorderclassruleService;	
	private Computerorderclassrule computerorderclassrule = new Computerorderclassrule();//实例化一个模型
	private ComputerorderclassruleFull computerorderclassruleFull = new ComputerorderclassruleFull();//实例化一个模型
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	private Integer computerorderclassruleid; //entity full 的id属性名称		
	
	
	
	//Service	
	@Resource
	private ComputerorderclassruledetailService computerorderclassruledetailService;	
	private Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();//实例化一个模型
	private ComputerorderclassruledetailFull computerorderclassruledetailFull = new ComputerorderclassruledetailFull();//实例化一个模型
	List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
	List<ComputerorderclassruledetailFull> computerorderclassruledetailFullList = new ArrayList<ComputerorderclassruledetailFull>();
	private Integer computerorderclassruledetailid; //entity full 的id属性名称		
	
	
	@Resource	
	private ComputerhomeworkService computerhomeworkService;	
	private Integer computerhomeworkid; //entity full 的id属性名称	
	private Computerhomework computerhomework = new Computerhomework();//实例化一个模型
	private ComputerhomeworkFull computerhomeworkFull = new ComputerhomeworkFull();//实例化一个模型
	private List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
	private List<ComputerhomeworkFull> computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
	
	@Resource
	private ComputerhomeworkreceiverService computerhomeworkreceiverService;
	private Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();//实例化一个模型
	private ComputerhomeworkreceiverFull computerhomeworkreceiverFull = new ComputerhomeworkreceiverFull();//实例化一个模型
	private List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
	private List<ComputerhomeworkreceiverFull> computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
	
	
	@Resource
	private ComputerorderconfigService computerorderconfigService;	
	private Computerorderconfig computerorderconfig = new Computerorderconfig();//实例化一个模型
	private List<Computerorderconfig> computerorderconfigList = new ArrayList<Computerorderconfig>();
	
	
	List<String> ordernum = new ArrayList<String>();

	private String logprefix = "exec method";


	private String userid;
	
	private int currentPeriod ;
	private int computeroderadvanceorderday;
	private int computerodertablercolumn;
	private List<Borrowperiod> borrowperiodList   = new ArrayList<Borrowperiod>();
	private HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();
	
	
	private int showComputeroderadvanceorderday = 0;
	private List<Borrowperiod> showtimeList   = new ArrayList<Borrowperiod>();
	
	private HashMap<Integer,ArrayList<String>> showDateMap = new HashMap<Integer,ArrayList<String>>();
	private HashMap<Integer,ArrayList<String>> dateMap = new HashMap<Integer,ArrayList<String>>();
	HashMap<Integer,ArrayList<String>> showWeekdayMap = new HashMap<Integer,ArrayList<String>>();
	
//	提交预约表单的参数
	private String orderInfoStr;
	
	private int computerordertype = 0;

//	可借出的pc model type
	private String borrowPcModelStr = "";


//	判断是否已经预约
	private Integer computerorderid =0; //entity full 的id属性名称		
	private int orderstatus;
	private int reorder = 0;//默认为0或者没有，如果为1，则为重新预约
	
	
	private String passType;
	
int curcomputerhomeworkid;
	
	/**
	 * 跳转到机房课程预约界面
	 * 需要传入课程规则id:computerorderclassruleid
	 * @return
	 */
	public String toComputerClassorderPage(){		
		log.info("exec toComputerClassorderPage");
		
//		curcomputerhomeworkid = 
		
//		设置当前时间
		Date currentDate = DateUtil.currentDate();
//		获取语言
		int currentlanguagetype = this.getCurrentLanguage();	
		
		//设置假的时间
//		Date currentDate = DateUtil.parseDate("2013-10-01 18:00:00");
		String currentDateStr = DateUtil.dateFormat(currentDate, DateUtil.dateformatstr1);
		
		
		
//		设置为课程预约
		computerordertype = ComputerorderInfo.ClassOrder;
		
		if(computerhomeworkid == null || computerhomeworkid <0){
			actionMsg = "无法获取作业信息，访问界面不存在";
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		

		
		computerhomework = computerhomeworkService.sel(computerhomeworkid);
		
//		作业存在多个或没有
		if(computerhomework==null){
			actionMsg = "获取作业信息出错";
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		
//		检查是否已经预约
//		String checkHaveOrderSql = " where computerhomeworkid = "+computerhomeworkid + " and userid = "+this.getCurrentUserId();
		computerhomeworkreceiver = computerhomeworkreceiverService.sel(computerhomeworkid,this.getCurrentUserId());
//		取出用户的关于这个作业的预约接收信息
		if(computerhomeworkreceiver!= null){		
		}else{
			actionMsg = "作业预约状态获取出错";
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}

//		原先使用 computerhomeworkreceiver.getHasorder()判断是否能预约 现在改为
		if(computerhomeworkreceiver.getHavefinish()!=null && computerhomeworkreceiver.getHavefinish()==1){
			orderstatus = 1;
		}
		
		
//		computerhomework.get
		
		if(orderstatus == 1 && reorder !=1){
//			actionMsg = "已经预约过，不能再进行预约";
			actionMsg = getMsg("computerclassorder_classorderhaveorder");
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		
		
//		获取预约配置信息
		computerorderconfig = computerorderconfigService.selectCurrentComputerorderconfig();
		if(computerorderconfig == null){
			computerorderconfig = ComputerActionUtil.getDefaultComputerorderconfig();
		}		
		if(computerorderconfig.getOpenorder() == 0){
//			this.actionMsg = "预约功能暂时关闭。";
			actionMsg = getMsg("computerclassorder_classorderclose");
			return "orderclose";
		}		
		
		
		
//		根据作业获取规则的id
		computerorderclassruleid = computerhomework.getComputerorderclassruleid();
		computerorderclassruleList = computerorderclassruleService.selectComputerorderclassruleByCondition( " where a.id = "+computerorderclassruleid+" " );
		if(computerorderclassruleList == null || computerorderclassruleList.size() != 1){
//			actionMsg = "无法获取作业规则信息，访问界面不存在";
			actionMsg = getMsg("computerclassorder_classordernotruler");
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		computerorderclassrule = computerorderclassruleList.get(0);
	
//		computerorderclassrule.get
		Date orderstartDate = computerorderclassrule.getOrderstarttime();
		if(currentDate.after(orderstartDate)){
			orderstartDate = currentDate;
			log.info("orderstartDate" + DateUtil.dateFormat(orderstartDate, DateUtil.dateformatstr1));
		}
		Date orderEndDate = computerorderclassrule.getOrderendtime();
//		检查规则日期
		if(orderstartDate == null || orderEndDate == null ){
//			actionMsg = "作业规则日期错误";
			actionMsg = getMsg("computerclassorder_classorderruledateerror");
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		
		
		if(currentDate.after(computerorderclassrule.getOrderendtime())){
//			actionMsg = "课程预约的规定日期已过。";
			actionMsg = this.getMsg("computerclassorder_classorderoutdate");
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		
		
//		查询可以预约的器材
		String classruledetailcondition = " where a.computerorderclassruleid = "+computerorderclassruleid+" ";
		computerorderclassruledetailList = computerorderclassruledetailService.selectComputerorderclassruledetailByCondition(classruledetailcondition);
		
		
//		如果为空，直接返回
		if(computerorderclassruledetailList == null || computerorderclassruledetailList.size() == 0){
			computermodelList = new ArrayList<Computermodel>();
//			actionMsg = "没有可以预约的PC";
			actionMsg = getMsg("computerclassorder_notpc");
			log.error(actionMsg);
			return ComputerConfig.pagenotfound;
		}
		
//		设置可借出的pc model type
		borrowPcModelStr = "";
		if(computerorderclassruledetailList!=null && computerorderclassruledetailList.size()>0){
			for (int i = 0; i < computerorderclassruledetailList.size(); i++) {
				borrowPcModelStr += computerorderclassruledetailList.get(i).getAllowedcomputermodelid() + ",";
			}
			borrowPcModelStr = borrowPcModelStr.substring(0,borrowPcModelStr.length()-1);
		}else{
			log.info("没有可以预约的模型,直接返回！");
//			actionMsg = "没有可以预约的模型!";
			actionMsg = getMsg("computerclassorder_notpcmodel");
			return SUCCESS;
		}
		
		
	
		
//		获取可以借出的PC模型信息
		String getAvailableComputermodelFullTypeSql = " where a.languagetype="+currentlanguagetype+" and a.computermodeltype in (" + borrowPcModelStr +") ";
		log.info("可以借出的PC模型信息:"+getAvailableComputermodelFullTypeSql);
		computermodelList = computermodelService.selectComputermodelByCondition(getAvailableComputermodelFullTypeSql);
		
		
		calculate(orderstartDate,orderEndDate );
//		System.out.println(borrowperiodList.size());
		return SUCCESS;
	}

	
	public void buildShowDate(Date orderStartDate,int computeroderadvanceorderday,int computerodertablercolumn){
		int weeknum = computeroderadvanceorderday/computerodertablercolumn;
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
	
	public  void calculate(Date orderStartDate,Date orderEndDate) {

		log.info("预约开始时间（如果是开始时间小于当前时间，则是当前时间）："+ DateUtil.dateFormat(orderStartDate, DateUtil.dateformatstr1));
		log.info("预约结束时间："+ DateUtil.dateFormat(orderEndDate, DateUtil.dateformatstr1));
		
		currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(orderStartDate);			 
		log.info("当前时间段: "+currentPeriod);
		//设置提前预约的天数+
		computeroderadvanceorderday = DateUtil.daysBetween(orderStartDate, orderEndDate)+1;
		log.info("可以提前预约的天数："+computeroderadvanceorderday);

//		设备预约表格显示的列数，默认是7
		computerodertablercolumn = ComputerConfig.computerodertablercolumn;
//		调整预约时间，使时间是7的倍数
		showComputeroderadvanceorderday = 0;
		if(computeroderadvanceorderday%computerodertablercolumn !=0){
			showComputeroderadvanceorderday = (computeroderadvanceorderday/computerodertablercolumn + 1) * computerodertablercolumn ;			
		}else{
			showComputeroderadvanceorderday = computeroderadvanceorderday;
		}		
		log.info("页面显示时的提前预约天数showComputeroderadvanceorderday "+showComputeroderadvanceorderday);

		
		buildShowDate(orderStartDate,showComputeroderadvanceorderday,computerodertablercolumn);	 
			 
		
		//取得所有PC类型的当前库存数量
//		String currentlanguagetype = "0";
//		String getAllComputermodelTypeSql = " where a.languagetype="+currentlanguagetype+" ";
//		computermodelList = computermodelService.selectComputermodelByCondition(getAllComputermodelTypeSql);
		
//		for (int i = 0; i < computermodelList.size(); i++) {
//			System.out.println("model type=" + computermodelList.get(i).getComputermodeltype() + "  " + " 名称："+ computermodelList.get(i).getName()
//					+ computermodelList.get(i).getAvailableborrowcountnumber());
//		}
		
		//所有可借时间段信息
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
		
		log.info("初始话借用机房的map");
//		ComputerActionUtil.printAvailBorrowModelMap(computermodelList, borrowperiodList, computeroderadvanceorderday, availableBorrowModelMap);
		
		
//		根据模型构建 模型、时间段、日期的map
		availableBorrowModelMap = ComputerorderActionUtil.computermodelPeriodDayInfo(computermodelList, currentPeriod, borrowperiodList, computeroderadvanceorderday);
		log.info("根据模型可借数量初始话借用机房的map");
//		ComputerActionUtil.printAvailBorrowModelMap(computermodelList, borrowperiodList, computeroderadvanceorderday, availableBorrowModelMap);
		
		
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
		
		/*
		//初始化每个型号每个时段可借数量数组，		
		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
//			ComputermodelFull tempmodelFull =  computermodelFullList.get(tempmodelindex);//full list已经赋值
			Computermodel tempmodel =  computermodelList.get(tempmodelindex);//full list已经赋值
			HashMap<Integer,ArrayList<Integer>> periodDayAvailInfo = new HashMap<Integer,ArrayList<Integer>>();
			for(int tempperiod=0; tempperiod < borrowperiodList.size(); tempperiod++){
				Borrowperiod tempBorrowperiod = borrowperiodList.get(tempperiod);
				ArrayList<Integer> dayInfo = new ArrayList<Integer>();
				
				//对于今天过去的时间段处理
				int todaynum = 0;
//				System.out.println("period "+tempBorrowperiod.getPeriodnum());
				if(tempBorrowperiod.getPeriodnum() < currentPeriod ){
					todaynum = 0;
				}else{
//					todaynum = tempmodelFull.getComputermodelavailableborrowcountnumber();
					todaynum = tempmodel.getAvailableborrowcountnumber();
				}
				dayInfo.add(todaynum);
				
				
				for(int tempday=1; tempday < computeroderadvanceorderday; tempday++){	
					
//					int num = 0;
//					//对于今天过去的时间段处理
//					if(tempday==0 && currentPeriod <=2){
//						
//					}else if(currentPeriod<=4){
//						availableBorrowModelMap.get(2).get(key)
//					}
//					dayInfo.add( tempmodelFull.getComputermodelavailableborrowcountnumber());
					if(tempday <=trueadvanceday){
						dayInfo.add( tempmodel.getAvailableborrowcountnumber());
					}else{
						dayInfo.add( 0);
					}
					
				}				
				periodDayAvailInfo.put(tempBorrowperiod.getId(), dayInfo);
			}
//			availableBorrowModelMap.put(tempmodelFull.getComputermodelcomputermodeltype(), periodDayAvailInfo);
			availableBorrowModelMap.put(tempmodel.getComputermodeltype(), periodDayAvailInfo);
		}
		*/
		
//		查询当前时间之后，预约的清单
		log.info("查询当前时间之后，预约的清单");
		

//		查询当前时间之后，预约的清单
		Date startDate = orderStartDate;
		int startPeriod = BorrowperiodUtil.getBorrowTimePeriod(startDate);
		Date endDate = DateUtil.addDay(startDate, computeroderadvanceorderday-1);
		int endPeriod = BorrowperiodUtil.getMaxPeriod();
		
//		查询已经有的预约
		List<Computerorderdetail> validHaveorderComputerorderdetailList  = computerorderdetailService.selectValidComputerorderdetailFromStartToEndByModel(startDate, startPeriod, endDate, endPeriod, borrowPcModelStr);	
//		System.out.println(validHaveorderComputerorderdetailList.size());
//		for(int i = 0; i <validHaveorderComputerorderdetailList.size(); i++){
//			log.info("订单序号：="+validHaveorderComputerorderdetailList.get(i).getId() + ";订单预约设备的类型:" +validHaveorderComputerorderdetailList.get(i).getComputermodelid());
//		}
		//根据预约清单计算当前可借数量			
		for(Computerorderdetail od : validHaveorderComputerorderdetailList){
			int between = DateUtil.daysBetween(orderStartDate,od.getBorrowday());
			System.out.println("订单详情："+od.getId()+";model: "+od.getComputermodelid()+"; day: "+od.getBorrowday()+"; period: "+od.getBorrowperiod());
			int newcount = availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).get(between) - od.getBorrownumber();
			availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).set(between, newcount);
		}
		
		log.info("去除预约的订单后，模型可借数量：");
		ComputerActionUtil.printAvailBorrowModelMap(computermodelList, borrowperiodList, computeroderadvanceorderday, availableBorrowModelMap);
		
		
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


	public ComputerhomeworkService getComputerhomeworkService() {
		return computerhomeworkService;
	}


	public void setComputerhomeworkService(
			ComputerhomeworkService computerhomeworkService) {
		this.computerhomeworkService = computerhomeworkService;
	}


	public Integer getComputerhomeworkid() {
		return computerhomeworkid;
	}


	public void setComputerhomeworkid(Integer computerhomeworkid) {
		this.computerhomeworkid = computerhomeworkid;
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


	public String getOrderInfoStr() {
		return orderInfoStr;
	}


	public void setOrderInfoStr(String orderInfoStr) {
		this.orderInfoStr = orderInfoStr;
	}


	public int getComputerordertype() {
		return computerordertype;
	}


	public void setComputerordertype(int computerordertype) {
		this.computerordertype = computerordertype;
	}


	public String getBorrowPcModelStr() {
		return borrowPcModelStr;
	}


	public void setBorrowPcModelStr(String borrowPcModelStr) {
		this.borrowPcModelStr = borrowPcModelStr;
	}


	public Integer getComputerorderid() {
		return computerorderid;
	}


	public void setComputerorderid(Integer computerorderid) {
		this.computerorderid = computerorderid;
	}


	public int getOrderstatus() {
		return orderstatus;
	}


	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}


	public int getReorder() {
		return reorder;
	}


	public void setReorder(int reorder) {
		this.reorder = reorder;
	}


	public String getPassType() {
		return passType;
	}


	public void setPassType(String passType) {
		this.passType = passType;
	}


	public static Log getLog() {
		return log;
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
