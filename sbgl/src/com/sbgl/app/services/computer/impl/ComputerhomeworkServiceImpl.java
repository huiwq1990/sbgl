package com.sbgl.app.services.computer.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.Computerorderclassrule;
import com.sbgl.app.entity.Computerorderclassruledetail;
import com.sbgl.app.services.computer.ComputerhomeworkService;
import com.sbgl.app.dao.ComputerhomeworkDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.ComputerorderclassruleDao;
import com.sbgl.app.dao.ComputerorderclassruledetailDao;
import com.sbgl.util.*;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope("prototype") 
@Service("computerhomeworkService")
@Transactional
public class ComputerhomeworkServiceImpl implements ComputerhomeworkService{
	@Resource
	private BaseDao baseDao;
	@Resource
	private ComputerhomeworkDao computerhomeworkDao;
	
	@Resource
	private ComputerorderclassruleDao computerorderclassruleDao;
	
	@Resource
	private ComputerorderclassruledetailDao computerorderclassruledetailDao;
	
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate
	@Override
	public void addComputerhomework(Computerhomework computerhomework){
		computerhomework.setId(baseDao.getCode("Computerhomework"));
		baseDao.saveEntity(computerhomework);		
	}
	
	@Override
	public void addComputerhomework(Computerhomework ch,Computerhomework en){
		
	/*
		int type = baseDao.getCode("Computerhomeworktype");
		ch.setId(baseDao.getCode("Computerhomework"));
		ch.setComputerhomeworktype(type);
		en.setId(baseDao.getCode("Computerhomework"));
		en.setComputerhomeworktype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);		
	*/
	}

	@Override
	public void sendComputerhomework(Computerhomework computerhomework,List<Computerhomeworkreceiver> chrList ) throws RuntimeException{
		
//		保存Homework
		Computerhomework temp = new Computerhomework();
		// 将model里的属性值赋给temp
		try {
			BeanUtils.copyProperties(temp, computerhomework);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("复制对象出错");  
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("复制对象出错");  
		}	
		temp.setId(baseDao.getCode("Computerhomework"));
		temp.setCreatetime(DateUtil.currentDate());		
//		先添加homework，再添加接收者
		baseDao.saveEntity(temp);
		
		
		
		for (Computerhomeworkreceiver chr : chrList ) {
			chr.setId(baseDao.getCode("Computerhomeworkreceiver"));
			chr.setComputerhomeworkid(temp.getId());
			
			baseDao.saveEntity(chr);
		}
		
	}
	
	
	@Override
	public void sendComputerhomeworkNew(Computerorderclassrule rule,List<Computerorderclassruledetail> codList ,Computerhomework computerhomework,List<Computerhomeworkreceiver> chrList ) throws RuntimeException{
		
		rule.setId(baseDao.getCode("Computerorderclassrule"));
		baseDao.saveEntity(rule);
		
		for(Computerorderclassruledetail cod : codList){
			cod.setId(baseDao.getCode("Computerorderclassruledetail"));
			cod.setComputerorderclassruleid(rule.getId());
			baseDao.saveEntity(cod);
		}
		
//		保存Homework
		Computerhomework temp = new Computerhomework();
		// 将model里的属性值赋给temp
		try {
			BeanUtils.copyProperties(temp, computerhomework);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("复制对象出错");  
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("复制对象出错");  
		}	
		temp.setId(baseDao.getCode("Computerhomework"));
		temp.setCreatetime(DateUtil.currentDate());		
//		先保存规则
		temp.setComputerorderclassruleid(rule.getId());
//		先添加homework，再添加接收者
		baseDao.saveEntity(temp);
		
		
		
		for (Computerhomeworkreceiver chr : chrList ) {
			chr.setId(baseDao.getCode("Computerhomeworkreceiver"));
			chr.setComputerhomeworkid(temp.getId());
			
			baseDao.saveEntity(chr);
		}
		
	}
	
	
	
	@Override
	public void addComputerhomeworkWithId(Computerhomework computerhomework){
	
		baseDao.saveEntity(computerhomework);		
	}

//  根据id删除实体	
	@Override
	public int deleteComputerhomework(Integer computerhomeworkId){
		Computerhomework computerhomework = new Computerhomework();
		computerhomework.setId(computerhomeworkId);
		//return computerhomeworkDao.deleteEntity(computerhomeworkId);	
		//baseDao.deleteEntityById(Computerhomework.class,computerhomeworkId);
		baseDao.deleteEntity(computerhomework);
		return 1;
	}

//  根据实体删除实体
	@Override
	public int deleteComputerhomework(Computerhomework computerhomework) {
		return deleteComputerhomework(computerhomework.getId());
	}

