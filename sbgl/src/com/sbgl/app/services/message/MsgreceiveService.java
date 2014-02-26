package com.sbgl.app.services.message;

import java.util.List;

import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.MsgreceiveFull;
import com.sbgl.util.*;

public interface MsgreceiveService{
	
	public void addMsgreceive(Msgreceive msgreceive);

	public void addMsgreceive(Msgreceive ch,Msgreceive en);
	public void addMsgreceiveWithId(Msgreceive msgreceive);
		
	public void updateMsgreceive(Msgreceive msgreceive);
	
//  根据id删除实体	
	public int deleteMsgreceive(Integer msgreceiveId);

//  根据实体删除实体
	public int deleteMsgreceive(Msgreceive msgreceive);
	
//	根据id查询实体类		
	public Msgreceive selectMsgreceiveById(Integer msgreceiveId);

//  查询全部实体
	//public List<Msgreceive> selectMsgreceiveAll();
	
//	根据id查询full类
	public MsgreceiveFull selectMsgreceiveFullById(Integer msgreceiveId);

//  查询全部full
//	public List<MsgreceiveFull> selectMsgreceiveFullAll();
		
//  查询数量
	public int countMsgreceiveRow();
//  分页查询
	public List<Msgreceive> selectMsgreceiveByPage(Page page);
	public List<MsgreceiveFull> selectMsgreceiveFullByPage(Page page);
		
		
	public List<Msgreceive> selectMsgreceiveByCondition(String condition);

	public List<Msgreceive>  selectMsgreceiveByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<MsgreceiveFull> selectMsgreceiveFullByCondition(String condition);
	
	
	// 查询实体full        
    public List<MsgreceiveFull>  selectMsgreceiveFullByConditionAndPage(String condition,final Page page);
		
	
	
	
}
