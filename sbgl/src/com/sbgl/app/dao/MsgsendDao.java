package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Msgsend;
import com.sbgl.app.entity.MsgsendFull;
import com.sbgl.util.*;

public interface MsgsendDao{

	
//删除实体
	public int deleteEntity(Integer msgsendId);

public List<Msgsend> selectMsgsendByCondition(String condition);
	 public List<Msgsend>  selectMsgsendByConditionAndPage(String conditionSql,final Page page) ;

		public List<MsgsendFull> selectMsgsendFullByCondition(String condition);
			 public List<MsgsendFull>  selectMsgsendFullByConditionAndPage(String conditionSql,final Page page);
	
	public MsgsendFull selectMsgsendFullById(Integer msgsendId);
		
	public List<MsgsendFull> selectMsgsendFullAll();
		
//  分页查询 实体full
	public List<MsgsendFull> selectMsgsendFullByPage(Page page);

 
}