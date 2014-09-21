package com.sbgl.app.dao.impl;

import java.math.BigDecimal;
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

import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.OrderFinishDao;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.entity.Listequipdetail;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.common.DataError;
import com.sbgl.util.DateUtil;
import com.sbgl.util.EscColumnToBean;

@Repository("orderFinishDao")
public class OrderFinishDaoImpl extends HibernateDaoSupport implements OrderFinishDao {
	
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId){
		
		final String sql = " select f.teacherid,a.*,b.name as userName,c.name as teacherName,d.name as examuserName,e.createtime,e.MsgTitle,e.content,c.photo as teacherPic,d.photo as examuserPic from EquipmenBorrow a left outer join loginUser b on a.userid = b.id "
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
		final String sql = " select a.*,b.applynumber,c.name as categoryName,b.borrownumber from Equipment a left outer join ListDetail b on a.comid = b.equipmentid "
			+ " left outer join equipmenborrow d on d.borrowid = b.borrowlistid "
			+ " left outer join EquipmentClassification c on c.classificationid=a.comid and c.lantype='"+lantype+"' "
			+ " where b.borrowlistid='"+borrowId+"'  and ((b.borrownumber is null and d.status in ('1','2','3','4')) or b.borrownumber is not null)  and a.lantype='"+lantype+"' ";
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
		" left outer join ListDetail c on b.comid=c.equipmentid  left outer join equipmenborrow d on d.borrowid = c.borrowlistid where c.borrowlistid='"+borrowId+"'  and ((c.borrownumber is null and d.status in ('1','2','3','4')) or c.borrownumber is not null) and a.lantype='"+lantype+"'  ";
		List<Equipmentclassification> equipmentList = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Equipmentclassification.class); 
				return query.list();
			}
		});	
		if(equipmentList!=null&&!equipmentList.isEmpty()){
			for(Equipmentclassification equipmentclassification:equipmentList){
				String sql1 = " select a.*,b.applynumber,c.name as categoryName,b.listdetailid from Equipment a left outer join ListDetail b on a.comid = b.equipmentid and a.lantype='"+lantype+"' "
					+ " left outer join equipmenborrow d on d.borrowid = b.borrowlistid "	
					+ " left outer join EquipmentClassification c on c.comid=a.classificationid and c.lantype='"+lantype+"' "
					+ " where b.borrowlistid='"+borrowId+"' and ((b.borrownumber is null and d.status in ('1','2','3','4')) or b.borrownumber is not null) and c.comid='"+equipmentclassification.getComid()+"' and a.lantype='"+lantype+"' ";
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

	@Override
	public void delay() {
		// TODO Auto-generated method stub
		String sql = " update listdetail a inner join equipmenborrow b on a.borrowlistid = b.borrowid and b.status not in (1,3) " +
				" set a.ifdelay = 'Y' where now() > a.returntime and (a.borrownumber > 0 or (a.borrownumber is null and a.applynumber > 0)) and a.ifdelay = 'N' ";
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public List<Listdetail> quarylistdetails(Integer borrowId) {
		// TODO Auto-generated method stub
		String sql1 = " select a.* from listdetail a where a.borrowlistid = '"+borrowId+"' ";
		final String sql2 = sql1;
		List<Listdetail> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql2); 
				query.setResultTransformer(new EscColumnToBean(Listdetail.class));
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

	@Override
	public boolean ifborrow(Integer equipmentId, String fromDate, String endDate,Integer borrownum) {
		// TODO Auto-generated method stub
		List<String> dateList = DateUtil.dateRegion(fromDate,endDate);
		final Integer size = dateList.size();
		String sql = " select a.activenum-(select ifnull(max(tempaa.aaa),0) from( " ;
	    for(int i=0;i<size;i++){
    		String dateTemp = dateList.get(i);
    		String dateStart = DateUtil.startDay(dateTemp);
    		String dateEnd = DateUtil.endDay(dateTemp);		
    		sql +="(select sum( case when c.status in (2, 4) then ifnull(ifnull(b.borrownumber, b.applynumber), 0) else ifnull(b.borrownumber, 0) end) as aaa,b.comId from ListDetail b " 
    	    	+ " inner join equipmenborrow c on b.borrowlistid=c.borrowid and c.status not in (1,3)  "
    			+ " where  ('"+dateStart+"'<=b.returntime and '"+dateEnd+"'>=b.borrowtime) or (b.ifdelay='Y') group by b.comId )  "  ;  
    		if(i!=size-1){
    			sql += " union ";
    		}
    	}
	    sql+= ")tempaa where tempaa.comId=a.comId) as borrownum from Equipment a  "
			+ " where a.lanType = '"+CommonConfig.languagechStr+"' and a.comid='"+equipmentId+"'  ";
		
		final String sql1 = sql;
		List<BigDecimal> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql1);
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			for(BigDecimal num :list){
				if(num.intValue()<borrownum){
					return false;
				}
			}
		}	
		return true;
	}

	@Override
	public String queryBorrowallId(Integer borrowId) {
		// TODO Auto-generated method stub
		final String sql = "select borrowallid from equipmenborrow where borrowid = "+borrowId;
		List<EquipmenborrowFull> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmenborrowFull.class));
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list.get(0).getBorrowallid();
		}
		return null;
	}

	@Override
	public void updateEquipmenNum(String type,Integer comid) throws Exception {
		// TODO Auto-generated method stub
		String str = "";
		if("1".equals(type)){
			str = "recyclingnum";
		}else if("2".equals(type)){
			str = "repairnum";
		}else if("3".equals(type)){
			str = "losednum";
		}
		String sql = "update Equipment set " + str + "=ifnull(" + str + ",0)+1,activenum=ifnull(activenum,0)-1 where comid = "+comid;
		Query query =  this.getCurrentSession().createSQLQuery(sql);
		int num = query.executeUpdate();
		if(num<=0){
			throw new DataError("设备信息修改失败");
		}
	}

	//根据订单号，订单中设备信息(用于下载)
	public List<EquipmentFull> queryEqumentBorrowallId(Integer borrowId,String lantype){
		final String sql = "select b.worth,b.equipserial,b.storenumber,c.equipmentname from listequipdetail a " +
				" left join equipmentdetail b on a.equipdetailid=b.equipdetailid " +
				" inner join Equipment c on a.equipmentid = c.comid and c.lantype='"+lantype+"' where borrowlistid = "+borrowId;
		List<EquipmentFull> list = this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql);
				query.setResultTransformer(new EscColumnToBean(EquipmentFull.class));
				return query.list();
			}
		});	
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
		
	}
	
	public  Session getCurrentSession(){
        //TODO Auto-generated method stub
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	}

}
