package com.sbgl.app.services.equipment.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import com.sbgl.app.dao.QueryResult;
import com.sbgl.app.entity.Category;
import com.sbgl.app.entity.Categorydetail;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentcategory;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.app.services.equipment.EquipService;
import com.sbgl.common.HQLOption;
import com.sbgl.util.Page;

@Scope("prototype") 
@Service("equipService")
public class EquipServiceImpl implements EquipService {
	@Resource
	private BaseDao baseDao;
	
	@Override
	public int getCountOfEquipInfo() {
		return baseDao.getAllEntity(Equipment.class).size();
	}
	
	@Override
	public Integer addEquipInfo(Equipment equip) {
		int id = baseDao.getCode("equipId");
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
	public Integer alterEquipInfo(Equipment equip) {
		// TODO Auto-generated method stub
		Integer id = equip.getEquipmentid();
		Equipment storeEquip = baseDao.getEntityById(Equipment.class, id);
		
		storeEquip.setEquipmentname( equip.getEquipmentname() );
		storeEquip.setBrandid( equip.getBrandid() );
		storeEquip.setImgName(equip.getImgName() );
		storeEquip.setImgNameSaved( equip.getImgNameSaved() );
		storeEquip.setClassificationid( equip.getClassificationid() );
		storeEquip.setAdministrationid( equip.getAdministrationid() );
		storeEquip.setModifydate( new Date() );
		storeEquip.setEquipmentnum( equip.getEquipmentnum() );
		storeEquip.setActivenum( equip.getActivenum() );
		storeEquip.setMaintainnum( equip.getMaintainnum() );
		storeEquip.setRepairnum( equip.getRepairnum() );
		storeEquip.setLosednum( equip.getLosednum() );
		storeEquip.setRecyclingnum( equip.getRecyclingnum() );
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
	public boolean deleteEquipInfo(Integer equipId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Equipment storeEquip = baseDao.getEntityById(Equipment.class, equipId);
		List<Equipment> needToDeleteList =  baseDao.getEntityByProperty(Equipment.class.getName(), "comId", String.valueOf( storeEquip.getComId() ));
		try {
			for (Equipment e : needToDeleteList) {
				baseDao.deleteEntity( e );
			}
			flag = true;
		} catch (RuntimeException re) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Equipment getEquipmentById(Integer equipId) {
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
		List<Integer> categoryId = new ArrayList<Integer>();
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
		/*List<Equipment> resultList = new ArrayList<Equipment>();
		final String sql = "select * from CATEGORYDETAIL where info like '%" + categorydetail.ge + "%'";
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
		*/
		return null;
	}

//	@Override
//	public List<Equipment> getEquipsByCategoryDetails(
//			List<Categorydetail> categorydetailList) {
//		// TODO Auto-generated method stub
//		List<Categorydetail> tempList = baseDao.getEntityByProperty("Categorydetail", "equipmentid", equipment.getEquipmentid().toString());
//		return null;
//	}

	@Override
	public List<Equipment> getEquipsByClassification(Integer equipmentclassificationId) {
		// TODO Auto-generated method stub
		List<Equipment> resultList = baseDao.getEntityByProperty("Equipment", "classificationid", String.valueOf(equipmentclassificationId));
		return resultList;
	}

	@Override
	public Integer addEquipmentdetail(Equipmentdetail equipmentdetail) {
		// TODO Auto-generated method stub
		Integer id = baseDao.getCode("equipDetailId");
		equipmentdetail.setEquipDetailid( id );
		equipmentdetail.setMakedate( new Date() );
		//判断当前添加器材的型号，更新型号表相关统计字段
		Equipment e = null;
		if( equipmentdetail.getEquipmentid() != -1 && equipmentdetail.getEquipmentid() != null ) {
			e = this.getEquipmentById( equipmentdetail.getEquipmentid() );
			if( e.getEquipmentnum() == null ) {  //总的器材数量
				e.setEquipmentnum( 1 );
			} else {
				e.setEquipmentnum( e.getEquipmentnum() + 1 );
			}
			if( "0".equals( equipmentdetail.getStatus() ) || "1".equals( equipmentdetail.getStatus() ) ) {  //细分器材状态数量
				e.setActivenum( e.getActivenum()==null ? 0 : e.getActivenum() + 1 );
			} else if( "2".equals( equipmentdetail.getStatus() ) ) {
				e.setMaintainnum( e.getMaintainnum()==null ? 0 : e.getMaintainnum() + 1 );
			} else if( "3".equals( equipmentdetail.getStatus() ) ) {
				e.setRepairnum( e.getRepairnum()==null ? 0 : e.getRepairnum() + 1 );
			} else if( "4".equals( equipmentdetail.getStatus() ) ) {
				e.setLosednum( e.getLosednum()==null ? 0 : e.getLosednum() + 1 );
			} else if( "5".equals( equipmentdetail.getStatus() ) ) {
				e.setRecyclingnum( e.getRecyclingnum()==null ? 0 : e.getRecyclingnum() + 1 );
			}
			this.alterEquipInfo( e );
		}
		try {
			baseDao.saveEntity( equipmentdetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public Integer alterEquipmentdetail(Equipmentdetail equipmentdetail) {
		Integer id = equipmentdetail.getEquipDetailid();
		Equipmentdetail storeEquipmentdetail = baseDao.getEntityById(Equipmentdetail.class, id);
		Integer oldEquipStatus = new Integer( storeEquipmentdetail.getStatus() );
		Integer oldEquipid = new Integer( storeEquipmentdetail.getEquipmentid() );
		
		storeEquipmentdetail.setEquipserial( equipmentdetail.getEquipserial() );
		storeEquipmentdetail.setAcquireDate( equipmentdetail.getAcquireDate() );
		storeEquipmentdetail.setWorth( equipmentdetail.getWorth() );
		storeEquipmentdetail.setAssetNumber( equipmentdetail.getAssetNumber() );
		storeEquipmentdetail.setUseManageDept( equipmentdetail.getUseManageDept() );
		storeEquipmentdetail.setManager( equipmentdetail.getManager() );
		storeEquipmentdetail.setManufactureDate( equipmentdetail.getManufactureDate() );
		storeEquipmentdetail.setManufacturer( equipmentdetail.getManufacturer() );
		storeEquipmentdetail.setStoragePlace( equipmentdetail.getStoragePlace() );
		storeEquipmentdetail.setStoragePosition( equipmentdetail.getStoragePosition() );
		storeEquipmentdetail.setStorenumber( equipmentdetail.getStorenumber() );
		storeEquipmentdetail.setSupplyer( equipmentdetail.getSupplyer() );
		storeEquipmentdetail.setEquipmentid( equipmentdetail.getEquipmentid() );
		storeEquipmentdetail.setStatus( equipmentdetail.getStatus() );
		storeEquipmentdetail.setAdministrationid( equipmentdetail.getAdministrationid() );
//		storeEquipmentdetail.setMakedate( equipmentdetail.getMakedate() );
		storeEquipmentdetail.setModifydate( new Date() );
		storeEquipmentdetail.setSysremark( equipmentdetail.getSysremark() );
		storeEquipmentdetail.setUsermark( equipmentdetail.getUsermark() );
		//判断当前添加器材的型号，更新型号表相关统计字段
		Equipment e = null;
		if( oldEquipid == -1 && equipmentdetail.getEquipmentid() != -1 ) {  //从没有型号变为有型号
			e = this.getEquipmentById( equipmentdetail.getEquipmentid() );
			e.setEquipmentnum( e.getEquipmentnum()==null ? 0 : e.getEquipmentnum() + 1 );
			if( "0".equals( equipmentdetail.getStatus() ) || "1".equals( equipmentdetail.getStatus() ) ) {  //细分器材状态数量
				e.setActivenum( e.getActivenum()==null ? 0 : e.getActivenum() + 1 );
			} else if( "2".equals( equipmentdetail.getStatus() ) ) {
				e.setMaintainnum( e.getMaintainnum()==null ? 0 : e.getMaintainnum() + 1 );
			} else if( "3".equals( equipmentdetail.getStatus() ) ) {
				e.setRepairnum( e.getRepairnum()==null ? 0 : e.getRepairnum() + 1 );
			} else if( "4".equals( equipmentdetail.getStatus() ) ) {
				e.setLosednum( e.getLosednum()==null ? 0 : e.getLosednum() + 1 );
			} else if( "5".equals( equipmentdetail.getStatus() ) ) {
				e.setRecyclingnum( e.getRecyclingnum()==null ? 0 : e.getRecyclingnum() + 1 );
			}
			this.alterEquipInfo( e );
		} else if( oldEquipid != -1 && equipmentdetail.getEquipmentid() == -1 ) { //从有型号变为没有型号
			e = this.getEquipmentById( oldEquipid );
			e.setEquipmentnum( e.getEquipmentnum() - 1 );
			if( "0".equals( oldEquipStatus ) || "1".equals( oldEquipStatus ) ) {  //细分器材状态数量
				e.setActivenum( e.getActivenum() - 1 );
			} else if( "2".equals( oldEquipStatus ) ) {
				e.setMaintainnum( e.getMaintainnum() - 1 );
			} else if( "3".equals( oldEquipStatus ) ) {
				e.setRepairnum( e.getRepairnum() - 1 );
			} else if( "4".equals( oldEquipStatus ) ) {
				e.setLosednum( e.getLosednum() - 1 );
			} else if( "5".equals( oldEquipStatus ) ) {
				e.setRecyclingnum( e.getRecyclingnum() - 1 );
			}
			this.alterEquipInfo( e );
		} else if( oldEquipid != -1 && equipmentdetail.getEquipmentid() != -1 && 
				   oldEquipid != equipmentdetail.getEquipmentid() ) {  //型号不相同
			e = this.getEquipmentById( equipmentdetail.getEquipmentid() );
			e.setEquipmentnum( e.getEquipmentnum()==null ? 0 : e.getEquipmentnum() + 1 );
			if( "0".equals( equipmentdetail.getStatus() ) || "1".equals( equipmentdetail.getStatus() ) ) {  //细分器材状态数量
				e.setActivenum( e.getActivenum()==null ? 0 : e.getActivenum() + 1 );
			} else if( "2".equals( equipmentdetail.getStatus() ) ) {
				e.setMaintainnum( e.getMaintainnum()==null ? 0 : e.getMaintainnum() + 1 );
			} else if( "3".equals( equipmentdetail.getStatus() ) ) {
				e.setRepairnum( e.getRepairnum()==null ? 0 : e.getRepairnum() + 1 );
			} else if( "4".equals( equipmentdetail.getStatus() ) ) {
				e.setLosednum( e.getLosednum()==null ? 0 : e.getLosednum() + 1 );
			} else if( "5".equals( equipmentdetail.getStatus() ) ) {
				e.setRecyclingnum( e.getRecyclingnum()==null ? 0 : e.getRecyclingnum() + 1 );
			}
			this.alterEquipInfo( e );
			e = this.getEquipmentById( oldEquipid );
			e.setEquipmentnum( e.getEquipmentnum() - 1 );
			if( "0".equals( oldEquipStatus ) || "1".equals( oldEquipStatus ) ) {  //细分器材状态数量
				e.setActivenum( e.getActivenum() - 1 );
			} else if( "2".equals( oldEquipStatus ) ) {
				e.setMaintainnum( e.getMaintainnum() - 1 );
			} else if( "3".equals( oldEquipStatus ) ) {
				e.setRepairnum( e.getRepairnum() - 1 );
			} else if( "4".equals( oldEquipStatus ) ) {
				e.setLosednum( e.getLosednum() - 1 );
			} else if( "5".equals( oldEquipStatus ) ) {
				e.setRecyclingnum( e.getRecyclingnum() - 1 );
			}
			this.alterEquipInfo( e );
		}
		
		try {
			baseDao.updateEntity( storeEquipmentdetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteEquipmentdetail(Integer equipmentdetailId) {
		//判断当前添加器材的型号，更新型号表相关统计字段
		Equipmentdetail d = this.getEquipmentdetail( equipmentdetailId );
		Equipment e = null;
		if( d.getEquipmentid() != -1 && d.getEquipmentid() != null ) {
			e = this.getEquipmentById( d.getEquipmentid() );
			if( e.getEquipmentnum() != null ) {  //总的器材数量
				e.setEquipmentnum( e.getEquipmentnum() - 1 );
			}
			
			if( "0".equals( d.getStatus() ) || "1".equals( d.getStatus() ) ) {  //细分器材状态数量
				e.setActivenum( e.getActivenum() - 1 );
			} else if( "2".equals( d.getStatus() ) ) {
				e.setMaintainnum( e.getMaintainnum() - 1 );
			} else if( "3".equals( d.getStatus() ) ) {
				e.setRepairnum( e.getRepairnum() - 1 );
			} else if( "4".equals( d.getStatus() ) ) {
				e.setLosednum( e.getLosednum() - 1 );
			} else if( "5".equals( d.getStatus() ) ) {
				e.setRecyclingnum( e.getRecyclingnum() - 1 );
			}
			this.alterEquipInfo( e );
		}
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
	public Equipmentdetail getEquipmentdetail(Integer equipmentdetailId) {
		// TODO Auto-generated method stub
		return baseDao.getEntityById(Equipmentdetail.class, equipmentdetailId);
	}
	
	@Override
	public List<Equipmentdetail> getAllEquipmentdetailByEquipInfo(Integer EquipId) {
		List<Equipmentdetail> tempList = baseDao.getEntityByProperty("Equipmentdetail", "equipmentid", String.valueOf(EquipId));
		return tempList;
	}
	
	@Override
	public List<Equipmentdetail> getAllEquipmentdetail() {
		List<Equipmentdetail> tempList = baseDao.getAllEntity( Equipmentdetail.class );
		return tempList;
	}

	@Override
	public Integer addEquipmentclassification(
			Equipmentclassification equipmentclassification) {
		Integer id = 0;
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
	public Integer alterEquipmentclassification(Equipmentclassification equipmentclassification) {
		// TODO Auto-generated method stub
		Integer id = equipmentclassification.getClassificationid();
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
	public boolean deleteEquipmentclassification(Integer equipmentclassificationId) {
		boolean flag = false;
		Equipmentclassification storeEquipmentclassification = baseDao.getEntityById(Equipmentclassification.class, equipmentclassificationId);
		List<Equipmentclassification> needToDeleteList =  baseDao.getEntityByProperty(Equipmentclassification.class.getName(), "comId", String.valueOf( storeEquipmentclassification.getComId() ));
		
		try {
			for (Equipmentclassification e : needToDeleteList) {
				baseDao.deleteEntity( e );
			}
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
	public List<Equipmentclassification> getAllChildEquipmentclassificationsByParentId(Integer parentId) {
		List<Equipmentclassification> tempList = baseDao.getEntityByProperty("Equipmentclassification", "parentid", String.valueOf(parentId));
		return tempList;
	}
	
	@Override
	public Integer getCountOfEquipByClassification(Integer classificationId, Boolean isParent) {
		if( isParent ) {
			int totalSum = 0;
			List<Equipmentclassification> childrenList = this.getAllChildEquipmentclassificationsByParentId( classificationId );
			if( childrenList != null && childrenList.size() > 0 ) {
				for (Equipmentclassification c : childrenList) {
					final String modelCountSQL = "select count(1) from Equipment where classificationid = " + c.getClassificationid();
					BigInteger EquipSum = baseDao.getHibernateTemplate().execute(new HibernateCallback(){
						public Object doInHibernate(Session session) throws HibernateException{
							Query query = session.createSQLQuery(modelCountSQL);
							return query.list().get(0); 
						}
					});
					if(EquipSum != null) {
						totalSum += EquipSum.intValue();
					}
				}
			}
			return totalSum;
		} else {
			final String modelCountSQL = "select count(1) from Equipment where classificationid = " + classificationId;
			BigInteger EquipSum = baseDao.getHibernateTemplate().execute(new HibernateCallback(){
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
	}
	
	@Override
	public Integer getCountOfEquipdetailByClassification(Integer classificationId, Boolean isParent) {
		if( isParent ) {
			int totalSum = 0;
			List<Equipmentclassification> childrenList = this.getAllChildEquipmentclassificationsByParentId( classificationId );
			if( childrenList != null && childrenList.size() > 0 ) {
				for (Equipmentclassification c : childrenList) {
					final String modelCountSQL = "select sum(equipmentnum) from Equipment where classificationid = " + c.getClassificationid();
					BigDecimal EquipSum = baseDao.getHibernateTemplate().execute(new HibernateCallback(){
						public Object doInHibernate(Session session) throws HibernateException{
							Query query = session.createSQLQuery(modelCountSQL);
							return query.list().get(0); 
						}
					});
					if(EquipSum != null) {
						totalSum += EquipSum.intValue();
					}
				}
			}
			return totalSum;
		} else {
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
	}
	
	@Override
	public Equipmentclassification getEquipmentclassificationByEquipmentModel( Integer equipmentModelId ) {
		Equipment equipment = baseDao.getEntityById(Equipment.class, equipmentModelId);
		Equipmentclassification equipmentclassification = baseDao.getEntityById(Equipmentclassification.class, equipment.getClassificationid());
		return equipmentclassification;
	}

	@Override
	public Integer addCategory(Category category) {
		// TODO Auto-generated method stub
		Integer id = baseDao.getCode("categoryId");
		category.setCategoryid( id );
		try {
			baseDao.saveEntity( category );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public Integer alterCategory(Category category) {
		// TODO Auto-generated method stub
		Integer id = category.getCategoryid();
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
	public boolean deleteCategory(Integer categoryId) {
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
	public Integer addCategorydetail(Categorydetail categorydetail) {
		// TODO Auto-generated method stub
		Integer id = baseDao.getCode("categorydetailId");
		categorydetail.setCategoryid( id );
		try {
			baseDao.saveEntity( categorydetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public Integer alterCategorydetail(Categorydetail categorydetail) {
		// TODO Auto-generated method stub
		Integer id = categorydetail.getCategorydetailsid();
		Categorydetail storeCategorydetail = baseDao.getEntityById(Categorydetail.class, id);
		
		storeCategorydetail.setCategorydetailsid( categorydetail.getCategorydetailsid() );
		storeCategorydetail.setEquipmentid( categorydetail.getEquipmentid() );
		storeCategorydetail.setCategoryid( categorydetail.getCategoryid() );
		
		try {
			baseDao.updateEntity( storeCategorydetail );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public boolean deleteCategorydetail(Integer categorydetailId) {
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
			Integer equipmentclassificationId) {
		// TODO Auto-generated method stub
		
		return baseDao.getEntityById(Equipmentclassification.class, equipmentclassificationId);
	}

	@Override
	public int getCountOfEquipmentdetail() {
		return baseDao.getAllEntity(Equipmentdetail.class).size();
	}

	@Override
	public int getCountOfEquipmentclassfication() {
		return baseDao.getAllEntity(Equipmentclassification.class).size();
	}

	@Override
	public QueryResult getgetEquipmentclassificationByPageWithOptions(
			List<HQLOption> hqlOptionList, Page page) {
		return baseDao.getEntityByPageWithOptions(Equipmentclassification.class, hqlOptionList, page);
	}

	@Override
	public QueryResult getEquipmentByPageWithOptions(
			List<HQLOption> hqlOptionList, Page page) {
		return baseDao.getEntityByPageWithOptions(Equipment.class, hqlOptionList, page);
	}
	
	@Override
	public QueryResult getEquipDetailByPageWithOptions(
			List<HQLOption> hqlOptionList, Page page) {
		return baseDao.getEntityByPageWithOptions(Equipmentdetail.class, hqlOptionList, page);
	}

	@Override
	public Boolean isExistThisClassification(String classificationName) {
		return baseDao.isExist(Equipmentclassification.class, "name", classificationName);
	}

	@Override
	public Integer getClassificationComId() {
		return baseDao.getCode( "equipClassificationComId" );
	}

	@Override
	public List<Equipmentclassification> getAllCHEquipmentclassifications() {
		return baseDao.getEntityByProperty(Equipmentclassification.class.getName(), "lanType", "CH");
	}

	@Override
	public boolean isExistEquipment(String EquipName) {
		return baseDao.isExist(Equipment.class, "equipmentname", EquipName);
	}

	@Override
	public Integer getEquipmentComId() {
		return baseDao.getCode( "equipComId" );
	}

	@Override
	public boolean isExistEquipDetial(int assetNumber) {
		return baseDao.isExist(Equipmentdetail.class, "assetNumber", String.valueOf(assetNumber));
	}

	@Override
	public Integer getEquipDetailComId() {
		return baseDao.getCode( "equipDetailComId" );
	}
	
	
}
