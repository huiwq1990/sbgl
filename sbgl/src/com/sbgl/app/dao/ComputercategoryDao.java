package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computercategoryi18n;
import com.sbgl.util.*;

public interface ComputercategoryDao{

	
//删除实体
	public int deleteEntity(Integer computercategoryId);

public List<Computercategory> selectComputercategoryByCondition(String condition);
	 public List<Computercategory>  selectComputercategoryByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputercategoryFull> selectComputercategoryFullByCondition(String condition);
			 public List<ComputercategoryFull>  selectComputercategoryFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputercategoryFull selectComputercategoryFullById(Integer computercategoryId);
		
	public List<ComputercategoryFull> selectComputercategoryFullAll();
		
//  分页查询 实体full
	public List<ComputercategoryFull> selectComputercategoryFullByPage(Page page);



	//根据关联查询实体 
	public List<Computercategory> selectComputercategoryByComputercategoryId(Integer parentcomputercategoryid );

	public List<ComputercategoryFull> selectComputercategoryFullByComputercategoryId(Integer parentcomputercategoryid );



	int countRow(String condition);

 
}