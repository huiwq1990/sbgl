package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.util.*;

public interface ComputerhomeworkService{
	
	public void addComputerhomework(Computerhomework computerhomework);

	public void addComputerhomework(Computerhomework ch,Computerhomework en);
	public void addComputerhomeworkWithId(Computerhomework computerhomework);
		
	public void updateComputerhomework(Computerhomework computerhomework);
	
//  根据id删除实体	
	public int deleteComputerhomework(Integer computerhomeworkId);

//  根据实体删除实体
	public int deleteComputerhomework(Computerhomework computerhomework);
	
//	根据id查询实体类		
	public Computerhomework selectComputerhomeworkById(Integer computerhomeworkId);

//  查询全部实体
	public List<Computerhomework> selectComputerhomeworkAll();
	
//	根据id查询full类
	public ComputerhomeworkFull selectComputerhomeworkFullById(Integer computerhomeworkId);

//  查询全部full
	public List<ComputerhomeworkFull> selectComputerhomeworkFullAll();
		
//  查询数量
	public int countComputerhomeworkRow();
//  分页查询
	public List<Computerhomework> selectComputerhomeworkByPage(Page page);
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByPage(Page page);
		
		
		public List<Computerhomework> selectComputerhomeworkByCondition(String condition);

        public List<Computerhomework>  selectComputerhomeworkByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerhomeworkFull>  selectComputerhomeworkFullByConditionAndPage(String condition,final Page page);
		
	
//根据关联查询实体	
	public List<Computerhomework> selectComputerhomeworkByComputerorderclassruleId(Integer computerorderclassruleid );
//根据关联查询实体full	
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByComputerorderclassruleId(Integer computerorderclassruleid );

	boolean deleteComputerhomework(Integer[] delIdArray);

	void sendComputerhomework(Computerhomework computerhomework,
			List<Computerhomeworkreceiver> chrList) throws RuntimeException;

	void sendComputerhomeworkNew(Computerorderclassrule rule,
			List<Computerorderclassruledetail> codList,
			Computerhomework computerhomework,
			List<Computerhomeworkreceiver> chrList) throws RuntimeException;

		
	
	
}
