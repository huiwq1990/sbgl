package com.sbgl.app.dao;

import java.util.List;

import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.util.Page;

public interface EquipGroupDao {
	//查询设备组
	public List<EquipmentgroupFull> findEquipmentGroup(Page page);
	//查询设备组总数量
	public OrderCountFull findCountEquipmentGroup();
	//删除设备组
	public void deleteequipGroup(String[] temp);
	//查询设备组(根据规则id)
	public List<EquipmentgroupFull> findEquipmentGroup(Integer courseruleid);
}
