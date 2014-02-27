package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.actions.util.JsonActionUtil;
import com.sbgl.app.actions.util.SnActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.dao.ComputerhomeworkreceiverDao;
import com.sbgl.app.dao.ComputerorderDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderService")
@Transactional
public class ComputerorderServiceImpl implements ComputerorderService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderDao computerorderDao;
	
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
		
		computerorder.setCreateuserid(uid);
		computerorder.setOrdertype(computerordertype);
		computerorder.setCreatetime(DateUtil.currentDate());
		computerorder.setStatus(ComputerorderInfo.ComputerorderStatusAduitWait);
//		保存订单信息
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
