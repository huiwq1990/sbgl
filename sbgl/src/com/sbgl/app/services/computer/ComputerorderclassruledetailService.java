package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.entity.ComputerorderclassruledetailFull;
import com.sbgl.util.*;

public interface ComputerorderclassruledetailService{
	
	public void addComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail);

	public void addComputerorderclassruledetail(Computerorderclassruledetail ch,Computerorderclassruledetail en);
	public void addComputerorderclassruledetailWithId(Computerorderclassruledetail computerorderclassruledetail);
		
	public void updateComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail);
	
//  根据id删除实体	
	public int deleteComputerorderclassruledetail(Integer computerorderclassruledetailId);

//  根据实体删除实体
	public int deleteComputerorderclassruledetail(Computerorderclassruledetail computerorderclassruledetail);
	
//	根据id查询实体类		
	public Computerorderclassruledetail selectComputerorderclassruledetailById(Integer computerorderclassruledetailId);

//  查询全部实体
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailAll();
	
//	根据id查询full类
	public ComputerorderclassruledetailFull selectComputerorderclassruledetailFullById(Integer computerorderclassruledetailId);

//  查询全部full
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullAll();
		
//  查询数量
	public int countComputerorderclassruledetailRow();
//  分页查询
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByPage(Page page);
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByPage(Page page);
		
		
		public List<Computerorderclassruledetail> selectComputerorderclassruledetailByCondition(String condition);

        public List<Computerorderclassruledetail>  selectComputerorderclassruledetailByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerorderclassruledetailFull>  selectComputerorderclassruledetailFullByConditionAndPage(String condition,final Page page);
		
	
//根据关联查询实体	
	public List<Computerorderclassruledetail> selectComputerorderclassruledetailByComputermodelId(Integer allowedcomputermodelid );
//根据关联查询实体full	
	public List<ComputerorderclassruledetailFull> selectComputerorderclassruledetailFullByComputermodelId(Integer allowedcomputermodelid );

		
	
	
}
