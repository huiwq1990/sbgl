package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerorderdetailService")
@Transactional
public class ComputerorderdetailServiceImpl implements ComputerorderdetailService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerorderdetailDao computerorderdetailDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerorderdetail(Computerorderdetail computerorderdetail){
		computerorderdetail.setId(baseDao.getCode("Computerorderdetail"));
		baseDao.saveEntity(computerorderdetail);		
	}

	@Override
	public void addComputerorderdetailWithId(Computerorderdetail computerorderdetail){
	
		baseDao.saveEntity(computerorderdetail);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerorderdetail(Integer computerorderdetailId){
		Computerorderdetail computerorderdetail = new Computerorderdetail();
		computerorderdetail.setId(computerorderdetailId);
		//return computerorderdetailDao.deleteEntity(computerorderdetailId);	
		//baseDao.deleteEntityById(Computerorderdetail.class,computerorderdetailId);
		baseDao.deleteEntity(computerorderdetail);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerorderdetail(Computerorderdetail computerorderdetail) {
		return deleteComputerorderdetail(computerorderdetail.getId());
	}

	
	@Override
	public void updateComputerorderdetail(Computerorderdetail computerorderdetail){
		
		Computerorderdetail tempComputerorderdetail = new Computerorderdetail();
		//tempComputerorderdetail = baseDao.getEntityById(Computerorderdetail.class, computerorderdetail.getId());
		tempComputerorderdetail = computerorderdetail;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerorderdetail);

	}

//	根据id查询实体类			
	@Override
	public Computerorderdetail selectComputerorderdetailById(Integer computerorderdetailId){		
		return baseDao.getEntityById(Computerorderdetail.class, computerorderdetailId);
	}
	
	@Override
	public List<Computerorderdetail> selectComputerorderdetailAll(){
			
		return baseDao.getAllEntity(Computerorderdetail.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId){
	
		return computerorderdetailDao.selectComputerorderdetailFullById(computerorderdetailId); 
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll() {
		// TODO Auto-generated method stub
		return computerorderdetailDao.selectComputerorderdetailFullAll();
	}
	
	
	public int countComputerorderdetailRow(){
		return baseDao.getRowCount(Computerorderdetail.class);
	}
		
//  分页查询

	public List<Computerorderdetail> selectComputerorderdetailByPage(Page page){	
		
		return baseDao.selectByPage(Computerorderdetail.class,page);
	}
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(Page page){	
		
		return computerorderdetailDao.selectComputerorderdetailFullByPage(page);
	}
	
	//根据computerorderid 查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid ) {
		List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
		String id = String.valueOf(computerorderid );
		computerorderdetailList = baseDao.getEntityByProperty("Computerorderdetail", "computerorderid ", id);
		return computerorderdetailList;
	}
	//根据computerid 查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid ) {
		List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
		String id = String.valueOf(computerid );
		computerorderdetailList = baseDao.getEntityByProperty("Computerorderdetail", "computerid ", id);
		return computerorderdetailList;
	}
	//根据computerorderid 查询实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid ) {
		return computerorderdetailDao.selectComputerorderdetailFullByComputerorderId(computerorderid );
	}
	//根据computerid 查询实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid ) {
		return computerorderdetailDao.selectComputerorderdetailFullByComputerId(computerid );
	}

}
