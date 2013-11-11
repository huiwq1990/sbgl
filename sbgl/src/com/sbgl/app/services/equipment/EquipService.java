package com.sbgl.app.services.equipment;

import java.util.List;

import com.sbgl.app.entity.Category;
import com.sbgl.app.entity.Categorydetail;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;

public interface EquipService {
	//添加器材类型信息
	public long addEquipInfo(Equipment equip);
	//修改器材类型信息
	public long alterEquipInfo(Equipment equip);
	//删除器材类型信息 
	public boolean deleteEquipInfo(long equipId);
	//查询器材类型信息-id
	public Equipment getEquipmentById(long equipId);
	//查询器材类型信息--所有器材类型
	public List<Equipment> getAllEquips();
	//查询器材类型信息--拥有某一品类
	public List<Equipment> getEquipsByCategory(Category category);
	//查询器材类型信息--拥有某些品类
	public List<Equipment> getEquipsByCategories(List<Category> categoryList);
	//查询器材类型信息--拥有某一品类特征
	public List<Equipment> getEquipsByCategoryDetail(Categorydetail categorydetail);
	//查询器材类型信息--拥有某些品类特征
//	public List<Equipment> getEquipsByCategoryDetails(List<Categorydetail> categorydetailList);
	//查询器材类型信息--某一分类
	public List<Equipment> getEquipsByClassification(long equipmentclassificationId);
	
	//添加器材详情
	public long addEquipmentdetail(Equipmentdetail equipmentdetail);
	//修改器材详情
	public long alterEquipmentdetail(Equipmentdetail equipmentdetail);
	//删除器材详情
	public boolean deleteEquipmentdetail(long equipmentdetailId);
	//查询器材详情
	public Equipmentdetail getEquipmentdetail(long equipmentdetailId);
	//查询某一设备型号下的全部器材详情
	public List<Equipmentdetail> getAllEquipmentdetailByEquipInfo(long EquipId);
	//查询全部器材详情
	public List<Equipmentdetail> getAllEquipmentdetail();
	
	//添加器材分类
	public long addEquipmentclassification(Equipmentclassification equipmentclassification);
	//修改器材分类
	public long alterEquipmentclassification(Equipmentclassification equipmentclassification);
	//删除器材分类
	public boolean deleteEquipmentclassification(long equipmentclassificationId);
	//查询单个分类
	public Equipmentclassification getEquipmentclassificationById(long equipmentclassificationId);
	//查询全部器材分类
	public List<Equipmentclassification> getAllEquipmentclassifications();
	//查询某一一级分类下的所有二级器材分类
	public List<Equipmentclassification> getAllChildEquipmentclassificationsByParentId(long equipmentclassificationId);
	//查询分类下器材模型的数量
	public Integer getCountOfEquipByClassification(long classificationId);
	//查询分类下所有设备的数量
	public Integer getCountOfEquipdetailByClassification(long classificationId);
	//查询某一器材所属的分类
	public Equipmentclassification getEquipmentclassificationByEquipmentdetail(long equipmentdetailId);
	
	//添加品类信息
	public long addCategory(Category category);
	//修改品类信息
	public long alterCategory(Category category);
	//删除品类信息
	public boolean deleteCategory(long categoryId);
	//查询全部品类
	public List<Category> getAllCategories();
	//查询某一器材的拥有的所有品类
	public List<Category> getAllCategoriesByEquip(Equipment equip);
	
	//添加品类详情
	public long addCategorydetail(Categorydetail categorydetail);
	//修改品类详情
	public long alterCategorydetail(Categorydetail categorydetail);
	//删除品类详情
	public boolean deleteCategorydetail(long categorydetailId);
	//查询某一品类下的所有品类详情
	public List<Categorydetail> getAllCategorydetailsByCategory(Category category);
	//查询某一器材下的所有品类详情
	public List<Categorydetail> getAllCategorydetailsByEquip(Equipment equip);
}
