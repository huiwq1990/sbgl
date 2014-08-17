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

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.util.DateUtil;
import com.sbgl.util.EscColumnToBean;
import com.sbgl.util.Page;

@Repository("orderMainDao")
public class OrderMainDaoImpl extends HibernateDaoSupport implements OrderMainDao {

	
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

	public void addEquipmentnum() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public List<Equipmentclassification> findSecondEquipmentclass(String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select * from Equipmentclassification a where a.parentid in ( "
			+ " select min(comid) from Equipmentclassification where parentid=0 and lantype='"+lantype+"' ) and a.lantype='"+lantype+"' ";
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

	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,a.comId,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select   sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where  ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum from Equipment a  "
			+ " where a.lanType = '"+lantype+"' and a.classificationid in (select comid from Equipmentclassification where parentid in (select min(comid) from Equipmentclassification "
            + " where parentid = 0 and lanType = '"+lantype+"') union (select min(comid) from Equipmentclassification where parentid = 0  and lanType = '"+lantype+"'))  group by a.comid  ";
		
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
	
	public List<EquipmentFull> findEquipmentByClss2(String fromDate,String endDate,String lantype, Integer courseruleid) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,a.comId,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select sum(ifnull(ifnull(b.borrownumber,b.applynumber),0))  as aaa,b.comId from ListDetail b " 
	    			+ " where  ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum,d.applynumber from Equipment a  left outer join orderCourseRuleDetail d on d.courseRuleId= '"+courseruleid+"' and d.comId = a.comId  "
			+ " where d.comid != '' and a.lanType = '"+lantype+"' and a.classificationid in (select comid from Equipmentclassification where parentid in (select min(comid) from Equipmentclassification "
            + " where parentid = 0) union (select min(comid) from Equipmentclassification where parentid = 0))  group by a.comid  ";
		
	    final String sql1 = sql;
	    List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size2= equipmentList.size();
			for(int i=0;i<size2;i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				if(equipmentFull.getApplynumber()<equipmentFull.getBorrownum()){
					equipmentFull.setBorrownum(Long.valueOf(equipmentFull.getApplynumber()));
					equipmentList.set(i, equipmentFull);
				}
			}
			return equipmentList;
		}	
		return null;
	}

	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,a.comId,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
		    	String dateTemp = dateList.get(i);
	    		sql +="(select  sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum from Equipment a  "
			+ " where a.lanType = '"+lantype+"' and a.classificationid in (select comid from Equipmentclassification where parentid='"+classificationid+"' and lanType = '"+lantype+"' union select comid from Equipmentclassification where comid='"+classificationid+"' and lanType = '"+lantype+"') group by a.comid  ";
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
	
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,String fromDate,String endDate,String lantype, Integer courseruleid) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,a.comId,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
		    	String dateTemp = dateList.get(i);
	    		sql +="(select  sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum ,d.applynumber from Equipment a  left outer join orderCourseRuleDetail d on d.courseRuleId= '"+courseruleid+"' and d.comId = a.comId  "
			+ " where d.comid != '' and a.lanType = '"+lantype+"' and a.classificationid in (select comid from Equipmentclassification where parentid='"+classificationid+"' and lanType = '"+lantype+"' union select comid from Equipmentclassification where comid='"+classificationid+"' and lanType = '"+lantype+"' ) group by a.comid  ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size2= equipmentList.size();
			for(int i=0;i<size2;i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				if(equipmentFull.getApplynumber()<equipmentFull.getBorrownum()){
					equipmentFull.setBorrownum(Long.valueOf(equipmentFull.getApplynumber()));
					equipmentList.set(i, equipmentFull);
				}
			}
			return equipmentList;
		}	
		return null;
	}
	
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,Integer courseruleid,String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select a.*,b.applynumber from Equipment a left outer join orderCourseRuleDetail b on a.Equipmentid = b.Equipmentid and b.courseruleid="+courseruleid+" where a.lanType = '"+lantype+"' and (a.classificationid = '"+classificationid+"'" +
			" or a.classificationid in (select comid from EquipmentClassification where parentid='"+classificationid+"') )";
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
			List<EquipmentFull> list =query.list();
			if(list!=null&&!list.isEmpty()){
				return list;  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype,String search) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,a.comId,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
		    	String dateTemp = dateList.get(i);
	    		sql +="(select  sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum from Equipment a  "
			+ " where a.lanType = '"+lantype+"' and a.Equipmentname like '%"+search+"%' and  a.classificationid in (select comid from Equipmentclassification where parentid='"+classificationid+"' and lanType = '"+lantype+"' union select comid from Equipmentclassification where comid='"+classificationid+"'  and lanType = '"+lantype+"') group by a.comid ";
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

	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,String fromDate,String endDate,String lantype,String search,Integer courseruleid) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,a.comId,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
		    	String dateTemp = dateList.get(i);
	    		sql +="(select  sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum ,d.applynumber from Equipment a  left outer join orderCourseRuleDetail d on d.courseRuleId= '"+courseruleid+"' and d.comId = a.comId  "
			+ " where d.comid != '' and a.lanType = '"+lantype+"' and a.Equipmentname like '%"+search+"%' and  a.classificationid in (select comid from Equipmentclassification where parentid='"+classificationid+"' and lanType = '"+lantype+"' union select comid from Equipmentclassification where comid='"+classificationid+"' and lanType = '"+lantype+"') group by a.comid ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size2= equipmentList.size();
			for(int i=0;i<size2;i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				if(equipmentFull.getApplynumber()<equipmentFull.getBorrownum()){
					equipmentFull.setBorrownum(Long.valueOf(equipmentFull.getApplynumber()));
					equipmentList.set(i, equipmentFull);
				}
			}
			return equipmentList;
		}	
		return null;
	}
	
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
	    String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.comid,a.lantype,a.imgNameSaved,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
	    	for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select   sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum,c.name as categoryName from Equipment a  "
	    	+ " left outer join EquipmentClassification c on a.classificationid = c.comid and c.lantype = '"+lantype+"' "
			+ " where a.comid ='"+equipmentId+"' and a.lantype = '"+lantype+"' group by a.comid ";
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
	
	public String findDayNum(Integer equipmentId,String fromDate,String endDate,String lantype){

		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
	    String sql = " select  CONCAT(" ;
	    	for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="ifnull((select  a.activenum-sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa from ListDetail b left outer join Equipment a on b.comId=a.comId " 
	    			+ " where a.equipmentid='"+equipmentId+"' and a.lantype='"+lantype+"' and (('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y')) group by a.comId),(select activenum from Equipment where equipmentid='"+equipmentId+"' and lantype = '"+lantype+"'))  "  ;  
	    		if(i!=size-1){
	    			sql += " ,',', ";
	    		}
	    	}
	    sql+= ") as daynum from dual  ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			return equipmentList.get(0).getDaynum(); 
		}	
		return null;
	}


	public String findEquipmentByBorrowId(Integer borrowId,String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select CONCAT(a.equipmentid,'^',a.applynumber,'^',b.equipmentname,'^',(select ifnull(b.activenum,0)-ifnull(max(tempaa.aaa),0) from( " ;
	    	for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select   sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.equipmentid from ListDetail b " 
	    			+ " where ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.equipmentid )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    	sql+= ")tempaa where tempaa.equipmentid=a.equipmentid)) as daynum from ListDetail a " 
	    	+ " left outer join Equipment b on a.equipmentid=b.comId and b.lantype = '"+lantype+"'  "
	    	+ " where a.borrowlistid='"+borrowId+"' ";
	    final String sql1 = sql;
		List<EquipmentFull> equipFullList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipFullList!=null&&!equipFullList.isEmpty()){
			int len = equipFullList.size();
			String a = "";
			for(int i=0;i<len;i++){
				if(i!=0){
					a+="|";
				}
				a += equipFullList.get(i).getDaynum();
			}
			return a;
		}	
		return null;
	}

	
	public EquipmenborrowFull findEquipmenborrow(Integer id){
		final String sql = " select a.*,b.MsgTitle,b.Content,b.startdate,b.enddate,b.createtime,c.name as teacherName from EquipmenBorrow a" +
				" left outer join sendRuleToUser b on a.sendruleid = b.sendruleid " +
				" left outer join loginuser c on a.teacherid = c.id where a.borrowid = '"+id+"' ";
		List<EquipmenborrowFull> equipmenborrowtList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
				return query.list();
			}
		});	
		if(equipmenborrowtList!=null&&!equipmenborrowtList.isEmpty()){
			return equipmenborrowtList.get(0); 
		}	
		return null;
	}

	public List<EquipmenborrowFull> findFinishOrder(Integer userId) {
		// TODO Auto-generated method stub
		final String sql = " select a.* from EquipmenBorrow a where a.status in ('8') and a.userId='"+userId+"' ";
		List<EquipmenborrowFull> equipmenborrowtList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
				return query.list();
			}
		});	
		if(equipmenborrowtList!=null&&!equipmenborrowtList.isEmpty()){
			return equipmenborrowtList; 
		}	
		return null;
	}


	public List<EquipmenborrowFull> findUnderWayOrder(Integer userId) {
		// TODO Auto-generated method stub
		final String sql = " select case when a.status='9' then b.MsgTitle else a.title end title,a.*  from EquipmenBorrow a left outer join sendruletouser b on a.Sendruleid = b.Sendruleid where a.status !=8 and a.userid ='"+userId+"'  ";
		List<EquipmenborrowFull> equipmenborrowtList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
				return query.list();
			}
		});	
		if(equipmenborrowtList!=null&&!equipmenborrowtList.isEmpty()){
			return equipmenborrowtList; 
		}	
		return null;
	}


	@Override
	public List<EquipmenborrowFull> findEquipmenborrow(String dealtype,
			String ordertype, Page page) {
		// TODO Auto-generated method stub
		if(ordertype.equals("0")){
			ordertype="1','2";
		}
		if(dealtype.equals("0")){
			dealtype="'1','2','3','4','5','6','7','8'";
		}else if(dealtype.equals("1")){
			dealtype="'2'";
		}else if(dealtype.equals("2")){
			dealtype="'4'";
		}else if(dealtype.equals("3")){
			dealtype="'5','6'";
		}else if(dealtype.equals("4")){
			dealtype="'8'";
		}
		final String sql = " select a.*,b.name as userName from EquipmenBorrow a left outer join LoginUser b on a.userid=b.id " +
				" where a.category in ('"+ordertype+"') and a.status in ("+dealtype+") limit "+(((page.getPageNo()-1)*page.getPageSize()))+","+page.getPageSize();
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
			List<EquipmenborrowFull> equipmenborrowtList =query.list();
			if(equipmenborrowtList!=null&&!equipmenborrowtList.isEmpty()){
				return equipmenborrowtList; 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return null;
	}
	
	public  Session getCurrentSession(){
        //TODO Auto-generated method stub
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	}


	@Override
	public OrderCountFull findOrderCount(String ordertype) {
		// TODO Auto-generated method stub
		if(ordertype.equals("0")){
			ordertype="1','2";
		}
		final String sql = " select (select count(*) from EquipmenBorrow a where a.status='2' and  a.category in ('"+ordertype+"')) as orderCount1, " +
				" (select count(*) from EquipmenBorrow a where  a.status='4' and  a.category in ('"+ordertype+"')) as orderCount2,(select count(*) from EquipmenBorrow a where  a.status in ('6','5') and  a.category in ('"+ordertype+"')) as orderCount3, " +
				" (select count(*) from EquipmenBorrow a where  a.status='8' and  a.category in ('"+ordertype+"')) as orderCount4,(select count(*) from EquipmenBorrow a where  a.category in ('"+ordertype+"')) as orderCount5 from dual  ";
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(OrderCountFull.class));
			List<OrderCountFull> list =query.list();
			if(list!=null&&!list.isEmpty()){
				return list.get(0);  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
			
		return null;
	}


	@Override
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid, String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select a.* from Equipment a where a.lanType = '"+lantype+"' and (a.classificationid = '"+classificationid+"'" +
				" or a.classificationid in (select comid from EquipmentClassification where parentid='"+classificationid+"') )";
		try {
			Query query =  this.getCurrentSession().createSQLQuery(sql);
			query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
			List<EquipmentFull> list =query.list();
			if(list!=null&&!list.isEmpty()){
				return list;  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Equipmentclassification> findTopEquipmentclass(String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select * from Equipmentclassification a where a.parentid = 0 and a.lantype='"+lantype+"' ";
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
	public List<Equipmentclassification> findSecondEquipmentclass(
			Integer parentid, String lantype) {
		// TODO Auto-generated method stub
		final String sql = " select * from Equipmentclassification a where a.parentid = '"+parentid+"' and a.lantype='"+lantype+"' ";
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
	public String findEquipmentByBorrowId(Integer borrowId, String fromDate,
			String endDate, Integer courseRuleId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype,
			String search) {
		// TODO Auto-generated method stub
		String sql = " select a.comid as Equipmentid,a.Equipmentname,a.Brandid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved  ";
	    sql+= "  from EquipmentGroup a   "
			+ " where a.lanType = '"+lantype+"' and a.equipmentname like '%"+search+"%' ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size = equipmentList.size();
			for(int i = 0; i<size; i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				equipmentFull.setClassificationid(-2);
				equipmentFull.setBorrownum(Long.valueOf(10));
				equipmentList.set(i, equipmentFull);
			}
			return equipmentList;
		}
		return null;
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype) {
		// TODO Auto-generated method stub
		String sql = " select a.comid as Equipmentid,a.Equipmentname,a.Brandid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved  ";
	    sql+= "  from EquipmentGroup a   "
			+ " where a.lanType = '"+lantype+"' ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size = equipmentList.size();
			for(int i = 0; i<size; i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				equipmentFull.setClassificationid(-2);
				equipmentFull.setBorrownum(Long.valueOf(10));
				equipmentList.set(i, equipmentFull);
			}
			return equipmentList;
		}
		return null;
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype,
			String serach, Integer courseRuleId) {
		// TODO Auto-generated method stub
		String sql = " select a.comid as Equipmentid,a.Equipmentname,a.Brandid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved  ";
	    sql+= "  from EquipmentGroup a  left outer join orderCourseRuleDetail d on d.courseRuleId= '"+courseRuleId+"' and d.comId = a.comId     "
			+ " where a.lanType = '"+lantype+"' and a.equipmentname like '%"+serach+"%'  and d.comid != '' ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size = equipmentList.size();
			for(int i = 0; i<size; i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				equipmentFull.setClassificationid(-2);
				equipmentFull.setBorrownum(Long.valueOf(10));
				equipmentList.set(i, equipmentFull);
			}
			return equipmentList;
		}
		return null;
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype,
			Integer courseRuleId) {
		// TODO Auto-generated method stub
		String sql = " select a.comid as Equipmentid,a.Equipmentname,a.Brandid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved  ";
	    sql+= "  from EquipmentGroup a  left outer join orderCourseRuleDetail d on d.courseRuleId= '"+courseRuleId+"' and d.comId = a.comId   "
			+ " where a.lanType = '"+lantype+"' and d.comid != '' ";
		final String sql1 = sql;
		List<EquipmentFull> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			int size = equipmentList.size();
			for(int i = 0; i<size; i++){
				EquipmentFull equipmentFull = equipmentList.get(i);
				equipmentFull.setClassificationid(-2);
				equipmentFull.setBorrownum(Long.valueOf(10));
				equipmentList.set(i, equipmentFull);
			}
			return equipmentList;
		}
		return null;
	}


	@Override
	public List<EquipmentFull> equipmentGroupOrder(Integer equipmentId,
			String fromDate, String endDate,String lantype) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.Equipmentid,a.Equipmentname,a.Brandid,a.Classificationid,a.Administrationid,a.Makedate,a.Modifydate,a.Equipmentnum,a.Activenum,a.Maintainnum,a.Repairnum,a.Losednum,a.Recyclingnum,a.Equipmentdetail,a.Category,a.Remark,a.imgNameSaved,(select a.activenum-ifnull(max(tempaa.aaa),0) from( " ;
		    for(int i=0;i<size;i++){
	    		String dateTemp = dateList.get(i);
	    		sql +="(select   sum(ifnull(ifnull(b.borrownumber,b.applynumber),0)) as aaa,b.comId from ListDetail b " 
	    			+ " where  ('"+dateTemp+"'<=b.returntime and '"+dateTemp+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
	    		if(i!=size-1){
	    			sql += " union ";
	    		}
	    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum,d.num from Equipment a  "
	    	+ " left outer join groupofequipment d on d.Equipmentid=a.comid "
	    	+ " left outer join equipmentgroup e on d.equipmentgroupid=e.comid "
			+ " where a.lanType = '"+lantype+"' and e.comid = '"+equipmentId+"' group by a.comid  ";
		
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


	public Equipmentclassification findEquipmentclassification(
			Integer classificationid, String lantype) {
		// TODO Auto-generated method stub
		final String sql = "select * from Equipmentclassification where comid = '"+classificationid+"' and lanType = '"+lantype+"' ";
		List<Equipmentclassification> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentclassification.class);
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}




}
