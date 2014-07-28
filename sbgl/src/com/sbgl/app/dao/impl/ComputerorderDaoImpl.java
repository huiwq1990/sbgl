package com.sbgl.app.dao.impl;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.teach.TeachConstant;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.DaoAbs;

import com.sbgl.app.dao.ComputerorderDao;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.util.*;

@Repository("computerorderDao")
public class ComputerorderDaoImpl extends HibernateDaoSupport implements ComputerorderDao{

	private static final Log log = LogFactory.getLog(ComputerorderDaoImpl.class);
	private final String basicComputerorderFullSql = "select a.id as computerorderid, a.serialnumber as computerorderserialnumber, a.createuserid as computerordercreateuserid, a.title as computerordertitle, a.ordertype as computerorderordertype, a.createtime as computerordercreatetime, a.remark as computerorderremark, a.rejectreason as computerorderrejectreason, a.computerhomeworkid as computerordercomputerhomeworkid, a.audituserid as computerorderaudituserid, a.status as computerorderstatus, b.id as createuserid, b.userId as createuseruserid, b.name as createusername, b.roletype as createuserroletype, b.privilege as createuserprivilege, b.password as createuserpassword, c.id as audituserid, c.userId as audituseruserid, c.name as auditusername, c.roletype as audituserroletype, c.privilege as audituserprivilege, c.password as audituserpassword, d.id as computerhomeworkid, d.name as computerhomeworkname, d.computerorderclassruleid as computerhomeworkcomputerorderclassruleid, d.content as computerhomeworkcontent, d.createuserid as computerhomeworkcreateuserid, d.attachment as computerhomeworkattachment, d.status as computerhomeworkstatus, d.createtime as computerhomeworkcreatetime from Computerorder a  left join Loginuser b on a.createuserid=b.id left join Loginuser c on a.audituserid=c.id left join Computerhomework d on a.computerhomeworkid=d.id ";
	
	
	private final String basicComputerorderSql = "From Computerorder as a ";
	

	
	public void selByWeek(){
		
	}
	
	
	/**
	 * 彻底删除订单信息
	 * @param computerorderid 
	 */
	@Override
	public void delById(int computerorderid){
		String sql = " delete Computerorder where id = "+computerorderid;
		try {
			Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	         Query query = session.createSQLQuery(sql);
			 query.executeUpdate();
		} catch (RuntimeException re) {
	        log.error("查询失败", re);           
	        throw re;
	            
	   }
	}
	
	/**
	 * 获取订单信息
	 * @param computerorderid 
	 */
	@Override
	public Computerorder selectById(int computerorderid){
		final String  sql = basicComputerorderSql +" where id= " + computerorderid;
		
		try {
             List l = this.getHibernateTemplate().find(sql);
             if(l!=null || l.size()!=0){
            	 return (Computerorder) l.get(0);
             }
			 return null;
        } catch (RuntimeException re) {
            log.error("失败", re);
            throw re;
        }
	}
	
	/**
	 * 查询进行中的预约
	 * @param uid
	 * @return
	 */
	@Override
	public List<ComputerorderFull> setUnderwayComputerorder(int uid){
		String selunderwayordersql = "  where a.createuserid="+uid + " and a.status in("+ComputerorderInfo.ComputerorderStatusAduitWait+","+ComputerorderInfo.ComputerorderStatusAduitReject+") order by a.createtime desc";
		List<ComputerorderFull> computerorderFullUnderwayList = selectComputerorderFullByCondition(selunderwayordersql);
		return computerorderFullUnderwayList;
		
	}

	@Override
	public List<ComputerorderFull> selFullByStatus(int orderstatus){
//		String selunderwayordersql = "  where a.createuserid="+uid + " and a.status in("+ComputerorderInfo.ComputerorderStatusAduitWait+","+ComputerorderInfo.ComputerorderStatusAduitReject+") order by a.createtime desc";
		String selbystatus = "";
		if(orderstatus==0){
			selbystatus = " order by a.createtime desc";
		}else{
			selbystatus = " where  a.status ="+orderstatus+" order by a.createtime desc";
		}
		
		List<ComputerorderFull> computerorderFullUnderwayList = selectComputerorderFullByCondition(selbystatus);
		return computerorderFullUnderwayList;		
	}	
	
