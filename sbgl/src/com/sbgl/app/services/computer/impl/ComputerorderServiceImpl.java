package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.computer.ComputerorderActionUtil;
import com.sbgl.app.actions.computer.ManageComputerorder;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.actions.util.SnActionUtil;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.dao.ComputerhomeworkreceiverDao;
import com.sbgl.app.dao.ComputermodelDao;
import com.sbgl.app.dao.ComputerorderDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderService")
@Transactional
public class ComputerorderServiceImpl implements ComputerorderService{
	
	private static final Log log = LogFactory.getLog(ComputerorderServiceImpl.class);

	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderDao computerorderDao;
	
	@Resource
	private ComputerorderdetailDao computerorderdetailDao;
	
	@Resource
	private ComputermodelDao computermodelDao;
	
	@Resource
	private ComputerhomeworkreceiverDao computerhomeworkreceiverDao;

	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorder(Computerorder computerorder){
		computerorder.setId(baseDao.getCode("Computerorder"));
		baseDao.saveEntity(computerorder);		
	}
	
	@Override
	public void addComputerorder(Computerorder tempcomputerorder,int computerordertype,int reorder,int uid,List<Computerorderdetail> computerorderdetailList) throws Exception{
		Computerorder computerorder = new Computerorder();
		String uuid = "";
		//		如果是驳回预约，则序列号不变
		if(reorder == 1){
			computerorder = baseDao.getEntityById(Computerorder.class,tempcomputerorder.getId());
			
			if(computerorder == null){
				 throw new RuntimeException("重新预约，无法获取原有订单");  
			}else{
//			computerorder中的序列号是原有的，重新预约不改变			
				computerorder.setTitle(tempcomputerorder.getTitle());
			}
			
		}else{
			uuid = SnActionUtil.genComputerorderSn(uid, computerordertype, DateUtil.currentDate());
			computerorder.setSerialnumber(uuid);
		}
//		需要设置课程预约的作业id
		computerorder.setComputerhomeworkid(tempcomputerorder.getComputerhomeworkid());
		computerorder.setCreateuserid(uid);
		computerorder.setOrdertype(computerordertype);
		computerorder.setCreatetime(DateUtil.currentDate());
		computerorder.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
		
		computerorder.setTitle(tempcomputerorder.getTitle());
		computerorder.setRemark(tempcomputerorder.getRemark());
//		保存订单信息 如果是驳回预约，则是更新
		if(reorder == 1){
//			temp.setId(computerorderid);
			baseDao.updateEntity(computerorder);
		}else{
			computerorder.setId(baseDao.getCode("Computerorder"));
			baseDao.saveEntity(computerorder);
		}
		
//		方便返回信息
		BeanUtils.copyProperties( tempcomputerorder , computerorder);			
		
//		如果是重新预约，需要删除原有的预约
		if(reorder == 1){
			String delSql = " delete from computerorderdetail where computerorderid = "+computerorder.getId();
			baseDao.createSQL(delSql);
		}
		
//		computerorderdetailList = (ArrayList<Computerorderdetail>)session.get("computerorderdetailList");
		
//		需要先保存order，获取id，再保存详情
		for(int i=0 ; i < computerorderdetailList.size();i++){
			Computerorderdetail cd = computerorderdetailList.get(i);
			cd.setComputerorderid(computerorder.getId());
			cd.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
			cd.setCreatetime(DateUtil.currentDate());
			
			cd.setId(baseDao.getCode("Computerorderdetail"));
			baseDao.saveEntity(cd);
		}
		
//		如果是课程预约，需要修改作业的状态，一次作业只能预约一次
		if(computerordertype == ComputerorderInfo.ClassOrder){
			String hmresql = " where computerhomeworkid = "+ computerorder.getComputerhomeworkid() +" and userid="+ uid;
			System.out.println(hmresql);
			List<Computerhomeworkreceiver> computerhomeworkreceiverList = computerhomeworkreceiverDao.selectComputerhomeworkreceiverByCondition(hmresql);
			if(computerhomeworkreceiverList == null || computerhomeworkreceiverList.size()!=1){
				throw new RuntimeException("无法更改作业状态");  
			}
			
			String updateChrSql = " update Computerhomeworkreceiver set hasorder="+ComputerConfig.computerhomeworkhasorder+" , hasview="+ComputerConfig.computerhomeworkhasview+" where id = "+computerhomeworkreceiverList.get(0).getId();
			baseDao.createSQL(updateChrSql);
		}

		
	}
	
