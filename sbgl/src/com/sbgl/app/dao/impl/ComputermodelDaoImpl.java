package com.sbgl.app.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.common.computer.BorrowperiodUtil;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.DaoAbs;

import com.sbgl.app.dao.ComputermodelDao;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.ComputermodelFull;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.util.*;

@Repository("computermodelDao")
public class ComputermodelDaoImpl extends HibernateDaoSupport implements ComputermodelDao{

	private static final Log log = LogFactory.getLog(ComputermodelDaoImpl.class);
	private final String basicComputermodelFullSql = "select a.id as computermodelid, a.computermodeltype as computermodelcomputermodeltype, a.languagetype as computermodellanguagetype, a.name as computermodelname, a.computercategoryid as computermodelcomputercategoryid, a.picpath as computermodelpicpath, a.createtime as computermodelcreatetime, a.createuserid as computermodelcreateuserid, a.computercount as computermodelcomputercount, a.availableborrowcountnumber as computermodelavailableborrowcountnumber, a.description as computermodeldescription, a.status as computermodelstatus, a.hourrentprice as computermodelhourrentprice, b.id as computercategoryid, b.computercategorytype as computercategorycomputercategorytype, b.languagetype as computercategorylanguagetype, b.parentcomputercategoryid as computercategoryparentcomputercategoryid, b.name as computercategoryname, b.createtime as computercategorycreatetime, b.createuserid as computercategorycreateuserid, b.status as computercategorystatus from Computermodel a " +
			" left join Computercategory b on a.computercategoryid=b.computercategorytype ";
	
	private final String basicComputermodelSql = "From Computermodel  as a ";
	
	private final String countSql = "select count(*) from Computermodel as a ";
	
	
	/**
	 * 根据分类类型查询模型
	*/
	@Override
	public List<Computermodel> selByCategorytype(int categoryType,int language){
		String sqlch = " where a.status >=0 " +
						  " and a.languagetype="+language+
						  " and a.computercategoryid = "+categoryType+
						  " order by a.computermodeltype,a.languagetype";		
		ArrayList<Computermodel> list  = (ArrayList<Computermodel>) selectComputermodelByCondition(sqlch );
		if(list == null){
			list = new ArrayList<Computermodel>();
		}
		
		return list;
	}
	
	@Override
	public List<ComputermodelFull> selFullByCategorytype(int categoryType,int language){
		String sqlch = " where a.status >=0 " +
						  " and a.languagetype="+language+
						  " and a.computercategoryid = "+categoryType+
						  " order by a.computermodeltype,a.languagetype";		
		ArrayList<ComputermodelFull> list  = (ArrayList<ComputermodelFull>) selectComputermodelFullByCondition(sqlch );
		if(list == null){
			list = new ArrayList<ComputermodelFull>();
		}
		
		return list;
	}

	/**
	 * 根据模型类型查询模型
	 * 
	*/
	@Override
	public List<Computermodel> selByModeltype(int modeltype,int language){
		String sqlch = " where a.status >=0 " +
						  " and a.languagetype="+language+
						  " and a.computermodeltype = "+modeltype+
						  " order by a.computermodeltype,a.languagetype";		
		ArrayList<Computermodel> list  = (ArrayList<Computermodel>) selectComputermodelByCondition(sqlch );
		if(list == null){
			list = new ArrayList<Computermodel>();
		}
		
		return list;
	}
	
	/**
	 * 根据模型类型查询模型
	 * 
	*/
	@Override
	public List<Computermodel> selByModeltypeList(List<Integer> modeltypeList,int language){
		if(modeltypeList==null || modeltypeList.size()==0){
			return new ArrayList<Computermodel>();
		}
		
		String insql=" ( -100 ";
		for(Integer type : modeltypeList){
			insql+=","+type;
		}
		insql+=")";
		String sqlch = " where a.status >=0 " +
						  " and a.languagetype="+language+
						  " and a.computermodeltype in "+insql+
						  " order by a.computermodeltype,a.languagetype";		
		ArrayList<Computermodel> list  = (ArrayList<Computermodel>) selectComputermodelByCondition(sqlch );
		if(list == null){
			list = new ArrayList<Computermodel>();
		}
		
		return list;
	}
	
