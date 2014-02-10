package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderdetailService")
@Transactional
public class ComputerorderdetailServiceImpl implements ComputerorderdetailService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderdetailDao computerorderdetailDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorderdetail(Computerorderdetail computerorderdetail){
		computerorderdetail.setId(baseDao.getCode("Computerorderdetail"));
		baseDao.saveEntity(computerorderdetail);		
	}
	
	@Override
	public void addComputerorderdetail(Computerorderdetail ch,Computerorderdetail en){
		
	/*
		int type = baseDao.getCode("Computerorderdetailtype");
		ch.setId(baseDao.getCode("Computerorderdetail"));
		ch.setComputerorderdetailtype(type);
		en.setId(baseDao.getCode("Computerorderdetail"));
		en.setComputerorderdetailtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerorderdetailWithId(Computerorderdetail computerorderdetail){
	
		baseDao.saveEntity(computerorderdetail);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorderdetail(Integer computerorderdetailId){
		Computerorderdetail computerorderdetail = new Computerorderdetail();
		computerorderdetail.setId(computerorderdetailId);
		//return computerorderdetailDao.deleteEntity(computerorderdetailId);	
		//baseDao.deleteEntityById(Computerorderdetail.class,computerorderdetailId);
		baseDao.deleteEntity(computerorderdetail);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerorderdetail(Computerorderdetail computerorderdetail) {
		return deleteComputerorderdetail(computerorderdetail.getId());
	}

	@Override
	public int deleteComputerorderdetailByCondition(String condition) {
		String sql = "delete from Computerorderdetail " + condition;
		baseDao.createSQL(sql);
		return 1;
		
	}
	
	@Override
	public void updateComputerorderdetail(Computerorderdetail computerorderdetail){
		
		Computerorderdetail tempComputerorderdetail = new Computerorderdetail();
		//tempComputerorderdetail = baseDao.getEntityById(Computerorderdetail.class, computerorderdetail.getId());
		tempComputerorderdetail = computerorderdetail;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorderdetail);

	}

//	根据id查询实体类			
	@Override
	public Computerorderdetail selectComputerorderdetailById(Integer computerorderdetailId){		
		return baseDao.getEntityById(Computerorderdetail.class, computerorderdetailId);
	}
	
	@Override
	public List<Computerorderdetail> selectComputerorderdetailAll(){
			
		return baseDao.getAllEntity(Computerorderdetail.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId){
	
		return computerorderdetailDao.selectComputerorderdetailFullById(computerorderdetailId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll() {
		// TODO Auto-generated method stub
		return computerorderdetailDao.selectComputerorderdetailFullAll();
	}
	
	
	public int countComputerorderdetailRow(){
		return baseDao.getRowCount(Computerorderdetail.class);
	}
		
//  分页查询
	public List<Computerorderdetail> selectComputerorderdetailByPage(Page page){	
		return baseDao.selectByPage(Computerorderdetail.class,page);
	}
//  分页查询full	
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerorderdetail.class));
		return computerorderdetailDao.selectComputerorderdetailFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByCondition(String condition) {
		 return computerorderdetailDao.selectComputerorderdetailByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerorderdetail>  selectComputerorderdetailByConditionAndPage(String condition,final Page page) {
              return computerorderdetailDao.selectComputerorderdetailByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByCondition(String condition) {
		return computerorderdetailDao.selectComputerorderdetailFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderdetailFull>  selectComputerorderdetailFullByConditionAndPage(String condition,final Page page) {
			return computerorderdetailDao.selectComputerorderdetailFullByConditionAndPage(condition, page);
		}
	
	
	//根据computerorderid 查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid ) {
		List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
		String id = String.valueOf(computerorderid );
		computerorderdetailList = baseDao.getEntityByProperty("Computerorderdetail", "computerorderid ", id);
		return computerorderdetailList;
	}
	//根据computermodelid 查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByComputermodelId(Integer computermodelid ) {
		List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
		String id = String.valueOf(computermodelid );
		computerorderdetailList = baseDao.getEntityByProperty("Computerorderdetail", "computermodelid ", id);
		return computerorderdetailList;
	}
	//根据computerid 查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid ) {
		List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
		String id = String.valueOf(computerid );
		computerorderdetailList = baseDao.getEntityByProperty("Computerorderdetail", "computerid ", id);
		return computerorderdetailList;
	}
	//根据computerorderid 查询实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid ) {
		return computerorderdetailDao.selectComputerorderdetailFullByComputerorderId(computerorderid );
	}
	//根据computermodelid 查询实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputermodelId(Integer computermodelid ) {
		return computerorderdetailDao.selectComputerorderdetailFullByComputermodelId(computermodelid );
	}
	//根据computerid 查询实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid ) {
		return computerorderdetailDao.selectComputerorderdetailFullByComputerId(computerid );
	}
	
	
	
	  /**
     * 取出所有的预约单，预约时间大于currentDay
     * 并且 小于预约期限
     * @param day
     * @param period
     * @return
     * select * from computerorderdetail where ((borrowday = "2013-10-02 00:00:00" and borrowperiod >=1) or (borrowday > "2013-10-02 00:00:00"))

     */
    @Override
    public List<Computerorderdetail> selectComputerorderdetailAfterNow(String currentDay,int currentPeriod){

            int computerorderTotalOrderDay = ComputerConfig.computeroderadvanceorderday;
//            int computerorderTotalOrderPeriod = ComputerConfig.computerorderTotalOrderPeriod;
            Date curDate = DateUtil.parseDate(currentDay);
            Date endDate = DateUtil.addDay(curDate, computerorderTotalOrderDay);
            String endate = DateUtil.dateFormat(endDate,DateUtil.dateformatstr1);
            String cond = "where ( (borrowday = '" + currentDay+"' and borrowperiod >="+currentPeriod+") or ";
            cond +=  "             ((borrowday > '" + currentDay+"') and (borrowday < '" + endate+"') )";
            cond +=  "            ) and ";
            cond +=  "            ( status in ("+ComputerorderdetailInfo.ComputerorderdetailStatusAduitPass+","+ComputerorderdetailInfo.ComputerorderdetailStatusAduitWait+") ) ";
//            cond = " ";
    System.out.println(cond);
            return computerorderdetailDao.selectComputerorderdetailByCondition(cond);

    }
    
    
    @Override
    public List<Computerorderdetail> selectBookedComputerorderdetailFromStartToEnd(String startDay,int startPeriod,String endDay,int endPeriod){

            int computerorderTotalOrderDay = ComputerConfig.computeroderadvanceorderday;
//            int computerorderTotalOrderPeriod = ComputerConfig.computerorderTotalOrderPeriod;
            Date curDate = DateUtil.parseDate(startDay);
            Date endDate = DateUtil.parseDate(endDay);
            String endate = DateUtil.dateFormat(endDate,DateUtil.dateformatstr1);
            String cond = "where ( (borrowday = '" + startDay+"' and borrowperiod >="+startPeriod+") or ";
            cond +=  "             ((borrowday > '" + startDay+"') and (borrowday <= '" + endate+"') )";
            cond +=  "            ) and ";
            cond +=  "            ( status in ("+ComputerorderdetailInfo.ComputerorderdetailStatusAduitPass+","+ComputerorderdetailInfo.ComputerorderdetailStatusAduitWait+") ) ";
//            cond = " ";
    System.out.println(cond);
            return computerorderdetailDao.selectComputerorderdetailByCondition(cond);

    }
    
    
	@Override
	public int execSql(String sql) {
//		String sql = "delete from Computerorderdetail " + condition;
		baseDao.createSQL(sql);
		return 1;
		
	}
	

}
