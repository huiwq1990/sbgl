package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.ComputerorderclassruleFull;
import com.sbgl.util.*;

public interface ComputerorderclassruleService{
	
	public void addComputerorderclassrule(Computerorderclassrule computerorderclassrule);

	public void addComputerorderclassrule(Computerorderclassrule ch,Computerorderclassrule en);
	public void addComputerorderclassruleWithId(Computerorderclassrule computerorderclassrule);
		
	public void updateComputerorderclassrule(Computerorderclassrule computerorderclassrule);
	
//  根据id删除实体	
	public int deleteComputerorderclassrule(Integer computerorderclassruleId);

//  根据实体删除实体
	public int deleteComputerorderclassrule(Computerorderclassrule computerorderclassrule);
	
//	根据id查询实体类		
	public Computerorderclassrule selectComputerorderclassruleById(Integer computerorderclassruleId);

//  查询全部实体
	public List<Computerorderclassrule> selectComputerorderclassruleAll();
	
//	根据id查询full类
	public ComputerorderclassruleFull selectComputerorderclassruleFullById(Integer computerorderclassruleId);

//  查询全部full
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullAll();
		
//  查询数量
	public int countComputerorderclassruleRow();
//  分页查询
	public List<Computerorderclassrule> selectComputerorderclassruleByPage(Page page);
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByPage(Page page);
		
		
		public List<Computerorderclassrule> selectComputerorderclassruleByCondition(String condition);

        public List<Computerorderclassrule>  selectComputerorderclassruleByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerorderclassruleFull> selectComputerorderclassruleFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerorderclassruleFull>  selectComputerorderclassruleFullByConditionAndPage(String condition,final Page page);
		
	
//根据关联查询实体	
		
	
	
}
