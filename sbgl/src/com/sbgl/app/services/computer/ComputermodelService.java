package com.sbgl.app.services.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.util.*;

public interface ComputermodelService{
	
	public void addComputermodel(Computermodel computermodel);
	
	public void addComputermodelWithId(Computermodel computermodel);
		
	public void updateComputermodel(Computermodel computermodel);
	/*
//  根据id删除实体	
	public int deleteComputermodel(Integer computermodelId);

//  根据实体删除实体
	public int deleteComputermodel(Computermodel computermodel);
	*/
//	根据id查询实体类		
	public Computermodel selectComputermodelById(Integer computermodelId);

//  查询全部实体
	public List<Computermodel> selectComputermodelAll();
	
//	根据id查询full类
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId);

//  查询全部full
	public List<ComputermodelFull> selectComputermodelFullAll();
		


//  分页查询
	public List<Computermodel> selectComputermodelByPage(Page page);
	public List<ComputermodelFull> selectComputermodelFullByPage(Page page);
	
	void updateCategoryComputermodel(int computercategoryid);

	boolean isComputermodelNameExist(String name);

	List<Computermodel> selectComputermodelByName(String name);

	List<Computermodel> selectComputermodelAvailableborrowcount(int modelid);

	void addComputermodel(Computermodel ch, Computermodel en);

	List<Computermodel> selectComputermodelByConditionAndPage(
			String conditionSql, Page page);



	List<Computermodel> selectComputermodelByCondition(String conditionSql);

	List<ComputermodelFull> selectComputermodelFullByConditionAndPage(
			String conditionSql, Page page);

	List<ComputermodelFull> selectComputermodelFullByCondition(
			String conditionSql);



	void updateComputermodel(Computermodel ch, Computermodel en);



	List<Computermodel> selectComputermodelAll(int language);

	HashMap<Integer, ArrayList<Computermodel>> getCategoryModelMap(
			List<Integer> categoryTypeList, int language);


	List<Computermodel> selByCategoryType(int categoryType, int language);

	List<Computermodel> selByCategoryType(List<Integer> categoryTypeList,
			int language);

//  查询数量
	int countRow(int categorytype);

	List<ComputermodelFull> selFullByPage(int categorytype, Page page,
			Integer language);

	HashMap<Integer, ArrayList<Computermodel>> getCategoryModelMapByCategoryList(
			List<Computercategory> categoryList, int language);

	List<Computermodel> selByModeltype(int modeltype, int language);

	
	
	
	int delByType(Integer modeltype);
	int delByType(List<Integer> modeltypeList);
//	int deleteComputermodelByType(List<Integer> typeList);


	List<Computermodel> selByCategorytype(Integer computercategorytype,
			int language);
	List<ComputermodelFull> selFullByCategorytype(Integer computercategorytype,
			int language);
		
	
	
}
