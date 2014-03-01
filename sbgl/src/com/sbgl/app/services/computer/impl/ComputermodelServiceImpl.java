package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.dao.ComputermodelDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computermodelService")
@Transactional
public class ComputermodelServiceImpl implements ComputermodelService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputermodelDao computermodelDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputermodel(Computermodel computermodel){
		computermodel.setId(baseDao.getCode("Computermodel"));
		baseDao.saveEntity(computermodel);		
	}
	
	@Override
	public void addComputermodel(Computermodel ch,Computermodel en){
		int type = baseDao.getCode("Computermodeltype");
		ch.setId(baseDao.getCode("Computermodel"));
		ch.setComputermodeltype(type);
		en.setId(baseDao.getCode("Computermodel"));
		en.setComputermodeltype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	}

	@Override
	public void addComputermodelWithId(Computermodel computermodel){
	
		baseDao.saveEntity(computermodel);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputermodel(Integer computermodelId){
		Computermodel computermodel = new Computermodel();
		computermodel.setId(computermodelId);
		//return computermodelDao.deleteEntity(computermodelId);	
		//baseDao.deleteEntityById(Computermodel.class,computermodelId);
		baseDao.deleteEntity(computermodel);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputermodel(Computermodel computermodel) {
		return deleteComputermodel(computermodel.getId());
	}

	@Override
	public int deleteComputermodelByTyp(Integer computermodeltype){
		String sql = "delete from Computermodel where computermodeltype="+computermodeltype;
		baseDao.createSQL(sql);
		return 1;
	}
	
	
	@Override
	public int deleteComputermodelByType(List<Integer> typeList){
		
		for(Integer type : typeList){
			String sql = "delete from Computermodel where computermodeltype="+type;
			baseDao.createSQL(sql);
		}
		
		return 1;
	}
	
	@Override
	public void updateComputermodel(Computermodel computermodel){
		
		Computermodel tempComputermodel = new Computermodel();
		//tempComputermodel = baseDao.getEntityById(Computermodel.class, computermodel.getId());
		tempComputermodel = computermodel;
		//add your code here
		
		
		baseDao.updateEntity(tempComputermodel);

	}
	
	@Override
	public void updateComputermodel(Computermodel ch,Computermodel en){
		
		Computermodel tempch = new Computermodel();
		//tempComputermodel = baseDao.getEntityById(Computermodel.class, computermodel.getId());
		tempch = ch;
		//add your code here
		
		baseDao.updateEntity(tempch);
		
		Computermodel tempen = new Computermodel();
		tempen = en;
		baseDao.updateEntity(tempen);

	}
	
	/**
	 * 更新某一分类下面的型号为为分类
	 */
	@Override
	public void updateCategoryComputermodel(int computercategoryid){
		String sql = "update Computermodel as tb set tb.computercategoryid = -1 where tb.computercategoryid =  " + computercategoryid;
		baseDao.createSQL(sql);		
	}

//	根据id查询实体类			
	@Override
	public Computermodel selectComputermodelById(Integer computermodelId){		
		return baseDao.getEntityById(Computermodel.class, computermodelId);
	}
	
	@Override
	public List<Computermodel> selectComputermodelAll(){
			
		return baseDao.getAllEntity(Computermodel.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId){
	
		return computermodelDao.selectComputermodelFullById(computermodelId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputermodelFull> selectComputermodelFullAll() {
		// TODO Auto-generated method stub
		return computermodelDao.selectComputermodelFullAll();
	}
	
	
	public int countComputermodelRow(){
		return baseDao.getRowCount(Computermodel.class);
	}
		
//  分页查询
	public List<Computermodel> selectComputermodelByPage(Page page){	
		return baseDao.selectByPage(Computermodel.class,page);
	}
//  分页查询full	
	public List<ComputermodelFull> selectComputermodelFullByPage(Page page){
//		page.setTotalCount(baseDao.getRowCount(Computermodel.class));
		return computermodelDao.selectComputermodelFullByPage(page);
	}
	
	//根据computercategoryid 查询实体
	@Override
	public List<Computermodel> selectComputermodelByComputercategoryId(Integer computercategoryid ) {
		List<Computermodel> computermodelList = new ArrayList<Computermodel>();
		String id = String.valueOf(computercategoryid );
		computermodelList = baseDao.getEntityByProperty("Computermodel", "computercategoryid ", id);
		return computermodelList;
	}
	//根据computercategoryid 查询实体full
	@Override
	public List<ComputermodelFull> selectComputermodelFullByComputercategoryId(Integer computercategoryid ) {
		return computermodelDao.selectComputermodelFullByComputercategoryId(computercategoryid );
	}
	@Override
	 public boolean isComputermodelNameExist(String name){
		 List<Computercategory>  l = baseDao.getEntityByProperty("Computermodel", "name", name);
		 if(l==null || l.size()==0){
			 return false;
		 }else{
			 return true;
		 }
	 }
	
	
	@Override
	 public List<Computermodel>  selectComputermodelByName(String name){
		 List<Computermodel>  l = baseDao.getEntityByProperty("Computermodel", "name", name);
		return l;
	 }	
//	@Override
//	 public List<Integer>  selectComputermodelidByName(String name){
//		 List<Integer>  l = baseDao.createSQL("select id from Computermodel");
//		return l;
//	 }
	
	@Override
	 public List<Computermodel>  selectComputermodelAvailableborrowcount(int modelid){
//		 List<Computermodel>  l = baseDao.createSQL("From Computermodel where id"+ modelid);
		return null;
	 }	
	
	/**
	 * 返回Model的条件查询内容，分页信息需要在Action中设置
	 * @param conditionSql
	 * @param page
	 * @return
	 */
	@Override
	public List<Computermodel> selectComputermodelByConditionAndPage(String conditionSql,Page page){
		return computermodelDao.selectComputermodelByConditionAndPage(conditionSql, page);
	}
	
	@Override
	public List<Computermodel> selectComputermodelByCondition(String conditionSql){
		return computermodelDao.selectComputermodelByCondition(conditionSql);
	}
	
	
	@Override
	public List<ComputermodelFull> selectComputermodelFullByCondition(String conditionSql){
		return computermodelDao.selectComputermodelFullByCondition(conditionSql);
	}
	
	@Override
	public List<ComputermodelFull> selectComputermodelFullByConditionAndPage(String conditionSql,Page page){
		return computermodelDao.selectComputermodelFullByConditionAndPage(conditionSql, page);
	}
	
	@Override
	public int execSql(String sql) {
//		String sql = "delete from Computerorderdetail " + condition;
		baseDao.createSQL(sql);
		return 1;
		
	}
}
