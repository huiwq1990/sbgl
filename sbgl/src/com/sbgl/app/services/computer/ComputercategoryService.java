package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computercategoryi18n;
import com.sbgl.util.*;

public interface ComputercategoryService{
	
	public void addComputercategory(Computercategory computercategory);
	
	public void addComputercategoryWithId(Computercategory computercategory);
		
	public void updateComputercategory(Computercategory computercategory);
	
//  根据id删除实体	
	public int deleteComputercategory(Integer computercategoryId);

//  根据实体删除实体
	public int deleteComputercategory(Computercategory computercategory);
	
//	根据id查询实体类		
	public Computercategory selectComputercategoryById(Integer computercategoryId);

//  查询全部实体
	public List<Computercategory> selectComputercategoryAll();
	
//	根据id查询full类
	public ComputercategoryFull selectComputercategoryFullById(Integer computercategoryId);

//  查询全部full
	public List<ComputercategoryFull> selectComputercategoryFullAll();
		
//  查询数量
	public int countComputercategoryRow();
//  分页查询
	public List<Computercategory> selectComputercategoryByPage(Page page);
	public List<ComputercategoryFull> selectComputercategoryFullByPage(Page page);
	


////查询所有的父级分类
//public List<Computercategory> selectParentComputercategory() ;

List<ComputercategoryFull> selectShowedComputercategoryFullByPage(Page page);

boolean isComputercategoryNameExist(String name);

void addComputercategory(Computercategory chcomputercategory,
		Computercategory encomputercategory);




int deleteComputercategoryByType(Integer computercategoryType);





public List<Computercategory> selectComputercategoryByCondition(String condition);

public List<Computercategory>  selectComputercategoryByConditionAndPage(String condition,final Page page);

//条件查询full
public List<ComputercategoryFull> selectComputercategoryFullByCondition(String condition);


// 查询实体full        
public List<ComputercategoryFull>  selectComputercategoryFullByConditionAndPage(String condition,final Page page);

int deleteComputercategoryByType(List<Integer> computercategoryTypeList);

void updateComputercategory(Computercategory ch, Computercategory en);

List<Computercategory> selectComputercategoryAll(int language);





List<ComputercategoryFull> selFullByPage(Page page, Integer language);

List<Computercategory> sel(Integer language);

List<ComputercategoryFull> selFull(Integer language);

List<Computercategory> selectComputercategoryByComputercategoryId(
		Integer parentcomputercategoryid);






	
}
