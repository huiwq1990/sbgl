package com.sbgl.app.services.order;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;

public interface OrderMainService {
	//查找设备是否可借
	public Map<Integer,List<Equipmentnum>> findEquipmentnum(List<Integer> equipmentidList,String startDate,String endDate);
	//维护设备剩余量
	public boolean updateEquipmentnum(List<Integer> equipmentidList,String startDate,String endDate,Integer num)  throws Exception ;
	//维护设备剩余量
	public void addEquipmentnum()  throws Exception ;
	//获得一级器材分类
	public List<Equipmentclassification> findTopEquipmentclass();
	//获得二级器材分类
	public List<Equipmentclassification> findSecondEquipmentclass(Integer parentid);
	//获得二级器材分类(根据最小的分类id)
	public List<Equipmentclassification> findSecondEquipmentclass();
	//根据器材分类获得设备
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate);
	//根据器材分类获得设备(根据最小的分类id)
	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate);
	//下订单
	public void saveOrder(String orderStr,Equipmenborrow equipmenBorrow);
	//获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate);
	
}
