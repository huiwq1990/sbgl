package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.InternalContextAdapter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
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
import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderclassruleService;
import com.sbgl.app.services.computer.ComputerstatusService;
import com.sbgl.util.ComputerDirective;
import com.sbgl.util.Page;
import com.sbgl.util.SpringContextUtil;
import com.sbgl.util.SpringUtil;



@Scope("prototype") 
@Controller("ManageComputerAction")
public class ManageComputerAction extends ActionSupport implements SessionAware{

	private static final Log log = LogFactory.getLog(ManageComputerAction.class);

	private Map<String, Object> session;
	private int pageNo;
	private String callType;

	// Service
	
	
	@Resource
	private ComputercategoryService computercategoryService;
	int computercategorytype = 0;
	//父级分类的list
	List<Computercategory> parentcomputercategoryList = new ArrayList<Computercategory>();
	List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
	List<ComputercategoryFull> computercategoryFullList = new ArrayList<ComputercategoryFull>();
	List<ComputercategoryFull> computercategoryFullListCh = new ArrayList<ComputercategoryFull>();
	List<ComputercategoryFull> computercategoryFullListEn = new ArrayList<ComputercategoryFull>();
	List<Integer> computercategoryModelSize = new ArrayList<Integer>();
	List<Integer> computercategoryComputerSize = new ArrayList<Integer>();
	
	@Resource
	private ComputermodelService computermodelService;
	int computermodeltype = 0;
//	List<Computercategory> computermodeComputercategoryList = new ArrayList<Computercategory>();
	List<Computermodel> computermodelList = new ArrayList<Computermodel>();
	List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListCh = new ArrayList<ComputermodelFull>();
	List<ComputermodelFull> computermodelFullListEn = new ArrayList<ComputermodelFull>();
	
	
	@Resource
	private ComputerService computerService;
	List<Computer> computerList = new ArrayList<Computer>();
	List<ComputerFull> computerFullList = new ArrayList<ComputerFull>();
	List<ComputerFull> computerFullListCh = new ArrayList<ComputerFull>();
	List<ComputerFull> computerFullListEn = new ArrayList<ComputerFull>();
	//key是分类信息，value是分类中的模型
//	HashMap<Integer, ArrayList<ComputermodelFull>> computermodelByComputercategoryId = new HashMap<Integer,ArrayList<ComputermodelFull>>();

	private Integer computerid; //entity full 的id属性名称	
	





	@Resource
	private ComputerorderService computerorderService;
	private int ComputerorderStatusAduitAll = ComputerorderInfo.ComputerorderStatusAduitAll;
	private int ComputerorderStatusAduitPass = ComputerorderInfo.ComputerorderStatusAduitPass;
	private int ComputerorderStatusAduitReject = ComputerorderInfo.ComputerorderStatusAduitReject;
	private int ComputerorderStatusAduitDel = ComputerorderInfo.ComputerorderStatusAduitDel;
	private int ComputerorderStatusAduitWait = ComputerorderInfo.ComputerorderStatusAduitWait;
	private int IndividualOrder = ComputerorderInfo.IndividualOrder;
	private int ClassOrder = ComputerorderInfo.IndividualOrder;
	
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();
	List<ComputerorderFull> computerorderFullList = new ArrayList<ComputerorderFull>();
	//前台传参，获取某类型的Order
	int computerorderStatus;
	
	@Resource
	private ComputerstatusService computerstatusService;
	int computerstatusid = 0;
	List<Computerstatus> computerstatusList = new ArrayList<Computerstatus>();
	List<ComputerstatusFull> computerstatusFullList = new ArrayList<ComputerstatusFull>();
	
	@Resource
	private ComputerorderclassruleService computerorderclassruleService;
	private Computerorderclassrule computerorderclassrule = new Computerorderclassrule();//实例化一个模型
	private ComputerorderclassruleFull computerorderclassruleFull = new ComputerorderclassruleFull();//实例化一个模型
	List<Computerorderclassrule> computerorderclassruleList = new ArrayList<Computerorderclassrule>();
	List<ComputerorderclassruleFull> computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
	
