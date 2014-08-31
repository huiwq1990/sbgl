package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.common.computer.ComputerConfig;
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

	/*
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
	}*/


	/**
	 * 获取分类-模型map
	 */
	@Override
	public HashMap<Integer, ArrayList<Computermodel>> getCategoryModelMap(List<Integer> categoryTypeList,int language){
		HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();
		
		if(categoryTypeList == null || categoryTypeList.size() == 0){
			computermodelByComputercategoryId  = new HashMap<Integer,ArrayList<Computermodel>>();
			return computermodelByComputercategoryId;
		}

		ArrayList<Computermodel> allModelList = (ArrayList<Computermodel>) selByCategoryType(categoryTypeList,language);
		
		for(Integer computercategoryType : categoryTypeList){
			computermodelByComputercategoryId.put(computercategoryType, new ArrayList<Computermodel>());
		}
		for(Computermodel model : allModelList){
			computermodelByComputercategoryId.get(model.getComputercategoryid()).add(model);
		}
		
		return computermodelByComputercategoryId;		
	}
	
	@Override
	public HashMap<Integer, ArrayList<Computermodel>> getCategoryModelMapByCategoryList(List<Computercategory> categoryList,int language){
		
		List<Integer> categoryTypeList =  new ArrayList<Integer>(); 
		for(Computercategory c : categoryList){
			categoryTypeList.add(c.getComputercategorytype());
		}
		
		return this.getCategoryModelMap(categoryTypeList, language);
	}
	
	/**
	 * 按页查询,如果分类为0，则默认查询所有的分类
	 */
	@Override
	public List<ComputermodelFull> selFullByPage(int categorytype,Page page,Integer language){
		String sql = "";
		if(categorytype == 0){
			 sql =" where a.status >=0 " +
	  				" and a.languagetype="+language+
	  				" and b.languagetype="+language+
	  				" order by a.computermodeltype,a.languagetype";	
		}else{
			 sql =" where a.status >=0 " +
				" and a.languagetype="+language+
				" and b.languagetype="+language+
				" and a.computercategoryid="+categorytype+
				" order by a.computermodeltype,a.languagetype";				
		}
		return computermodelDao.selectComputermodelFullByConditionAndPage(sql,page);
	}	
	
	/**
	 * 统计行数
	 */
	@Override
	public int countRow(int categorytype){
		String sql = " where a.status >= 0";
		if(categorytype > 0){
			sql += " and a.computercategoryid="+categorytype;
		}
		return computermodelDao.countRow(sql);
	}
	
	/**
	 * 根据类型查询模型
	*/
	@Override
	public List<Computermodel> selByCategoryType(int categoryType,int language){		
		return computermodelDao.selByCategorytype(categoryType, language);
	}
	@Override
	public List<Computermodel> selByCategoryType(List<Integer> categoryTypeList,int language){
		
//		SQL: select * from table where id IN (3,6,9,1,2,5,8,7); 
//		这样的情况取出来后，其实，id还是按1,2,3,4,5,6,7,8,9,排序的，但如果我们真要按IN里面的顺序排序怎么办？SQL能不能完成？是否需要取回来后再foreach一下？其实mysql就有这个方法 
		
		String categoryTypeInSql = "  a.computercategoryid in (";
		for(int computercategoryType : categoryTypeList){
			categoryTypeInSql += computercategoryType + ",";
		}
		categoryTypeInSql = categoryTypeInSql.substring(0,categoryTypeInSql.length()-1);
		categoryTypeInSql += ")";

		String sql = "  where a.status >=0 " +
						" and a.languagetype="+language + 
						" and "+categoryTypeInSql;
//		System.out.println(sql);
		
		List<Computermodel> allComputermodelFullList =  computermodelDao.selectComputermodelByCondition(sql);
		
		if(allComputermodelFullList == null){
			allComputermodelFullList = new ArrayList<Computermodel>();
		}
		
		return allComputermodelFullList;
	}
	
	/**
	 * 根据模型类型查询
	 */
	public List<Computermodel> selByModeltype(int modeltype,int language){		
		return computermodelDao.selByModeltype(modeltype, language);		
	}
//	public 
	
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
	
	@Override
	public List<Computermodel> selectComputermodelAll(int language){
			
		return computermodelDao.selectComputermodelByCondition("where a.languagetype="+language +" ");
		
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
	public List<Computermodel> selByCategorytype(Integer computercategorytype,int language ) {
		return computermodelDao.selByCategorytype(computercategorytype,language);		
	}
	@Override
	public List<ComputermodelFull> selFullByCategorytype(Integer computercategorytype,int language ) {
		return computermodelDao.selFullByCategorytype(computercategorytype,language);		
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
	public int delByType(Integer modeltype) {
		// TODO Auto-generated method stub
		return computermodelDao.deleteEntity(modeltype);
	}

	@Override
	public int delByType(List<Integer> modeltypeList) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
}
