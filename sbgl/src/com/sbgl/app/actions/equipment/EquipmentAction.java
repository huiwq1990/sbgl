package com.sbgl.app.actions.equipment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.equipment.template.ClassficationCourse;
import com.sbgl.app.actions.equipment.template.EquipCourse;
import com.sbgl.app.actions.equipment.template.EquipModelCourse;
import com.sbgl.app.actions.equipment.template.ParentClassIdName;
import com.sbgl.app.dao.QueryResult;
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.app.services.equipment.EquipService;
import com.sbgl.common.HQLOption;
import com.sbgl.common.SBGLConsistent;
import com.sbgl.util.Page;

import net.sf.json.JSONArray;

@Scope("prototype") 
@Controller("EquipmentAction")
public class EquipmentAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(EquipmentAction.class);
	@SuppressWarnings("unused")
	private Map<String, Object> session;
	private String tag;     //返回执行结果 0-成功 1-失败
	private String message; //返回信息
	
	@Resource
	private EquipService equipService;
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	/**
	 * Ajax调用返回封装的JSON对象
	 * @return
	 */
	private Map<String,Object> returnJSON;
	public Map<String,Object> getReturnJSON() {
		return returnJSON;
	}

//	public String getTag() {
//		return tag;
//	}
//	
//
//	public String getMessage() {
//		return message;
//	}
	/**
	 * 添加分类信息
	 */
	private Equipmentclassification equipClassforAdd;
	private Equipmentclassification equipClassforAddEN;
	public Equipmentclassification getEquipClassforAdd() {
		return equipClassforAdd;
	}
	public void setEquipClassforAdd(Equipmentclassification equipClassforAdd) {
		this.equipClassforAdd = equipClassforAdd;
	}
	public Equipmentclassification getEquipClassforAddEN() {
		return equipClassforAddEN;
	}
	public void setEquipClassforAddEN(Equipmentclassification equipClassforAddEN) {
		this.equipClassforAddEN = equipClassforAddEN;
	}

	public String addEquipmentclassification() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Boolean isExist = equipService.isExistThisClassification( equipClassforAdd.getName() );
		Boolean isExistEN = equipService.isExistThisClassification( equipClassforAddEN.getName() );
		if( !isExist && !isExistEN ) {
			int comId = equipService.getClassificationComId();
			equipClassforAdd.setComId( comId );
			equipClassforAddEN.setComId( comId );
			
			long returnCode = equipService.addEquipmentclassification( equipClassforAdd );
			long returnCodeEN = equipService.addEquipmentclassification( equipClassforAddEN );
			if( returnCode != -1 && returnCodeEN == -1 ) {
				equipService.deleteEquipmentclassification( (int)returnCode );
				this.tag = "1";
				this.message = "中文分类保存失败！";
				log.error("################ 保存英文器材分类失败！ ################");
				//gotoEquipManageClassfiction();  //获取最新一集分类信息返回页面
			} else if( returnCode == -1 && returnCodeEN != -1 ) {
				equipService.deleteEquipmentclassification( (int)returnCodeEN );
				this.tag = "1";
				this.message = "英文分类保存失败！";
				log.error("################ 保存英文器材分类失败！ ################");
			} else if( returnCode == -1 && returnCodeEN == -1 ) {
				this.tag = "1";
				this.message = "分类保存失败！";
				log.error("################ 保存器材分类失败！ ################");
			} else if( returnCode != -1 && returnCodeEN != -1 ) {
				gotoEquipManageClassfiction();  //获取最新一集分类信息返回页面
				this.tag = "0";
				this.message = "分类保存成功！";
			}
		} else if(isExist && !isExistEN) {
			this.tag = "100";
			this.message = "所填中文分类已经存在！";
		} else if(!isExist && isExistEN) {
			this.tag = "100";
			this.message = "所填英文分类已经存在！";
		} else if(isExist && isExistEN) {
			this.tag = "100";
			this.message = "所填中文和英文分类已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		returnJSON.put("pClass", JSONArray.fromObject(allParent));
		
		return SUCCESS;
	}
	/**
	 * 修改分类信息
	 * 使用添加分类的参数 equipmentclassification 作为传入变量
	 */
	private Equipmentclassification equipClassforAltert;
	private Equipmentclassification equipClassforAltertEN;
	public Equipmentclassification getEquipClassforAltert() {
		return equipClassforAltert;
	}
	public void setEquipClassforAltert(Equipmentclassification equipClassforAltert) {
		this.equipClassforAltert = equipClassforAltert;
	}
	public Equipmentclassification getEquipClassforAltertEN() {
		return equipClassforAltertEN;
	}
	public void setEquipClassforAltertEN(
			Equipmentclassification equipClassforAltertEN) {
		this.equipClassforAltertEN = equipClassforAltertEN;
	}

	public String alterEquipmentclassification() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		long returnCode = equipService.alterEquipmentclassification( equipClassforAltert );
		long returnCodeEN = equipService.alterEquipmentclassification( equipClassforAltertEN );
		if( returnCode != -1 && returnCodeEN != -1) {
			this.tag = "0";
			gotoEquipManageClassfiction();  //获取最新一集分类信息返回页面
			this.message = "分类修改成功！";
		} else {
			this.tag = "1";
			this.message = "分类修改失败！";
			log.error("################ 修改器材分类失败！ ################");
		}
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		returnJSON.put("pClass", JSONArray.fromObject(allParent));
		return SUCCESS;
	}
	/**
	 * 查询全部分类信息
	 */
	private Map<Integer, String> idNameMap = new HashMap<Integer, String>();
	private List<Equipmentclassification> tempList;
	
	public String getAllEquipmentclassifications() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		tempList = equipService.getAllEquipmentclassifications();
		Map<String, List<String>> allClass = new HashMap<String, List<String>>();
		
		try {
			//首先遍历找出所有的一级分类
			for (Equipmentclassification equipmentclassification : tempList) {
				String name = equipmentclassification.getName();
				Integer parentId = equipmentclassification.getParentid();
				if( parentId == 0 ) {
//					System.out.println("************  一级分类名：" + name);
					if( ! allClass.containsKey( name ) ) {
						allClass.put(name, new ArrayList<String>());
						idNameMap.put(equipmentclassification.getClassificationid(), name);
					}
				}
			}
			//再遍历找出一级分类下的二级分类
			for (Equipmentclassification equipmentclassification : tempList) {
				String name = equipmentclassification.getName();
				Integer parentId = equipmentclassification.getParentid();
				if( parentId != 0 && parentId != null ) {
//					System.out.println("************  二级分类名：" + name);
					List<String> nameList = allClass.get( idNameMap.get( parentId ) );
					nameList.add( name );
				}
			}
			
			this.tag = "0";
			returnJSON.put( "tag", tag );
			returnJSON.put( "pClassNames", idNameMap.values() );
			returnJSON.put( "dropDownList", allClass );
			returnJSON.put( "allClass", tempList );
		} catch (RuntimeException re) {
			this.tag = "1";
	    }
		return SUCCESS;
	}
	/**
	 * 查询某个设备所属的分类信息
	 */
	private Integer equipId;
	private Equipmentclassification equipClass;
	
	public void setEquipId(Integer equipId) {
		this.equipId = equipId;
	}
	
	public Equipmentclassification getEquipClass() {
		return equipClass;
	}