	@Resource
	private ComputerhomeworkService computerhomeworkService;	
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
	
	
	
	
	
	HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
//	HashMap<Integer, ArrayList<Computer>> computerByComputermodelId = new HashMap<Integer,ArrayList<Computer>>();
	
	
	private String logprefix = "exec method";
	Page page = new Page();

	
	
	
	
	
			
	//管理 查询
	public String manageComputercategoryFull(){
		log.info("exec action method:manageComputercategoryFull");
		
//      分页查询		
		if(pageNo ==0){
			pageNo =1;
		}
		
		
		//设置总数量，由于是双语 除2
		page.setTotalCount(computercategoryService.countComputercategoryRow()/2);
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		if(page.getTotalCount()==0){
			page.setPageNo(0);
			page.setTotalpage(0);
			pageNo = 0;
		}
		
//		查询中文的分类
		String sqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
		computercategoryFullListCh  = computercategoryService.selectComputercategoryFullByConditionAndPage(sqlch , page);
		
//		查询英文的分类
		String sqlen = " where a.languagetype=1 order by a.computercategorytype,a.languagetype";
		computercategoryFullListEn  = computercategoryService.selectComputercategoryFullByConditionAndPage(sqlen , page);
		
//		计算数量
		computerCategoryModelMap(computercategoryFullListCh);
		
		for(ComputercategoryFull categoryFull : computercategoryFullListCh){
			int categoryType = categoryFull.getComputercategorycomputercategorytype();
			ArrayList<Computermodel> entry = computermodelByComputercategoryId.get(categoryType);
//		for (Entry<Integer, ArrayList<Computermodel>> entry : computermodelByComputercategoryId.entrySet()) {
			computercategoryModelSize.add(entry.size());
			
			int computersize = 0;
			if(entry != null && entry.size() > 0){
				for(Computermodel m : entry){
//					System.out.println(m.getComputercount());
					if(m.getComputercount() != null){
						computersize += m.getComputercount();
					}					
				}
			}
			
			computercategoryComputerSize.add(computersize);
		}
		
//		查询全部
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();

		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		if(computercategoryFullListCh == null){
			computercategoryFullListCh = new ArrayList<ComputercategoryFull>();
		}
		if(computercategoryFullListEn == null){
			computercategoryFullListEn = new ArrayList<ComputercategoryFull>();
		}	
		if(computercategoryModelSize == null){
			computercategoryModelSize = new ArrayList<Integer>();
		}
		if(computercategoryComputerSize == null){
			computercategoryComputerSize = new ArrayList<Integer>();
		}
		
		parentcomputercategoryList = computercategoryService.selectParentComputercategory();
		if(parentcomputercategoryList == null){
			parentcomputercategoryList = new ArrayList<Computercategory>();
		}
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success";
		}
	}			
	
	
