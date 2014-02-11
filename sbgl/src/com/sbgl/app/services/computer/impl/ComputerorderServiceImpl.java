package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.dao.ComputerorderDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderService")
@Transactional
public class ComputerorderServiceImpl implements ComputerorderService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderDao computerorderDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorder(Computerorder computerorder){
		computerorder.setId(baseDao.getCode("Computerorder"));
		baseDao.saveEntity(computerorder);		
	}
	
	@Override
	public void addComputerorder(Computerorder ch,Computerorder en){
		
	/*
		int type = baseDao.getCode("Computerordertype");
		ch.setId(baseDao.getCode("Computerorder"));
		ch.setComputerordertype(type);
		en.setId(baseDao.getCode("Computerorder"));
		en.setComputerordertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addComputerorderWithId(Computerorder computerorder){
	
		baseDao.saveEntity(computerorder);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorder(Integer computerorderId){
		String sql = "delete from Computerorder where id="+computerorderId;
		baseDao.createSQL(sql);
		return 1;
	}
	

//  根据实体删除实体
	@Override
	public int deleteComputerorder(Computerorder computerorder) {
		return deleteComputerorder(computerorder.getId());
	}

	
	@Override
	public void updateComputerorder(Computerorder computerorder){
		
		Computerorder tempComputerorder = new Computerorder();
		//tempComputerorder = baseDao.getEntityById(Computerorder.class, computerorder.getId());
		tempComputerorder = computerorder;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorder);

	}

//	根据id查询实体类			
	@Override
	public Computerorder selectComputerorderById(Integer computerorderId){		
		return baseDao.getEntityById(Computerorder.class, computerorderId);
	}
	
	@Override
	public List<Computerorder> selectComputerorderAll(){
			
		return baseDao.getAllEntity(Computerorder.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerorderFull selectComputerorderFullById(Integer computerorderId){
	
		return computerorderDao.selectComputerorderFullById(computerorderId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerorderFull> selectComputerorderFullAll() {
		// TODO Auto-generated method stub
		return computerorderDao.selectComputerorderFullAll();
	}
	
	
	public int countComputerorderRow(){
		return baseDao.getRowCount(Computerorder.class);
	}
		
//  分页查询
	public List<Computerorder> selectComputerorderByPage(Page page){	
		return baseDao.selectByPage(Computerorder.class,page);
	}
//  分页查询full	
	public List<ComputerorderFull> selectComputerorderFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerorder.class));
		return computerorderDao.selectComputerorderFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorder> selectComputerorderByCondition(String condition) {
		 return computerorderDao.selectComputerorderByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerorder>  selectComputerorderByConditionAndPage(String condition,final Page page) {
              return computerorderDao.selectComputerorderByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerorderFull> selectComputerorderFullByCondition(String condition) {
		return computerorderDao.selectComputerorderFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderFull>  selectComputerorderFullByConditionAndPage(String condition,final Page page) {
			return computerorderDao.selectComputerorderFullByConditionAndPage(condition, page);
		}
    	@Override
    	public int execSql(String sql) {
//    		String sql = "delete from Computerorderdetail " + condition;
    		baseDao.createSQL(sql);
    		return 1;
    		
    	}
	

}
