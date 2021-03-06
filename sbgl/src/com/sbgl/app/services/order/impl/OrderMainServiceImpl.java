package com.sbgl.app.services.order.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.f1j.paint.en;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.services.order.OrderMainService;
import com.sbgl.common.DataError;
import com.sbgl.util.DateUtil;

@Scope("prototype") 
@Service("orderMainService")
@Transactional(rollbackFor=Exception.class)
public class OrderMainServiceImpl implements OrderMainService {

	@Resource
	private OrderMainDao orderMainDao;
	
	@Resource
	private BaseDao baseDao;
	
	public Map<Integer,List<Equipmentnum>> findEquipmentnum(List<Integer> equipmentidList,
			String startDate, String endDate) {
		// TODO Auto-generated method stub
		Map<Integer,List<Equipmentnum>> equipmentnumMap = new HashMap<Integer, List<Equipmentnum>>();
		for(Integer equipmentid:equipmentidList){
			List<Equipmentnum> equipmentnumList = new ArrayList<Equipmentnum>();
			equipmentnumList = orderMainDao.findEquipmentnum(equipmentid, startDate, endDate);
			equipmentnumMap.put(equipmentid, equipmentnumList);
		}
		return equipmentnumMap;
	}
 

	public boolean updateEquipmentnum(List<Integer> equipmentidList, 
			String startDate, String endDate, Integer num) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = true;
		for(Integer equipmentid:equipmentidList){
			flag = orderMainDao.updateEquipmentnum(equipmentid, startDate, endDate, num);
			if(!flag){
				throw new Exception();			
			}
		}
		return flag;
	}


	public void addEquipmentnum() throws Exception {
		// TODO Auto-generated method stub
		List<Equipmentnum> alist = baseDao.getEntityByProperty("Equipmentnum", "Equipmentnum", "asd");
	}


	public List<Equipmentclassification> findTopEquipmentclass(String lantype) {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = orderMainDao.findTopEquipmentclass(lantype); 
		return equipmentclassList;
	}


	public List<Equipmentclassification> findSecondEquipmentclass(Integer parentid,String lantype) {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = orderMainDao.findSecondEquipmentclass(parentid,lantype);
		return equipmentclassList;
	}


	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		List<EquipmentFull> equipmentList = new ArrayList<EquipmentFull>(); 
		equipmentList = orderMainDao.findEquipmentByClss(classificationid,fromDate,endDate,lantype);
		return equipmentList;
	}
	
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype,String serach) {
		// TODO Auto-generated method stub
		List<EquipmentFull> equipmentList = new ArrayList<EquipmentFull>(); 
		equipmentList = orderMainDao.findEquipmentByClss(classificationid,fromDate,endDate,lantype,serach);
		return equipmentList;
	}
	
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,String fromDate,String endDate,String lantype, Integer courseruleid) {
		// TODO Auto-generated method stub
		List<EquipmentFull> equipmentList = new ArrayList<EquipmentFull>(); 
		equipmentList = orderMainDao.findEquipmentByClss2(classificationid,fromDate,endDate,lantype,courseruleid);
		return equipmentList;
	}
	
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,String fromDate,String endDate,String lantype,String serach, Integer courseruleid) {
		// TODO Auto-generated method stub
		List<EquipmentFull> equipmentList = new ArrayList<EquipmentFull>(); 
		equipmentList = orderMainDao.findEquipmentByClss2(classificationid,fromDate,endDate,lantype,serach,courseruleid);
		return equipmentList;
	}


	public void saveOrder(String orderStr, Equipmenborrow equipmenBorrow) {
		// TODO Auto-generated method stub
			String temp1[] = orderStr.split("あ");
			if(temp1.length>0){
				for(int i=0;i<temp1.length;i++){
					String temp2[] = temp1[i].split("い");
					Listdetail listdetail = new Listdetail();
					listdetail.setBorrowlistid(equipmenBorrow.getBorrowid());
					listdetail.setListdetailid(baseDao.getCode("ListDetail"));
					listdetail.setEquipmentid(Integer.parseInt(temp2[0]));
					listdetail.setApplynumber(Integer.parseInt(temp2[1]));
					baseDao.saveEntity(listdetail);
				}
			}
			baseDao.saveEntity(equipmenBorrow);
	}
	
	
	public Integer subOrder(String equIds,String equNums,String fromDate,String endDate,Integer borrowId,Loginuser user,String lantype) throws Exception{ 
		Equipmenborrow equipmenborrow = new Equipmenborrow();
		String temp1[] = equIds.split(",");
		String temp2[] = equNums.split(",");
		if(borrowId==null||borrowId.equals("")){
			equipmenborrow.setBorrowid(baseDao.getCode("equipmenborrow"));	
			String date = DateUtil.getBorrowDay(new Date());
			String tempid = baseDao.getCode("equipmenallborrow"+date).toString();
			while(tempid.length()<5){
				tempid = "0"+tempid;
			}
			equipmenborrow.setBorrowallid("EP"+date+tempid);
		}else{
			equipmenborrow = baseDao.getEntityById(Equipmenborrow.class, borrowId);
			baseDao.deleteByProperty("Listdetail", "borrowlistid", borrowId);	
		}
		if(temp1!=null&&temp1.length>0){
			for(int i=0;i<temp1.length;i++){
				if(i==0){
					equipmenborrow.setBorrowtime(DateUtil.parseDate(fromDate));
					equipmenborrow.setReturntime(DateUtil.endDay(DateUtil.parseDate(endDate)));
				}
				Listdetail listdetail = new Listdetail();
				EquipmentFull equipmentFull = orderMainDao.findEquipmentById(Integer.parseInt(temp1[i]),fromDate,endDate,lantype);
				if(equipmentFull!=null&&equipmentFull.getBorrownum()!=null&&equipmentFull.getBorrownum()>=Integer.parseInt(temp2[i])){
					listdetail.setBorrowlistid(equipmenborrow.getBorrowid());
					listdetail.setListdetailid(baseDao.getCode("ListDetail"));
					listdetail.setEquipmentid(Integer.parseInt(temp1[i]));
					listdetail.setApplynumber(Integer.parseInt(temp2[i]));
					listdetail.setLantype(equipmentFull.getLanType());
					listdetail.setComid(equipmentFull.getComId());
					listdetail.setBorrowtime(DateUtil.parseDate(fromDate));
					listdetail.setReturntime(DateUtil.endDay(DateUtil.parseDate(endDate))); 
					listdetail.setIfdelay("N");
					baseDao.saveEntity(listdetail);
				}else{
					throw new DataError(equipmentFull.getEquipmentname() + "数量不足只剩" +equipmentFull.getBorrownum()+"个");
				}
			}
		}
		equipmenborrow.setStatus(1);
		equipmenborrow.setUserid(user.getId());
		if(equipmenborrow.getCategory()==null){
			equipmenborrow.setCategory(1);
		}
		baseDao.updateEntity(equipmenborrow);	
		return equipmenborrow.getBorrowid();
	}	

	public List<Equipmentclassification> findSecondEquipmentclass(String lantype) {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = orderMainDao.findSecondEquipmentclass(lantype);
		return equipmentclassList;
	} 
	
	public String findDayNum(Integer equipmentId,String fromDate,String endDate,String lantype){
		return orderMainDao.findDayNum(equipmentId,fromDate,endDate,lantype);
	}
	
	public Equipmentclassification findEquipmentclassification(Integer classificationid,String lantype){
		return orderMainDao.findEquipmentclassification(classificationid, lantype);
	}


	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByClss(fromDate,endDate,lantype);
	}
	
	public List<EquipmentFull> findEquipmentByClss2(String fromDate,String endDate,String lantype, Integer courseruleid){
		return orderMainDao.findEquipmentByClss2(fromDate,endDate,lantype,courseruleid);
	}

	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentById(equipmentId,fromDate,endDate,lantype);
	}


	public String findEquipmentByBorrowId(Integer borrowId,String fromDate,String endDate,String lantype) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByBorrowId(borrowId,fromDate,endDate,lantype);
	}
	
	


	@Override
	public List<EquipmenborrowFull> findUnderWayOrder(Integer userId) {
		// TODO Auto-generated method stub
		return orderMainDao.findUnderWayOrder(userId);
	}


	@Override
	public List<EquipmenborrowFull> findFinishOrder(Integer userId) {
		// TODO Auto-generated method stub
		return orderMainDao.findFinishOrder(userId);
	} 
	
	public EquipmenborrowFull findEquipmenborrow(Integer id){
		return orderMainDao.findEquipmenborrow(id);
	}


	@Override
	public String findEquipmentByBorrowId(Integer borrowId, String fromDate,
			String endDate, Integer courseRuleId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype,
			String serach) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByGroup(lantype, serach);
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByGroup(lantype);
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype,
			String serach, Integer courseRuleId) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByGroup(lantype, serach, courseRuleId);
	}


	@Override
	public List<EquipmentFull> findEquipmentByGroup(String lantype,
			Integer courseRuleId) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByGroup(lantype, courseRuleId);
	}


	@Override
	public List<EquipmentFull> equipmentGroupOrder(Integer equipmentId,
			String fromDate, String endDate,String lantype) {
		// TODO Auto-generated method stub
		return orderMainDao.equipmentGroupOrder(equipmentId, fromDate, endDate,lantype); 
	}


	@Override
	public EquipmentFull findEquipmentGroupById(Integer equipmentId,
			String lantype) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentGroupById(equipmentId, lantype); 
	}

	
	
}
