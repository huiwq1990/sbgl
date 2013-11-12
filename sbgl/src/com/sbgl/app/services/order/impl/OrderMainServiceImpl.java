package com.sbgl.app.services.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.OrderMainDao;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.services.order.OrderMainService;

@Scope("prototype") 
@Service("orderMainService")
public class OrderMainServiceImpl implements OrderMainService {

	@Resource
	private OrderMainDao orderMainDao;
	
	@Resource
	private BaseDao baseDao;
	
	@Override
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
 
	@Override
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

	@Override
	public void addEquipmentnum() throws Exception {
		// TODO Auto-generated method stub
		List<Equipmentnum> alist = baseDao.getEntityByProperty("Equipmentnum", "Equipmentnum", "asd");
	}

	@Override
	public List<Equipmentclassification> findTopEquipmentclass() {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = baseDao.getEntityByProperty("Equipmentclassification", "parentid", "0");
		return equipmentclassList;
	}

	@Override
	public List<Equipmentclassification> findSecondEquipmentclass(Integer parentid) {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = baseDao.getEntityByProperty("Equipmentclassification", "parentid", parentid.toString());
		return equipmentclassList;
	}

	@Override
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate) {
		// TODO Auto-generated method stub
		List<EquipmentFull> equipmentList = new ArrayList<EquipmentFull>(); 
		List<Equipmentclassification> eclassList = baseDao.getEntityByProperty("Equipmentclassification", "parentid", classificationid.toString());
		if(eclassList!=null&&!eclassList.isEmpty()){
			for(Equipmentclassification eclass:eclassList){
				List<EquipmentFull> equipmentTempList = new ArrayList<EquipmentFull>();  
				
				equipmentTempList = orderMainDao.findEquipmentByClss(eclass.getClassificationid(),fromDate,endDate);
				if(equipmentTempList!=null){
					equipmentList.addAll(equipmentTempList);
				}
			}
		}else{
			equipmentList = orderMainDao.findEquipmentByClss(classificationid,fromDate,endDate);
		}
		return equipmentList;
	}

	@Override
	public void saveOrder(String orderStr, Equipmenborrow equipmenBorrow) {
		// TODO Auto-generated method stub
			String temp1[] = orderStr.split("あ");
			if(temp1.length>0){
				for(int i=0;i<temp1.length;i++){
					String temp2[] = temp1[i].split("い");
					Listdetail listdetail = new Listdetail();
					listdetail.setBorrowlistid(equipmenBorrow.getId().getBorrowid());
					listdetail.setListdetailid(baseDao.getCode("ListDetail"));
					listdetail.setEquipmentid(Integer.parseInt(temp2[0]));
					listdetail.setApplynumber(Integer.parseInt(temp2[1]));
					baseDao.saveEntity(listdetail);
				}
			}
			baseDao.saveEntity(equipmenBorrow);
	}

	@Override
	public List<Equipmentclassification> findSecondEquipmentclass() {
		// TODO Auto-generated method stub
		List<Equipmentclassification> equipmentclassList = orderMainDao.findSecondEquipmentclass();
		return equipmentclassList;
	}

	@Override
	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentByClss(fromDate,endDate);
	}

	@Override
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate) {
		// TODO Auto-generated method stub
		return orderMainDao.findEquipmentById(equipmentId,fromDate,endDate);
	}

	
	
}
