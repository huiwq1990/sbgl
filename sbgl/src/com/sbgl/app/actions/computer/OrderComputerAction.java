package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.ComputerDirective;
import com.sbgl.util.DateUtil;
import com.sbgl.util.Page;
import com.sbgl.util.SpringUtil;


@Scope("prototype") 
@Controller("OrderComputerAction")
public class OrderComputerAction  extends ActionSupport implements SessionAware{

	private static final Log log = LogFactory.getLog(ManageComputerAction.class);

	private Map<String, Object> session;
	private int pageNo;
	private String passType;

	// Service
	@Resource
	private ComputerService computerService;
	List<Computer> computerList = new ArrayList<Computer>();
	List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	private Integer computerid; //entity full 的id属性名称		
	
	@Resource
	private ComputercategoryService computercategoryService;
	@Resource
	private ComputerorderdetailService computerorderdetailService;
	
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

	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();

	private String logprefix = "exec method";
	
	
	int currentPeriod ;
//	int computerorderTotalOrderDay ;
	int computeroderadvanceorderday;
	int computerodertablercolumn;
	List<Borrowperiod> borrowperiodList   = new ArrayList<Borrowperiod>();
	HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();
	
	List<Borrowperiod> showtimeList   = new ArrayList<Borrowperiod>();
	
	

	public String toOderComputerPage(){
		String currentlanguagetype = "0";
		String getAllComputermodelFullTypeSql = " where a.languagetype="+currentlanguagetype+" ";
//		String conditionSql = " where ";
		computermodelFullList = computermodelService.selectComputermodelFullByCondition(getAllComputermodelFullTypeSql );
		
		calculate();
		
		
		
		System.out.println(borrowperiodList.size());
		return SUCCESS;
	}
	
	
	
	public  void calculate() {
		// TODO Auto-generated method stub
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
//		
		computeroderadvanceorderday = ComputerConfig.computeroderadvanceorderday;
		computerodertablercolumn = ComputerConfig.computerodertablercolumn;
		if(computeroderadvanceorderday%computerodertablercolumn !=0){
			computeroderadvanceorderday = (computeroderadvanceorderday/computerodertablercolumn + 1) * computerodertablercolumn ;
			
		}
		System.out.println("computeroderadvanceorderday "+computeroderadvanceorderday);
//		int computerorderTotalOrderPeriod = ComputerConfig.computerorderTotalOrderPeriod;
		
//			设置当前时间
			String currentDay = "2013-10-01 18:00:00";
			 currentPeriod = BorrowperiodUtil.getBorrowTimePeriod(DateUtil.parseDate(currentDay));
			 System.out.println("currentPeriod: "+currentPeriod);
		 
		//取得所有PC类型的当前库存数量
		String currentlanguagetype = "0";
		String getAllComputermodelTypeSql = " where languagetype="+currentlanguagetype+" ";
		computermodelList = computermodelService.selectComputermodelByCondition(getAllComputermodelTypeSql);
		for (int i = 0; i < computermodelList.size(); i++) {
			System.out.println("当前可借数量id=" + computermodelList.get(i).getId() + "  " + " 名称："+ computermodelList.get(i).getName()
					+ computermodelList.get(i).getAvailableborrowcountnumber());
		}
		
		//所有可借时间段信息
//		Map<Integer,Borrowperiod> periodMap = BorrowperiodUtil.getBorrowperiodMap();
		borrowperiodList =  BorrowperiodUtil.getBorrowperiodList();
		//初始化每个型号每个时段可借数量数组，
		
		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
			HashMap<Integer,ArrayList<Integer>> periodDayAvailInfo = new HashMap<Integer,ArrayList<Integer>>();
			for(int tempperiod=0; tempperiod < borrowperiodList.size(); tempperiod++){
				Borrowperiod tempBorrowperiod = borrowperiodList.get(tempperiod);
				ArrayList<Integer> dayInfo = new ArrayList<Integer>();
				
				//对于今天过去的时间段处理
				int todaynum = 0;
				if(tempBorrowperiod.getId() < currentPeriod ){
					todaynum = 0;
				}else{
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
					dayInfo.add( tempmodel.getAvailableborrowcountnumber());
				}				
				periodDayAvailInfo.put(tempBorrowperiod.getId(), dayInfo);
			}
			availableBorrowModelMap.put(tempmodel.getComputermodeltype(), periodDayAvailInfo);
		}
		
		System.out.println(availableBorrowModelMap.size());
		
		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
			System.out.println(tempmodel.getName());
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
		}
		
		//pc模型1的可借数量
//		Integer[][] pcnumberArray = availableBorrowModelMap.get(1);
//		for(int tempperiod=0; tempperiod < computerorderTotalOrderPeriod; tempperiod++){
//			for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){				
//				System.out.print(pcnumberArray[tempperiod][tempday] + " ");
//			}	
//			System.out.println();
//		}
	
			

//		查询当前时间之后，预约的清单
		System.out.println("预约订单：");
//		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		 
		List<Computerorderdetail> computerorderdetailList  = computerorderdetailService.selectComputerorderdetailAfterNow(currentDay, currentPeriod);
		System.out.println(computerorderdetailList.size());
		for(int i = 0; i <computerorderdetailList.size(); i++){
			System.out.println("id="+computerorderdetailList.get(i).getId() + "  " +computerorderdetailList.get(i).getComputermodelid());
		}

		//根据预约清单计算当前可借数量			
		for(Computerorderdetail od : computerorderdetailList){
				int between = DateUtil.daysBetween(DateUtil.parseDate(currentDay),od.getBorrowday());
				System.out.println(od.getId());
				int newcount = availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).get(between) - od.getBorrownumber();
				 availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).set(between, newcount);
		}

	
		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
			for(int tempperiod=0; tempperiod < borrowperiodList.size(); tempperiod++){
				Borrowperiod tempBorrowperiod = borrowperiodList.get(tempperiod);
				for(int tempday=0; tempday < computeroderadvanceorderday; tempday++){				
					
					System.out.print(availableBorrowModelMap.get(tempmodel.getComputermodeltype()).get(tempBorrowperiod.getId()).get(tempday)+"  ");
				}	
				System.out.println();
//				periodDayAvailInfo.put(periodList.get(tempperiod).getId(), dayInfo);
			}
			System.out.println();
//			availableBorrowModelMap.put(modelList.get(tempmodel).getComputermodeltype(), periodDayAvailInfo);
		}
		
		
		
		
	
	}



	public Map<String, Object> getSession() {
		return session;
	}



	public void setSession(Map<String, Object> session) {
		this.session = session;
	}



	public int getPageNo() {
		return pageNo;
	}



	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}



	public String getPassType() {
		return passType;
	}



	public void setPassType(String passType) {
		this.passType = passType;
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



	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}



	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
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



	public List<ComputerorderFull> getComputerorderFullList() {
		return computerorderFullList;
	}



	public void setComputerorderFullList(
			List<ComputerorderFull> computerorderFullList) {
		this.computerorderFullList = computerorderFullList;
	}



	public String getLogprefix() {
		return logprefix;
	}



	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
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



	public static Log getLog() {
		return log;
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



	public List<Borrowperiod> getShowtimeList() {
		return showtimeList;
	}



	public void setShowtimeList(List<Borrowperiod> showtimeList) {
		this.showtimeList = showtimeList;
	}
	
	
	
	
}
