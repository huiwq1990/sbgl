package com.sbgl.app.services.order;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.app.entity.Loginuser;

public interface OrderMainService {
	//查找设备是否可借
	public Map<Integer,List<Equipmentnum>> findEquipmentnum(List<Integer> equipmentidList,String startDate,String endDate);
	//维护设备剩余量
	public boolean updateEquipmentnum(List<Integer> equipmentidList,String startDate,String endDate,Integer num)  throws Exception ;
	//维护设备剩余量
	public void addEquipmentnum()  throws Exception ;
	//获得一级器材分类
	public List<Equipmentclassification> findTopEquipmentclass(String lantype);
	//获得二级器材分类
	public List<Equipmentclassification> findSecondEquipmentclass(Integer parentid);
	//获得二级器材分类(根据最小的分类id)
	public List<Equipmentclassification> findSecondEquipmentclass(String lantype);
	//根据器材分类获得设备
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype);
	//根据器材分类获得设备(根据设备名称)
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype,String serach);
	//根据器材分类获得设备(根据最小的分类id)
	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate,String lantype);
	//下订单
	public void saveOrder(String orderStr,Equipmenborrow equipmenBorrow);
	//获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate);
	//获得分类详情
	public Equipmentclassification findEquipmentclassification(Integer classificationid);
	//获得每天剩余数量（根据设备号）
	public String findDayNum(Integer equipmentId,String fromDate,String endDate);
	//提交订单
	public Integer subOrder(String equIds,String equNums,String fromDate,String endDate,Integer borrowId,Loginuser user) throws Exception;
	//根据订单查找选择的商品
	public String findEquipmentByBorrowId(Integer borrowId,String fromDate,String endDate);
	//找未完成的账单
	public List<EquipmenborrowFull> findUnderWayOrder(Integer userId);
	//找完成的账单
	public List<EquipmenborrowFull> findFinishOrder(Integer userId);
	
}
