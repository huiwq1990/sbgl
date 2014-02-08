package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerconfig;
import com.sbgl.app.entity.ComputerconfigFull;
import com.sbgl.util.*;

public interface ComputerconfigService{
	
	public void addComputerconfig(Computerconfig computerconfig);

	public void addComputerconfig(Computerconfig ch,Computerconfig en);
	public void addComputerconfigWithId(Computerconfig computerconfig);
		
	public void updateComputerconfig(Computerconfig computerconfig);
	
//  根据id删除实体	
	public int deleteComputerconfig(Integer computerconfigId);

//  根据实体删除实体
	public int deleteComputerconfig(Computerconfig computerconfig);
	
//	根据id查询实体类		
	public Computerconfig selectComputerconfigById(Integer computerconfigId);

//  查询全部实体
	public List<Computerconfig> selectComputerconfigAll();
	
//	根据id查询full类
	public ComputerconfigFull selectComputerconfigFullById(Integer computerconfigId);

//  查询全部full
	public List<ComputerconfigFull> selectComputerconfigFullAll();
		
//  查询数量
	public int countComputerconfigRow();
//  分页查询
	public List<Computerconfig> selectComputerconfigByPage(Page page);
	public List<ComputerconfigFull> selectComputerconfigFullByPage(Page page);
		
		
		public List<Computerconfig> selectComputerconfigByCondition(String condition);

        public List<Computerconfig>  selectComputerconfigByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerconfigFull> selectComputerconfigFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerconfigFull>  selectComputerconfigFullByConditionAndPage(String condition,final Page page);

		int execSql(String sql);
		
	
//根据关联查询实体	
		
	
	
}
