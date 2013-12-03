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
	
//根据关联查询实体	
	public List<Computercategory> selectComputercategoryByComputercategoryId(Integer parentcomputercategoryid );
//根据关联查询实体full	
	public List<ComputercategoryFull> selectComputercategoryFullByComputercategoryId(Integer parentcomputercategoryid );

		

////查询所有的父级分类
public List<Computercategory> selectParentComputercategory() ;

List<ComputercategoryFull> selectShowedComputercategoryFullByPage(Page page);

boolean isComputercategoryNameExist(String name);

void addComputercategory(Computercategory chcomputercategory,
		Computercategory encomputercategory);

List<ComputercategoryFull> selectComputercategoryFullByConditionAndPage(
		String sql, Page page);

List<Computercategoryi18n> selectComputercategoryi18nByCondition(
		String conditionSql);

List<Computercategoryi18n> selectComputercategoryi18nByConditionAndPage(
		String conditionSql, Page page);

int deleteComputercategoryByType(Integer computercategoryType);

List<ComputercategoryFull> selectComputercategoryFullByCondition(
		String condition);
	
}
