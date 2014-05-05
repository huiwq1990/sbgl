package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.util.*;

public interface ComputerDao{

	
//删除实体
	public int deleteEntity(Integer computerId);

public List<Computer> selectComputerByCondition(String condition);
	 public List<Computer>  selectComputerByConditionAndPage(String conditionSql,final Page page) ;

		public List<ComputerFull> selectComputerFullByCondition(String condition);
			 public List<ComputerFull>  selectComputerFullByConditionAndPage(String conditionSql,final Page page);
	
	public ComputerFull selectComputerFullById(Integer computerId);
		
	public List<ComputerFull> selectComputerFullAll();
		
//  分页查询 实体full
	public List<ComputerFull> selectComputerFullByPage(Page page);



	//根据关联查询实体 
	public List<Computer> selectComputerByComputermodelId(Integer computermodelid );

	public List<ComputerFull> selectComputerFullByComputermodelId(Integer computermodelid );

	List<Computer> selByModeltype(List<Integer> modeltypeList, int language);

	List<ComputerFull> selFullByModeltype(List<Integer> modeltypeList,
			int language);

	List<Computer> selByModeltype(int modeltype, int language);

	List<ComputerFull> selFullByModeltype(int modeltype, int language);


}