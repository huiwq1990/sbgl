package com.sbgl.app.services.equipment.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.EquipGroupDao;
import com.sbgl.app.entity.Equipmentgroup;
import com.sbgl.app.entity.Groupofequipment;
import com.sbgl.app.services.equipment.EquipGroupService;
import com.sbgl.util.Page;

@Scope("prototype") 
@Service("equipGroupService")
@Transactional
public class EquipGroupServiceImpl implements EquipGroupService{
	
	@Resource
	private BaseDao baseDao;
	
	@Resource
	private EquipGroupDao equipGroupDao;
	 
	@Override
	public void addEquipmentGroup(Equipmentgroup equipmentgroup,
			Equipmentgroup equipmentENgroup,
			List<Groupofequipment> groupofequipmentList) {
		// TODO Auto-generated method stub
		baseDao.saveEntity(equipmentgroup);
		baseDao.saveEntity(equipmentENgroup);
		for(Groupofequipment groupofequipment :groupofequipmentList){
			baseDao.saveEntity(groupofequipment);
		}
	}

	@Override
	public List<EquipmentgroupFull> findEquipmentGroup(Page page) {
		// TODO Auto-generated method stub
		return equipGroupDao.findEquipmentGroup(page);
	}
	
	@Override
	public List<EquipmentgroupFull> findEquipmentGroup(Integer courseruleid) {
		// TODO Auto-generated method stub
		return equipGroupDao.findEquipmentGroup(courseruleid);
	}

	@Override
	public OrderCountFull findCountEquipmentGroup() {
		// TODO Auto-generated method stub
		return equipGroupDao.findCountEquipmentGroup();
	}

	@Override
	public void deleteequipGroup(String[] temp) {
		// TODO Auto-generated method stub
		equipGroupDao.deleteequipGroup(temp);
	}

	

}
