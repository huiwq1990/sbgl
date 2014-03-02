package com.sbgl.app.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.OrderFinishDao;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.util.DateUtil;
import com.sbgl.util.EscColumnToBean;

@Repository("orderFinishDao")
public class OrderFinishDaoImpl extends HibernateDaoSupport implements OrderFinishDao {
	
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId){
		
		final String sql = " select a.*,b.name as userName,c.name as teacherName,d.name as examuserName from EquipmenBorrow a left outer join loginUser b on a.userid = b.id "
			+ " left outer join loginUser c on c.id=a.teacherid "
			+ " left outer join loginUser d on d.id=a.examuser "
			+ " where a.Borrowid='"+borrowId+"' ";
		List<EquipmenborrowFull> equipmenborrowFullList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
				return query.list();
			}
		});	
		if(equipmenborrowFullList!=null&&!equipmenborrowFullList.isEmpty()){
			return equipmenborrowFullList.get(0);
		}	
		return null;
	}
	
	public List<EquipmentFull> findListBorrow(Integer borrowId) {
		// TODO Auto-generated method stub
		final String sql = " select a.*,b.applynumber,c.name as categoryName from Equipment a left outer join ListDetail b on a.equipmentid = b.equipmentid "
			+ " left outer join EquipmentClassification c on c.classificationid=a.classificationid "
			+ " where b.borrowlistid='"+borrowId+"' ";
		List<EquipmentFull> equipmentFullList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentFullList!=null&&!equipmentFullList.isEmpty()){
			return equipmentFullList;
		}	
		return null;
	}
	
	public EquipmentFull findEquipmentById(Integer equipmentId) {
		// TODO Auto-generated method stub
		final String sql = " select a.Equipmentid,a.Equipmentname,c.name as categoryName from Equipment a  "
	    	+ " left outer join EquipmentClassification c on a.classificationid = c.classificationid "
			+ " where a.equipmentid ='"+equipmentId+"' ";
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			return equipmentList.get(0); 
		}	
		return null;
	}

	@Override
	public List<Equipmentclassification> findclassList(Integer borrowId) {
		// TODO Auto-generated method stub
		final String sql = " select distinct a.* from EquipmentClassification a left outer join Equipment b on a.classificationid = b.classificationid " +
				" left outer join ListDetail c on b.equipmentid=c.equipmentid where c.borrowlistid='"+borrowId+"' ";
		List<Equipmentclassification> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentclassification.class); 
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			return equipmentList; 
		}
		return null;
	}

	@Override
	public Map<Integer, List<EquipmentFull>> findMapBorrow(Integer borrowId) {
		// TODO Auto-generated method stub
		Map<Integer, List<EquipmentFull>> map = new HashMap<Integer, List<EquipmentFull>>();
		final String sql = " select distinct a.* from EquipmentClassification a left outer join Equipment b on a.classificationid = b.classificationid " +
		" left outer join ListDetail c on b.equipmentid=c.equipmentid where c.borrowlistid='"+borrowId+"' ";
		List<Equipmentclassification> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentclassification.class); 
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			for(Equipmentclassification equipmentclassification:equipmentList){
				String sql1 = " select a.*,b.applynumber,c.name as categoryName,b.equipDetailids,b.listdetailid from Equipment a left outer join ListDetail b on a.equipmentid = b.equipmentid "
					+ " left outer join EquipmentClassification c on c.classificationid=a.classificationid "
					+ " where b.borrowlistid='"+borrowId+"' and c.classificationid='"+equipmentclassification.getClassificationid()+"' ";
				final String sql2 = sql1;
				List<EquipmentFull> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException{
						Query query = session.createSQLQuery(sql2); 
						query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
						return query.list();
					}
				});	
				if(list!=null&&!list.isEmpty()){
					for(int i =0;i<list.size();i++){
						EquipmentFull equipmentFull = list.get(i);
						if(equipmentFull.getEquipDetailids()!=null&&!equipmentFull.getEquipDetailidlist().equals("")){
							equipmentFull.setEquipDetailidlist(Arrays.asList(equipmentFull.getEquipDetailids().split(",")));
							list.set(i, equipmentFull);
						}
					}
					map.put(equipmentclassification.getClassificationid(), list);
				}
			}
			return map;
		}
		return null;
	}

}
