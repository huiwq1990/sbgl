package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.dao.ComputerDao;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.ComputermodelDao;
import com.sbgl.app.dao.ComputerstatusDao;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

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
	@Resource
	private ComputermodelDao computermodelDao;
	
	@Resource
	private ComputerstatusDao computerstatusDao;
	
	//http://blog.csdn.net/softimes/article/details/7008875 实体添加时需要配置hibernate

	@Override
	public void addComputerAndSetNum(Computer ch,Computer en,int availBorrow,int originalTotalNum,int originalAvailBorrowNum){
		int type = baseDao.getCode("Computertype");
		ch.setId(baseDao.getCode("Computer"));
		ch.setComputertype(type);
		en.setId(baseDao.getCode("Computer"));
		en.setComputertype(type);
		baseDao.saveEntity(ch);	
		baseDao.saveEntity(en);	
		
//		模型总数量加1
		baseDao.createSQL(" update Computermodel set computercount="+(originalTotalNum+1)+"  where computermodeltype = "+ch.getComputermodelid());

//		如果设备可以借出，则可借数量加一
		if(availBorrow == ComputerConfig.computeravailableborrowstatusid){		
			baseDao.createSQL(" update Computermodel set availableborrowcountnumber="+(originalAvailBorrowNum+1)+"  where computermodeltype = "+ch.getComputermodelid());
		}
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
	
	/**
	 * 根据类型删除
	 * @param computermodeltype
	 * @return
	 */
	@Override
	public int deleteComputerByType(Integer type){
		String sql = "delete from Computer where computertype="+type;
		baseDao.createSQL(sql);
		return 1;
	}
	
	@Override
	public int deleteComputerByType(List<Integer> delTypeList) throws DataError{
		
		for(Integer computertype : delTypeList){

			List<Computer> computerList = computerDao.selectComputerByCondition(" where computertype="+computertype+" and languagetype = "+ComputerConfig.languagech);	
			if(computerList== null || computerList.size() == 0){
				throw new DataError("无法获取机器"+computertype);
			}	
//			要删除的Computer
			Computer delComputer = computerList.get(0);
			
//			查询要删除机器的所属模型 修改数量
			List<Computermodel> computermodelList = computermodelDao.selectComputermodelByCondition(" where computermodeltype = " + delComputer.getComputermodelid() );
			if(computermodelList== null || computermodelList.size() !=2){					
				throw new DataError("获取机器"+delComputer.getSerialnumber()+"的所属模型");
			}			
			Computermodel cm = computermodelList.get(0);
			baseDao.createSQL(" update Computermodel set availableborrowcountnumber="+(cm.getAvailableborrowcountnumber()-1)+", computercount="+(cm.getComputercount()-1)+"  where computermodeltype = "+cm.getComputermodeltype());
				
//			执行删除
			String sql = "delete from Computer where computertype="+computertype;
			baseDao.createSQL(sql);
		}
		
		return 1;
	}

	@Override
	public void updateComputer(Computer ch,Computer en,int orignialComputerModelType,int nowAviBow,int orgAviBow) throws DataError{
		
		int nowComputermodelType = ch.getComputermodelid();
		
//		如果没有改变机器所属模型
		if(nowComputermodelType == orignialComputerModelType){
//			原先可借现在不可借
//			将模型的可借数量减一
			if( (nowAviBow != orgAviBow) && (orgAviBow == ComputerConfig.computeravailableborrowstatusid) ){
				Computermodel cm = computermodelDao.selectComputermodelByCondition(" where computermodeltype = " + ch.getComputermodelid() ).get(0);
				if(cm.getAvailableborrowcountnumber() == 0){
					cm.setAvailableborrowcountnumber(0);
				}				
				baseDao.createSQL(" update Computermodel set availableborrowcountnumber="+(cm.getAvailableborrowcountnumber()-1)+"  where computermodeltype = "+ch.getComputermodelid());
			}
			
//			原先不可借现在可借
//			将模型数量加一
			if( (nowAviBow != orgAviBow) && (orgAviBow != ComputerConfig.computeravailableborrowstatusid) ){
				Computermodel cm = computermodelDao.selectComputermodelByCondition(" where computermodeltype = " + ch.getComputermodelid() ).get(0);
				if(cm.getAvailableborrowcountnumber() == 0){
					cm.setAvailableborrowcountnumber(0);
				}				
				baseDao.createSQL(" update Computermodel set availableborrowcountnumber="+(cm.getAvailableborrowcountnumber()+1)+"  where computermodeltype = "+ch.getComputermodelid());
			}
			
		}else{
//			获取原先模型
			List<Computermodel> orgCmList = computermodelDao.selectComputermodelByCondition(" where computermodeltype = " + orignialComputerModelType  );
			List<Computermodel> curCmList = computermodelDao.selectComputermodelByCondition(" where computermodeltype = " + nowComputermodelType  );
			if(orgCmList == null || orgCmList.size()==0 || curCmList == null || curCmList.size()==0 ){
				throw new DataError("无法获取变动后的机房模型");
			}
			Computermodel orgCm = orgCmList.get(0);
			Computermodel curCm = curCmList.get(0);
			
			if(orgCm.getAvailableborrowcountnumber() == null){
				orgCm.setAvailableborrowcountnumber(0);
			}	
			
			if(curCm.getAvailableborrowcountnumber() == null){
				curCm.setAvailableborrowcountnumber(0);
			}	
			
//			修改原有模型数量
			String sql = "";
//			如果原来机器不可借,只要将模型的总数减一
			if( orgAviBow != ComputerConfig.computeravailableborrowstatusid ){
				sql = " update Computermodel set computercount="+(orgCm.getComputercount()-1)+"  where computermodeltype = "+orgCm.getComputermodeltype();
				baseDao.createSQL(sql);
			}else{
//				原来模型可借，需要将可借数量减一
				sql = " update Computermodel set computercount="+(orgCm.getComputercount()-1)+" , availableborrowcountnumber="+(orgCm.getAvailableborrowcountnumber()-1)+"  where computermodeltype = "+orgCm.getComputermodeltype();
				baseDao.createSQL(sql);	
			}
			
			System.out.println(sql);
			
//			修改后的模型 ，如果不可借，只将模型总数量加一
			if( nowAviBow != ComputerConfig.computeravailableborrowstatusid ){
				sql = " update Computermodel set computercount="+(curCm.getComputercount()+1)+"  where computermodeltype = "+curCm.getComputermodeltype();
				baseDao.createSQL(sql);
			}else{
				sql = " update Computermodel set computercount="+(curCm.getComputercount()+1)+" , availableborrowcountnumber="+(curCm.getAvailableborrowcountnumber()+1)+"  where computermodeltype = "+curCm.getComputermodeltype();
				baseDao.createSQL(sql);	
			}
			System.out.println(sql);
		}

	


		
		baseDao.updateEntity(ch);
//		baseDao.updateEntity(en);
	}
	
	
	/**
	 * 更新某一型号下面所有的机器
	 */
	@Override
	public void updateComputermodelTo(int originalComputermodelid,int toComputermodelid){
		String sql = "update Computer as tb set tb.computermodelid = "+toComputermodelid +" where tb.computermodelid =  " + originalComputermodelid;
		baseDao.createSQL(sql);		
	}
	

	public Computer selectComputerByTypeAndLanguage(int type,int language){
		List<Computer> l =  computerDao.selectComputerByCondition(" where computertype="+type + " and languagetype="+language);
		if(l == null){
			return null;
		}else{
			return l.get(0);
		}
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
	

	
	// 根据条件查询查询实体
	@Override
	public List<Computer> selectComputerByCondition(String condition) {
		 return computerDao.selectComputerByCondition(condition);
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computer>  selectComputerByConditionAndPage(String condition,final Page page) {
              return computerDao.selectComputerByConditionAndPage(condition,page);
        }
	
	
	//条件查询full
	@Override
	public List<ComputerFull> selectComputerFullByCondition(String condition) {
		return computerDao.selectComputerFullByCondition(condition);
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerFull>  selectComputerFullByConditionAndPage(String condition,final Page page) {
			return computerDao.selectComputerFullByConditionAndPage(condition, page);
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
