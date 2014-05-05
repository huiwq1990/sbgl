package com.sbgl.app.services.equipment;

import java.util.List;
import java.util.Map;

import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.entity.Equipmentgroup;
import com.sbgl.app.entity.Groupofequipment;
import com.sbgl.util.Page;

public interface EquipGroupService {
	//增加设备组
	public void addEquipmentGroup(Equipmentgroup equipmentgroup,Equipmentgroup equipmentENgroup,List<Groupofequipment> groupofequipmentList);
	//查询设备组
	public List<EquipmentgroupFull> findEquipmentGroup(Page page);
	//查询设备组(全部)
	public List<EquipmentgroupFull> findEquipmentGroup(Integer courseruleid);
	//查询设备组总数量
	public OrderCountFull findCountEquipmentGroup();
	//删除设备组
	public void deleteequipGroup(String[] temp);
}
