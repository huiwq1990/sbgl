package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.app.services.computer.ComputerorderclassruledetailService;
import com.sbgl.app.dao.ComputerorderclassruledetailDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderclassruledetailService")
@Transactional
public class ComputerorderclassruledetailServiceImpl implements ComputerorderclassruledetailService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderclassruledetailDao computerorderclassruledetailDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail){
		computerorderclassruledetail.setId(baseDao.getCode("Computerorderclassruledetail"));
		baseDao.saveEntity(computerorderclassruledetail);		
	}
	
	@Override
	public void addComputerorderclassruledetail(Computerorderclassruledetail ch,Computerorderclassruledetail en){
		
	/*
		int type = baseDao.getCode("Computerorderclassruledetailtype");
		ch.setId(baseDao.getCode("Computerorderclassruledetail"));
		ch.setComputerorderclassruledetailtype(type);
		en.setId(baseDao.getCode("Computerorderclassruledetail"));
		en.setComputerorderclassruledetailtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerorderclassruledetailWithId(Computerorderclassruledetail computerorderclassruledetail){
	
		baseDao.saveEntity(computerorderclassruledetail);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorderclassruledetail(Integer computerorderclassruledetailId){
		Computerorderclassruledetail computerorderclassruledetail = new Computerorderclassruledetail();
		computerorderclassruledetail.setId(computerorderclassruledetailId);
		//return computerorderclassruledetailDao.deleteEntity(computerorderclassruledetailId);	
		//baseDao.deleteEntityById(Computerorderclassruledetail.class,computerorderclassruledetailId);
		baseDao.deleteEntity(computerorderclassruledetail);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail) {
		return deleteComputerorderclassruledetail(computerorderclassruledetail.getId());
	}

	
	@Override
	public void updateComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail){
		
		Computerorderclassruledetail tempComputerorderclassruledetail = new Computerorderclassruledetail();
		//tempComputerorderclassruledetail = baseDao.getEntityById(Computerorderclassruledetail.class, computerorderclassruledetail.getId());
		tempComputerorderclassruledetail = computerorderclassruledetail;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorderclassruledetail);

	}

//	根据id查询实体类			
	@Override
	public Computerorderclassruledetail selectComputerorderclassruledetailById(Integer computerorderclassruledetailId){		
		return baseDao.getEntityById(Computerorderclassruledetail.class, computerorderclassruledetailId);
	}
	
	@Override
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailAll(){
			
		return baseDao.getAllEntity(Computerorderclassruledetail.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerorderclassruledetailFull selectComputerorderclassruledetailFullById(Integer computerorderclassruledetailId){
	
		return computerorderclassruledetailDao.selectComputerorderclassruledetailFullById(computerorderclassruledetailId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullAll() {
		// TODO Auto-generated method stub
		return computerorderclassruledetailDao.selectComputerorderclassruledetailFullAll();
	}
	
	
	public int countComputerorderclassruledetailRow(){
		return baseDao.getRowCount(Computerorderclassruledetail.class);
	}
		
//  分页查询
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByPage(Page page){	
		return baseDao.selectByPage(Computerorderclassruledetail.class,page);
	}
//  分页查询full	
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerorderclassruledetail.class));
		return computerorderclassruledetailDao.selectComputerorderclassruledetailFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByCondition(String condition) {
		 return computerorderclassruledetailDao.selectComputerorderclassruledetailByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerorderclassruledetail>  selectComputerorderclassruledetailByConditionAndPage(String condition,final Page page) {
              return computerorderclassruledetailDao.selectComputerorderclassruledetailByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByCondition(String condition) {
		return computerorderclassruledetailDao.selectComputerorderclassruledetailFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderclassruledetailFull>  selectComputerorderclassruledetailFullByConditionAndPage(String condition,final Page page) {
			return computerorderclassruledetailDao.selectComputerorderclassruledetailFullByConditionAndPage(condition, page);
		}
	
	
	//根据allowedcomputermodelid 查询实体
	@Override
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByComputermodelId(Integer allowedcomputermodelid ) {
		List<Computerorderclassruledetail> computerorderclassruledetailList = new ArrayList<Computerorderclassruledetail>();
		String id = String.valueOf(allowedcomputermodelid );
		computerorderclassruledetailList = baseDao.getEntityByProperty("Computerorderclassruledetail", "allowedcomputermodelid ", id);
		return computerorderclassruledetailList;
	}
	//根据allowedcomputermodelid 查询实体full
	@Override
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByComputermodelId(Integer allowedcomputermodelid ) {
		return computerorderclassruledetailDao.selectComputerorderclassruledetailFullByComputermodelId(allowedcomputermodelid );
	}

}