	/**
	 * 查询的预约
	 * @param uid
	 * @return
	 */
	@Override
	public List<ComputerorderFull> selFullByStatus(int uid,int orderstatus){
//		String selunderwayordersql = "  where a.createuserid="+uid + " and a.status in("+ComputerorderInfo.ComputerorderStatusAduitWait+","+ComputerorderInfo.ComputerorderStatusAduitReject+") order by a.createtime desc";
		String selbystatus = " where a.createuserid="+uid + " and a.status ="+orderstatus+" order by a.createtime desc";
		
		List<ComputerorderFull> computerorderFullUnderwayList = selectComputerorderFullByCondition(selbystatus);
		return computerorderFullUnderwayList;	
	}	
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorder> selectComputerorderByCondition(String condition) {
		final String  sql = basicComputerorderSql +" " + condition;
		
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
        public List<Computerorder>  selectComputerorderByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderSql  +conditionSql;
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
	public List<ComputerorderFull> selectComputerorderFullByCondition(String condition) {
		final String  sql = basicComputerorderFullSql +" " + condition;
		
		List<ComputerorderFull> computerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});		
		return computerorderList;
	}
	
	
	// 查询实体full        
        @Override
        public List<ComputerorderFull>  selectComputerorderFullByConditionAndPage(String conditionSql,final Page page) {
                final String  sql = basicComputerorderFullSql  +conditionSql;
                List<ComputerorderFull> computerorderList = getHibernateTemplate()
                                .executeFind(new HibernateCallback() {
                                        public Object doInHibernate(Session session)
                                                        throws HibernateException {
                                                        
                                                        //Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
                                                Query query = session.createSQLQuery(sql);
                                                query.setFirstResult(page.getStartNum());
                                                query.setMaxResults(page.getPageSize());
                                                query.setResultTransformer(new EscColumnToBean(
                                                                ComputerorderFull.class));
                                                return query.list();
                                        }
                                });
                if (computerorderList != null && !computerorderList.isEmpty()) {
                        return computerorderList;
                }
                return computerorderList;
        }
	
	
	
//  删除实体
	public int deleteEntity(Integer computerorderId) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString="update Computerorder as tb set tb.status = 0 where tb.id = " +computerorderId; 
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
	public ComputerorderFull selectComputerorderFullById(Integer computerorderId) {
		final String  sql =  basicComputerorderFullSql + " where a.id = "+computerorderId;
		
		List<ComputerorderFull> computerorderList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
							
							//Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(YaomingFull.class));  
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});
		if (computerorderList != null && !computerorderList.isEmpty()) {
			return computerorderList.get(0);
		}
		return null;
	}
	
//	查询全部实体full
	@Override
	public List<ComputerorderFull> selectComputerorderFullAll() {
		final String  sql = basicComputerorderFullSql;
		
		List<ComputerorderFull> computerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});
		if (computerorderFullList != null && !computerorderFullList.isEmpty()) {			
			return computerorderFullList;
		}
		return null;
	}
//  分页查询 实体full
	public List<ComputerorderFull> selectComputerorderFullByPage(final Page page){
		final String  sql = basicComputerorderFullSql;
		
		List<ComputerorderFull> computerorderFullList = getHibernateTemplate()
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createSQLQuery(sql);
						query.setFirstResult(page.getStartNum());
						query.setMaxResults(page.getPageSize());
						query.setResultTransformer(new EscColumnToBean(
								ComputerorderFull.class));
						return query.list();
					}
				});
		if (computerorderFullList != null && !computerorderFullList.isEmpty()) {			
			return computerorderFullList;
		}
		return null;
	}

}