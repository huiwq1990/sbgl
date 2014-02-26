package com.sbgl.app.services.message.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.MsgreceiveFull;
import com.sbgl.app.services.message.MsgreceiveService;
import com.sbgl.app.dao.MsgreceiveDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("msgreceiveService")
@Transactional
public class MsgreceiveServiceImpl implements MsgreceiveService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private MsgreceiveDao msgreceiveDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addMsgreceive(Msgreceive msgreceive){
		msgreceive.setId(baseDao.getCode("Msgreceive"));
		baseDao.saveEntity(msgreceive);		
	}
	
	@Override
	public void addMsgreceive(Msgreceive ch,Msgreceive en){
		
	/*
		int type = baseDao.getCode("Msgreceivetype");
		ch.setId(baseDao.getCode("Msgreceive"));
		ch.setMsgreceivetype(type);
		en.setId(baseDao.getCode("Msgreceive"));
		en.setMsgreceivetype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addMsgreceiveWithId(Msgreceive msgreceive){
	
		baseDao.saveEntity(msgreceive);		
	}

//  根据id删除实体	
	@Override
	public int deleteMsgreceive(Integer msgreceiveId){
		Msgreceive msgreceive = new Msgreceive();
		msgreceive.setId(msgreceiveId);
		//return msgreceiveDao.deleteEntity(msgreceiveId);	
		//baseDao.deleteEntityById(Msgreceive.class,msgreceiveId);
		baseDao.deleteEntity(msgreceive);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteMsgreceive(Msgreceive msgreceive) {
		return deleteMsgreceive(msgreceive.getId());
	}

	
	@Override
	public void updateMsgreceive(Msgreceive msgreceive){
		
		Msgreceive tempMsgreceive = new Msgreceive();
		//tempMsgreceive = baseDao.getEntityById(Msgreceive.class, msgreceive.getId());
		tempMsgreceive = msgreceive;
		//add your code here
		
		
		baseDao.updateEntity(tempMsgreceive);

	}

//	根据id查询实体类			
	@Override
	public Msgreceive selectMsgreceiveById(Integer msgreceiveId){		
		return baseDao.getEntityById(Msgreceive.class, msgreceiveId);
	}
	
	/*
	@Override
	public List<Msgreceive> selectMsgreceiveAll(){
			
		return baseDao.getAllEntity(Msgreceive.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public MsgreceiveFull selectMsgreceiveFullById(Integer msgreceiveId){
		String sql = "where a.id=" + msgreceiveId;
		List<MsgreceiveFull> temp = msgreceiveDao.selectMsgreceiveFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countMsgreceiveRow(){
		return baseDao.getRowCount(Msgreceive.class);
	}
		
//  分页查询
	public List<Msgreceive> selectMsgreceiveByPage(Page page){	
		return baseDao.selectByPage(Msgreceive.class,page);
	}
//  分页查询full	
	public List<MsgreceiveFull> selectMsgreceiveFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Msgreceive.class));
		return msgreceiveDao.selectMsgreceiveFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Msgreceive> selectMsgreceiveByCondition(String condition) {
		 return msgreceiveDao.selectMsgreceiveByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Msgreceive>  selectMsgreceiveByConditionAndPage(String condition,final Page page) {
		return msgreceiveDao.selectMsgreceiveByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<MsgreceiveFull> selectMsgreceiveFullByCondition(String condition) {
		return msgreceiveDao.selectMsgreceiveFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<MsgreceiveFull>  selectMsgreceiveFullByConditionAndPage(String condition,final Page page) {
			return msgreceiveDao.selectMsgreceiveFullByConditionAndPage(condition, page);
		}
	


}
