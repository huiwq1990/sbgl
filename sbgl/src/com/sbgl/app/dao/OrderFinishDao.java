package com.sbgl.app.dao;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipmenborrow;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Listdetail;
import com.sbgl.app.entity.Listequipdetail;
import com.sbgl.app.entity.Loginuser;

public interface OrderFinishDao {
	//根据id查找订单，获得订单设备详情
	public List<EquipmentFull> findListBorrow(Integer borrowId,String lantype);
	//根据id查找订单
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId);
	//获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId,String lantype);
	//获得订单设备的分类
	public List<Equipmentclassification> findclassList(Integer borrowId,String lantype);
	//获得商品详情信息，根据分类获得
	public Map<Integer,List<EquipmentFull>> findMapBorrow(Integer borrowId,Integer type,String lantype);
	//根据联合主键和订单主键获得
	public  Listdetail findListDetail(Integer borrowId,Integer comId);
	//根据联合主键和订单主键获得
	public  Listequipdetail findlistequipdetail(Integer borrowId,Integer comId);
	//用户详情
	public Loginuser userdetail(Integer userId);
	//根据订单号获取订单详情
	public List<Listdetail> quarylistdetails(Integer borrowId);
	//根据订单号获取订单详情
	public boolean ifborrow(Integer equipmentId,String fromDate,String endDate,Integer borrownum);
	//判断归还时间到期是否归还
	public void delay();
}
