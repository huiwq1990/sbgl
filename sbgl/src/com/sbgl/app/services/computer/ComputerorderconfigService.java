package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerorderconfig;
import com.sbgl.app.entity.ComputerorderconfigFull;
import com.sbgl.util.*;

public interface ComputerorderconfigService{
	
	public void addComputerorderconfig(Computerorderconfig computerorderconfig);

	public void addComputerorderconfig(Computerorderconfig ch,Computerorderconfig en);
	public void addComputerorderconfigWithId(Computerorderconfig computerorderconfig);
		
	public void updateComputerorderconfig(Computerorderconfig computerorderconfig);
	
//  根据id删除实体	
	public int deleteComputerorderconfig(Integer computerorderconfigId);

//  根据实体删除实体
	public int deleteComputerorderconfig(Computerorderconfig computerorderconfig);
	
//	根据id查询实体类		
	public Computerorderconfig selectComputerorderconfigById(Integer computerorderconfigId);

//  查询全部实体
	//public List<Computerorderconfig> selectComputerorderconfigAll();
	
//	根据id查询full类
	public ComputerorderconfigFull selectComputerorderconfigFullById(Integer computerorderconfigId);

//  查询全部full
//	public List<ComputerorderconfigFull> selectComputerorderconfigFullAll();
		
//  查询数量
	public int countComputerorderconfigRow();
//  分页查询
	public List<Computerorderconfig> selectComputerorderconfigByPage(Page page);
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByPage(Page page);
		
		
	public List<Computerorderconfig> selectComputerorderconfigByCondition(String condition);

	public List<Computerorderconfig>  selectComputerorderconfigByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerorderconfigFull> selectComputerorderconfigFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<ComputerorderconfigFull>  selectComputerorderconfigFullByConditionAndPage(String condition,final Page page);

	Computerorderconfig selectCurrentComputerorderconfig();

	Computerorderconfig getCurrentComputerorderconfig();
		
	
	
	
}
