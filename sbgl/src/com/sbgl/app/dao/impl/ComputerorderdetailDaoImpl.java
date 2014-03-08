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

import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.common.computer.ComputerorderdetailInfo;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.DaoAbs;

import com.sbgl.app.dao.ComputerorderdetailDao;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.*;

@Repository("computerorderdetailDao")
public class ComputerorderdetailDaoImpl extends HibernateDaoSupport implements ComputerorderdetailDao{

	private static final Log log = LogFactory.getLog(ComputerorderdetailDaoImpl.class);
	private final String basicComputerorderdetailFullSql = 
		"select a.id as computerorderdetailid, a.computerorderid as computerorderdetailcomputerorderid, a.computermodelid as computerorderdetailcomputermodelid, a.borrownumber as computerorderdetailborrownumber, a.createtime as computerorderdetailcreatetime, a.borrowday as computerorderdetailborrowday, a.borrowperiod as computerorderdetailborrowperiod, a.computerid as computerorderdetailcomputerid, a.status as computerorderdetailstatus, b.id as computerorderid, b.serialnumber as computerorderserialnumber, " +
		"b.createuserid as computerordercreateuserid, b.title as computerordertitle, b.ordertype as computerorderordertype, b.createtime as computerordercreatetime, b.remark as computerorderremark, b.status as computerorderstatus," +
		" c.id as computermodelid, c.computermodeltype as computermodelcomputermodeltype, c.languagetype as computermodellanguagetype, c.name as computermodelname, c.computercategoryid as computermodelcomputercategoryid, c.picpath as computermodelpicpath, c.createtime as computermodelcreatetime, c.createuserid as computermodelcreateuserid, c.computercount as computermodelcomputercount, c.availableborrowcountnumber as computermodelavailableborrowcountnumber, c.description as computermodeldescription, c.status as computermodelstatus " +
//		" d.id as computerid, d.serialnumber as computerserialnumber, d.computertype as computercomputertype, d.languagetype as computerlanguagetype, d.computermodelid as computercomputermodelid, d.createtime as computercreatetime, d.createuserid as computercreateuserid, d.status as computerstatus, d.remark as computerremark, d.computerstatusid as computercomputerstatusid " +
		"from Computerorderdetail a  left join Computerorder b on a.computerorderid=b.id " +
		"left join Computermodel c on a.computermodelid=c.computermodeltype" ;//+
//		" left join Computer d on a.computerid=d.computertype ";
	
	private final String basicComputerorderdetailSql = "From Computerorderdetail as a ";
	
	private final String basicComputerorderdetailDelSql = "delete from  Computerorderdetail as a ";
	/*
    @Override
    public List<Computerorderdetail> selectComputerorderdetailAfterNow(String currentDay,int currentPeriod){

            int computerorderTotalOrderDay = ComputerConfig.computeroderadvanceorderday;
//            int computerorderTotalOrderPeriod = ComputerConfig.computerorderTotalOrderPeriod;
            Date curDate = DateUtil.parseDate(currentDay);
            Date endDate = DateUtil.addDay(curDate, computerorderTotalOrderDay);
            String endate = DateUtil.dateFormat(endDate,DateUtil.dateformatstr1);
            String cond = "where ( (borrowday = '" + currentDay+"' and borrowperiod >="+currentPeriod+") or ";
            cond +=  "             ((borrowday > '" + currentDay+"') and (borrowday < '" + endate+"') )";
            cond +=  "            ) and ";
            cond +=  "            ( status in ("+ComputerorderdetailInfo.ComputerorderdetailStatusAduitPass+","+ComputerorderdetailInfo.ComputerorderdetailStatusAduitWait+") ) ";
//            cond = " ";
    System.out.println(cond);
            return computerorderdetailDao.selectComputerorderdetailByCondition(cond);

    }
	*/
	
