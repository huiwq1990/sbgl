package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.util.*;

public interface ComputermodelService{
	
	public void addComputermodel(Computermodel computermodel);
	
	public void addComputermodelWithId(Computermodel computermodel);
		
	public void updateComputermodel(Computermodel computermodel);
	
//  根据id删除实体	
	public int deleteComputermodel(Integer computermodelId);

//  根据实体删除实体
	public int deleteComputermodel(Computermodel computermodel);
	
//	根据id查询实体类		
	public Computermodel selectComputermodelById(Integer computermodelId);

//  查询全部实体
	public List<Computermodel> selectComputermodelAll();
	
//	根据id查询full类
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId);

//  查询全部full
	public List<ComputermodelFull> selectComputermodelFullAll();
		
//  查询数量
	public int countComputermodelRow();
//  分页查询
	public List<Computermodel> selectComputermodelByPage(Page page);
	public List<ComputermodelFull> selectComputermodelFullByPage(Page page);
	
//根据关联查询实体	
	public List<Computermodel> selectComputermodelByComputercategoryId(Integer computercategoryid );
//根据关联查询实体full	
	public List<ComputermodelFull> selectComputermodelFullByComputercategoryId(Integer computercategoryid );

	void updateCategoryComputermodel(int computercategoryid);

	boolean isComputermodelNameExist(String name);

	List<Computermodel> selectComputermodelByName(String name);

	List<Computermodel> selectComputermodelAvailableborrowcount(int modelid);

	void addComputermodel(Computermodel ch, Computermodel en);

	List<Computermodel> selectComputermodelByConditionAndPage(
			String conditionSql, Page page);

	int deleteComputermodelByTyp(Integer computermodeltype);

	List<Computermodel> selectComputermodelByCondition(String conditionSql);

	List<ComputermodelFull> selectComputermodelFullByConditionAndPage(
			String conditionSql, Page page);

	List<ComputermodelFull> selectComputermodelFullByCondition(
			String conditionSql);

	int execSql(String sql);

	int deleteComputermodelByType(List<Integer> typeList);

	void updateComputermodel(Computermodel ch, Computermodel en);

	List<Computermodel> selectComputermodelByComputercategoryId(
			Integer computercategoryid, int language);

	List<Computermodel> selectComputermodelAll(int language);

	
		
	
	
}
