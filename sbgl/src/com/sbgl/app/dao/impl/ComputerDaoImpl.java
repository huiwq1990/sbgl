package com.sbgl.app.dao.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.DaoAbs;

import com.sbgl.app.dao.ComputerDao;
import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.app.entity.Computermodel;
import com.sbgl.util.*;

@Repository("computerDao")
public class ComputerDaoImpl extends HibernateDaoSupport implements ComputerDao{

	private static final Log log = LogFactory.getLog(ComputerDaoImpl.class);
	//关联多个表，需要设置join id
	private final String basicComputerFullSql = "select a.id as computerid, a.serialnumber as computerserialnumber, a.computertype as computercomputertype, a.languagetype as computerlanguagetype, a.computermodelid as computercomputermodelid, a.createtime as computercreatetime, a.createuserid as computercreateuserid, a.status as computerstatus, a.remark as computerremark, a.computerstatusid as computercomputerstatusid, b.id as computermodelid, b.computermodeltype as computermodelcomputermodeltype, b.languagetype as computermodellanguagetype, b.name as computermodelname, b.computercategoryid as computermodelcomputercategoryid, b.picpath as computermodelpicpath, b.createtime as computermodelcreatetime, b.createuserid as computermodelcreateuserid, b.computercount as computermodelcomputercount, b.availableborrowcountnumber as computermodelavailableborrowcountnumber, b.description as computermodeldescription, b.status as computermodelstatus, c.id as computerstatusid, " +
			"c.name as computerstatusname from Computer a  left join Computermodel b on a.computermodelid=b.computermodeltype " +
			"left join Computerstatus c on a.computerstatusid=c.id ";
	
	private final String basicComputerSql = "From Computer  as a ";
	
	@Override
	public int countRow(String sql){
		return getHibernateTemplate().find(this.basicComputerSql+" "+sql).size();
	}
	
	
	@Override
	public List<Computer> selByModeltype(int modeltype, int language) {
		String sql = "  where a.status >=0 " +
		" and a.languagetype="+language + 
		" and a.computermodelid ="+modeltype;

		return this.selectComputerByCondition(sql);
	}
	
	@Override
	public List<ComputerFull> selFullByModeltype(int modeltype, int language) {
		String sql = "  where a.status >=0 " +
		" and a.languagetype="+language + 
		" and a.languagetype="+language + 
		" and a.computermodelid ="+modeltype;

		return this.selectComputerFullByCondition(sql);
	}
	
	@Override
	public List<Computer> selByModeltype(List<Integer> modeltypeList, int language) {
		String modeltypeInSql = "  a.computermodelid in (";
		for(int modeltype : modeltypeList){
			modeltypeInSql += modeltype + ",";
		}
		modeltypeInSql = modeltypeInSql.substring(0,modeltypeInSql.length()-1);
		modeltypeInSql += ")";

		String sql = "  where a.status >=0 " +
						" and a.languagetype="+language + 
						" and "+modeltypeInSql;
		
		return this.selectComputerByCondition(sql);
	}
	
	@Override
	public  List<ComputerFull> selFullByModeltype(List<Integer> modeltypeList,int language){
		String modeltypeInSql = "  a.computermodelid in (";
		for(int modeltype : modeltypeList){
			modeltypeInSql += modeltype + ",";
		}
		modeltypeInSql = modeltypeInSql.substring(0,modeltypeInSql.length()-1);
		modeltypeInSql += ")";

		String sql = "  where a.status >=0 " +
						" and a.languagetype="+language + 
						" and b.languagetype="+language + 
						" and "+modeltypeInSql;
		
		return this.selectComputerFullByCondition(sql);
	}
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computer> selectComputerByCondition(String condition) {
		final String  sql = basicComputerSql +" " + condition;
		
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
        public List<Computer>  selectComputerByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerSql  +conditionSql;
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
	public List<ComputerFull> selectComputerFullByCondition(String condition) {
		final String  sql = basicComputerFullSql +" " + condition;
		
		List<ComputerFull> computerList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});		
		return computerList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerFull>  selectComputerFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerFullSql  +conditionSql;
                List<ComputerFull> computerList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerFull.class));
                                                return query.list();
                                        }
                                });
                if (computerList != null && !computerList.isEmpty()) {
                        return computerList;
                }
                return computerList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computer as tb set tb.status = 0 where tb.id = " +computerId; 
        	Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hqlString);    	   	
        	int ret=query.executeUpdate();

            log.debug("删除:"+ret);
            return ret;
        } catch (RuntimeException re) {
            log.error("删除失败", re);
            throw re;
        }
	}

//  根据实体id查询实体full	
	@Override
	public ComputerFull selectComputerFullById(Integer computerId) {
		final String  sql =  basicComputerFullSql + " where a.id = "+computerId;
		
		List<ComputerFull> computerList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerList != null && !computerList.isEmpty()) {
			return computerList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerFull> selectComputerFullAll() {
		final String  sql = basicComputerFullSql;
		
		List<ComputerFull> computerFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerFullList != null && !computerFullList.isEmpty()) {			
			return computerFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerFull> selectComputerFullByPage(final Page page){
		final String  sql = basicComputerFullSql;
		
		List<ComputerFull> computerFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerFull.class));
						return query.list();
					}
				});
		if (computerFullList != null && !computerFullList.isEmpty()) {			
			return computerFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computer> selectComputerByComputermodelId(Integer computermodelid ){
	
		return null;
	}
  

	public List<ComputerFull> selectComputerFullByComputermodelId(Integer computermodelid ){
	
		return null;
	}

 
}