	@Override
	public boolean deleteComputerhomework(Integer delIdArray[]) {
		
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<delIdArray.length;i++){
			sb.append(delIdArray[i]);
			if(i!=delIdArray.length-1){
				sb.append(",");
			}
			
		}
			
		String sql = "delete from Computerhomework where id in("+sb.toString()+")";
		
		baseDao.createSQL(sql);
		
		return true;
			
	}
	
	@Override
	public void updateComputerhomework(Computerhomework computerhomework){
		
		Computerhomework tempComputerhomework = new Computerhomework();
		//tempComputerhomework = baseDao.getEntityById(Computerhomework.class, computerhomework.getId());
		tempComputerhomework = computerhomework;
		//add your code here
		
		
		baseDao.updateEntity(tempComputerhomework);

	}

//	根据id查询实体类			
	@Override
	public Computerhomework selectComputerhomeworkById(Integer computerhomeworkId){		
		return baseDao.getEntityById(Computerhomework.class, computerhomeworkId);
	}
	
	@Override
	public List<Computerhomework> selectComputerhomeworkAll(){
			
		return baseDao.getAllEntity(Computerhomework.class);
		
	}
	
//	根据id查询full类
	@Override
	public ComputerhomeworkFull selectComputerhomeworkFullById(Integer computerhomeworkId){
		String sql = "where a.id=" + computerhomeworkId;
		List<ComputerhomeworkFull> temp = computerhomeworkDao.selectComputerhomeworkFullByCondition(sql);
		if(temp !=null && temp.size()==1){
			return temp.get(0);
		}else{
			return null;
		}
	}	
	
	
	

//  查询全部实体full
	@Override
	public List<ComputerhomeworkFull> selectComputerhomeworkFullAll() {
		// TODO Auto-generated method stub
		return computerhomeworkDao.selectComputerhomeworkFullAll();
	}
	
	
	public int countComputerhomeworkRow(){
		return baseDao.getRowCount(Computerhomework.class);
	}
		
//  分页查询
	public List<Computerhomework> selectComputerhomeworkByPage(Page page){	
		return baseDao.selectByPage(Computerhomework.class,page);
	}
//  分页查询full	
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByPage(Page page){
		page.setTotalCount(baseDao.getRowCount(Computerhomework.class));
		return computerhomeworkDao.selectComputerhomeworkFullByPage(page);
	}
	
	// 根据条件查询查询实体
	@Override
	public List<Computerhomework> selectComputerhomeworkByCondition(String condition) {
		 return computerhomeworkDao.selectComputerhomeworkByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computerhomework>  selectComputerhomeworkByConditionAndPage(String condition,final Page page) {
              return computerhomeworkDao.selectComputerhomeworkByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByCondition(String condition) {
		return computerhomeworkDao.selectComputerhomeworkFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerhomeworkFull>  selectComputerhomeworkFullByConditionAndPage(String condition,final Page page) {
			return computerhomeworkDao.selectComputerhomeworkFullByConditionAndPage(condition, page);
		}
	
	
	//根据computerorderclassruleid 查询实体
	@Override
	public List<Computerhomework> selectComputerhomeworkByComputerorderclassruleId(Integer computerorderclassruleid ) {
		List<Computerhomework> computerhomeworkList = new ArrayList<Computerhomework>();
		String id = String.valueOf(computerorderclassruleid );
		computerhomeworkList = baseDao.getEntityByProperty("Computerhomework", "computerorderclassruleid ", id);
		return computerhomeworkList;
	}
	//根据computerorderclassruleid 查询实体full
	@Override
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByComputerorderclassruleId(Integer computerorderclassruleid ) {
		return computerhomeworkDao.selectComputerhomeworkFullByComputerorderclassruleId(computerorderclassruleid );
	}

//    @Override
//    public List<ComputerhomeworkFull>  selectComputerhomeworkFullByUserAndOrder(int uid,int hasorder) {
//		return computerhomeworkDao.selectComputerhomeworkFullByCondition(" where a.userid ="+userid+" and a.")
//	}
	
}
