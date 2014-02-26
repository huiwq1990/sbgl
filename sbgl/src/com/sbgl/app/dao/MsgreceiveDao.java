package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.MsgreceiveFull;
import com.sbgl.util.*;

public interface MsgreceiveDao{

	
//删除实体
	public int deleteEntity(Integer msgreceiveId);

public List<Msgreceive> selectMsgreceiveByCondition(String condition);
	 public List<Msgreceive>  selectMsgreceiveByConditionAndPage(String conditionSql,final Page page) ;

		public List<MsgreceiveFull> selectMsgreceiveFullByCondition(String condition);
			 public List<MsgreceiveFull>  selectMsgreceiveFullByConditionAndPage(String conditionSql,final Page page);
	
	public MsgreceiveFull selectMsgreceiveFullById(Integer msgreceiveId);
		
	public List<MsgreceiveFull> selectMsgreceiveFullAll();
		
//  分页查询 实体full
	public List<MsgreceiveFull> selectMsgreceiveFullByPage(Page page);




 
}