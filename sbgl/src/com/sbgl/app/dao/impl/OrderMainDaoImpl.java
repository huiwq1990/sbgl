package com.sbgl.app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.DateUtil;
import com.sbgl.util.EscColumnToBean;

@Repository("orderMainDao")
public class OrderMainDaoImpl extends HibernateDaoSupport implements OrderMainDao {

	@Override
	public List<Equipmentnum> findEquipmentnum(Integer equipmentid,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		final String sql = " select * from EquipmentNum a where a.equipmentid='"+equipmentid+"' "
			+ " and a.enumdate>='"+startDate+"' and a.enumdate<='"+endDate+"' ";
		List<Equipmentnum> equipmentnumList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentnum.class);
				return query.list();
			}
		});	
		if(equipmentnumList!=null&&!equipmentnumList.isEmpty()){
			return equipmentnumList;
		}	
		return null;
	}

	@Override
	public boolean updateEquipmentnum(Integer equipmentid,String startDate,String endDate, Integer num) {
		// TODO Auto-generated method stub
		final String sql = " select * from EquipmentNum a where a.equipmentid='"+equipmentid+"' "
			+ " and a.enumdate>='"+startDate+"' and a.enumdate<='"+endDate+"' ";
		List<Equipmentnum> equipmentnumList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentnum.class);
				return query.list();
			}
		});	
		if(equipmentnumList!=null&&!equipmentnumList.isEmpty()){
			for(Equipmentnum equipmentnum:equipmentnumList){
				equipmentnum.setBorrownum(equipmentnum.getBorrownum()-num);
				this.getHibernateTemplate().saveOrUpdate(equipmentnum);
			}
			return true;
		}			
		return false;
	}

	@Override
	public void addEquipmentnum() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Equipmentclassification> findSecondEquipmentclass() {
		// TODO Auto-generated method stub
		final String sql = " select * from Equipmentclassification a where a.classificationid in ( "
			+ " select min(classificationid) from Equipmentclassification where parentid=0) ";
		List<Equipmentclassification> equipmentnumList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentclassification.class);
				return query.list();
			}
		});	
		if(equipmentnumList!=null&&!equipmentnumList.isEmpty()){
			return equipmentnumList;
		}	
		return null;
	}

	@Override
	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,min(( " ;
		    for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select  nvl(a.activenum,0) -sum(nvl(b.applynumber,0)) from ListDetail b " 
	    			+ " where b.equipmentid = a.equipmentid and "+dateTemp+"<=to_char(b.returntime,'day') and "+dateTemp+">=to_char(b.borrowtime,'day') )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")) as borrownum from Equipment a  "
			+ " where a.classificationid in (select min(classificationid) from Equipmentclassification where parentid=0) group by a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark  ";
		
	    final String sql1 = sql;
	    List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			return equipmentList;
		}	
		return null;
	}

	@Override
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,min(( " ;
		    for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select  nvl(a.activenum,0) -sum(nvl(b.applynumber,0)) from ListDetail b " 
	    			+ " where b.equipmentid = a.equipmentid and "+dateTemp+"<=to_char(b.returntime,'day') and "+dateTemp+">=to_char(b.borrowtime,'day') )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")) as borrownum from Equipment a  "
			+ " where a.classificationid in (select min(classificationid) from Equipmentclassification where parentid="+classificationid+") group by a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark  ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			return equipmentList;
		}	
		return null;
	}

	@Override
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
	    String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,min(( " ;
	    	for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select  nvl(a.activenum,0) -sum(nvl(b.applynumber,0)) from ListDetail b " 
	    			+ " where b.equipmentid = a.equipmentid and "+dateTemp+"<=to_char(b.returntime,'day') and "+dateTemp+">=to_char(b.borrowtime,'day') )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")) as borrownum,c.name from Equipment a  "
	    	+ " left outer join EquipmentClassification c on a.classificationid = c.classificationid "
			+ " where a.equipmentid ="+equipmentId+" group by a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,c.name ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			return equipmentList.get(0); 
		}	
		return null;
	}
}
