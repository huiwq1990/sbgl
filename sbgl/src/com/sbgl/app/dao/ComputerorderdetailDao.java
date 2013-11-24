package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.*;

public interface ComputerorderdetailDao{

	
//删除实体
	public int deleteEntity(Integer computerorderdetailId);



	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId);
		
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll();
		
//  分页查询 实体full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(Page page);



	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid );
	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid );

	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid );
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid );



	List<Computerorderdetail> selectComputerorderdetailByCondition(
			String condition);

 
}