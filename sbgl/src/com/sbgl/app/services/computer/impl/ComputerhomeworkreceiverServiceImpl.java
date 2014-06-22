package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.app.services.computer.ComputerhomeworkreceiverService;
import com.sbgl.app.dao.ComputerhomeworkreceiverDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerhomeworkreceiverService")
@Transactional
public class ComputerhomeworkreceiverServiceImpl implements ComputerhomeworkreceiverService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerhomeworkreceiverDao computerhomeworkreceiverDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver){
		computerhomeworkreceiver.setId(baseDao.getCode("Computerhomeworkreceiver"));
		baseDao.saveEntity(computerhomeworkreceiver);		
	}
	
	@Override
	public void addComputerhomeworkreceiver(Computerhomeworkreceiver ch,Computerhomeworkreceiver en){
		
	/*
		int type = baseDao.getCode("Computerhomeworkreceivertype");
		ch.setId(baseDao.getCode("Computerhomeworkreceiver"));
		ch.setComputerhomeworkreceivertype(type);
		en.setId(baseDao.getCode("Computerhomeworkreceiver"));
		en.setComputerhomeworkreceivertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerhomeworkreceiverWithId(Computerhomeworkreceiver computerhomeworkreceiver){
	
		baseDao.saveEntity(computerhomeworkreceiver);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerhomeworkreceiver(Integer computerhomeworkreceiverId){
		Computerhomeworkreceiver computerhomeworkreceiver = new Computerhomeworkreceiver();
		computerhomeworkreceiver.setId(computerhomeworkreceiverId);
		//return computerhomeworkreceiverDao.deleteEntity(computerhomeworkreceiverId);	
		//baseDao.deleteEntityById(Computerhomeworkreceiver.class,computerhomeworkreceiverId);
		baseDao.deleteEntity(computerhomeworkreceiver);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver) {
		return deleteComputerhomeworkreceiver(computerhomeworkreceiver.getId());
	}
	@Override
	public int delById(int id){
		computerhomeworkreceiverDao.delById(id);
		return 1;
	}
	
	@Override
	public void updateComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver){
		
		Computerhomeworkreceiver tempComputerhomeworkreceiver = new Computerhomeworkreceiver();
		//tempComputerhomeworkreceiver = baseDao.getEntityById(Computerhomeworkreceiver.class, computerhomeworkreceiver.getId());
//		tempComputerhomeworkreceiver = computerhomeworkreceiver;
		//add your code here
		
		
		baseDao.updateEntity(computerhomeworkreceiver);

	}
	
	
	
	
	@Override
	public int execSql(String sql) {
//		String sql = "delete from Computerorderdetail " + condition;
		baseDao.createSQL(sql);
		return 1;
		
	}
	
	/**
	 * 获取信息
	 */
	@Override
	public Computerhomeworkreceiver sel(int homeworkid,int userid) {
		List<Computerhomeworkreceiver> l = computerhomeworkreceiverDao.sel(homeworkid, userid);
		if(l ==null || l.size() == 0){
			return null;
		}else{
			
		}
		return l.get(0);
	}

//	根据id查询实体类			
	@Override
	public Computerhomeworkreceiver selectComputerhomeworkreceiverById(Integer computerhomeworkreceiverId){		
		return baseDao.getEntityById(Computerhomeworkreceiver.class, computerhomeworkreceiverId);
	}
	
	@Override
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverAll(){
			
		return baseDao.getAllEntity(Computerhomeworkreceiver.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerhomeworkreceiverFull selectComputerhomeworkreceiverFullById(Integer computerhomeworkreceiverId){
		String sql = "where a.id=" + computerhomeworkreceiverId;
		List<ComputerhomeworkreceiverFull> temp = computerhomeworkreceiverDao.selectComputerhomeworkreceiverFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullAll() {
		// TODO Auto-generated method stub
		return computerhomeworkreceiverDao.selectComputerhomeworkreceiverFullAll();
	}
	
	
	public int countComputerhomeworkreceiverRow(){
		return baseDao.getRowCount(Computerhomeworkreceiver.class);
	}
		
//  分页查询
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByPage(Page page){	
		return baseDao.selectByPage(Computerhomeworkreceiver.class,page);
	}
//  分页查询full	
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerhomeworkreceiver.class));
		return computerhomeworkreceiverDao.selectComputerhomeworkreceiverFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByCondition(String condition) {
		 return computerhomeworkreceiverDao.selectComputerhomeworkreceiverByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerhomeworkreceiver>  selectComputerhomeworkreceiverByConditionAndPage(String condition,final Page page) {
              return computerhomeworkreceiverDao.selectComputerhomeworkreceiverByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByCondition(String condition) {
		return computerhomeworkreceiverDao.selectComputerhomeworkreceiverFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerhomeworkreceiverFull>  selectComputerhomeworkreceiverFullByConditionAndPage(String condition,final Page page) {
			return computerhomeworkreceiverDao.selectComputerhomeworkreceiverFullByConditionAndPage(condition, page);
		}
	
	
	//根据userid 查询实体
	@Override
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByLoginuserId(Integer userid ) {
		List<Computerhomeworkreceiver> computerhomeworkreceiverList = new ArrayList<Computerhomeworkreceiver>();
		String id = String.valueOf(userid );
		computerhomeworkreceiverList = baseDao.getEntityByProperty("Computerhomeworkreceiver", "userid ", id);
		return computerhomeworkreceiverList;
	}
	//根据userid 查询实体full
	@Override
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByLoginuserId(Integer userid ) {
		return computerhomeworkreceiverDao.selectComputerhomeworkreceiverFullByLoginuserId(userid );
	}
	
	
//	@Override
//	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByUserAndOrder(int uid,int hasorder) {
//		return computerhomeworkreceiverDao.selectComputerhomeworkreceiverByCondition(" where userid ="+uid+" and hasorder="+hasorder+" ");
//	}


	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public ComputerhomeworkreceiverDao getComputerhomeworkreceiverDao() {
		return computerhomeworkreceiverDao;
	}

	public void setComputerhomeworkreceiverDao(
			ComputerhomeworkreceiverDao computerhomeworkreceiverDao) {
		this.computerhomeworkreceiverDao = computerhomeworkreceiverDao;
	}
	
	
	

}
