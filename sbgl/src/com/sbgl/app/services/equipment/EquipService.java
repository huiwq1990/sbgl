package com.sbgl.app.services.equipment;

import java.io.Serializable;
import java.util.List;

import com.sbgl.app.dao.QueryResult;
import com.sbgl.app.entity.Category;
import com.sbgl.app.entity.Categorydetail;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.common.HQLOption;
import com.sbgl.util.Page;

public interface EquipService {
	//获取器材类型总数
	public int getCountOfEquipInfo();
	//添加器材类型信息
	public Integer addEquipInfo(Equipment equip);
	//修改器材类型信息
	public Integer alterEquipInfo(Equipment equip, String flag);
	//删除器材类型信息 
	public boolean deleteEquipInfo(Integer equipId);
	//查询器材类型信息-id
	public Equipment getEquipmentById(Integer equipId);
	//查询器材类型信息-name
	public Equipment getEquipmentByName(String name);
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
	public List<Equipment> getEquipsByClassification(Integer equipmentclassificationId);
	//按条件查询并以分页方式查询
	public QueryResult getEquipmentByPageWithOptions(List<HQLOption> hqlOptionList, Page page);
	//查询该型号是否已经存在
	public boolean isExistEquipment(String EquipName);
	//获取联合主键
	public Integer getEquipmentComId();
	//根据联合主键和语言类型选择器材类型
	public Equipment getEquipByComidAndLanType(Integer comid, String lantype);
	
	//查询器材详情记录总数
	public int getCountOfEquipmentdetail();
	//添加器材详情
	public Integer addEquipmentdetail(Equipmentdetail equipmentdetail);
	//修改器材详情
	public Integer alterEquipmentdetail(Equipmentdetail equipmentdetail);
	//删除器材详情
	public boolean deleteEquipmentdetail(Integer equipmentdetailId);
	//查询器材详情
	public Equipmentdetail getEquipmentdetail(Integer equipmentdetailId);
	//查询某一设备型号下的全部器材详情
	public List<Equipmentdetail> getAllEquipmentdetailByEquipInfo(Integer EquipId);
	//查询全部器材详情
	public List<Equipmentdetail> getAllEquipmentdetail();
	//根据条件进行分页查询
	public QueryResult getEquipDetailByPageWithOptions(List<HQLOption> hqlOptionList, Page page); 
	//查询是否该器材已经存在
	public boolean isExistEquipDetial(String assetNumber);
	//获取联合主键
	public Integer getEquipDetailComId();
	
	//查询器材分类总数
	public int getCountOfEquipmentclassfication();
	//添加器材分类
	public Integer addEquipmentclassification(Equipmentclassification equipmentclassification);
	//修改器材分类
	public Integer alterEquipmentclassification(Equipmentclassification equipmentclassification);
	//删除器材分类
	public boolean deleteEquipmentclassification(Integer equipmentclassificationId);
	//查询单个分类
	public Equipmentclassification getEquipmentclassificationById(Integer equipmentclassificationId);
	//查询具体名称分类
	public Equipmentclassification getEquipmentclassificationByName(String name);
	//查询全部器材分类
	public List<Equipmentclassification> getAllEquipmentclassifications();
	//查询全部中文名称的器材分类
	public List<Equipmentclassification> getAllCHEquipmentclassifications();
	//查询某一一级分类下的所有二级器材分类
	public List<Equipmentclassification> getAllChildEquipmentclassificationsByParentId(Integer equipmentclassificationId);
	//查询分类下器材模型的数量
	public Integer getCountOfEquipByClassification(Integer classificationId, Boolean isParent);
	//查询分类下所有设备的数量
	public Integer getCountOfEquipdetailByClassification(Integer classificationId, Boolean isParent);
	//查询某一型号所属的分类
	public Equipmentclassification getEquipmentclassificationByEquipmentModel(Integer equipmentModelId);
	//按条件查询并以分页方式查询
	public QueryResult getgetEquipmentclassificationByPageWithOptions(List<HQLOption> hqlOptionList, Page page);
	//查询该分类名称是否已经存在
	public Boolean isExistThisClassification(String classificationName);
	//获取联合主键
	public Integer getClassificationComId();
	//查询分类根据联合主键
	public List<Equipmentclassification> getEquipmentclassificationByComid(Integer comid); 
	
	//添加品类信息
	public Integer addCategory(Category category);
	//修改品类信息
	public Integer alterCategory(Category category);
	//删除品类信息
	public boolean deleteCategory(Integer categoryId);
	//查询全部品类
	public List<Category> getAllCategories();
	//查询某一器材的拥有的所有品类
	public List<Category> getAllCategoriesByEquip(Equipment equip);
	
	//添加品类详情
	public Integer addCategorydetail(Categorydetail categorydetail);
	//修改品类详情
	public Integer alterCategorydetail(Categorydetail categorydetail);
	//删除品类详情
	public boolean deleteCategorydetail(Integer categorydetailId);
	//查询某一品类下的所有品类详情
	public List<Categorydetail> getAllCategorydetailsByCategory(Category category);
	//查询某一器材下的所有品类详情
	public List<Categorydetail> getAllCategorydetailsByEquip(Equipment equip);
}
