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
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.entity.Listequipdetail;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.DateUtil;
import com.sbgl.util.EscColumnToBean;

@Repository("orderFinishDao")
public class OrderFinishDaoImpl extends HibernateDaoSupport implements OrderFinishDao {
	
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId){
		
		final String sql = " select f.teacherid,a.*,b.name as userName,c.name as teacherName,d.name as examuserName,e.createtime,e.MsgTitle from EquipmenBorrow a left outer join loginUser b on a.userid = b.id "
			+ " left outer join  sendRuleToUser e on a.sendruleid = e.sendruleid "
			+ " left outer join  ordercourserule f on f.courseruleid = e.courseruleid "		
			+ " left outer join loginUser c on c.id=f.teacherid "
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
	
	public List<EquipmentFull> findListBorrow(Integer borrowId,String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select a.*,b.applynumber,c.name as categoryName from Equipment a left outer join ListDetail b on a.comid = b.equipmentid "
			+ " left outer join EquipmentClassification c on c.classificationid=a.comid and c.lantype='"+lantype+"' "
			+ " where b.borrowlistid='"+borrowId+"'  and a.lantype='"+lantype+"' ";
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
	
	public EquipmentFull findEquipmentById(Integer equipmentId,String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select a.Equipmentid,a.Equipmentname,c.name as categoryName,a.imgNameSaved,a.equipmentdetail from Equipment a  "
	    	+ " left outer join EquipmentClassification c on a.classificationid = c.comId  and c.lantype='"+lantype+"' "
			+ " where a.comid ='"+equipmentId+"' and a.lantype='"+lantype+"' ";
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
	public List<Equipmentclassification> findclassList(Integer borrowId,String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select distinct a.* from EquipmentClassification a left outer join Equipment b on a.comid = b.classificationid  and b.lantype='"+lantype+"'  " +
				" left outer join ListDetail c on b.comid=c.equipmentid where c.borrowlistid='"+borrowId+"' and a.lantype='"+lantype+"' ";
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
	public Map<Integer, List<EquipmentFull>> findMapBorrow(Integer borrowId,Integer type,String lantype) {
		// TODO Auto-generated method stub
		Map<Integer, List<EquipmentFull>> map = new HashMap<Integer, List<EquipmentFull>>();
		final String sql = " select distinct a.* from EquipmentClassification a left outer join Equipment b on a.comid  = b.classificationid  and b.lantype='"+lantype+"' " +
		" left outer join ListDetail c on b.comid=c.equipmentid where c.borrowlistid='"+borrowId+"' and a.lantype='"+lantype+"'  ";
		List<Equipmentclassification> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentclassification.class); 
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			for(Equipmentclassification equipmentclassification:equipmentList){
				String sql1 = " select a.*,b.applynumber,c.name as categoryName,b.listdetailid from Equipment a left outer join ListDetail b on a.comid = b.equipmentid and a.lantype='"+lantype+"' "
					+ " left outer join EquipmentClassification c on c.comid=a.classificationid and c.lantype='"+lantype+"' "
					+ " where b.borrowlistid='"+borrowId+"' and c.comid='"+equipmentclassification.getComid()+"' and a.lantype='"+lantype+"' ";
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
						String sqltemp = "";
						if(type==1){
							sqltemp = "  select a.equipDetailid from EquipmentDetail a where a.equipmentid = '"+equipmentFull.getComId()+"' and  a.equipDetailid not in (select Equipdetailid from Listequipdetail b left outer join EquipmenBorrow c on b.borrowlistid = c.borrowid where c.status !='8') ";
						}else{
							sqltemp = " select a.equipDetailid from listequipdetail a where a.equipmentid = '"+equipmentFull.getComId()+"' and  a.borrowlistid='"+borrowId+"' ";
						}
						final String sql3 = sqltemp;
						List<String> list2 = this.getHibernateTemplate().executeFind(new HibernateCallback(){
							public Object doInHibernate(Session session) throws HibernateException{
								Query query = session.createSQLQuery(sql3); 
								return query.list();
							}
						});	
						if(list2!=null&&!list2.isEmpty()){
							equipmentFull.setEquipDetailidlist(list2);
							list.set(i, equipmentFull);
						}	
					}
					map.put(equipmentclassification.getComid(), list);
				}
			}
			return map;
		}
		return null;
	}

	@Override
	public Listdetail findListDetail(Integer borrowId, Integer equipDetailid) {
		// TODO Auto-generated method stub
		String sql1 = " select a.* from ListDetail a left outer join EquipmentDetail b on a.comId = b.equipmentid where a.borrowlistid = '"+borrowId+"' and b.equipDetailid='"+equipDetailid+"' ";
		final String sql2 = sql1;
		List<Listdetail> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql2).addEntity(Listdetail.class); 
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Listequipdetail findlistequipdetail(Integer borrowId, Integer equipDetailid) {
		// TODO Auto-generated method stub
		String sql1 = " select a.* from listequipdetail a where a.borrowlistid = '"+borrowId+"' and a.equipDetailid='"+equipDetailid+"' ";
		final String sql2 = sql1;
		List<Listequipdetail> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql2).addEntity(Listequipdetail.class); 
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Loginuser userdetail(Integer userId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql1 = " select a.* from loginuser a where a.id = '"+userId+"' ";
		final String sql2 = sql1;
		List<Loginuser> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql2); 
				query.setResultTransformer(new EscColumnToBean(Loginuser.class));
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}


}
