package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
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
	//	computer.setId(baseDao.getCode("Computer"));
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
		return computerDao.deleteEntity(computerId);		
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
	public List<ComputerFull> selectComputerFullByPage(Page page){	
		
		return computerDao.selectComputerFullByPage(page);
	}
	
	//根据computercategoryid 查询实体
	@Override
	public List<Computer> selectComputerByComputercategoryId(Integer computercategoryid ) {
		List<Computer> computerList = new ArrayList<Computer>();
		String id = String.valueOf(computercategoryid );
		computerList = baseDao.getEntityByProperty("Computer", "computercategoryid ", id);
		return computerList;
	}
	//根据createuserid 查询实体
	@Override
	public List<Computer> selectComputerByLoginuserId(Integer createuserid ) {
		List<Computer> computerList = new ArrayList<Computer>();
		String id = String.valueOf(createuserid );
		computerList = baseDao.getEntityByProperty("Computer", "createuserid ", id);
		return computerList;
	}
	//根据computercategoryid 查询实体full
	@Override
	public List<ComputerFull> selectComputerFullByComputercategoryId(Integer computercategoryid ) {
		return computerDao.selectComputerFullByComputercategoryId(computercategoryid );
	}
	//根据createuserid 查询实体full
	@Override
	public List<ComputerFull> selectComputerFullByLoginuserId(Integer createuserid ) {
		return computerDao.selectComputerFullByLoginuserId(createuserid );
	}

}
