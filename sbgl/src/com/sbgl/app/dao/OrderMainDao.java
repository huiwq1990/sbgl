package com.sbgl.app.dao;

import java.util.Date;
import java.util.List;

import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;

public interface OrderMainDao {
	//查找设备是否可借
	public List<Equipmentnum> findEquipmentnum(Integer equipmentid,String startDate,String endDate);
	//维护设备剩余量
	public boolean updateEquipmentnum(Integer equipmentid,String startDate,String endDate,Integer num);
	//维护设备剩余量
	public void addEquipmentnum()  throws Exception ;
	//获得二级器材分类(根据最小的分类id)
	public List<Equipmentclassification> findSecondEquipmentclass();
	//根据器材分类获得设备(根据最小的分类id)
	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate);
	//根据器材分类获得设备
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate);
	//获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate);
}
