package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.util.*;

public interface ComputerDao{

	
//删除实体
	public int deleteEntity(Integer computerId);



	public ComputerFull selectComputerFullById(Integer computerId);
		
	public List<ComputerFull> selectComputerFullAll();
		
//  分页查询 实体full
	public List<ComputerFull> selectComputerFullByPage(Page page);



	//根据关联查询实体 
	public List<Computer> selectComputerByComputermodelId(Integer computermodelid );
	//根据关联查询实体 
	public List<Computer> selectComputerByLoginuserId(Integer createuserid );

	public List<ComputerFull> selectComputerFullByComputermodelId(Integer computermodelid );
	public List<ComputerFull> selectComputerFullByLoginuserId(Integer createuserid );

 
}