	/**
	 * 彻底删除某一时间段的课程预约
	 * @param computerorderid
	 */
	@Override
	public void delByPeriod(int computerorderid,String borrowday,int period){
		String sql = " delete from Computerorderdetail where computerorderid = "+computerorderid + "  and borrowday='"+borrowday+"' and borrowperiod="+period;
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
	 * 强制使某一天某个器材不能预约
	 * 需要指定器材及时间
	 * 彻底删除某一时间段的关于某个模型的预约，不管这一事件有多少个预约都要删除
	 * @param computerorderid
	 */
	@Override
	public void delByPeriodComputermodeltype(String borrowday,int period,int computermodeltype){
		String sql = " delete from Computerorderdetail where  computermodelid = "+computermodeltype+"borrowday='"+borrowday+"' and borrowperiod="+period;
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
	 * 彻底删除订单详情信息
	 * @param computerorderid
	 */
	@Override
	public void delByComputerorderid(int computerorderid){
		String sql = " delete Computerorderdetail where computerorderid = "+computerorderid;
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
	 * 查询某个天有效的订单详情
	 */
	@Override
	public List<Computerorderdetail> selectComputerorderByDate(Date queryDate) {
		
		String dateStr= DateUtil.dateFormat(DateUtil.getDateDayDate(queryDate), DateUtil.dateformatstr1);
		
		  String cond = "where ( " ;
		  cond += " borrowday = '" + dateStr+"' ";//今天当前时段之后的
           cond +=  "           ) and ";
           cond +=  "            ( status in ("+ComputerorderdetailInfo.ComputerorderdetailStatusAduitPass+","+ComputerorderdetailInfo.ComputerorderdetailStatusAduitWait+") ) ";
           
          log.info(cond);
          return selectComputerorderdetailByCondition(cond);
		
		 
	}
	
	/**
	 * 查询某个时间段内有效的订单详情
	 */
    @Override
	public List<Computerorderdetail> selectValidComputerorderdetailFromStartToEnd(Date startDate, int startPeriod, Date endDate, int endPeriod){
    	
//		日期格式化为 yyyy-MM-dd 00:00:00
		String startDateStr= DateUtil.dateFormat(DateUtil.getDateDayDate(startDate), DateUtil.dateformatstr1);
    	String endDateStr = DateUtil.dateFormat(DateUtil.getDateDayDate(endDate), DateUtil.dateformatstr1);
        
            String cond = "where ( (borrowday = '" + startDateStr+"' and borrowperiod >="+startPeriod+") or ";//今天当前时段之后的
            cond +=  "             ((borrowday > '" + startDateStr+"') and (borrowday < '" + endDateStr+"'))  or";//明天到最后一天前之前的
            cond +=  "             ((borrowday = '" + endDateStr+"') and (borrowperiod <= '" + endPeriod+"') )";//最后一天时间小于
            cond +=  "           ) and ";
            cond +=  "            ( status in ("+ComputerorderdetailInfo.ComputerorderdetailStatusAduitPass+","+ComputerorderdetailInfo.ComputerorderdetailStatusAduitWait+") ) ";
//            cond = " ";
            log.info(cond);
            return selectComputerorderdetailByCondition(cond);

    }
    
    @Override
	public List<Computerorderdetail> selectValidComputerorderdetailFromStartToEndByModel(Date startDate, int startPeriod, Date endDate, int endPeriod,String modeltypeStr){
    	
//		日期格式化为 yyyy-MM-dd 00:00:00
		String startDateStr= DateUtil.dateFormat(DateUtil.getDateDayDate(startDate), DateUtil.dateformatstr1);
    	String endDateStr = DateUtil.dateFormat(DateUtil.getDateDayDate(endDate), DateUtil.dateformatstr1);
        
            String cond = "where ( (borrowday = '" + startDateStr+"' and borrowperiod >="+startPeriod+") or ";//今天当前时段之后的
            cond +=  "             ((borrowday > '" + startDateStr+"') and (borrowday < '" + endDateStr+"'))  or";//明天到最后一天前之前的
            cond +=  "             ((borrowday = '" + endDateStr+"') and (borrowperiod <= '" + endPeriod+"') )";//最后一天时间小于
            cond +=  "           ) and ";
            cond +=  "            ( status in ("+ComputerorderdetailInfo.ComputerorderdetailStatusAduitPass+","+ComputerorderdetailInfo.ComputerorderdetailStatusAduitWait+") ) ";
            cond += "                 and";
            cond += "              (computermodelid in ("+modeltypeStr+") )";
            log.info(cond);
            return selectComputerorderdetailByCondition(cond);

    }
	
	// 根据条件查询查询实体
	@Override
	public List<Computerorderdetail> selectComputerorderdetailByCondition(String condition) {
		final String  sql = basicComputerorderdetailSql +" " + condition;
		
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
                final String  sql = basicComputerorderdetailSql  +conditionSql;
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
		return computerorderdetailList;
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
                return computerorderdetailList;
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
	
	@Override
	public int deleteComputerorderdetailByCondition(String condition) {
		// TODO Auto-generated method stub		
//		log.debug("正在删除");
        try {
        	String hqlString=basicComputerorderdetailDelSql + " " +condition;  
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