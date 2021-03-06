package com.sbgl.app.services.computer.impl;

import java.util.ArrayList;
import java.util.List;


import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.Computerstatus;
import com.sbgl.app.services.computer.ComputerService;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.computer.ComputerActionUtil;
import com.sbgl.app.actions.util.PageActionUtil;
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
	
	
	@Override
	public  List<Computer> selByModeltype(int modeltype,int language){		
		return computerDao.selByModeltype(modeltype, language);
	}	
	@Override
	public  List<ComputerFull> selFullByModeltype(int modeltype,int language){		
		return computerDao.selFullByModeltype(modeltype, language);
	}
	
	/**
	 * 根据分类查询
	 */
	@Override
	public  List<Computer> selByCategorytype(int categorytype,int language){
		List<Computermodel> modeltypeList = computermodelDao.selByCategorytype(categorytype, language);
		
		List<Integer> modellist = new ArrayList<Integer>();
		
		for(Computermodel m : modeltypeList){
			modellist.add(m.getComputermodeltype());
		}
		
		return computerDao.selByModeltype(modellist, language);
	}	
	@Override
	public  List<ComputerFull> selFullByCategorytype(int categorytype,int language){
		List<Computermodel> modeltypeList = computermodelDao.selByCategorytype(categorytype, language);	
		List<Integer> modellist = new ArrayList<Integer>();
		for(Computermodel m : modeltypeList){
			modellist.add(m.getComputermodeltype());
		}		
		return computerDao.selFullByModeltype(modellist, language);
	}
	
	
	
//	根据id查询实体类			
	@Override
	public Computer selectComputerById(Integer computerId){		
		return baseDao.getEntityById(Computer.class, computerId);
	}


	@Override
	public void selFullByPage(int computercategorytype,int computermodeltype,int computerstatusid,Page page,int languge,List<ComputerFull> computerFullListCh,List<ComputerFull> computerFullListEn){

		String countsql = " where a.languagetype="+CommonConfig.languagech;
		String sqlch =" where a.languagetype="+CommonConfig.languagech+" and b.languagetype="+CommonConfig.languagech;
		String sqlen= " where a.languagetype="+CommonConfig.languageen+" and b.languagetype="+CommonConfig.languageen;
		
		//查询全部中文的
		
		//查询所有分类
		if(computercategorytype==0){
			
		}
		
		//查询某一个Model下的PC
		if(computercategorytype!=0 && computermodeltype!=0){
			countsql +=  " and a.computermodelid="+computermodeltype;
			sqlch = sqlch + " and a.computermodelid="+computermodeltype;
			sqlen = sqlen +  " and a.computermodelid="+computermodeltype;
		}
		
//		查询某一分类下的PC,先获取分类下面的模型
		if(computercategorytype!=0 && computermodeltype==0){
			List<Computermodel> tempComputermodelList = computermodelDao.selByCategorytype(computercategorytype, CommonConfig.languagech);
			String inStr = " (";
			
//			如果不存在分类不存在模型，设置一个空的模型id
			if(tempComputermodelList==null || tempComputermodelList.size()<1){
				inStr +=" -10,";
			}else{
				for(Computermodel c : tempComputermodelList){
					inStr += c.getComputermodeltype()+",";
				}
			}
			
			inStr = inStr.substring(0,inStr.length()-1);
			inStr += ") ";
			countsql += " and a.computermodelid in "+inStr+" ";
			sqlch = sqlch + " and a.computermodelid in "+inStr+" ";
			sqlen = sqlen + " and a.computermodelid in "+inStr+" ";
		}

//		//查询sql,如果computermodeltype为0，查询全部
//		if(computermodeltype == 0){
//			countsql += " where a.languagetype="+ComputerConfig.languagech;
//			sqlch = sqlch + " where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech;
//			sqlen = sqlen + " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen;
//		}else{
//			countsql =" where a.computermodelid="+computermodeltype;
////			countsql = countsql + " where a.languagetype="+ComputerConfig.languagech+" and a.computercategoryid="+computercategoryid+"  order by a.computermodeltype,a.languagetype";
//			sqlch = sqlch + " where a.languagetype="+ComputerConfig.languagech+" and b.languagetype="+ComputerConfig.languagech+" and a.computermodelid="+computermodeltype;
//			sqlen = sqlen + " where a.languagetype="+ComputerConfig.languageen+" and b.languagetype="+ComputerConfig.languageen+" and a.computermodelid="+computermodeltype;
//		}
		
		if(computerstatusid == 0){
			
		}else{
//			if(computermodeltype != 0){
//				countsql = countsql +" where ";
//			}else{
//				countsql = countsql +" and ";
//			}
			countsql = countsql + " and a.computerstatusid=" + computerstatusid;
			sqlch = sqlch + " and a.computerstatusid=" + computerstatusid;
			sqlen = sqlen + " and a.computerstatusid=" + computerstatusid;
		}		
		sqlch += " order by a.computertype,a.languagetype";
		sqlen += " order by a.computertype,a.languagetype";
		
		System.out.println(countsql);
//		System.out.println(sqlch);
//		System.out.println(sqlen);
		
		int totalcount = computerDao.countRow(countsql);
//		System.out.println();
		PageActionUtil.getPage(page ,totalcount, page.getPageNo());
		computerFullListCh.clear();
		computerFullListCh.addAll(computerDao.selectComputerFullByConditionAndPage(sqlch, page));
		
		computerFullListEn.clear();
		computerFullListEn.addAll(computerDao.selectComputerFullByConditionAndPage(sqlen, page));
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