//	获取种类到模型的map
	public void computerCategoryModelMap(List<ComputercategoryFull> tempComputercategoryFullList){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		
		if(tempComputercategoryFullList == null){
			computermodelByComputercategoryId  = new HashMap<Integer,ArrayList<Computermodel>>();
			return ;
		}
		for(ComputercategoryFull computercategoryFull : tempComputercategoryFullList){
			int computercategoryType = computercategoryFull.getComputercategorycomputercategorytype();

			String sqlch = " where a.languagetype="+ComputerConfig.languagech+" and a.computercategoryid = "+computercategoryType+" order by a.computermodeltype,a.languagetype";		
			ArrayList<Computermodel> tempComputermodelFullListCh  = (ArrayList<Computermodel>) computermodelService.selectComputermodelByCondition(sqlch );
			if(tempComputermodelFullListCh == null){
				tempComputermodelFullListCh = new ArrayList<Computermodel>();
			}
//			System.out.println(tempComputermodelFullListCh.size());
			computermodelByComputercategoryId.put(computercategoryType, tempComputermodelFullListCh);
		}
		
	}
	
	
	/*
//	获取模型到机器的map
	public void computerModelComputerMap(List<ComputercategoryFull> tempComputermodelList){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		
		if(tempComputercategoryFullList == null){
			computerByComputermodelId  = new HashMap<Integer,ArrayList<Computer>>();
			return ;
		}
		for(ComputercategoryFull computercategoryFull : tempComputercategoryFullList){
			int computercategoryType = computercategoryFull.getComputercategorycomputercategorytype();

			String sqlch = " where a.languagetype="+ComputerConfig.languagech+" and a.computercategoryid = "+computercategoryType+" order by a.computermodeltype,a.languagetype";		
			ArrayList<Computer> tempComputermodelFullListCh  = (ArrayList<Computer>) computermodelService.selectComputermodelByCondition(sqlch );
			if(tempComputermodelFullListCh == null){
				tempComputermodelFullListCh = new ArrayList<Computer>();
			}
			System.out.println(tempComputermodelFullListCh.size());
			computerByComputermodelId.put(computercategoryType, tempComputermodelFullListCh);
		}
		
	}
	*/
	
	//管理 查询
