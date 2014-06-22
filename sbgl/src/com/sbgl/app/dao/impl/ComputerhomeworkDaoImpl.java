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

import com.sbgl.app.dao.ComputerhomeworkDao;
import com.sbgl.app.entity.Computerhomework;
import com.sbgl.app.entity.ComputerhomeworkFull;
import com.sbgl.util.*;

@Repository("computerhomeworkDao")
public class ComputerhomeworkDaoImpl extends HibernateDaoSupport implements ComputerhomeworkDao{

	private static final Log log = LogFactory.getLog(ComputerhomeworkDaoImpl.class);
	private final String basicComputerhomeworkFullSql = "select a.id as computerhomeworkid, a.name as computerhomeworkname, a.computerorderclassruleid as computerhomeworkcomputerorderclassruleid, a.content as computerhomeworkcontent, a.createuserid as computerhomeworkcreateuserid, a.attachment as computerhomeworkattachment, a.status as computerhomeworkstatus, a.createtime as computerhomeworkcreatetime, b.id as computerorderclassruleid, b.name as computerorderclassrulename, b.classname as computerorderclassruleclassname, b.classid as computerorderclassruleclassid, b.orderstarttime as computerorderclassruleorderstarttime, b.orderendtime as computerorderclassruleorderendtime, b.availableordertime as computerorderclassruleavailableordertime, b.createuserid as computerorderclassrulecreateuserid, b.createtime as computerorderclassrulecreatetime, b.status as computerorderclassrulestatus from Computerhomework a  left join Computerorderclassrule b on a.computerorderclassruleid=b.id ";
	
	private final String basicComputerhomeworkSql = "From Computerhomework as a  ";
	
	
	@Override
	public List<Computerhomework> sel(Integer id) {
		String sql = " where status >=0 and id = "+id;
		List l = selectComputerhomeworkByCondition(sql);
//		if(l == null)
		return l;
	}
	
	
	
	// 根据条件查询查询实体
	@Override
	public List<Computerhomework> selectComputerhomeworkByCondition(String condition) {
		final String  sql = basicComputerhomeworkSql +" " + condition;
		
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
        public List<Computerhomework>  selectComputerhomeworkByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerhomeworkSql  +conditionSql;
              try {
	        
	         Query q = this.getSession().createQuery(sql).setFirstResult(page.getPageNo()).setMaxResults(page.getPageSize());
	         
	         List l = q.list(); 
			 return l;
	      } catch (RuntimeException re) {
	         log.error("查询失败", re);
	         throw re;
	      }
        }
	
    @Override
    public List<ComputerhomeworkFull> selFullByList(List<Integer> idList) {

    	if(idList.size()<1){
    		return new ArrayList<ComputerhomeworkFull>();
    	}
    	String sql = "";
    	for(int i : idList){
    		sql +=i+",";
    	}
    	sql = sql.substring(0,sql.length()-1);
    	
    	String r = " where a.id in (" +sql+") "  + " order by computerhomeworkcreatetime desc ";
//    	System.out.println(r);
    	return selectComputerhomeworkFullByCondition(r);
    	
    		
    	
    	
    }
        
	//条件查询full
	@Override
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByCondition(String condition) {
		final String  sql = basicComputerhomeworkFullSql +" " + condition;
		
		List<ComputerhomeworkFull> computerhomeworkList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkFull.class));
						return query.list();
					}
				});		
		return computerhomeworkList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerhomeworkFull>  selectComputerhomeworkFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerhomeworkFullSql  +conditionSql;
                List<ComputerhomeworkFull> computerhomeworkList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerhomeworkFull.class));
                                                return query.list();
                                        }
                                });
                if (computerhomeworkList != null && !computerhomeworkList.isEmpty()) {
                        return computerhomeworkList;
                }
                return computerhomeworkList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerhomeworkId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerhomework as tb set tb.status = 0 where tb.id = " +computerhomeworkId; 
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
	public ComputerhomeworkFull selectComputerhomeworkFullById(Integer computerhomeworkId) {
		final String  sql =  basicComputerhomeworkFullSql + " where a.id = "+computerhomeworkId;
		
		List<ComputerhomeworkFull> computerhomeworkList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkFull.class));
						return query.list();
					}
				});
		if (computerhomeworkList != null && !computerhomeworkList.isEmpty()) {
			return computerhomeworkList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerhomeworkFull> selectComputerhomeworkFullAll() {
		final String  sql = basicComputerhomeworkFullSql;
		
		List<ComputerhomeworkFull> computerhomeworkFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkFull.class));
						return query.list();
					}
				});
		if (computerhomeworkFullList != null && !computerhomeworkFullList.isEmpty()) {			
			return computerhomeworkFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerhomeworkFull> selectComputerhomeworkFullByPage(final Page page){
		final String  sql = basicComputerhomeworkFullSql;
		
		List<ComputerhomeworkFull> computerhomeworkFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerhomeworkFull.class));
						return query.list();
					}
				});
		if (computerhomeworkFullList != null && !computerhomeworkFullList.isEmpty()) {			
			return computerhomeworkFullList;
		}
		return null;
	}
	
//  根据关联查询实体full

	//根据关联查询实体 
	public List<Computerhomework> selectComputerhomeworkByComputerorderclassruleId(Integer computerorderclassruleid ){
	
		return null;
	}
  

	public List<ComputerhomeworkFull> selectComputerhomeworkFullByComputerorderclassruleId(Integer computerorderclassruleid ){
	
		return null;
	}

 
}