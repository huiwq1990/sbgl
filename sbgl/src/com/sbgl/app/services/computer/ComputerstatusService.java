package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.entity.ComputerstatusFull;
import com.sbgl.util.*;

public interface ComputerstatusService{
	
	public void addComputerstatus(Computerstatus computerstatus);
	
	public void addComputerstatusWithId(Computerstatus computerstatus);
		
	public void updateComputerstatus(Computerstatus computerstatus);
	
//  根据id删除实体	
	public int deleteComputerstatus(Integer computerstatusId);

//  根据实体删除实体
	public int deleteComputerstatus(Computerstatus computerstatus);
	
//	根据id查询实体类		
	public Computerstatus selectComputerstatusById(Integer computerstatusId);

//  查询全部实体
	public List<Computerstatus> selectComputerstatusAll();
	
//	根据id查询full类
	public ComputerstatusFull selectComputerstatusFullById(Integer computerstatusId);

//  查询全部full
	public List<ComputerstatusFull> selectComputerstatusFullAll();
		
//  查询数量
	public int countComputerstatusRow();
//  分页查询
	public List<Computerstatus> selectComputerstatusByPage(Page page);
	public List<ComputerstatusFull> selectComputerstatusFullByPage(Page page);
		
		
		public List<Computerstatus> selectComputerstatusByCondition(String condition);

        public List<Computerstatus>  selectComputerstatusByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerstatusFull> selectComputerstatusFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerstatusFull>  selectComputerstatusFullByConditionAndPage(String condition,final Page page);



		List<ComputerstatusFull> selFull();

		List<Computerstatus> sel();
		
	
//根据关联查询实体	
		
	
	
}
