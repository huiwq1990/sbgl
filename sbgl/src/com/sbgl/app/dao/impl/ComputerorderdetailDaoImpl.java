package com.sbgl.app.dao.impl;


import java.io.Serializable;
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

import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.*;

@Repository("computerorderdetailDao")
public class ComputerorderdetailDaoImpl extends HibernateDaoSupport implements ComputerorderdetailDao{

	private static final Log log = LogFactory.getLog(ComputerorderdetailDaoImpl.class);
	private final String basicComputerorderdetailFullSql = "select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computermodelid as computerorderdetailcomputermodelid, a.borrownumber as computerorderdetailborrownumber, a.createtime as computerorderdetailcreatetime, a.borrowday as computerorderdetailborrowday, a.borrowperiod as computerorderdetailborrowperiod, a.computerid as computerorderdetailcomputerid, a.status as computerorderdetailstatus, b.id as computerorderid, b.serialnumber as computerorderserialnumber, b.userid as computerorderuserid, b.createtime as computerordercreatetime, b.status as computerorderstatus, c.id as computermodelid, c.computermodeltype as computermodelcomputermodeltype, c.languagetype as computermodellanguagetype, c.name as computermodelname, c.computercategoryid as computermodelcomputercategoryid, c.picpath as computermodelpicpath, c.createtime as computermodelcreatetime, c.createuserid as computermodelcreateuserid, c.computercount as computermodelcomputercount, c.availableborrowcountnumber as computermodelavailableborrowcountnumber, c.description as computermodeldescription, c.status as computermodelstatus, d.id as computerid, d.serialnumber as computerserialnumber, d.computertype as computercomputertype, d.languagetype as computerlanguagetype, d.computermodelid as computercomputermodelid, d.createtime as computercreatetime, d.createuserid as computercreateuserid, d.status as computerstatus, d.remark as computerremark from Computerorderdetail a  left join Computerorder b on a.computerorderid=b.id left join Computerorder c on a.computerorderid=c.id left join Computerorder d on a.computerorderid=d.id ";
	
	private final String basicComputerorderdetailSql = "From Computerorderdetail  ";
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByCondition(String condition) {
		final String  sql = basicComputerorderdetailSql +" " + condition;
		
		System.out.println(sql);
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
        public List<Computerorderdetail>  selectComputerorderdetailByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderdetailSql+" "  +conditionSql;
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
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByCondition(String condition) {
		final String  sql = basicComputerorderdetailFullSql +" " + condition;
		
		List<ComputerorderdetailFull> computerorderdetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});		
		return null;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderdetailFull>  selectComputerorderdetailFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderdetailFullSql  +conditionSql;
                List<ComputerorderdetailFull> computerorderdetailList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerorderdetailFull.class));
                                                return query.list();
                                        }
                                });
                if (computerorderdetailList != null && !computerorderdetailList.isEmpty()) {
                        return computerorderdetailList;
                }
                return null;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerorderdetailId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorderdetail as tb set tb.status = 0 where tb.id = " +computerorderdetailId; 
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
	public ComputerorderdetailFull selectComputerorderdetailFullById(Integer computerorderdetailId) {
		final String  sql =  basicComputerorderdetailFullSql + " where a.id = "+computerorderdetailId;
		
		List<ComputerorderdetailFull> computerorderdetailList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailList != null && !computerorderdetailList.isEmpty()) {
			return computerorderdetailList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerorderdetailFull> selectComputerorderdetailFullAll() {
		final String  sql = basicComputerorderdetailFullSql;
		
		List<ComputerorderdetailFull> computerorderdetailFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailFullList != null && !computerorderdetailFullList.isEmpty()) {			
			return computerorderdetailFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByPage(final Page page){
		final String  sql = basicComputerorderdetailFullSql;
		
		List<ComputerorderdetailFull> computerorderdetailFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderdetailFull.class));
						return query.list();
					}
				});
		if (computerorderdetailFullList != null && !computerorderdetailFullList.isEmpty()) {			
			return computerorderdetailFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computerorderdetail> selectComputerorderdetailByComputerorderId(Integer computerorderid ){
	
		return null;
	}
	public List<Computerorderdetail> selectComputerorderdetailByComputermodelId(Integer computermodelid ){
	
		return null;
	}
	public List<Computerorderdetail> selectComputerorderdetailByComputerId(Integer computerid ){
	
		return null;
	}
  

	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerorderId(Integer computerorderid ){
	
		return null;
	}
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputermodelId(Integer computermodelid ){
	
		return null;
	}
	public List<ComputerorderdetailFull> selectComputerorderdetailFullByComputerId(Integer computerid ){
	
		return null;
	}

 
}