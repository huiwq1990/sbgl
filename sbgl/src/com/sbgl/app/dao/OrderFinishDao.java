package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.actions.order.EquipmenborrowFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.entity.Equipmenborrow;

public interface OrderFinishDao {
	//根据id查找订单，获得订单设备详情
	public List<EquipmentFull> findListBorrow(Integer borrowId);
	//根据id查找订单
	public EquipmenborrowFull findEquipmenborrow(Integer borrowId);
	//获得设备详情
	public EquipmentFull findEquipmentById(Integer equipmentId);
}
