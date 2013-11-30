package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.dao.ComputerDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerService")
@Transactional
public class ComputerServiceImpl implements ComputerService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerDao computerDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputer(Computer computer){
		computer.setId(baseDao.getCode("Computer"));
		baseDao.saveEntity(computer);		
	}

	@Override
	public void addComputerWithId(Computer computer){
	
		baseDao.saveEntity(computer);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputer(Integer computerId){
		Computer computer = new Computer();
		computer.setId(computerId);
		//return computerDao.deleteEntity(computerId);	
		//baseDao.deleteEntityById(Computer.class,computerId);
		baseDao.deleteEntity(computer);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputer(Computer computer) {
		return deleteComputer(computer.getId());
	}

	
	@Override
	public void updateComputer(Computer computer){
		
		Computer tempComputer = new Computer();
		//tempComputer = baseDao.getEntityById(Computer.class, computer.getId());
		tempComputer = computer;
		//add your code here
		
		
		baseDao.updateEntity(tempComputer);

	}
	
	/**
	 * 更新某一型号下面所有的机器
	 */
	@Override
	public void updateComputermodelTo(int originalComputermodelid,int toComputermodelid){
		String sql = "update Computer as tb set tb.computermodelid = "+toComputermodelid +" where tb.computermodelid =  " + originalComputermodelid;
		baseDao.createSQL(sql);		
	}
	

//	根据id查询实体类			
	@Override
	public Computer selectComputerById(Integer computerId){		
		return baseDao.getEntityById(Computer.class, computerId);
	}
	
	@Override
	public List<Computer> selectComputerAll(){
			
		return baseDao.getAllEntity(Computer.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerFull selectComputerFullById(Integer computerId){
	
		return computerDao.selectComputerFullById(computerId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerFull> selectComputerFullAll() {
		// TODO Auto-generated method stub
		return computerDao.selectComputerFullAll();
	}
	
	
	public int countComputerRow(){
		return baseDao.getRowCount(Computer.class);
	}
		
//  分页查询
	public List<Computer> selectComputerByPage(Page page){	
		return baseDao.selectByPage(Computer.class,page);
	}
//  分页查询full	
	public List<ComputerFull> selectComputerFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computer.class));
		return computerDao.selectComputerFullByPage(page);
	}
	
	//根据computermodelid 查询实体
	@Override
	public List<Computer> selectComputerByComputermodelId(Integer computermodelid ) {
		List<Computer> computerList = new ArrayList<Computer>();
		String id = String.valueOf(computermodelid );
		computerList = baseDao.getEntityByProperty("Computer", "computermodelid ", id);
		return computerList;
	}
	//根据computermodelid 查询实体full
	@Override
	public List<ComputerFull> selectComputerFullByComputermodelId(Integer computermodelid ) {
		return computerDao.selectComputerFullByComputermodelId(computermodelid );
	}
	/**
	 * 更改属于某一型号的所有机器
	 * @param name
	 * @return
	 */
//	@Override
//	 public boolean isComputermodelNameExist(String name){
//		 List<Computercategory>  l = baseDao.getEntityByProperty("Computermodel", "name", name);
//		 if(l==null || l.size()==0){
//			 return false;
//		 }else{
//			 return true;
//		 }
//	 }
}