//  通过器材没有意义，应该通过该器材的型号来找齐其相应的分类
//	public String getClassificationByEquipdetail() {
//		try {
//			equipClass = equipService.getEquipmentclassificationByEquipmentdetail( equipId );
//			this.tag = "0";
//		} catch (RuntimeException re) {
//			this.tag = "1";
//	    }
//		return SUCCESS;
//	}
	/**
	 * 获取全部分类信息进程单详情	 * 显示格式为：分类名称 -父级分类-型号数量-设备数量
	 */
	public String getAllClassficationCourse() {
		List<ClassficationCourse> classCourse = new ArrayList<ClassficationCourse>();
		getAllEquipmentclassifications();
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		if(tempList != null) {
			for (Equipmentclassification classfication : tempList) {
				ClassficationCourse cc = new ClassficationCourse();
				if(classfication.getParentid() != 0) {
					cc.setId( String.valueOf( classfication.getClassificationid() ) );
					cc.setName( classfication.getName() );
					if( classfication.getParentid() == 0 ) {
						cc.setModelCount( String.valueOf( equipService.getCountOfEquipByClassification(classfication.getClassificationid(), true ) ) );
						cc.setEquipCount( String.valueOf( equipService.getCountOfEquipdetailByClassification(classfication.getClassificationid(), true ) ) );
					} else {
						cc.setModelCount( String.valueOf( equipService.getCountOfEquipByClassification(classfication.getClassificationid(), false ) ) );
						cc.setEquipCount( String.valueOf( equipService.getCountOfEquipdetailByClassification(classfication.getClassificationid(), false ) ) );
					}
					cc.setpName( idNameMap.get( classfication.getParentid() ) );
					classCourse.add( cc );
				}
			}
		}
		
		returnJSON.put("classCourse", classCourse);
		return SUCCESS;
	}
	/**
	 * 删除分类信息
	 * 业务逻辑说明   1.在删除了某个分类信息后，其分类下的所有型号都归为未分类，未分类本身并不存储，将型号中的分类设置成-1
	 *          2.如果删除的是一个父分类，连同其所有的子分类一起删除
	 * 分页说明 如果整页删除，判断一下当前页是否不存在，要更新界面的当前页数         
	 * 
	 */
	private Integer classficationId;
	public Integer getClassficationId() {
		return classficationId;
	}
	public void setClassficationId(Integer classficationId) {
		this.classficationId = classficationId;
	}

	private String deleteClassfication() {
		
		try {
			Equipmentclassification ec = equipService.getEquipmentclassificationById( this.classficationId );
			if(ec.getParentid() == 0) {
				List<Equipmentclassification> childList = equipService.getAllChildEquipmentclassificationsByParentId( ec.getClassificationid() );
				if(childList != null) {
					for (Equipmentclassification e : childList) {
						List<Equipment> equipList = equipService.getEquipsByClassification( e.getClassificationid() );
						if(equipList != null) {
							for (Equipment equipment : equipList) {
								equipment.setClassificationid( Integer.valueOf(-1) );
								equipService.alterEquipInfo( equipment );
							}
						}
						equipService.deleteEquipmentclassification( e.getClassificationid() );
					}
				}
			} else {
				List<Equipment> equipList = equipService.getEquipsByClassification( this.classficationId );
				if(equipList != null) {
					for (Equipment equipment : equipList) {
						equipment.setClassificationid( Integer.valueOf(-1) );
						equipService.alterEquipInfo( equipment );
					}
				}
			}
			
			equipService.deleteEquipmentclassification( this.classficationId );
			this.tag = "0";
		} catch(RuntimeException re) {
			re.printStackTrace();
			this.tag = "200";
		}
		return tag;
	}
	/**
	 * 批量删除设备分类
	 */
	private String classIds;
	public String getClassIds() {
		return classIds;
	}
	public void setClassIds(String classIds) {
		this.classIds = classIds;
	}
	
	public String deleteClassficationByIds() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = classIds.split("_");
		for (String id : ids) {
			this.classficationId = Integer.valueOf( id );
			String result = deleteClassfication();
			if("200".equals(result)) {
				this.tag = "1";
				this.message = "分类删除失败！";
				break;
			} else {
				gotoEquipManageClassfiction();  //获取最新一集分类信息返回页面
				this.message = "分类删除成功！";
				this.tag = "0";
			}
		}
		//当前页数判断
		int curCountOfRow = equipService.getCountOfEquipmentclassfication();
		int curPages = curCountOfRow / 10;
		if(curCountOfRow % 10 != 0 && curCountOfRow > 10) {
			curPages++;
		}
		if(Integer.valueOf(currentPage) > curPages && curPages > 0) {
			currentPage = String.valueOf(curPages);
		} else {
			currentPage = "1";
		}
		
		
		returnJSON.put("crtPage", this.currentPage);
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		returnJSON.put("pClass", JSONArray.fromObject(allParent));
		return SUCCESS;
	}

	/**
	 * 添加设备型号
	 */
	private Equipment equipment;
	private Equipment equipmentEN;
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public Equipment getEquipmentEN() {
		return equipmentEN;
	}
	public void setEquipmentEN(Equipment equipmentEN) {
		this.equipmentEN = equipmentEN;
	}

	public String addEquipInfo() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Boolean isExist = equipService.isExistEquipment( equipment.getEquipmentname() );
		Boolean isExistEN = equipService.isExistEquipment( equipmentEN.getEquipmentname() );
		
		if(!isExist && !isExistEN) {
			int comId = equipService.getEquipmentComId();
			equipment.setComId( comId );
			equipmentEN.setComId( comId );
			long returnCode = equipService.addEquipInfo( equipment );
			long returnCodeEN = equipService.addEquipInfo( equipmentEN );
			if( returnCode != -1 && returnCodeEN == -1) {
				equipService.deleteEquipInfo( (int)returnCode );
				this.tag = "1";
				this.message = "添加英文型号失败！";
			} else if( returnCode == -1 && returnCodeEN != -1) {
				equipService.deleteEquipInfo( (int)returnCodeEN );
				this.tag = "1";
				this.message = "添加中文型号失败！";
				log.error("################ 保存设备型号失败！ ################");
			} else if( returnCode == -1 && returnCodeEN == -1) {
				this.tag = "1";
				this.message = "添加中文和英文型号失败！";
				log.error("################ 保存设备型号失败！ ################");
			} else if( returnCode != -1 && returnCodeEN != -1) {
				this.tag = "0";
				this.message = "添加型号成功！";
			}
		} else if(isExist && !isExistEN) {
			this.tag = "100";
			this.message = "所填中文型号已经存在！";
		} else if(!isExist && isExistEN) {
			this.tag = "100";
			this.message = "所填英文型号已经存在！";
		} else if(isExist && isExistEN) {
			this.tag = "100";
			this.message = "所填中文和英文型号都已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	/**
	 * 修改设备型号
	 * 使用添加设备型号的参数 equipment
	 */
	public String alterEquipInfo() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		long returnCode = equipService.alterEquipInfo( equipment );
		long returnCodeEN = equipService.alterEquipInfo( equipmentEN );
		if( returnCode != -1 && returnCodeEN != -1 ) {
			this.tag = "0";
			this.message = "修改型号成功！";
		} else {
			this.tag = "1";
			this.message = "修改型号失败！";
			log.error("################ 修改设备型号失败！ ################");
		}
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	/**
	 * 删除设备型号
	 * 所有此设备型号的设备的设备型号归为位置型号型号，将其型号外键设为 -1 以作标识
	 */
	private String equipInfoIds;
	public String getEquipInfoIds() {
		return equipInfoIds;
	}
	public void setEquipInfoIds(String equipInfoIds) {
		this.equipInfoIds = equipInfoIds;
	}
	
	public String deleteEquipInfo() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = equipInfoIds.split("_");
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			if( equipService.deleteEquipInfo( oneId ) ) {
				List<Equipmentdetail> equips = equipService.getAllEquipmentdetailByEquipInfo( oneId );
				for (Equipmentdetail equipmentdetail : equips) {
					equipmentdetail.setEquipmentid( Integer.valueOf( -1 ) );
					equipService.alterEquipmentdetail( equipmentdetail );
				}
				this.message = "删除型号成功！";
				this.tag = "0";
			} else {
				this.tag = "1";
				this.message = "删除型号失败！";
			}
		}
		//当前页数判断
		int curCountOfRow = equipService.getCountOfEquipInfo();
		int curPages = curCountOfRow / 10;
		if(curCountOfRow % 10 != 0 && curCountOfRow > 10) {
			curPages++;
		}
		if(Integer.valueOf(crtModelPage) > curPages && curPages > 0) {
			crtModelPage = String.valueOf(curPages);
		} else {
			crtModelPage = "1";
		}
		
		
		returnJSON.put("crtPage", this.crtModelPage);
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部设备型号信息列表
	 */
	private List<EquipModelCourse> allModelCourse;
	public List<EquipModelCourse> getAllModelCourse() {
		return allModelCourse;
	}
	
	private String classificationId;
	public String getClassificationId() {
		return classificationId;
	}
	public void setClassificationId(String classificationId) {
		this.classificationId = classificationId;
	}
	
	public String modelCount;
	public String getModelCount() {
		return modelCount;
	}
	
	public String totalModelPages;
	public String getTotalModelPages() {
		return totalModelPages;
	}
	
	public String crtModelPage;
	public String getCrtModelPage() {
		return crtModelPage;
	}
	public void setCrtModelPage(String crtModelPage) {
		this.crtModelPage = crtModelPage;
	}
	
	public String getAllEquipInfoCourse() {
		allModelCourse = new ArrayList<EquipModelCourse>();
		
		List<HQLOption> hqlOptionList = new ArrayList<HQLOption>();
		if(classificationId != null && !classificationId.equals("0")) {
			hqlOptionList.add( new HQLOption<Integer>("classificationid", Integer.valueOf(classificationId), SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_INT, SBGLConsistent.HQL_OPTION_AD) );
		}
		//筛选出中文型号名称
		hqlOptionList.add( new HQLOption<String>("lanType", "CH", SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_STR, SBGLConsistent.HQL_OPTION_AD) );
		
		if( crtModelPage != null && !crtModelPage.equals("0") && crtModelPage != "" ) {
			
		} else {
			crtModelPage = "1";
		}
		
		//判断是否新查询出来的页数小于上次查询的当前页数
		int curCount = 0;
		int curPages = 0;
		if(classificationId != null && !classificationId.equals("0")) {
			curCount = equipService.getEquipsByClassification( Integer.valueOf(classificationId) ).size()/2;
			curPages = curCount / 10;
		} else {
			curCount = equipService.getAllEquips().size()/2;
			curPages = curCount / 10;
		}
		if(curCount % 10 != 0 && curCount > 10) {
			curPages++;
		} else if(curCount % 10 != 0 && curCount < 10) {
			curPages = 1;
		} else if(curCount == 0) {
			curPages = 1;
		}
		if(Integer.valueOf( crtModelPage ) > curPages) {
			crtModelPage = String.valueOf( curPages );
		}
		
		Page page = new Page( (Integer.valueOf(crtModelPage)-1)*10, 10 );
		QueryResult result = equipService.getEquipmentByPageWithOptions(hqlOptionList, page);
		
		if(result != null) {
			modelCount = String.valueOf( result.getTotalResultNum() );
			if(result.getTotalResultNum() == 0) {
				totalModelPages = "1";
			} else if(result.getTotalResultNum() % 10 != 0 && result.getTotalResultNum() > 10) {
				totalModelPages = String.valueOf( result.getTotalResultNum() / 10 + 1 );
			} else if(result.getTotalResultNum() % 10 != 0 && result.getTotalResultNum() < 10) {
				totalModelPages = "1";
			} else {
				totalModelPages = String.valueOf( result.getTotalResultNum() / 10 );
			}
			
			for (Equipment equipment : (List<Equipment>)result.getResultList()) {
				EquipModelCourse emc = new EquipModelCourse();
				emc.setId( String.valueOf( equipment.getEquipmentid() ) );
				emc.setName( String.valueOf( equipment.getEquipmentname() ) );
				
				Equipmentclassification cf = equipService.getEquipmentclassificationById( equipment.getClassificationid() );
				emc.setcId( String.valueOf( cf == null ? -1 : cf.getClassificationid() ) );
				emc.setcName( cf == null ? "未分类" : cf.getName() );
				emc.setMemo( equipment.getEquipmentdetail() );
				emc.setComId( String.valueOf( equipment.getComId() ) );
				emc.setImgName( equipment.getImgName() );
				emc.setBranId( String.valueOf( equipment.getBrandid() ) );
				
				allModelCourse.add( emc );
			}
			
			if(Integer.valueOf( crtModelPage ) > Integer.valueOf( totalModelPages )) {
				crtModelPage = totalModelPages;
			}
			
		} else {
			modelCount = "0";
			totalModelPages = "1";
		}
		
		hqlOptionList.clear();
		if(classificationId != null && !classificationId.equals("0")) {
			hqlOptionList.add( new HQLOption<Integer>("classificationid", Integer.valueOf(classificationId), SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_INT, SBGLConsistent.HQL_OPTION_AD) );
		}
		//筛选出英文名称的型号
		hqlOptionList.add( new HQLOption<String>("lanType", "EN", SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_STR, SBGLConsistent.HQL_OPTION_AD) );
		
		Page pageEN = new Page( 0, 10000 );
		QueryResult resultEN = equipService.getEquipmentByPageWithOptions(hqlOptionList, pageEN);
		if(resultEN != null) {
			for (EquipModelCourse m : allModelCourse) {
				for (Equipment e : (List<Equipment>)resultEN.getResultList()) {
					if(m.getComId().equals( e.getComId().toString() )) {
						m.setIdEN( String.valueOf( e.getEquipmentid() ) );
						m.setNameEN( e.getEquipmentname() );
						m.setMemoEN( e.getEquipmentdetail() );
					}
				}
			}
		}
		
		
		return SUCCESS;
	}
	/**
	 * 获取用于器材添加或修改用的型号
	 */
	private List<EquipModelCourse> equipCourse = new ArrayList<EquipModelCourse>();
	public List<EquipModelCourse> getEquipCourse() {
		return equipCourse;
	}
	
	private void getAllModelForSelect(Integer classificationId) {
		List<Equipment> allModel = null;
		if(classificationId != null) {
			allModel = equipService.getEquipsByClassification( classificationId );
		} else {
			allModel = equipService.getAllEquips();
		}
		
		for (Equipment equipment : allModel) {
			EquipModelCourse emc = new EquipModelCourse();
			emc.setId( String.valueOf( equipment.getEquipmentid() ) );
			emc.setName( String.valueOf( equipment.getEquipmentname() ) );
			
			Equipmentclassification cf = equipService.getEquipmentclassificationById( equipment.getClassificationid() );
			emc.setcId( String.valueOf( cf == null ? -1 : cf.getClassificationid() ) );
			emc.setcName( cf == null ? "未分类" : cf.getName() );
			emc.setMemo( equipment.getEquipmentdetail() );
			emc.setImgName( equipment.getImgName() );
			emc.setBranId( String.valueOf( equipment.getBrandid() ) );
			
			equipCourse.add( emc );
		}
		
		List<String> classIds = new ArrayList<String>();
		List<EquipModelCourse> tempCourse = new ArrayList<EquipModelCourse>();
		for (EquipModelCourse ec : equipCourse) {
			String cId = ec.getcId();
			if( !classIds.contains(cId) ) {
				classIds.add( cId );
				EquipModelCourse showModelClass = new EquipModelCourse();
				showModelClass.setcName( ec.getcName() );
				showModelClass.setShowClass("1");
				tempCourse.add( showModelClass );
				
				for (EquipModelCourse equipModel : equipCourse) {
					if(equipModel.getcId().equals( cId )) {
						tempCourse.add( equipModel );
					}
				}
			}
		}
		equipCourse = tempCourse;
	}
	
	/**
	 * 添加设备
	 * 业务说明     1.对新添加的设备器材，要统计其状态并对型号库中相应状态的数量进行增加操作
	 *        2.器材状态说明：0-正常；1-借出；2-维护；3-维修；4-遗失；5-回收站
	 */
	private Equipmentdetail equipmentdetail;
	private Equipmentdetail equipmentdetailEN;
	public Equipmentdetail getEquipmentdetail() {
		return equipmentdetail;
	}
	public void setEquipmentdetail(Equipmentdetail equipmentdetail) {
		this.equipmentdetail = equipmentdetail;
	}
	public Equipmentdetail getEquipmentdetailEN() {
		return equipmentdetailEN;
	}
	public void setEquipmentdetailEN(Equipmentdetail equipmentdetailEN) {
		this.equipmentdetailEN = equipmentdetailEN;
	}
	private String manufactureDateStr;
	public String getManufactureDateStr() {
		return manufactureDateStr;
	}
	public void setManufactureDateStr(String manufactureDateStr) {
		this.manufactureDateStr = manufactureDateStr;
	}
	private String acquireDateStr;

	public String getAcquireDateStr() {
		return acquireDateStr;
	}
	public void setAcquireDateStr(String acquireDateStr) {
		this.acquireDateStr = acquireDateStr;
	}
	public String addEquipdetail() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Date mDate = null;
		Date aDate = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			mDate = sdf.parse(manufactureDateStr);
			aDate = sdf.parse(acquireDateStr);
		} catch (ParseException e) {
			this.tag = "200";
			this.message = "请检查输入日期格式是否正确！";
			returnJSON.put("tag", tag);
			returnJSON.put("msg", message);
			return SUCCESS;
		}
		
		Boolean isExist = equipService.isExistEquipDetial( equipmentdetail.getAssetNumber() );
		
		if(!isExist) {
			/*int comId = equipService.getEquipDetailComId();
			equipmentdetail.setComId( comId );
			equipmentdetailEN.setComId( comId );*/
			equipmentdetail.setManufactureDate( mDate );
			equipmentdetail.setAcquireDate( aDate );
			long returnCode = equipService.addEquipmentdetail( equipmentdetail );
//			long returnCodeEN = equipService.addEquipmentdetail( equipmentdetailEN );
			
			if( returnCode != -1 ) {
//				equipService.deleteEquipmentdetail( (int)returnCode );
				this.tag = "1";
				this.message = "添加设备失败！";
//			} else if( returnCode == -1 && returnCodeEN != -1) {
//				equipService.deleteEquipmentdetail( (int)returnCodeEN );
//				this.tag = "1";
//				this.message = "添加中文设备失败！";
//				log.error("################ 保存设备失败！ ################");
//			} else if( returnCode == -1 && returnCodeEN == -1) {
//				this.tag = "1";
//				this.message = "添加中文和英文设备失败！";
//				log.error("################ 保存设备失败！ ################");
			} else if( returnCode != -1 ) {
				this.tag = "0";
				this.message = "添加设备成功！";
			}
			
			if(equipmentdetail.getEquipmentid() != -1) {  //不是未知型号
				Equipment equip = equipService.getEquipmentById(  equipmentdetail.getEquipmentid() );
				
				String stateCodeStr = equipmentdetail.getStatus();
				Integer activeNum = equip.getActivenum() == null ? 0 : equip.getActivenum();
				Integer maintainNum = equip.getMaintainnum() == null ? 0 : equip.getMaintainnum();
				Integer repairNum = equip.getRepairnum() == null ? 0 : equip.getRepairnum();
				Integer losedNum = equip.getLosednum() == null ? 0 : equip.getLosednum();
				Integer recycleNum = equip.getRecyclingnum() == null ? 0 : equip.getRecyclingnum();
				
				if(stateCodeStr.equals("0")) {
					activeNum += 1;
				} else if(stateCodeStr.equals("1")) {
					//TODO 借出如何获取和存储借出数量
				} else if(stateCodeStr.equals("2")) {
					maintainNum += 1;
				} else if(stateCodeStr.equals("3")) {
					repairNum += 1;
				} else if(stateCodeStr.equals("4")) {
					losedNum += 1;
				} else if(stateCodeStr.equals("5")) {
					recycleNum += 1;
				}
				equip.setActivenum( activeNum );
				equip.setMaintainnum( maintainNum );
				equip.setRepairnum( repairNum );
				equip.setLosednum( losedNum );
				equip.setRecyclingnum( recycleNum );
				equipService.alterEquipInfo( equip );
			}
			
			
			if( returnCode != -1 ) {
				this.tag = "0";
				this.message = "器材添加成功！";
			} else {
				this.tag = "1";
				this.message = "器材添加失败！";
				log.error("################ 保存设备失败！ ################");
			}
			
		} else if(isExist) {
			this.tag = "100";
			this.message = "所填设备已经存在！";
		}
		
		
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	/**
	 * 修改设备
	 * 使用添加设备的参数 equipmentdetail
	 * 同样需要对型号库进行数量清点
	 */
	public String alterEquipmentdetail() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Date mDate = null;
		Date aDate = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			mDate = sdf.parse(manufactureDateStr);
			aDate = sdf.parse(acquireDateStr);
		} catch (ParseException e) {
			this.tag = "200";
			this.message = "请检查输入日期格式是否正确！";
			returnJSON.put("tag", tag);
			returnJSON.put("msg", message);
			return SUCCESS;
		}
		equipmentdetail.setAcquireDate( aDate );
		equipmentdetail.setManufactureDate( mDate );
		
		long returnCode = equipService.alterEquipmentdetail( equipmentdetail );
		if( returnCode != -1 ) {
			this.tag = "0";
			this.message = "器材修改成功！";
		} else {
			this.tag = "1";
			this.message = "器材修改失败！";
			log.error("################ 修改设备失败！ ################");
		}
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	/**
	 * 批量删除设备
	 * 使用添加设备的参数 equipmentdetail
	 * 器材设备删除业务说明 1.删除时需要统计该器材类型状态对型号库中的数量做减少操作
	 */
	private String equipdetailIds;
	public String getEquipdetailIds() {
		return equipdetailIds;
	}
	public void setEquipdetailIds(String equipdetailIds) {
		this.equipdetailIds = equipdetailIds;
	}

	public String deleteEquipmentdetail() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = equipdetailIds.split("_");
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
//			
//			Equipmentdetail ed = equipService.getEquipmentdetail( oneId );
//			if(ed.getEquipmentid() != -1) {  //不是未知型号
//				Equipment equip = equipService.getEquipmentById( ed.getEquipmentid() );
//				Integer activeNum = equip.getActivenum() == null ? 0 : equip.getActivenum();
//				Integer maintainNum = equip.getMaintainnum() == null ? 0 : equip.getMaintainnum();
//				Integer repairNum = equip.getRepairnum() == null ? 0 : equip.getRepairnum();
//				Integer losedNum = equip.getLosednum() == null ? 0 : equip.getLosednum();
//				Integer recycleNum = equip.getRecyclingnum() == null ? 0 : equip.getRecyclingnum();
//				
//				String stateCodeStr = ed.getStatus();
//				if(stateCodeStr.equals("0")) {
//					activeNum -= 1;
//				} else if(stateCodeStr.equals("1")) {
//					//TODO 借出如何获取和存储借出数量
//				} else if(stateCodeStr.equals("2")) {
//					maintainNum -= 1;
//				} else if(stateCodeStr.equals("3")) {
//					repairNum -= 1;
//				} else if(stateCodeStr.equals("4")) {
//					losedNum -= 1;
//				} else if(stateCodeStr.equals("5")) {
//					recycleNum -= 1;
//				}
//				equip.setActivenum( activeNum );
//				equip.setMaintainnum( maintainNum );
//				equip.setRepairnum( repairNum );
//				equip.setLosednum( losedNum );
//				equip.setRecyclingnum( recycleNum );
//				equipService.alterEquipInfo( equip );
//			}
			
			if( equipService.deleteEquipmentdetail( oneId ) ) {
				this.tag = "0";
				this.message = "器材删除成功！";
			} else {
				this.tag = "1";
				this.message = "器材删除失败！";
			}
		}
		//当前页数判断
		List<HQLOption> hqlOptionList =  new ArrayList<HQLOption>();
		if(classificationId != null && !classificationId.equals("0")) {
			if("-2".equals(classificationId)) {  //如果是未分类的情况下
				hqlOptionList.add( new HQLOption<Integer>("equipmentid", -1, SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_INT, SBGLConsistent.HQL_OPTION_AD) );
			} else {
				List<Equipment> modelList = equipService.getEquipsByClassification( Integer.valueOf( classificationId ) );
				StringBuffer sb = new StringBuffer();
				if(modelList != null && modelList.size() > 0) {
					for (Equipment e : modelList) {
						sb.append( e.getEquipmentid() + "," );
					}
					hqlOptionList.add( new HQLOption<String>("equipmentid", sb.toString(), SBGLConsistent.HQL_OPTION_IN, SBGLConsistent.HQL_VALUE_INT, SBGLConsistent.HQL_OPTION_AD) );
				}
			}
		}
		if(!"-1".equals(selectedState) && selectedState != null) {
			hqlOptionList.add( new HQLOption<String>("status", selectedState, SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_STR, SBGLConsistent.HQL_OPTION_AD) );
		}
		Page page = new Page(0, 10000);
		QueryResult result = equipService.getEquipDetailByPageWithOptions(hqlOptionList, page);
		
		int curCountOfRow = 0;
		if(result != null) {
			curCountOfRow = result.getTotalResultNum();
		}
		int curPages = curCountOfRow / 10;
		if(curCountOfRow % 10 != 0 && curCountOfRow > 10) {
			curPages++;
		}
		if(Integer.valueOf(crtDetailPage) > curPages && curPages > 0) {
			crtDetailPage = String.valueOf(curPages);
		} else {
			crtDetailPage = "1";
		}
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		returnJSON.put("crtPage", crtDetailPage);
		return SUCCESS;
	}
	/**
	 * 获取设备详情列表
	 */
	private List<EquipCourse> equipDetailCourse;
	public List<EquipCourse> getEquipDetailCourse() {
		return equipDetailCourse;
	}
	private String crtDetailPage;
	private String totalDetailPages;
	private String totalDetailSum;	//器材总数
	private String normalSum;		//正常数
	private String loanSum;			//借出数
	private String maintSum;		//维护数
	private String repairSum;		//维修数
	private String lostSum;			//遗失数
	private String recycleSum;		//回收站数
	private String selectedState;   //筛选的器材状态
	
	public String getTotalDetailSum() {
		return totalDetailSum;
	}
	public String getNormalSum() {
		return normalSum;
	}
	public String getLoanSum() {
		return loanSum;
	}
	public String getMaintSum() {
		return maintSum;
	}
	public String getRepairSum() {
		return repairSum;
	}
	public String getLostSum() {
		return lostSum;
	}
	public String getRecycleSum() {
		return recycleSum;
	}
	public String getSelectedState() {
		return selectedState;
	}
	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}
	public String getCrtDetailPage() {
		return crtDetailPage;
	}
	public void setCrtDetailPage(String crtDetailPage) {
		this.crtDetailPage = crtDetailPage;
	}
	public String getTotalDetailPages() {
		return totalDetailPages;
	}

	public String showAllEquipDetailCourse() {
		equipDetailCourse = new ArrayList<EquipCourse>();
		List<Equipmentdetail> tempList = null;
		int _0 = 0, _1 = 0, _2 = 0, _3 = 0, _4 = 0, _5 = 0, _t = 0;
		
		//如果界面按分类筛选器材
		/*if(seletedClassId != null && !classificationId.equals("0")) {
			tempList = new ArrayList<Equipmentdetail>();
			List<Equipment> modelList = equipService.getEquipsByClassification( Integer.valueOf( seletedClassId ) );
			if(modelList != null) {
				List<Equipmentdetail> partList = null;
				for (Equipment e : modelList) {
					partList = equipService.getAllEquipmentdetailByEquipInfo( e.getEquipmentid() );
					if(partList != null) {
						tempList.addAll( partList );
					}
				}
			}
		} else {
			tempList = equipService.getAllEquipmentdetail();
		}*/
		
		//如果界面按器材状态筛选
		/*if(selectedState != null && !"-1".equals( selectedState ) && tempList.size() > 0) {
			for (int i=0; i<tempList.size(); i++) {
				if(!tempList.get(i).getStatus().equals( selectedState )) {
					tempList.remove( i );
				}
			}
		}*/
		List<HQLOption> hqlOptionList =  new ArrayList<HQLOption>();
		if(classificationId != null && !classificationId.equals("0")) {
			if("-2".equals(classificationId)) {  //如果是未分类的情况下
				hqlOptionList.add( new HQLOption<Integer>("equipmentid", -1, SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_INT, SBGLConsistent.HQL_OPTION_AD) );
			} else {
				List<Equipment> modelList = equipService.getEquipsByClassification( Integer.valueOf( classificationId ) );
				StringBuffer sb = new StringBuffer();
				if(modelList != null && modelList.size() > 0) {
					for (Equipment e : modelList) {
						sb.append( e.getEquipmentid() + "," );
					}
					hqlOptionList.add( new HQLOption<String>("equipmentid", sb.toString(), SBGLConsistent.HQL_OPTION_IN, SBGLConsistent.HQL_VALUE_INT, SBGLConsistent.HQL_OPTION_AD) );
				}
				//由于更新界面器材状态数量的需要，此时需要统计在当前分类下的各种状态的数量
//				QueryResult tempResult = equipService.getEquipDetailByPageWithOptions(hqlOptionList, new Page(0, 10000));
//				if(tempResult != null && tempResult.getTotalResultNum() > 0) {
//					tempList = (List<Equipmentdetail>) tempResult.getResultList();
//				}
			}
			QueryResult tempResult = equipService.getEquipDetailByPageWithOptions(hqlOptionList, new Page(0, 10000));
			if(tempResult != null && tempResult.getTotalResultNum() > 0) {
				tempList = (List<Equipmentdetail>) tempResult.getResultList();
			}
		} else {
			QueryResult tempResult = equipService.getEquipDetailByPageWithOptions(null, new Page(0, 10000));
			if(tempResult != null && tempResult.getTotalResultNum() > 0) {
				tempList = (List<Equipmentdetail>) tempResult.getResultList();
			}
		}
		
		if(tempList != null && tempList.size() > 0) {
			_t = tempList.size();
			for (Equipmentdetail d : tempList) {
				if( "0".equals( d.getStatus() ) ) {
					_0 ++;
				} else if( "1".equals( d.getStatus() ) ) {
					_1 ++;
				} else if( "2".equals( d.getStatus() ) ) {
					_2 ++;
				} else if( "3".equals( d.getStatus() ) ) {
					_3 ++;
				} else if( "4".equals( d.getStatus() ) ) {
					_4 ++;
				} else if( "5".equals( d.getStatus() ) ) {
					_5 ++;
				}
			}
			totalDetailSum = String.valueOf( _t );
			normalSum = String.valueOf( _0 );
			loanSum = String.valueOf( _1 );
			maintSum = String.valueOf( _2 );
			repairSum = String.valueOf( _3 );
			lostSum = String.valueOf( _4 );
			recycleSum = String.valueOf( _5 );
		} else {
			totalDetailSum = String.valueOf( "0" );
			normalSum = String.valueOf( "0" );
			loanSum = String.valueOf( "0" );
			maintSum = String.valueOf( "0" );
			repairSum = String.valueOf( "0" );
			lostSum = String.valueOf( "0" );
			recycleSum = String.valueOf( "0" );
		}
		
		if(!"-1".equals(selectedState) && selectedState != null) {
			hqlOptionList.add( new HQLOption<String>("status", selectedState, SBGLConsistent.HQL_OPTION_EQ, SBGLConsistent.HQL_VALUE_STR, SBGLConsistent.HQL_OPTION_AD) );
		}
		if( crtDetailPage != null && !crtDetailPage.equals("0") && crtDetailPage != "" ) {
			
		} else {
			crtDetailPage = "1";
		}
		Page page = new Page( (Integer.valueOf(crtDetailPage)-1)*10, 10 );
		QueryResult result = null;
		if(hqlOptionList.size() == 0) {
			result = equipService.getEquipDetailByPageWithOptions(null, page);
		} else {
			result = equipService.getEquipDetailByPageWithOptions(hqlOptionList, page);
		}
		if(result != null) {
			tempList = (List<Equipmentdetail>) result.getResultList();
			
//			if("0".equals(selectedState)) {
//				normalSum = String.valueOf( result.getTotalResultNum() );
//			} else if("1".equals(selectedState)) {
//				loanSum = String.valueOf( result.getTotalResultNum() );
//			} else if("2".equals(selectedState)) {
//				maintSum = String.valueOf( result.getTotalResultNum() );
//			} else if("3".equals(selectedState)) {
//				repairSum = String.valueOf( result.getTotalResultNum() );
//			} else if("4".equals(selectedState)) {
//				lostSum = String.valueOf( result.getTotalResultNum() );
//			} else if("5".equals(selectedState)) {
//				recycleSum = String.valueOf( result.getTotalResultNum() );
//			}
			if(result.getTotalResultNum() == 0) {
				totalDetailPages = "1";
			} else if(result.getTotalResultNum() % 10 != 0 && result.getTotalResultNum() > 10) {
				totalDetailPages = String.valueOf( result.getTotalResultNum() / 10 + 1 );
			} else if(result.getTotalResultNum() % 10 != 0 && result.getTotalResultNum() < 10) {
				totalDetailPages = "1";
			} else {
				totalDetailPages = String.valueOf( result.getTotalResultNum() / 10 );
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Equipmentdetail equipdetail : tempList) {
			EquipCourse ec = new EquipCourse();
			ec.setId( String.valueOf( equipdetail.getEquipDetailid() ) );
			
			if(equipdetail.getEquipmentid() == -1) {  //如果该器材未知型号
				
				ec.setModelId( String.valueOf( -1 ) );
				ec.setModelName( "未知型号" );
				ec.setClassId( String.valueOf( -1 ) );
				ec.setClassName( "未知分类" );
//				ec.setCode( String.valueOf( equipdetail.getEquipserial() ) );
				ec.setState( String.valueOf( equipdetail.getStatus() ) );
				if(equipdetail.getSysremark() != null && equipdetail.getUsermark() != null) {
					ec.setMemo( equipdetail.getSysremark() + " " + equipdetail.getUsermark());
				} else if(equipdetail.getSysremark() == null && equipdetail.getUsermark() != null) {
					ec.setMemo(equipdetail.getUsermark());
				} else if(equipdetail.getSysremark() != null && equipdetail.getUsermark() == null) {
					ec.setMemo( equipdetail.getSysremark());
				} else {
					ec.setMemo("");
				}
				ec.setaDate( equipdetail.getAcquireDate()==null?"":sdf.format( equipdetail.getAcquireDate() ) );
				ec.setAssetNumber( String.valueOf( equipdetail.getAssetNumber() ) );
				ec.setEquipserial( equipdetail.getEquipserial() );
				ec.setManager( equipdetail.getManager() );
				ec.setManufacturer( equipdetail.getManufacturer() );
				ec.setmDate( equipdetail.getManufactureDate()==null?"":sdf.format( equipdetail.getManufactureDate() ) );
				ec.setStoragePlace( equipdetail.getStoragePlace() );
				ec.setStoragePosition( equipdetail.getStoragePosition() );
				ec.setStorenumber( String.valueOf( equipdetail.getStorenumber() ) );
				ec.setSupplyer( equipdetail.getSupplyer() );
				ec.setUseManageDept( equipdetail.getUseManageDept() );
				ec.setWorth( String.valueOf( equipdetail.getWorth() ) );
				
				equipDetailCourse.add( ec );
			} else {
				Equipment e = equipService.getEquipmentById( equipdetail.getEquipmentid() );
			
				if(e != null) {
					ec.setModelId( String.valueOf( e.getEquipmentid() ) );
					ec.setModelName( e.getEquipmentname() );
					if(e.getClassificationid() != -1) { //该设备有分类
						Equipmentclassification ecf = equipService.getEquipmentclassificationByEquipmentModel( equipdetail.getEquipmentid() );
						if(ecf != null) {
							ec.setClassId(  String.valueOf( ecf.getClassificationid() ) );
							ec.setClassName( ecf.getName() );
						}
					} else {
						ec.setClassId(  String.valueOf( -1 ) );
						ec.setClassName( "未分类" );
					}
//					ec.setCode( String.valueOf( equipdetail.getEquipserial() ) );
					ec.setState( String.valueOf( equipdetail.getStatus() ) );
					if(equipdetail.getSysremark() != null && equipdetail.getUsermark() != null) {
						ec.setMemo( equipdetail.getSysremark() + " " + equipdetail.getUsermark());
					} else if(equipdetail.getSysremark() == null && equipdetail.getUsermark() != null) {
						ec.setMemo(equipdetail.getUsermark());
					} else if(equipdetail.getSysremark() != null && equipdetail.getUsermark() == null) {
						ec.setMemo( equipdetail.getSysremark());
					} else {
						ec.setMemo("");
					}
					ec.setaDate( equipdetail.getAcquireDate()==null?"":sdf.format( equipdetail.getAcquireDate() ) );
					ec.setAssetNumber( String.valueOf( equipdetail.getAssetNumber() ) );
					ec.setEquipserial( equipdetail.getEquipserial() );
					ec.setManager( equipdetail.getManager() );
					ec.setManufacturer( equipdetail.getManufacturer() );
					ec.setmDate( equipdetail.getManufactureDate()==null?"":sdf.format( equipdetail.getManufactureDate() ) );
					ec.setStoragePlace( equipdetail.getStoragePlace() );
					ec.setStoragePosition( equipdetail.getStoragePosition() );
					ec.setStorenumber( String.valueOf( equipdetail.getStorenumber() ) );
					ec.setSupplyer( equipdetail.getSupplyer() );
					ec.setUseManageDept( equipdetail.getUseManageDept() );
					ec.setWorth( String.valueOf( equipdetail.getWorth() ) );
					
					equipDetailCourse.add( ec );
				}
			}
		}
		//进行分页操作
		/*if(equipDetailCourse != null) {
			if(equipDetailCourse.size() == 0) {
				totalDetailPages = "1";
			} else if(equipDetailCourse.size() % 10 != 0 && equipDetailCourse.size() > 10) {
				totalDetailPages = String.valueOf( equipDetailCourse.size() / 10 + 1 );
			} else if(equipDetailCourse.size() % 10 != 0 && equipDetailCourse.size() < 10) {
				totalDetailPages = "1";
			} else {
				totalDetailPages = String.valueOf( equipDetailCourse.size() / 10 );
			}
			if(crtDetailPage == "0" || crtDetailPage == "" || crtDetailPage == null) {
				crtDetailPage = "1";
			}
			
			if(crtDetailPage != null && crtDetailPage != "") {
				int startIndex = Integer.valueOf( crtDetailPage.trim() ) - 1;
				int endIndex = (startIndex + 1) * 10 > equipDetailCourse.size() ? equipDetailCourse.size() : (startIndex + 1) * 10;
				equipDetailCourse = equipDetailCourse.subList(startIndex*10, endIndex);
			}
			
		} else {
			totalDetailPages = "1";
		}*/
		return SUCCESS;
	}
	
	/**
	 * 视图资源映射
	 * 
	 */
	//设备管理首页
	public String gotoEquipManageAdmin() {
		showAllEquipDetailCourse();
		getAllModelForSelect(null);
		doGetClassForEquipAdd();
		
		return SUCCESS;
	}
	/**
	 * 设备管理-添加设备
	 * @return
	 */
	private String seletedClassId;
	public String getSeletedClassId() {
		return seletedClassId;
	}
	public void setSeletedClassId(String seletedClassId) {
		this.seletedClassId = seletedClassId;
	}
	
	public String gotoEquipManageEquip() {
		Integer cId = null;
		if( seletedClassId != null && !seletedClassId.equals("0") ) {
			cId = Integer.valueOf( seletedClassId );
			getAllModelForSelect(cId);
		} else {
			getAllModelForSelect(null);
		}
		
		doGetClassForEquipAdd();
		
		return SUCCESS;
	}
	/**设备管理-型号管理
	 * 
	 * @return
	 */
	public List<ClassficationCourse> classForEquipAdd = new ArrayList<ClassficationCourse>();
	public List<ClassficationCourse> getClassForEquipAdd() {
		return classForEquipAdd;
	}
	
	private void doGetClassForEquipAdd() {
		List<Equipmentclassification> ecList = equipService.getAllCHEquipmentclassifications();  //获取全部中文名称的分类
		for (Equipmentclassification ec : ecList) {
			if(ec.getParentid() == 0) {
				ClassficationCourse cc = new ClassficationCourse();
				cc.setId( String.valueOf( ec.getClassificationid() ) );
				cc.setName( ec.getName() );
				cc.setpId( "0" );
				cc.setIsParent( "1" );
				classForEquipAdd.add( cc );
				
				List<Equipmentclassification> tempList = equipService.getAllChildEquipmentclassificationsByParentId( ec.getClassificationid() );
				if(tempList != null) {
					for (Equipmentclassification ec2 : tempList) {
						ClassficationCourse cc2 = new ClassficationCourse();
						cc2.setId( String.valueOf( ec2.getClassificationid() ) );
						cc2.setName( ec2.getName() );
						cc2.setpId( String.valueOf( ec2.getParentid() ) );
						cc2.setIsParent( "0" );
						classForEquipAdd.add( cc2 );
					}
				}
			}
		}
	}

	public String gotoEquipManageModel() {
		getAllEquipInfoCourse();
		doGetClassForEquipAdd();
		
		return SUCCESS;
	}
	/**
	 * 设备管理-分类管理
	 * 进入分类管理页面同步刷新数据
	 */
	List<ClassficationCourse> allClassCourse = new ArrayList<ClassficationCourse>();
	public List<ClassficationCourse> getAllClassCourse() {
		return allClassCourse;
	}
	
	List<ParentClassIdName> allParent = new ArrayList<ParentClassIdName>();
	public List<ParentClassIdName> getAllParent() {
		return allParent;
	}
	
	public String classSum;
	public String getClassSum() {
		return classSum;
	}
	
	public String currentPage;
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	
	public String totalPage;
	public String getTotalPage() {
		return totalPage;
	}
	
	public String gotoEquipManageClassfiction() {
		allParent.clear();
		List<Equipmentclassification> equipList =  equipService.getAllEquipmentclassifications();
		
		if(equipList != null) {
			classSum = String.valueOf( equipList.size()/2 );
			if(equipList == null || equipList.size() == 0) {
				totalPage = "1";
			} else if(equipList.size()/2 % 10 != 0 && equipList.size()/2 > 10) {
				totalPage = String.valueOf( equipList.size()/2 / 10 + 1 );
			} else if(equipList.size()/2 % 10 != 0 && equipList.size()/2 < 10) {
				totalPage = "1";
			} else {
				totalPage = String.valueOf( equipList.size()/2 / 10 );
			}
			if(currentPage == "0" || currentPage == "" || currentPage == null) {
				currentPage = "1";
			}
			for (Equipmentclassification equipmentclassification : equipList) {
				if( "CH".equals( equipmentclassification.getLanType() ) ) {
					String name = equipmentclassification.getName();
					Integer parentId = equipmentclassification.getParentid();
					if( parentId == 0 ) {
						idNameMap.put(equipmentclassification.getClassificationid(), name);
					}
				}
			}
			List<ClassficationCourse> tempCourse = new ArrayList<ClassficationCourse>();
			for (Equipmentclassification classfication : equipList) {
				if( "CH".equals( classfication.getLanType() ) ) {
					ClassficationCourse cc = new ClassficationCourse();
					cc.setId( String.valueOf( classfication.getClassificationid() ) );
					cc.setName( classfication.getName() );
					if( classfication.getParentid() == 0 ) {
						cc.setModelCount( String.valueOf( equipService.getCountOfEquipByClassification( classfication.getClassificationid(), true ) ) );
						cc.setEquipCount( String.valueOf( equipService.getCountOfEquipdetailByClassification( classfication.getClassificationid(), true ) ) );
					} else {
						cc.setModelCount( String.valueOf( equipService.getCountOfEquipByClassification( classfication.getClassificationid(), false ) ) );
						cc.setEquipCount( String.valueOf( equipService.getCountOfEquipdetailByClassification( classfication.getClassificationid(), false ) ) );
					}
					cc.setpId( String.valueOf( classfication.getParentid() ) );
					cc.setpName( idNameMap.get( classfication.getParentid() ) == null ? "无" : idNameMap.get( classfication.getParentid() ) );
					cc.setComId( String.valueOf( classfication.getComId() ) );
					tempCourse.add( cc );
					
					if(classfication.getParentid() == 0) {
						allParent.add( new ParentClassIdName( classfication.getClassificationid(), classfication.getName() ) );
					}
				}
			}
			List<ClassficationCourse> tempCourseEN = new ArrayList<ClassficationCourse>();
			for (Equipmentclassification classfication : equipList) {
				if( "EN".equals( classfication.getLanType() ) ) {
					ClassficationCourse cc = new ClassficationCourse();
					cc.setIdEN( String.valueOf( classfication.getClassificationid() ) );
					cc.setNameEN( classfication.getName() );
					cc.setComId( String.valueOf( classfication.getComId() ) );
					tempCourseEN.add( cc );
				}
			}
			for (ClassficationCourse c1 : tempCourse) {
				for (ClassficationCourse c2 : tempCourseEN) {
					if( c1.getComId().equals(c2.getComId()) ) {
						ClassficationCourse cc = new ClassficationCourse();
						cc.setComId( c1.getComId() );
						cc.setEquipCount( c1.getEquipCount() );
						cc.setId( c1.getId() );
						cc.setIdEN( c2.getIdEN() );
						cc.setModelCount( c1.getModelCount() );
						cc.setName( c1.getName() );
						cc.setNameEN( c2.getNameEN() );
						cc.setpId( c1.getpId() );
						cc.setpName( c1.getpName() );
						allClassCourse.add( cc );
					}
				}
			}
			//根据前台页面请求页码返回数据
			if(currentPage != null && currentPage != "") {
				int startIndex = Integer.valueOf( currentPage.trim() ) - 1;
				int endIndex = (startIndex + 1) * 10 > equipList.size()/2 ? equipList.size()/2 : (startIndex + 1) * 10;
				allClassCourse = allClassCourse.subList(startIndex*10, endIndex);
			}
			
		} else {
			classSum = "0";
			totalPage = "1";
		}
		return SUCCESS;
	}
	
	/**
	 * 异步请求刷新界面数据
	 */
	//刷新分类界面
	public String flushClassification() {
		gotoEquipManageClassfiction();
		return SUCCESS;
	}
	//刷新器材界面
	public String flushEquipDetail() {
		gotoEquipManageAdmin();
		return SUCCESS;
	}
	//刷新型号界面
	public String flushEquipModel() {
		gotoEquipManageModel();
		return SUCCESS;
	}
	//刷新器材添加界面
	public String flushEquipDetailForAdd() {
		gotoEquipManageEquip();
		return SUCCESS;
	}
	//刷新器材型号下拉列表
	public String flushEquipDetailForSelect() {
		Integer cId = null;
		if( seletedClassId != null && !seletedClassId.equals("0") ) {
			cId = Integer.valueOf( seletedClassId );
			getAllModelForSelect(cId);
		} else {
			getAllModelForSelect(null);
		}
		return SUCCESS;
	}
}
