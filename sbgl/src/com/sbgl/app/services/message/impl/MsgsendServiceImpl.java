package com.sbgl.app.services.message.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Msgreceive;
import com.sbgl.app.entity.Msgsend;
import com.sbgl.app.entity.MsgsendFull;
import com.sbgl.app.services.message.MsgsendService;
import com.sbgl.app.dao.MsgreceiveDao;
import com.sbgl.app.dao.MsgsendDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.util.*;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("msgsendService")
@Transactional
public class MsgsendServiceImpl implements MsgsendService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private MsgsendDao msgsendDao;
	
	@Resource
	private MsgreceiveDao msgreceiveDao;
	
	@Override
	public void snedMsg(Msgsend msgsend,Msgreceive msgreceive){
		msgsend.setId(baseDao.getCode("Msgsend"));
		baseDao.saveEntity(msgsend);
		msgreceive.setId(baseDao.getCode("Msgreceive"));
		baseDao.saveEntity(msgreceive);		
	}
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addMsgsend(Msgsend msgsend){
		msgsend.setId(baseDao.getCode("Msgsend"));
		baseDao.saveEntity(msgsend);		
	}
	
	@Override
	public void addMsgsend(Msgsend ch,Msgsend en){
		
	/*
		int type = baseDao.getCode("Msgsendtype");
		ch.setId(baseDao.getCode("Msgsend"));
		ch.setMsgsendtype(type);
		en.setId(baseDao.getCode("Msgsend"));
		en.setMsgsendtype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void addMsgsendWithId(Msgsend msgsend){
	
		baseDao.saveEntity(msgsend);		
	}

//  根据id删除实体	
	@Override
	public int deleteMsgsend(Integer msgsendId){
		Msgsend msgsend = new Msgsend();
		msgsend.setId(msgsendId);
		//return msgsendDao.deleteEntity(msgsendId);	
		//baseDao.deleteEntityById(Msgsend.class,msgsendId);
		baseDao.deleteEntity(msgsend);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteMsgsend(Msgsend msgsend) {
		return deleteMsgsend(msgsend.getId());
	}

	
	@Override
	public void updateMsgsend(Msgsend msgsend){
		
		Msgsend tempMsgsend = new Msgsend();
		//tempMsgsend = baseDao.getEntityById(Msgsend.class, msgsend.getId());
		tempMsgsend = msgsend;
		//add your code here
		
		
		baseDao.updateEntity(tempMsgsend);

	}

//	根据id查询实体类			
	@Override
	public Msgsend selectMsgsendById(Integer msgsendId){		
		return baseDao.getEntityById(Msgsend.class, msgsendId);
	}
	
	/*
	@Override
	public List<Msgsend> selectMsgsendAll(){
			
		return baseDao.getAllEntity(Msgsend.class);
		
	}
	*/
	
//	根据id查询full类
	@Override
	public MsgsendFull selectMsgsendFullById(Integer msgsendId){
		String sql = "where a.id=" + msgsendId;
		List<MsgsendFull> temp = msgsendDao.selectMsgsendFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	

	public int countMsgsendRow(){
		return baseDao.getRowCount(Msgsend.class);
	}
		
//  分页查询
	public List<Msgsend> selectMsgsendByPage(Page page){	
		return baseDao.selectByPage(Msgsend.class,page);
	}
//  分页查询full	
	public List<MsgsendFull> selectMsgsendFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Msgsend.class));
		return msgsendDao.selectMsgsendFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Msgsend> selectMsgsendByCondition(String condition) {
		 return msgsendDao.selectMsgsendByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
	@Override
	public List<Msgsend>  selectMsgsendByConditionAndPage(String condition,final Page page) {
		return msgsendDao.selectMsgsendByConditionAndPage(condition,page);
	}
	
	
	//条件查询full
	@Override
	public List<MsgsendFull> selectMsgsendFullByCondition(String condition) {
		return msgsendDao.selectMsgsendFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<MsgsendFull>  selectMsgsendFullByConditionAndPage(String condition,final Page page) {
			return msgsendDao.selectMsgsendFullByConditionAndPage(condition, page);
		}
	


}