/*	public void manageComputercategoryFull(InternalContextAdapter cxt,Map param,VelocityContext context){
		log.info("exec action method:manageComputercategoryFull");
//		ApplicationContext a=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		 WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
		ComputercategoryService computercategoryService =(ComputercategoryService)SpringContextUtil.getWebApplicationContext().getBean("computercategoryService");
		if(computercategoryService ==null){
			System.out.println("aaaaaassssssssssssssssssss");
		}
//      分页查询		
		page.setPageNo(pageNo);
		//设置总数量，在service中设置
		//page.setTotalpage(computercategoryService.countComputercategoryRow());
//		computercategoryFullList  = computercategoryService.selectShowedComputercategoryFullByPage(page);
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByConditionAndPage(" order by computercategorytype,languagetype", page);
		
//		查询全部
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullAll();

		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		
		parentcomputercategoryList = computercategoryService.selectParentComputercategory();
		if(parentcomputercategoryList == null){
			parentcomputercategoryList = new ArrayList<Computercategory>();
		}
		context.put("computercategoryFullList", computercategoryFullList);
//		return SUCCESS;
	}
*/	
	
	
			
	//pc管理 
	/**
	 * 
	 * 需要获取PC的所有可能状态
	 */
	public String manageComputerFull(){
		log.info("exec action method:manageComputerFull");

		
//      分页查询		
		if(pageNo ==0){
			pageNo =1;
		}		
		
		String countsql = " where a.languagetype="+ComputerConfig.languagech;
		String sqlch =" where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech;
		String sqlen= " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen;
		
		//查询全部中文的
		
		//查询所有分类
		if(computercategorytype==0){
			
		}
		//查询某一个Model下的
		if(computercategorytype!=0 && computermodeltype!=0){
			countsql +=  " and a.computermodelid="+computermodeltype;
			sqlch = sqlch + " and a.computermodelid="+computermodeltype;
			sqlen = sqlen +  " and a.computermodelid="+computermodeltype;
		}
//		查询某一分类下的
		if(computercategorytype!=0 && computermodeltype==0){
			List<Computermodel> tempComputermodelList = computermodelService.selectComputermodelByCondition(" where a.computercategoryid="+computercategorytype);
			String inStr = " (";
			for(Computermodel c : tempComputermodelList){
				inStr += c.getId()+",";
			}
			inStr = inStr.substring(0,inStr.length()-1);
			inStr += ") ";
			countsql += " and a.computermodelid in "+inStr+" ";
			sqlch = sqlch + " and a.computermodelid in "+inStr+" ";
			sqlen = sqlen + " and a.computermodelid in "+inStr+" ";
		}

//		//查询sql,如果computermodeltype为0，查询全部
//		if(computermodeltype == 0){
//			countsql += " where a.languagetype="+ComputerConfig.languagech;
//			sqlch = sqlch + " where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech;
//			sqlen = sqlen + " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen;
//		}else{
//			countsql =" where a.computermodelid="+computermodeltype;
////			countsql = countsql + " where a.languagetype="+ComputerConfig.languagech+" and a.computercategoryid="+computercategoryid+"  order by a.computermodeltype,a.languagetype";
//			sqlch = sqlch + " where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech+" and a.computermodelid="+computermodeltype;
//			sqlen = sqlen + " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen+" and a.computermodelid="+computermodeltype;
//		}
		
		if(computerstatusid == 0){
			
		}else{
//			if(computermodeltype != 0){
//				countsql = countsql +" where ";
//			}else{
//				countsql = countsql +" and ";
//			}
			countsql = countsql + " and a.computerstatusid=" + computerstatusid;
			sqlch = sqlch + " and a.computerstatusid=" + computerstatusid;
			sqlen = sqlen + " and a.computerstatusid=" + computerstatusid;
		}		
		sqlch += " order by a.computertype,a.languagetype";
		sqlen += " order by a.computertype,a.languagetype";
		
		System.out.println(countsql);
		System.out.println(sqlch);
		System.out.println(sqlen);
		
		//设置总数量，查询中文的
		page.setTotalCount(computerService.selectComputerByCondition(countsql).size());
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		System.out.println("page count:"+page.getTotalCount());
		
		computerFullListCh = computerService.selectComputerFullByConditionAndPage(sqlch, page);
		computerFullListEn = computerService.selectComputerFullByConditionAndPage(sqlen, page);
		System.out.println(computerFullListCh.size()+" " + computerFullListEn.size());
		
//		computer的model及分类信息，只显示中文的
		String categorysqlch = " where a.languagetype="+ComputerConfig.languagech+" order by a.computercategorytype,a.languagetype";
//		System.out.println(categorysqlch);
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
//		System.out.println("computercategoryFullList size:"+computercategoryFullList.size());
		//分类与PC模型map
		categoryModelMap();
		
		
		//pc所有状态
		computerstatusFullList  = computerstatusService.selectComputerstatusFullByCondition("");//查询所有的状态
		computerstatusList  = computerstatusService.selectComputerstatusByCondition("");//查询所有的状态
//		log.info("computerstatusList" + computerstatusList.size());
		
		if(computerFullListCh == null){
			computerFullListCh = new ArrayList<ComputerFull>();
		}
		if(computerFullListEn == null){
			computerFullListEn = new ArrayList<ComputerFull>();
		}
		if(computermodelByComputercategoryId == null){
			computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
		}
		if(computerstatusList == null){
			computerstatusList = new ArrayList<Computerstatus>();
		}
		

		//进入管理界面直接请求，Ajax请求使用AjaxType
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}		
	
	//管理 查询
	public String manageComputerorderFull(){
		log.info("exec action method:manageComputerorderFull");
		
//      分页查询		
		if(pageNo ==0){
			pageNo =1;
		}		
		
		
//		装载数据
		String sql = " ";	
		if(computerorderStatus==ComputerorderInfo.ComputerorderStatusAduitAll){
			
		}else{
			sql = sql+" where a.status="+computerorderStatus+" ";
		}
			
		//设置总数量
		page.setTotalCount(computerorderService.selectComputerorderFullByCondition(sql).size());
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		computerorderFullList  = computerorderService.selectComputerorderFullByConditionAndPage(sql, page);

		
		if(computerorderFullList == null){
			computerorderFullList = new ArrayList<ComputerorderFull>();
		}

		//进入管理界面直接请求，Ajax请求使用AjaxType
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
	
	public void categoryModelMap(){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		
		if(computercategoryFullList == null){
			computermodelByComputercategoryId  = new HashMap<Integer,ArrayList<Computermodel>>();
			return ;
		}
		for(ComputercategoryFull computercategoryFull : computercategoryFullList){
			int computercategoryType = computercategoryFull.getComputercategorycomputercategorytype();
//			if(!computermodelByComputercategoryId.containsKey(computercategoryType)){		
//				computermodelByComputercategoryId.put(computercategoryType, new ArrayList<ComputermodelFull>());
//			}
			String sqlch = " where a.languagetype="+ComputerConfig.languagech+" and a.computercategoryid = "+computercategoryType+" order by a.computermodeltype,a.languagetype";		
			ArrayList<Computermodel> tempComputermodelFullListCh  = (ArrayList<Computermodel>) computermodelService.selectComputermodelByCondition(sqlch );
			if(tempComputermodelFullListCh == null){
				tempComputermodelFullListCh = new ArrayList<Computermodel>();
			}
//			System.out.println(tempComputermodelFullListCh.size());
			computermodelByComputercategoryId.put(computercategoryType, tempComputermodelFullListCh);
		}
		
	}
	
	//管理ComputermodelFull
	public String manageComputermodelFull(){
		log.info("exec action method:manageComputermodelFull"+computercategorytype );

		
//      分页查询		
		if(pageNo ==0){
			pageNo =1;
		}		
		
		
		String countsql = "";
		String sqlch = "";
		String sqlen = "";
		//查询sql,如果computercategoryid为0，查询全部
		if(computercategorytype == 0){
//			countsql = countsql + " where a.languagetype="+ComputerConfig.languagech;
			countsql = "";
			sqlch = sqlch + " where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech+" order by a.computermodeltype ";
			sqlen = sqlen + " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen+" order by a.computermodeltype,a.languagetype";
		}else{
			countsql =" where a.computercategoryid="+computercategorytype;
//			countsql = countsql + " where a.languagetype="+ComputerConfig.languagech+" and a.computercategoryid="+computercategoryid+"  order by a.computermodeltype,a.languagetype";
			sqlch = sqlch + " where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech+" and a.computercategoryid="+computercategorytype+"  order by a.computermodeltype ";
			sqlen = sqlen + " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen+" and a.computercategoryid="+computercategorytype+"  order by a.computermodeltype,a.languagetype";
		}
		
		//设置总数量，直接查询Computermodel表，由于是双语 除2
		List<Computermodel> countlist = computermodelService.selectComputermodelByCondition(countsql);
		if(countlist==null){
			page.setTotalCount(0);
		}else{
			page.setTotalCount(countlist.size()/2);
		}
		
		//如果页码大于总页数，重新设置
		if(pageNo>page.getTotalpage()){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
//		System.out.println(page.getTotalCount());
		
		//查询中文的Model		
		log.info("computermodelFullListCh: " + sqlch);
		computermodelFullListCh  = computermodelService.selectComputermodelFullByConditionAndPage(sqlch , page);
//		log.info("computermodelFullListCh.size"+ computermodelFullListCh.size());
		//查询英文的Model
		computermodelFullListEn  = computermodelService.selectComputermodelFullByConditionAndPage(sqlen , page);
//		System.out.println("ssss"+computermodelFullListEn.size());
		
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype="+ComputerConfig.languagech+" order by a.computercategorytype,a.languagetype";
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		String categorysqlch = " where a.languagetype="+ComputerConfig.languagech+" order by a.computercategorytype,a.languagetype";
		computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorysqlch);
		
		if(computermodelFullListCh == null){
			computermodelFullListCh = new ArrayList<ComputermodelFull>();
		}
		if(computermodelFullListEn == null){
			computermodelFullListEn = new ArrayList<ComputermodelFull>();
		}
		if(computercategoryFullList == null){
			computercategoryFullList = new ArrayList<ComputercategoryFull>();
		}
		if(computercategoryList == null){
			computercategoryList = new ArrayList<Computercategory>();
		}
		

		//进入管理界面直接请求，Ajax请求使用AjaxType
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
	
	
	
	
	/**
	 * 跳转到pc添加页面
	 * @return
	 */
	public String toAddComputerPage(){
//		String sqlch = " where a.languagetype="+ ComputerConfig.languagech +" order by a.computercategorytype";
//		computercategoryFullList  = computercategoryService.selectComputercategoryFullByConditionAndPage(sqlch , page);
//		sqlch = " where a.languagetype=0 order by a.computermodeltype ";		
//		computermodelFullList  = computermodelService.selectComputermodelFullByConditionAndPage(sqlch , page);
//		
		
		String categorysqlch = " where a.languagetype="+ComputerConfig.languagech+" order by a.computercategorytype";
//		System.out.println(categorysqlch);
		computercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
//		System.out.println("computercategoryFullList size:"+computercategoryFullList.size());
		//分类与PC模型map
		categoryModelMap();
		
		//PC状态
		computerstatusList  = computerstatusService.selectComputerstatusByCondition("");//查询所有的状态
		
		if(computermodelFullList == null){
			computermodelFullList = new ArrayList<ComputermodelFull>();
		}
		if(computerFullListEn == null){
			computerFullListEn = new ArrayList<ComputerFull>();
		}
		if(computermodelByComputercategoryId == null){
			computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
		}
		if(computerstatusList == null){
			computerstatusList = new ArrayList<Computerstatus>();
		}
		
		return SUCCESS;
	}
	
	
	//管理PC状态
	public String manageComputerstatusFull(){
		log.info("exec action method:manageComputerstatusFull");
		
//      分页查询	
		if(pageNo ==0){
			pageNo =1;
		}
		page.setTotalCount(computerstatusService.countComputerstatusRow());
		if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		
		computerstatusFullList  = computerstatusService.selectComputerstatusFullByPage(page);
		
//		查询全部
//		computerstatusFullList  = computerstatusService.selectComputerstatusFullAll();

		if(computerstatusFullList == null){
			computerstatusFullList = new ArrayList<ComputerstatusFull>();
		}
//		for(int i = 0; i < computerstatusFullList.size(); i++){
//			System.out.println("id="+computerstatusFullList.get(i).getLoginusername());
//		}
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
			
	
	
	//管理课程预约规则
	public String manageComputerorderclassruleFull(){
		log.info("exec action method:manageComputerorderclassruleFull");
		
//      分页查询	
		if(pageNo ==0){
			pageNo =1;
		}
		page.setTotalCount(computerorderclassruleService.countComputerorderclassruleRow());
		if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		//page.setTotalpage(computerorderclassruleService.countComputerorderclassruleRow());
		computerorderclassruleFullList  = computerorderclassruleService.selectComputerorderclassruleFullByConditionAndPage("", page);
		
//		查询全部
//		computerorderclassruleFullList  = computerorderclassruleService.selectComputerorderclassruleFullAll();

		if(computerorderclassruleFullList == null){
			computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
		}
//		for(int i = 0; i < computerorderclassruleFullList.size(); i++){
//			System.out.println("id="+computerorderclassruleFullList.get(i).getLoginusername());
//		}
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
	
	
	
	public String toAddComputerorderclassrulePage(){
//		获取全部分类信息
		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
		computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorysqlch);
//		获取全部模型信息
		String modelsqlen = " where a.languagetype=1 order by a.computermodeltype,a.languagetype";
		computermodelList  =  computermodelService.selectComputermodelByCondition(modelsqlen);
		
//		将模型与分类关联
		computermodelByComputercategoryId = ComputerActionUtil.categoryModelMap(computermodelList);
	
		
		if(computercategoryList == null){
			computercategoryList = new ArrayList<Computercategory>();
		}
		
		return SUCCESS;
	}
	
	
	public String createComputerorderclassrule(){
		
		return SUCCESS;
	}
	
	
	//跳转到添加作业界面
	/**
	 * 返回参数
	 * computercategoryList
	 * computermodelByComputercategoryId
	 */
	public String toAddComputerhomeworkPage(){
		log.info("exec action method: toAddComputerhomeworkPage");
		

//		获取全部分类信息
		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";	
		computercategoryList  = computercategoryService.selectComputercategoryByCondition(categorysqlch);
//		获取全部模型信息
		String modelsqlen = " where a.languagetype=1 order by a.computermodeltype,a.languagetype";
		computermodelList  =  computermodelService.selectComputermodelByCondition(modelsqlen);
		
//		将模型与分类关联
		computermodelByComputercategoryId = ComputerActionUtil.categoryModelMap(computermodelList);
	
//		查询可以选择的预约规则
		computerorderclassruleFullList  = computerorderclassruleService.selectComputerorderclassruleFullByCondition("");
		
		if(computercategoryList == null){
			computercategoryList = new ArrayList<Computercategory>();
		}
		if(computerorderclassruleFullList == null){
			computerorderclassruleFullList = new ArrayList<ComputerorderclassruleFull>();
		}
		
		return SUCCESS;
	}	
	
	
	
	//管理作业
	public String manageComputerhomeworkFull(){
		log.info("exec action method:manageComputerhomeworkFull");
		
//      分页查询	
		if(pageNo ==0){
			pageNo =1;
		}
		page.setTotalCount(computerhomeworkService.countComputerhomeworkRow());
		if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullByConditionAndPage("", page);
		System.out.println("computerhomeworkFullList.size"+computerhomeworkFullList.size());
//		查询全部
//		computerhomeworkFullList  = computerhomeworkService.selectComputerhomeworkFullAll();

		if(computerhomeworkFullList == null){
			computerhomeworkFullList = new ArrayList<ComputerhomeworkFull>();
		}
//		for(int i = 0; i < computerhomeworkFullList.size(); i++){
//			System.out.println("id="+computerhomeworkFullList.get(i).getLoginusername());
//		}
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
			
	
	//管理作业接受人
	public String manageComputerhomeworkreceiverFull(){
		log.info("exec action method:manageComputerhomeworkreceiverFull");
		
//      分页查询	
		if(pageNo ==0){
			pageNo =1;
		}
		page.setTotalCount(computerhomeworkreceiverService.countComputerhomeworkreceiverRow());
		if(page.getTotalpage() < pageNo){
			pageNo = page.getTotalpage();
		}
		page.setPageNo(pageNo);
		
		computerhomeworkreceiverFullList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullByPage(page);
		
//		查询全部
//		computerhomeworkreceiverFullList  = computerhomeworkreceiverService.selectComputerhomeworkreceiverFullAll();

		if(computerhomeworkreceiverFullList == null){
			computerhomeworkreceiverFullList = new ArrayList<ComputerhomeworkreceiverFull>();
		}
//		for(int i = 0; i < computerhomeworkreceiverFullList.size(); i++){
//			System.out.println("id="+computerhomeworkreceiverFullList.get(i).getLoginusername());
//		}
		
		if(callType!=null&&callType.equals("ajaxType")){
			return "success2";
		}else{
			return "success1";
		}
	}			
			
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public ComputerService getComputerService() {
		return computerService;
	}

	public void setComputerService(ComputerService computerService) {
		this.computerService = computerService;
	}

	public ComputercategoryService getComputercategoryService() {
		return computercategoryService;
	}

	public void setComputercategoryService(
			ComputercategoryService computercategoryService) {
		this.computercategoryService = computercategoryService;
	}

	public ComputermodelService getComputermodelService() {
		return computermodelService;
	}

	public void setComputermodelService(ComputermodelService computermodelService) {
		this.computermodelService = computermodelService;
	}

	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}

	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
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

	public List<ComputerorderFull> getComputerorderFullList() {
		return computerorderFullList;
	}

	public void setComputerorderFullList(
			List<ComputerorderFull> computerorderFullList) {
		this.computerorderFullList = computerorderFullList;
	}



	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public static Log getLog() {
		return log;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getComputerid() {
		return computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public String getLogprefix() {
		return logprefix;
	}

	public void setLogprefix(String logprefix) {
		this.logprefix = logprefix;
	}

	public List<Computercategory> getParentcomputercategoryList() {
		return parentcomputercategoryList;
	}

	public void setParentcomputercategoryList(
			List<Computercategory> parentcomputercategoryList) {
		this.parentcomputercategoryList = parentcomputercategoryList;
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



	public String getCallType() {
		return callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public List<ComputercategoryFull> getComputercategoryFullListCh() {
		return computercategoryFullListCh;
	}

	public void setComputercategoryFullListCh(
			List<ComputercategoryFull> computercategoryFullListCh) {
		this.computercategoryFullListCh = computercategoryFullListCh;
	}

	public List<ComputercategoryFull> getComputercategoryFullListEn() {
		return computercategoryFullListEn;
	}

	public void setComputercategoryFullListEn(
			List<ComputercategoryFull> computercategoryFullListEn) {
		this.computercategoryFullListEn = computercategoryFullListEn;
	}

	public List<ComputermodelFull> getComputermodelFullListCh() {
		return computermodelFullListCh;
	}

	public void setComputermodelFullListCh(
			List<ComputermodelFull> computermodelFullListCh) {
		this.computermodelFullListCh = computermodelFullListCh;
	}

	public List<ComputermodelFull> getComputermodelFullListEn() {
		return computermodelFullListEn;
	}

	public void setComputermodelFullListEn(
			List<ComputermodelFull> computermodelFullListEn) {
		this.computermodelFullListEn = computermodelFullListEn;
	}

	public List<ComputerFull> getComputerFullListCh() {
		return computerFullListCh;
	}

	public void setComputerFullListCh(List<ComputerFull> computerFullListCh) {
		this.computerFullListCh = computerFullListCh;
	}

	public List<ComputerFull> getComputerFullListEn() {
		return computerFullListEn;
	}

	public void setComputerFullListEn(List<ComputerFull> computerFullListEn) {
		this.computerFullListEn = computerFullListEn;
	}

	

	public HashMap<Integer, ArrayList<Computermodel>> getComputermodelByComputercategoryId() {
		return computermodelByComputercategoryId;
	}

	public void setComputermodelByComputercategoryId(
			HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId) {
		this.computermodelByComputercategoryId = computermodelByComputercategoryId;
	}

	public ComputerstatusService getComputerstatusService() {
		return computerstatusService;
	}

	public void setComputerstatusService(ComputerstatusService computerstatusService) {
		this.computerstatusService = computerstatusService;
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

	public List<Computerorder> getComputerorderList() {
		return computerorderList;
	}

	public void setComputerorderList(List<Computerorder> computerorderList) {
		this.computerorderList = computerorderList;
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

	public int getComputerorderStatus() {
		return computerorderStatus;
	}

	public void setComputerorderStatus(int computerorderStatus) {
		this.computerorderStatus = computerorderStatus;
	}

	public int getComputercategorytype() {
		return computercategorytype;
	}

	public void setComputercategorytype(int computercategorytype) {
		this.computercategorytype = computercategorytype;
	}

	public int getComputermodeltype() {
		return computermodeltype;
	}

	public void setComputermodeltype(int computermodeltype) {
		this.computermodeltype = computermodeltype;
	}

	public int getComputerstatusid() {
		return computerstatusid;
	}

	public void setComputerstatusid(int computerstatusid) {
		this.computerstatusid = computerstatusid;
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


	public List<Integer> getComputercategoryModelSize() {
		return computercategoryModelSize;
	}


	public void setComputercategoryModelSize(List<Integer> computercategoryModelSize) {
		this.computercategoryModelSize = computercategoryModelSize;
	}


	public List<Integer> getComputercategoryComputerSize() {
		return computercategoryComputerSize;
	}


	public void setComputercategoryComputerSize(
			List<Integer> computercategoryComputerSize) {
		this.computercategoryComputerSize = computercategoryComputerSize;
	}


	
	
}
