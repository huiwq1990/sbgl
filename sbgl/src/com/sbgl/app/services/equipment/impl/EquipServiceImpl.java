package com.sbgl.app.services.equipment.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Category;
import com.sbgl.app.entity.Categorydetail;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentcategory;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.app.services.equipment.EquipService;

@Scope("prototype") 
@Service("equipService")
public class EquipServiceImpl implements EquipService {
	@Resource
	private BaseDao baseDao;
	
	@Override
	public long addEquipInfo(Equipment equip) {
		long id = baseDao.getCode("equipId");
		equip.setEquipmentid( id );
		equip.setMakedate( new Date() );
		try {
			baseDao.saveEntity( equip );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public long alterEquipInfo(Equipment equip) {
		// TODO Auto-generated method stub
		long id = equip.getEquipmentid();
		Equipment storeEquip = baseDao.getEntityById(Equipment.class, id);
		
		storeEquip.setEquipmentname( equip.getEquipmentname() );
		storeEquip.setBrandid( equip.getBrandid() );
		storeEquip.setClassificationid( equip.getClassificationid() );
		storeEquip.setModifydate( new Date() );
		storeEquip.setEquipmentnum( equip.getEquipmentnum() );
		//storeEquip.setProductnum( equip.getProductnum() );
		storeEquip.setEquipmentdetail( equip.getEquipmentdetail() );
		storeEquip.setCategory( equip.getCategory() );
		storeEquip.setRemark( equip.getRemark() );
		
		try {
			baseDao.updateEntity( storeEquip );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteEquipInfo(long equipId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Equipment storeEquip = baseDao.getEntityById(Equipment.class, equipId);
		try {
			baseDao.deleteEntity( storeEquip );
			flag = true;
		} catch (RuntimeException re) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Equipment getEquipmentById(long equipId) {
		try {
			return baseDao.getEntityById(Equipment.class, equipId);
        } catch (RuntimeException re) {
        	return null;
        }
	}
	
	@Override
	public List<Equipment> getAllEquips() {
		// TODO Auto-generated method stub
		List<Equipment> resultList = baseDao.getAllEntity( Equipment.class );
		return resultList;
	}

	@Override
	public List<Equipment> getEquipsByCategory(Category category) {
		// TODO Auto-generated method stub
		List<Equipment> resultList = baseDao.getEntityByProperty("Equipment", "category", category.getCategoryid().toString());
		return resultList;
	}

	@Override
	public List<Equipment> getEquipsByCategories(List<Category> categoryList) {
		// TODO Auto-generated method stub
		List<Long> categoryId = new ArrayList<Long>();
		for (Category category : categoryList) {
			categoryId.add( category.getCategoryid() );
		}
		
		List<Equipment> resultList = new ArrayList<Equipment>();
		List<Equipment> allEquipList = getAllEquips();
		for (Equipment equipment : allEquipList) {
			List<Equipmentcategory> hadAllCategoryList = baseDao.getEntityByProperty("Equipmentcategory", "equipmentid", equipment.getEquipmentid().toString());
			boolean isMatch = true;
			if( hadAllCategoryList != null && hadAllCategoryList.size() >= categoryList.size() ) {
				for (Equipmentcategory equipmentcategory : hadAllCategoryList) {
					if( !categoryId.contains( equipmentcategory.getCategoryid() ) ) {
						isMatch = false;
						break;
					}
				}
				if( isMatch ) {
					resultList.add( equipment );
				}
			}
		}
		return resultList;
	}

	@Override
	public List<Equipment> getEquipsByCategoryDetail(
			Categorydetail categorydetail) {
		// TODO Auto-generated method stub
		List<Equipment> resultList = new ArrayList<Equipment>();
		final String sql = "select * from CATEGORYDETAIL where info like '%" + categorydetail.getInfo() + "%'";
		List<Categorydetail> tempList = baseDao.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(sql).addEntity(Categorydetail.class);
				return query.list();
			}
		});	
		for (Categorydetail cd : tempList) {
			resultList.add( baseDao.getEntityById(Equipment.class, cd.getEquipmentid()) );
		}
		return resultList;
	}

//	@Override
//	public List<Equipment> getEquipsByCategoryDetails(
//			List<Categorydetail> categorydetailList) {
//		// TODO Auto-generated method stub
//		List<Categorydetail> tempList = baseDao.getEntityByProperty("Categorydetail", "equipmentid", equipment.getEquipmentid().toString());
//		return null;
//	}

	@Override
	public List<Equipment> getEquipsByClassification(long equipmentclassificationId) {
		// TODO Auto-generated method stub
		List<Equipment> resultList = baseDao.getEntityByProperty("Equipment", "classificationid", String.valueOf(equipmentclassificationId));
		return resultList;
	}

	@Override
	public long addEquipmentdetail(Equipmentdetail equipmentdetail) {
		// TODO Auto-generated method stub
		long id = baseDao.getCode("equipDetailId");
		equipmentdetail.setEquipdetaild( id );
		equipmentdetail.setMakedate( new Date() );
		try {
			baseDao.saveEntity( equipmentdetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public long alterEquipmentdetail(Equipmentdetail equipmentdetail) {
		// TODO Auto-generated method stub
		long id = equipmentdetail.getEquipdetaild();
		Equipmentdetail storeEquipmentdetail = baseDao.getEntityById(Equipmentdetail.class, id);
		
		storeEquipmentdetail.setEquipserial( equipmentdetail.getEquipserial() );
		storeEquipmentdetail.setEquipmentid( equipmentdetail.getEquipmentid() );
		storeEquipmentdetail.setStatus( equipmentdetail.getStatus() );
		storeEquipmentdetail.setAdministrationid( equipmentdetail.getAdministrationid() );
		storeEquipmentdetail.setMakedate( equipmentdetail.getMakedate() );
		storeEquipmentdetail.setModifydate( new Date() );
		storeEquipmentdetail.setSysremark( equipmentdetail.getSysremark() );
		storeEquipmentdetail.setUsermark( equipmentdetail.getUsermark() );
		
		try {
			baseDao.updateEntity( storeEquipmentdetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteEquipmentdetail(long equipmentdetailId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Equipmentdetail storeEquipmentdetail = baseDao.getEntityById(Equipmentdetail.class, equipmentdetailId);
		try {
			baseDao.deleteEntity( storeEquipmentdetail );
			flag = true;
		} catch (RuntimeException re) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Equipmentdetail getEquipmentdetail(long equipmentdetailId) {
		// TODO Auto-generated method stub
		return baseDao.getEntityById(Equipmentdetail.class, equipmentdetailId);
	}
	
	@Override
	public List<Equipmentdetail> getAllEquipmentdetailByEquipInfo(long EquipId) {
		List<Equipmentdetail> tempList = baseDao.getEntityByProperty("Equipmentdetail", "equipmentid", String.valueOf(EquipId));
		return tempList;
	}
	
	@Override
	public List<Equipmentdetail> getAllEquipmentdetail() {
		List<Equipmentdetail> tempList = baseDao.getAllEntity( Equipmentdetail.class );
		return tempList;
	}

	@Override
	public long addEquipmentclassification(
			Equipmentclassification equipmentclassification) {
		// TODO Auto-generated method stub
		long id = 0;
		if( equipmentclassification.getClassificationid() == null ) {
			id = baseDao.getCode( "equipClassificationId" );
			equipmentclassification.setClassificationid( id );
			equipmentclassification.setMaketime( new Date() );
		}
		
		try {
			baseDao.saveEntity( equipmentclassification );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public long alterEquipmentclassification(Equipmentclassification equipmentclassification) {
		// TODO Auto-generated method stub
		long id = equipmentclassification.getClassificationid();
		Equipmentclassification storeEquipmentclassification = baseDao.getEntityById(Equipmentclassification.class, id);
		
		storeEquipmentclassification.setParentid( equipmentclassification.getParentid() );
		storeEquipmentclassification.setName( equipmentclassification.getName() );
		storeEquipmentclassification.setModifytime( new Date() );
		storeEquipmentclassification.setUserid( equipmentclassification.getUserid() );
		
		try {
			baseDao.updateEntity( storeEquipmentclassification );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteEquipmentclassification(long equipmentclassificationId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Equipmentclassification storeEquipmentclassification = baseDao.getEntityById(Equipmentclassification.class, equipmentclassificationId);
		try {
			baseDao.deleteEntity( storeEquipmentclassification );
			flag = true;
		} catch (RuntimeException re) {
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Equipmentclassification> getAllEquipmentclassifications() {
		// TODO Auto-generated method stub
		return baseDao.getAllEntity( Equipmentclassification.class );
	}
	
	@Override
	public List<Equipmentclassification> getAllChildEquipmentclassificationsByParentId(long parentId) {
		List<Equipmentclassification> tempList = baseDao.getEntityByProperty("Equipmentclassification", "parentid", String.valueOf(parentId));
		return tempList;
	}
	
	@Override
	public Integer getCountOfEquipByClassification(long classificationId) {
		final String modelCountSQL = "select count(*) from Equipment where classificationid = " + classificationId;
		BigDecimal EquipSum = baseDao.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(modelCountSQL);
				return query.list().get(0); 
			}
		});
		if(EquipSum != null) {
			return EquipSum.intValue();
		} else {
			return 0;
		}
	}
	
	@Override
	public Integer getCountOfEquipdetailByClassification(long classificationId) {
		final String equipCountSQL = "select sum(equipmentnum) from Equipment  where classificationid = " + classificationId;
		BigDecimal EquipdetailSum = baseDao.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException{
				Query query = session.createSQLQuery(equipCountSQL);
				return query.list().get(0); 
			}
		});
		if(EquipdetailSum != null) {
			return EquipdetailSum.intValue();
		} else {
			return 0;
		}
		
	}
	
	@Override
	public Equipmentclassification getEquipmentclassificationByEquipmentdetail( long equipmentdetailId ) {
		Equipmentdetail equipmentdetail = baseDao.getEntityById(Equipmentdetail.class, equipmentdetailId);
		Equipment equipment = baseDao.getEntityById(Equipment.class, equipmentdetail.getEquipmentid());
		Equipmentclassification equipmentclassification = baseDao.getEntityById(Equipmentclassification.class, equipment.getClassificationid());
		return equipmentclassification;
	}

	@Override
	public long addCategory(Category category) {
		// TODO Auto-generated method stub
		long id = baseDao.getCode("categoryId");
		category.setCategoryid( id );
		try {
			baseDao.saveEntity( category );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public long alterCategory(Category category) {
		// TODO Auto-generated method stub
		long id = category.getCategoryid();
		Category storeCategory = baseDao.getEntityById(Category.class, id);
		
		storeCategory.setCategoryid( category.getCategoryid() );
		storeCategory.setCategoryname( category.getCategoryname() );
		storeCategory.setMaketime( category.getMaketime() );
		storeCategory.setModifytime( category.getModifytime() );
		storeCategory.setUserid( category.getUserid() );
		
		try {
			baseDao.updateEntity( storeCategory );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteCategory(long categoryId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Category storeCategory = baseDao.getEntityById(Category.class, categoryId);
		try {
			baseDao.deleteEntity( storeCategory );
			flag = true;
		} catch (RuntimeException re) {
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return baseDao.getAllEntity( Category.class );
	}

	@Override
	public List<Category> getAllCategoriesByEquip(Equipment equip) {
		// TODO Auto-generated method stub
		List<Category> resultList = new ArrayList<Category>();
		List<Equipmentcategory> tempList = baseDao.getEntityByProperty("Equipmentcategory", "equipmentid", equip.getEquipmentid().toString());
		for (Equipmentcategory equipmentcategory : tempList) {
			resultList.add( baseDao.getEntityById(Category.class, equipmentcategory.getCategoryid()) );
		}
		return resultList;
	}

	@Override
	public long addCategorydetail(Categorydetail categorydetail) {
		// TODO Auto-generated method stub
		long id = baseDao.getCode("categorydetailId");
		categorydetail.setCategoryid( id );
		try {
			baseDao.saveEntity( categorydetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public long alterCategorydetail(Categorydetail categorydetail) {
		// TODO Auto-generated method stub
		long id = categorydetail.getCategorydetailsid();
		Categorydetail storeCategorydetail = baseDao.getEntityById(Categorydetail.class, id);
		
		storeCategorydetail.setCategorydetailsid( categorydetail.getCategorydetailsid() );
		storeCategorydetail.setEquipmentid( categorydetail.getEquipmentid() );
		storeCategorydetail.setCategoryid( categorydetail.getCategoryid() );
		storeCategorydetail.setInfo( categorydetail.getInfo() );
		
		try {
			baseDao.updateEntity( storeCategorydetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteCategorydetail(long categorydetailId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Categorydetail storeCategorydetail = baseDao.getEntityById(Categorydetail.class, categorydetailId);
		try {
			baseDao.deleteEntity( storeCategorydetail );
			flag = true;
		} catch (RuntimeException re) {
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Categorydetail> getAllCategorydetailsByCategory(
			Category category) {
		// TODO Auto-generated method stub
		return baseDao.getAllEntity( Categorydetail.class );
	}

	@Override
	public List<Categorydetail> getAllCategorydetailsByEquip(Equipment equip) {
		// TODO Auto-generated method stub
		List<Categorydetail> resultList = new ArrayList<Categorydetail>();
		List<Equipmentcategory> tempList = baseDao.getEntityByProperty("Categorydetail", "equipmentid", equip.getEquipmentid().toString());
		for (Equipmentcategory equipmentcategory : tempList) {
			resultList.add( baseDao.getEntityById(Categorydetail.class, equipmentcategory.getCategoryid()) );
		}
		return resultList;
	}

	@Override
	public Equipmentclassification getEquipmentclassificationById(
			long equipmentclassificationId) {
		// TODO Auto-generated method stub
		
		return baseDao.getEntityById(Equipmentclassification.class, equipmentclassificationId);
	}

}
