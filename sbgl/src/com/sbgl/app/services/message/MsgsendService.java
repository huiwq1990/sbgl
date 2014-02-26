package com.sbgl.app.services.message;

import java.util.List;

import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.Msgsend;
import com.sbgl.app.entity.MsgsendFull;
import com.sbgl.util.*;

public interface MsgsendService{
	
	public void addMsgsend(Msgsend msgsend);

	public void addMsgsend(Msgsend ch,Msgsend en);
	public void addMsgsendWithId(Msgsend msgsend);
		
	public void updateMsgsend(Msgsend msgsend);
	
//  根据id删除实体	
	public int deleteMsgsend(Integer msgsendId);

//  根据实体删除实体
	public int deleteMsgsend(Msgsend msgsend);
	
//	根据id查询实体类		
	public Msgsend selectMsgsendById(Integer msgsendId);

//  查询全部实体
	//public List<Msgsend> selectMsgsendAll();
	
//	根据id查询full类
	public MsgsendFull selectMsgsendFullById(Integer msgsendId);

//  查询全部full
//	public List<MsgsendFull> selectMsgsendFullAll();
		
//  查询数量
	public int countMsgsendRow();
//  分页查询
	public List<Msgsend> selectMsgsendByPage(Page page);
	public List<MsgsendFull> selectMsgsendFullByPage(Page page);
		
		
	public List<Msgsend> selectMsgsendByCondition(String condition);

	public List<Msgsend>  selectMsgsendByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<MsgsendFull> selectMsgsendFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<MsgsendFull>  selectMsgsendFullByConditionAndPage(String condition,final Page page);

	void snedMsg(Msgsend msgsend, Msgreceive msgreceive);
		
	
	
	
}
