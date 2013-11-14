package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.util.*;

public interface ComputerorderDao{

	
//删除实体
	public int deleteEntity(Integer computerorderId);



	public ComputerorderFull selectComputerorderFullById(Integer computerorderId);
		
	public List<ComputerorderFull> selectComputerorderFullAll();
		
//  分页查询 实体full
	public List<ComputerorderFull> selectComputerorderFullByPage(Page page);

 
}