	@Override
	public void addComputerorder(Computerorder ch,Computerorder en){
		
	/*
		int type = baseDao.getCode("Computerordertype");
		ch.setId(baseDao.getCode("Computerorder"));
		ch.setComputerordertype(type);
		en.setId(baseDao.getCode("Computerorder"));
		en.setComputerordertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerorderWithId(Computerorder computerorder){
	
		baseDao.saveEntity(computerorder);		
	}
	
	/**
	 * 验证表单中的预约数量能否满足
	 */
	@Override
	public boolean vaildComputerorderForm(List<Computerorderdetail> newOrderComputerorderdetailList,Date currentDate, int currentPeriod ,Date endDate, int endPeriod ,int currentLanguage, List<Borrowperiod> borrowperiodList,int computeroderadvanceorderday){
		
//		查找所有的模型
		List<Computermodel> computermodelList = new ArrayList<Computermodel>();
		String getAllComputermodelTypeSql = " where a.languagetype="+CommonConfig.languagech+" ";
		computermodelList = computermodelDao.selectComputermodelByCondition(getAllComputermodelTypeSql);
		
//		根据模型构建 模型、时间段、日期的map
		HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = ComputerorderActionUtil.computermodelPeriodDayInfo(computermodelList, currentPeriod, borrowperiodList, computeroderadvanceorderday);
		
		
//		获取已经预约的表单
		List<Computerorderdetail> haveOrderedValidComputerorderdetailList  = computerorderdetailDao.selectValidComputerorderdetailFromStartToEnd(currentDate, currentPeriod, endDate, endPeriod);
		
		return ComputerorderActionUtil.checkVaildComputerorderForm(availableBorrowModelMap, haveOrderedValidComputerorderdetailList, newOrderComputerorderdetailList, currentDate);
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorder(Integer computerorderId){
		String sql = "delete from Computerorder where id="+computerorderId;
		baseDao.createSQL(sql);
		return 1;
	}
	

//  根据实体删除实体
	@Override
	public int deleteComputerorder(Computerorder computerorder) {
		return deleteComputerorder(computerorder.getId());
	}

	
	@Override
	public int deleteComputerorder(List<Integer> orderidList) throws DataError {
		
		for(Integer orderid : orderidList){
			
			Computerorder tempCo = baseDao.getEntityById(Computerorder.class,orderid);
			if(tempCo == null){
				throw new DataError("获取删除表单出错");
			}
			
			tempCo.setStatus(ComputerorderInfo.ComputerorderStatusAduitDel);
			baseDao.updateEntity(tempCo);
			
			baseDao.createSQL(" update Computerorderdetail set status="+ComputerorderInfo.ComputerorderStatusAduitDel+" where computerorderid = "+orderid);
			
		}
		return 1;
	}
	
	@Override
	public void updateComputerorder(Computerorder computerorder){
		
		Computerorder tempComputerorder = new Computerorder();
		//tempComputerorder = baseDao.getEntityById(Computerorder.class, computerorder.getId());
		tempComputerorder = computerorder;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorder);

	}

//	根据id查询实体类			
	@Override
	public Computerorder selectComputerorderById(Integer computerorderId){		
		return baseDao.getEntityById(Computerorder.class, computerorderId);
	}
	
	@Override
	public List<Computerorder> selectComputerorderAll(){
			
		return baseDao.getAllEntity(Computerorder.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerorderFull selectComputerorderFullById(Integer computerorderId){
	
		return computerorderDao.selectComputerorderFullById(computerorderId); 
	}	
	
	@Override
	public boolean auditComputerorder(Computerorder cr){


			
//		computerorderService.execSql("  update Computerorder set rejectreason= "+computerorder.getRejectreason()+" and status="+computerorder.getStatus()+" where id = "+computerorder.getId());

		baseDao.updateEntity(cr);
		baseDao.createSQL(" update Computerorderdetail set status="+cr.getStatus()+" where computerorderid = "+cr.getId());

		return true;
	}
	
	

//  查询全部实体full
	@Override
	public List<ComputerorderFull> selectComputerorderFullAll() {
		// TODO Auto-generated method stub
		return computerorderDao.selectComputerorderFullAll();
	}
	
	
	public int countComputerorderRow(){
		return baseDao.getRowCount(Computerorder.class);
	}
		
//  分页查询
	public List<Computerorder> selectComputerorderByPage(Page page){	
		return baseDao.selectByPage(Computerorder.class,page);
	}
//  分页查询full	
	public List<ComputerorderFull> selectComputerorderFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerorder.class));
		return computerorderDao.selectComputerorderFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorder> selectComputerorderByCondition(String condition) {
		 return computerorderDao.selectComputerorderByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerorder>  selectComputerorderByConditionAndPage(String condition,final Page page) {
              return computerorderDao.selectComputerorderByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerorderFull> selectComputerorderFullByCondition(String condition) {
		return computerorderDao.selectComputerorderFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderFull>  selectComputerorderFullByConditionAndPage(String condition,final Page page) {
			return computerorderDao.selectComputerorderFullByConditionAndPage(condition, page);
		}
    	@Override
    	public int execSql(String sql) {
//    		String sql = "delete from Computerorderdetail " + condition;
    		baseDao.createSQL(sql);
    		return 1;
    		
    	}
	

}