	@Override
	public int countRow(String condition) {
		Long count = (Long)this.getSession()
         .createQuery(countSql + condition)
         .uniqueResult();
		return count.intValue();
		
	}
	
//	构建某一个型号，某一时段 某一天的可借数量         天的长度是可提前预约的天数（预约n天内的PC）
	public 	HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> computermodelPeriodDayInfo(int currentPeriod , List<Borrowperiod> borrowperiodList,int computeroderadvanceorderday){

		HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();

		
		List<Computermodel> computermodelList = new ArrayList<Computermodel>();
		List<ComputermodelFull> computermodelFullList = new ArrayList<ComputermodelFull>();
		
//		查找所有的模型
		String getAllComputermodelTypeSql = " where a.languagetype="+CommonConfig.languagech+" ";
		computermodelList = selectComputermodelByCondition(getAllComputermodelTypeSql);
		
//		初始化每个型号每个时段每天可借数量	
		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
			Computermodel tempmodel =  computermodelList.get(tempmodelindex);//full list已经赋值
			HashMap<Integer,ArrayList<Integer>> periodDayAvailInfo = new HashMap<Integer,ArrayList<Integer>>();
			for(int tempperiod=0; tempperiod < borrowperiodList.size(); tempperiod++){
				Borrowperiod tempBorrowperiod = borrowperiodList.get(tempperiod);
				ArrayList<Integer> dayInfo = new ArrayList<Integer>();
				
				//对于今天过去的时间段处理
				int todaynum = 0;
//				System.out.println("period "+tempBorrowperiod.getPeriodnum());
				if(tempBorrowperiod.getPeriodnum() < currentPeriod ){
					todaynum = 0;
				}else{
//					todaynum = tempmodelFull.getComputermodelavailableborrowcountnumber();
					todaynum = tempmodel.getAvailableborrowcountnumber();
				}
				dayInfo.add(todaynum);
				
				
				for(int tempday=1; tempday < computeroderadvanceorderday; tempday++){	
					dayInfo.add( tempmodel.getAvailableborrowcountnumber());
				}				
				periodDayAvailInfo.put(tempBorrowperiod.getId(), dayInfo);
			}
//			availableBorrowModelMap.put(tempmodelFull.getComputermodelcomputermodeltype(), periodDayAvailInfo);
			availableBorrowModelMap.put(tempmodel.getComputermodeltype(), periodDayAvailInfo);
		}
		
		return availableBorrowModelMap;
	}
	
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computermodel> selectComputermodelByCondition(String condition) {
		final String  sql = basicComputermodelSql +" " + condition;
		
		try {
             List l = this.getHibernateTemplate().find(sql);
			 return l;
        } catch (RuntimeException re) {
            log.error("失败", re);
            throw re;
        }
	}
	
	
	//  根据条件分页查询实体        
        @Override
        public List<Computermodel>  selectComputermodelByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputermodelSql  +conditionSql;
              try {
	        
	         Query q = this.getSession().createQuery(sql).setFirstResult(page.getPageNo()).setMaxResults(page.getPageSize());
	         
	         List l = q.list(); 
			 return l;
	      } catch (RuntimeException re) {
	         log.error("查询失败", re);
	         throw re;
	      }
        }
	
	
	//条件查询full
	@Override
	public List<ComputermodelFull> selectComputermodelFullByCondition(String condition) {
		final String  sql = basicComputermodelFullSql +" " + condition;
		System.out.println(sql);
		List<ComputermodelFull> computermodelList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});		
		return computermodelList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputermodelFull>  selectComputermodelFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputermodelFullSql  +conditionSql;
                List<ComputermodelFull> computermodelList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputermodelFull.class));
                                                return query.list();
                                        }
                                });
                if (computermodelList != null && !computermodelList.isEmpty()) {
                        return computermodelList;
                }
                return null;
        }
	
	
	


//  根据实体id查询实体full	
	@Override
	public ComputermodelFull selectComputermodelFullById(Integer computermodelId) {
		final String  sql =  basicComputermodelFullSql + " where a.id = "+computermodelId;
		
		List<ComputermodelFull> computermodelList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});
		if (computermodelList != null && !computermodelList.isEmpty()) {
			return computermodelList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputermodelFull> selectComputermodelFullAll() {
		final String  sql = basicComputermodelFullSql;
		
		List<ComputermodelFull> computermodelFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});
		if (computermodelFullList != null && !computermodelFullList.isEmpty()) {			
			return computermodelFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputermodelFull> selectComputermodelFullByPage(final Page page){
		final String  sql = basicComputermodelFullSql;
		
		List<ComputermodelFull> computermodelFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputermodelFull.class));
						return query.list();
					}
				});
		if (computermodelFullList != null && !computermodelFullList.isEmpty()) {			
			return computermodelFullList;
		}
		return null;
	}


 
//  删除实体
//	public int deleteEntity(Integer computermodelId) {
//        try {
//        	String hqlString="update Computermodel as tb set tb.status = -1 where tb.id = " +computermodelId; 
//        	Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hqlString);    	   	
//        	int ret=query.executeUpdate();
//
//            log.debug("删除:"+ret);
//            return ret;
//        } catch (RuntimeException re) {
//            log.error("删除失败", re);
//            throw re;
//        }
//	}
	
	public int delByType(Integer modeltype) {
		try {
			String hqlString = "update Computermodel as tb set tb.status = -1 where tb.computermodeltype = "
					+ modeltype;
			Query query = this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(hqlString);
			int ret = query.executeUpdate();

			log.debug("删除:" + ret);
			return ret;
		} catch (RuntimeException re) {
			log.error("删除失败", re);
			throw re;
		}
	}
	
	@Override
	public int delByType(List<Integer> modeltypeList){
		String sqlStr = "(-100";
		for(int type : modeltypeList){
			sqlStr +=","+type;
		}
		sqlStr+=")";
		try {
			String hqlString = "update Computermodel as tb set tb.status = -1 where tb.computermodeltype in "
					+ sqlStr;
			Query query = this.getHibernateTemplate().getSessionFactory()
					.getCurrentSession().createQuery(hqlString);
			int ret = query.executeUpdate();

			log.debug("删除:" + ret);
			return ret;
		} catch (RuntimeException re) {
			log.error("删除失败", re);
			throw re;
		}
	}
	
	@Override
	public int realDelByType(Integer modeltype){
		
			String sql = "delete from Computermodel where computermodeltype="+modeltype;
			this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).executeUpdate();

		return 1;
	}
	


}