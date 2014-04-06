package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computercategoryi18n;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.services.computer.ComputercategoryService;
import com.sbgl.app.dao.ComputercategoryDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.ComputermodelDao;

import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computercategoryService")
@Transactional
public class ComputercategoryServiceImpl implements ComputercategoryService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputercategoryDao computercategoryDao;
	
	@Resource
	private ComputermodelDao computermodelDao;
	
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputercategory(Computercategory computercategory){
		computercategory.setId(baseDao.getCode("Computercategory"));
		baseDao.saveEntity(computercategory);		
	}
	
	@Override
	public void addComputercategory(Computercategory chcomputercategory,Computercategory encomputercategory){
		int type = baseDao.getCode("Computercategorytype");
		chcomputercategory.setId(baseDao.getCode("Computercategory"));
		chcomputercategory.setComputercategorytype(type);
		encomputercategory.setId(baseDao.getCode("Computercategory"));
		encomputercategory.setComputercategorytype(type);
		baseDao.saveEntity(chcomputercategory);	
		baseDao.saveEntity(encomputercategory);		
	}

	@Override
	public void addComputercategoryWithId(Computercategory computercategory){
	
		baseDao.saveEntity(computercategory);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputercategory(Integer computercategoryId){
		Computercategory computercategory = new Computercategory();
		computercategory.setId(computercategoryId);
		//return computercategoryDao.deleteEntity(computercategoryId);	
		//baseDao.deleteEntityById(Computercategory.class,computercategoryId);
		baseDao.deleteEntity(computercategory);
		return 1;
	}
	

	
	
	@Override
	public int deleteComputercategoryByType(Integer computercategoryType){
		String sql = "delete from Computercategory where computercategorytype="+computercategoryType;
		baseDao.createSQL(sql);
		return 1;
	}

	
	@Override	
	public int deleteComputercategoryByType(List<Integer> computercategoryTypeList){
		
		for(Integer type : computercategoryTypeList){
			String sql = "delete from Computercategory where computercategorytype="+type;
			baseDao.createSQL(sql);
		}
		
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputercategory(Computercategory computercategory) {
		return deleteComputercategory(computercategory.getId());
	}

	
	@Override
	public void updateComputercategory(Computercategory computercategory){
		
		Computercategory tempComputercategory = new Computercategory();
		//tempComputercategory = baseDao.getEntityById(Computercategory.class, computercategory.getId());
		tempComputercategory = computercategory;
		//add your code here
			
		baseDao.updateEntity(tempComputercategory);

	}
	
	
	@Override
	public void updateComputercategory(Computercategory ch,Computercategory en){
		
		Computercategory tempch = new Computercategory();
		Computercategory tempen = new Computercategory();
		//tempComputercategory = baseDao.getEntityById(Computercategory.class, computercategory.getId());
		tempch = ch;
		tempen = en;
		//add your code here
			
		baseDao.updateEntity(tempch);
		
		baseDao.updateEntity(tempen);
		
		

	}
	
	

//	根据id查询实体类			
	@Override
	public Computercategory selectComputercategoryById(Integer computercategoryId){		
		return baseDao.getEntityById(Computercategory.class, computercategoryId);
	}
	
	@Override
	public List<Computercategory> selectComputercategoryAll(){
			
		return baseDao.getAllEntity(Computercategory.class);
		
	}
	
	@Override
	public List<Computercategory> selectComputercategoryAll(int language){
			
		return computercategoryDao.selectComputercategoryByCondition("where a.languagetype="+language +" ");
		
	}

	
//	根据id查询full类
	@Override
	public ComputercategoryFull selectComputercategoryFullById(Integer computercategoryId){
	
		return computercategoryDao.selectComputercategoryFullById(computercategoryId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullAll() {
		// TODO Auto-generated method stub
		return computercategoryDao.selectComputercategoryFullAll();
	}
	

	@Override
	/**
	 *  查询实体full,要显示的full
	 *  条件 id大于0
	 * @param page
	 * @return
	 */
	public List<ComputercategoryFull> selectShowedComputercategoryFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computercategory.class));
		String showFullConditon = "where a.id > 0";
		return computercategoryDao.selectComputercategoryFullByConditionAndPage(showFullConditon,page);
	}
	

	
	
	public int countComputercategoryRow(){
		return baseDao.getRowCount(Computercategory.class);
	}
		
//  分页查询
	public List<Computercategory> selectComputercategoryByPage(Page page){	
		return baseDao.selectByPage(Computercategory.class,page);
	}
//  分页查询full	
	public List<ComputercategoryFull> selectComputercategoryFullByPage(Page page){
		return computercategoryDao.selectComputercategoryFullByPage(page);
	}
	
	//根据parentcomputercategoryid 查询实体
	@Override
	public List<Computercategory> selectComputercategoryByComputercategoryId(Integer parentcomputercategoryid ) {
		List<Computercategory> computercategoryList = new ArrayList<Computercategory>();
		String id = String.valueOf(parentcomputercategoryid );
		computercategoryList = baseDao.getEntityByProperty("Computercategory", "parentcomputercategoryid ", id);
		return computercategoryList;
	}
	//根据parentcomputercategoryid 查询实体full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullByComputercategoryId(Integer parentcomputercategoryid ) {
		return computercategoryDao.selectComputercategoryFullByComputercategoryId(parentcomputercategoryid );
	}

	@Override
	public List<Computercategory> selectParentComputercategory() {
		List<Computercategory> parentcomputercategoryList = new ArrayList<Computercategory>();
		parentcomputercategoryList = baseDao.getEntityByProperty("Computercategory", "parentcomputercategoryid", "0");
		return parentcomputercategoryList;
	}
	
	
	@Override
	 public boolean isComputercategoryNameExist(String name){
		 List<Computercategory>  l = baseDao.getEntityByProperty("Computercategory", "name", name);
		 if(l==null || l.size()==0){
			 return false;
		 }else{
			 return true;
		 }
	 }

	@Override
	public List<Computercategoryi18n> selectComputercategoryi18nByCondition(String conditionSql){
		return computercategoryDao.selectComputercategoryi18nByCondition(conditionSql);
	}
	@Override
	public List<Computercategoryi18n> selectComputercategoryi18nByConditionAndPage(String conditionSql,final Page page){
		return computercategoryDao.selectComputercategoryi18nByConditionAndPage(conditionSql, page);
	}
	
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computercategory> selectComputercategoryByCondition(String condition) {
		 return computercategoryDao.selectComputercategoryByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computercategory>  selectComputercategoryByConditionAndPage(String condition,final Page page) {
              return computercategoryDao.selectComputercategoryByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputercategoryFull> selectComputercategoryFullByCondition(String condition) {
		return computercategoryDao.selectComputercategoryFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputercategoryFull>  selectComputercategoryFullByConditionAndPage(String condition,final Page page) {
			return computercategoryDao.selectComputercategoryFullByConditionAndPage(condition, page);
		}
	
	
}
