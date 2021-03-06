package com.sbgl.app.dao;

import java.util.Date;
import java.util.List;

import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentnum;
import com.sbgl.util.Page;

public interface OrderMainDao {
	//查找设备是否可借
	public List<Equipmentnum> findEquipmentnum(Integer equipmentid,String startDate,String endDate);
	//维护设备剩余量
	public boolean updateEquipmentnum(Integer equipmentid,String startDate,String endDate,Integer num);
	//维护设备剩余量
	public void addEquipmentnum()  throws Exception ;
	//获得一级器材分类
	public List<Equipmentclassification> findTopEquipmentclass(String lantype);
	//获得二级器材分类(根据最小的分类id)
	public List<Equipmentclassification> findSecondEquipmentclass(String lantype);
	//根据器材分类获得设备(根据设备名称)
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype,String serach);
	//根据器材分类获得设备(根据最小的分类id)
	public List<EquipmentFull> findEquipmentByClss(String fromDate,String endDate,String lantype);
	//根据器材分类获得设备(根据最小的分类id)(课程预约)
	public List<EquipmentFull> findEquipmentByClss2(String fromDate,String endDate,String lantype, Integer courseruleid);
	//根据器材分类获得设备(根据设备名称)
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,String fromDate,String endDate,String lantype,String serach, Integer courseruleid);
	//根据器材分类获得设备
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,String fromDate,String endDate,String lantype, Integer courseruleid);
	//根据器材分类获得设备
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String fromDate,String endDate,String lantype);
	//根据器材分类获得设备,同时查出课程规则选出的设备
	public List<EquipmentFull> findEquipmentByClss2(Integer classificationid,Integer courseruleid,String lantype);
	//获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId,String fromDate,String endDate,String lantype);
	//获得每天剩余数量（根据设备号）
	public String findDayNum(Integer equipmentId,String fromDate,String endDate,String lantype);
	//根据订单查找选择的商品
	public String findEquipmentByBorrowId(Integer borrowId,String fromDate,String endDate,String lantype);
	//根据订单查找选择的商品(课程预约)
	public String findEquipmentByBorrowId(Integer borrowId,String fromDate,String endDate,Integer courseRuleId);
	//找未完成的账单
	public List<EquipmenborrowFull> findUnderWayOrder(Integer userId);
	//找完成的账单
	public List<EquipmenborrowFull> findFinishOrder(Integer userId);
	//后台根据类型分页获取订单
	public List<EquipmenborrowFull> findEquipmenborrow(String dealtype,String ordertype,Page page);
	//查找后台订单统计数据
	public OrderCountFull findOrderCount(String ordertype);
	//根据器材一级分类获得设备
	public List<EquipmentFull> findEquipmentByClss(Integer classificationid,String lantype);
	//获得二级器材分类
	public List<Equipmentclassification> findSecondEquipmentclass(Integer parentid,String lantype);
	//获取搜索页面课程预约信息
	public EquipmenborrowFull findEquipmenborrow(Integer id);
	//获得设备组信息
	public List<EquipmentFull> findEquipmentByGroup(String lantype,String serach);
	//获得设备组信息
	public List<EquipmentFull> findEquipmentByGroup(String lantype);
	//获得设备组信息(课程预约)
	public List<EquipmentFull> findEquipmentByGroup(String lantype,String serach,Integer courseRuleId);
	//获得设备组信息(课程预约)
	public List<EquipmentFull> findEquipmentByGroup(String lantype,Integer courseRuleId);
	//获取设备组中设备信息
	public List<EquipmentFull> equipmentGroupOrder(Integer equipmentId,String fromDate,String endDate,String lantype);
	//根据分类语音和联合主键获取分类
	public Equipmentclassification findEquipmentclassification(Integer classificationid,String lantype);
	//根据设备id详情
	public EquipmentFull findEquipmentById(Integer comid,String lantype);
	//根据设备组id详情
	public EquipmentgroupFull findEquipmentgroupById(Integer comid,String lantype);

	//获得设备组详情
	public EquipmentFull findEquipmentGroupById(Integer equipmentId,String lantype);
}
