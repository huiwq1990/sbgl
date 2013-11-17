package com.sbgl.app.actions.equipment;

import java.util.ArrayList;
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
import com.sbgl.app.entity.Equipment;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentdetail;
import com.sbgl.app.services.equipment.EquipService;
import com.sbgl.util.Page;

@Scope("prototype") 
@Controller("EquipmentAction")
public class EquipmentAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	Page page = new Page();
	Integer pageNo=1;	
	
	private static final Log log = LogFactory.getLog(EquipmentAction.class);
	@SuppressWarnings("unused")
	private Map<String, Object> session;
	private String tag;    //返回执行结果 0-成功 1-失败
	
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

	public String getTag() {
		return tag;
	}


	/**
	 * 添加分类信息
	 */
	private Equipmentclassification equipClassforAdd;
	public Equipmentclassification getEquipClassforAdd() {
		return equipClassforAdd;
	}
	public void setEquipClassforAdd(Equipmentclassification equipClassforAdd) {
		this.equipClassforAdd = equipClassforAdd;
	}

	public String addEquipmentclassification() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		long returnCode = equipService.addEquipmentclassification( equipClassforAdd );
		if( returnCode != -1 ) {
			this.tag = "0";
		} else {
			this.tag = "1";
			log.error("################ 保存器材分类失败！ ################");
		}
		returnJSON.put("addClassTag", tag);
		return SUCCESS;
	}
	/**
	 * 修改分类信息
	 * 使用添加分类的参数 equipmentclassification 作为传入变量
	 */
	private Equipmentclassification equipClassforAltert;
	public Equipmentclassification getEquipClassforAltert() {
		return equipClassforAltert;
	}
	public void setEquipClassforAltert(Equipmentclassification equipClassforAltert) {
		this.equipClassforAltert = equipClassforAltert;
	}

	public String alterEquipmentclassification() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		long returnCode = equipService.alterEquipmentclassification( equipClassforAltert );
		if( returnCode != -1) {
			this.tag = "0";
		} else {
			this.tag = "1";
			log.error("################ 修改器材分类失败！ ################");
		}
		returnJSON.put("alterClassTag", tag);
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
					cc.setModelCount( String.valueOf( equipService.getCountOfEquipByClassification(classfication.getClassificationid()) ) );
					cc.setEquipCount( String.valueOf( equipService.getCountOfEquipdetailByClassification(classfication.getClassificationid() ) ) );
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
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
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
		} catch(RuntimeException re) {
			re.printStackTrace();
			this.tag = "200";
		}
		
		this.tag = "0";
		returnJSON.put("tag", this.tag);
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
				this.tag = "0";
				break;
			} else {
				this.tag = "1";
			}
		}
		returnJSON.put("tag", tag);
		return SUCCESS;
	}

	/**
	 * 添加设备型号
	 */
	private Equipment equipment;
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public String addEquipInfo() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		long returnCode = equipService.addEquipInfo( equipment );
		if( returnCode != -1 ) {
			this.tag = "0";
		} else {
			this.tag = "1";
			log.error("################ 保存设备型号失败！ ################");
		}
		returnJSON.put("tag", tag);
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
		if( returnCode != -1 ) {
			this.tag = "0";
		} else {
			this.tag = "1";
			log.error("################ 修改设备型号失败！ ################");
		}
		returnJSON.put("tag", tag);
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
				
				this.tag = "0";
			} else {
				this.tag = "1";
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获取全部设备型号信息列表
	 */
	private List<EquipModelCourse> equipCourse = new ArrayList<EquipModelCourse>();
	public List<EquipModelCourse> getEquipCourse() {
		return equipCourse;
	}

	public String getAllEquipInfoCourse() {
			List<Equipment> allEquips = equipService.getAllEquips();
			for (Equipment equipment : allEquips) {
				EquipModelCourse emc = new EquipModelCourse();
				emc.setId( String.valueOf( equipment.getEquipmentid() ) );
				emc.setName( String.valueOf( equipment.getEquipmentname() ) );
				
				Equipmentclassification cf = equipService.getEquipmentclassificationById( equipment.getClassificationid() );
				emc.setcId( String.valueOf( cf == null ? -1 : cf.getClassificationid() ) );
				emc.setcName( cf == null ? "未分类" : cf.getName() );
				emc.setMemo( equipment.getEquipmentdetail() );
				
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
		
//		returnJSON.put("allEquips", allEquips);
//		returnJSON.put("classIdName", classIdName);
		return SUCCESS;
	}
	
	/**
	 * 添加设备
	 * 业务说明     1.对新添加的设备器材，要统计其状态并对型号库中相应状态的数量进行增加操作
	 *        2.器材状态说明：0-正常；1-借出；2-维护；3-维修；4-遗失；5-回收站
	 */
	private Equipmentdetail equipmentdetail;
	public Equipmentdetail getEquipmentdetail() {
		return equipmentdetail;
	}
	public void setEquipmentdetail(Equipmentdetail equipmentdetail) {
		this.equipmentdetail = equipmentdetail;
	}
	

	public String addEquipdetail() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		long returnCode = equipService.addEquipmentdetail( equipmentdetail );
		Equipment equip = equipService.getEquipmentById(  equipmentdetail.getEquipmentid() );
		
		String stateCodeStr = equipmentdetail.getStatus();
		Integer activeNum = equip.getActivenum();
		Integer maintainNum = equip.getMaintainnum();
		Integer repairNum = equip.getRepairnum();
		Integer losedNum = equip.getLosednum();
		Integer recycleNum = equip.getRecyclingnum();
		
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
		
		if( returnCode != -1 ) {
			this.tag = "0";
		} else {
			this.tag = "1";
			log.error("################ 保存设备失败！ ################");
		}
		returnJSON.put("tag", tag);
		return SUCCESS;
	}
	/**
	 * 修改设备
	 * 使用添加设备的参数 equipmentdetail
	 * 同样需要对型号库进行数量清点
	 */
	public String alterEquipmentdetail() {
		Equipmentdetail temp = equipService.getEquipmentdetail( equipmentdetail.getEquipDetailid() );
		Equipment equip = equipService.getEquipmentById( equipmentdetail.getEquipmentid() );
		
		String stateCodeStr = equipmentdetail.getStatus();
		String stateCodeOld = temp.getStatus();
		
		Integer activeNum = equip.getActivenum();
		Integer maintainNum = equip.getMaintainnum();
		Integer repairNum = equip.getRepairnum();
		Integer losedNum = equip.getLosednum();
		Integer recycleNum = equip.getRecyclingnum();
		
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
		if(stateCodeOld.equals("0")) {
			activeNum -= 1;
		} else if(stateCodeOld.equals("1")) {
			//TODO 借出如何获取和存储借出数量
		} else if(stateCodeOld.equals("2")) {
			maintainNum -= 1;
		} else if(stateCodeOld.equals("3")) {
			repairNum -= 1;
		} else if(stateCodeOld.equals("4")) {
			losedNum -= 1;
		} else if(stateCodeOld.equals("5")) {
			recycleNum -= 1;
		}
		equip.setActivenum( activeNum );
		equip.setMaintainnum( maintainNum );
		equip.setRepairnum( repairNum );
		equip.setLosednum( losedNum );
		equip.setRecyclingnum( recycleNum );
		equipService.alterEquipInfo( equip );
		
		long returnCode = equipService.alterEquipmentdetail( equipmentdetail );
		if( returnCode != -1 ) {
			this.tag = "0";
		} else {
			this.tag = "1";
			log.error("################ 修改设备失败！ ################");
		}
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
		Equipment equip = equipService.getEquipmentById( equipmentdetail.getEquipmentid() );
		Integer activeNum = equip.getActivenum();
		Integer maintainNum = equip.getMaintainnum();
		Integer repairNum = equip.getRepairnum();
		Integer losedNum = equip.getLosednum();
		Integer recycleNum = equip.getRecyclingnum();
		
		String stateCodeStr = equipmentdetail.getStatus();
		if(stateCodeStr.equals("0")) {
			activeNum -= 1;
		} else if(stateCodeStr.equals("1")) {
			//TODO 借出如何获取和存储借出数量
		} else if(stateCodeStr.equals("2")) {
			maintainNum -= 1;
		} else if(stateCodeStr.equals("3")) {
			repairNum -= 1;
		} else if(stateCodeStr.equals("4")) {
			losedNum -= 1;
		} else if(stateCodeStr.equals("5")) {
			recycleNum -= 1;
		}
		equip.setActivenum( activeNum );
		equip.setMaintainnum( maintainNum );
		equip.setRepairnum( repairNum );
		equip.setLosednum( losedNum );
		equip.setRecyclingnum( recycleNum );
		equipService.alterEquipInfo( equip );
		
		String[] ids = equipdetailIds.split("_");
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			if( equipService.deleteEquipmentdetail( oneId ) ) {
				this.tag = "0";
			} else {
				this.tag = "1";
			}
		}
		return SUCCESS;
	}
	/**
	 * 获取设备详情列表
	 */
	private List<EquipCourse> equipDetailCourse;
	public List<EquipCourse> getEquipDetailCourse() {
		return equipDetailCourse;
	}

	public String showAllEquipDetailCourse() {
		equipDetailCourse = new ArrayList<EquipCourse>();
		List<Equipmentdetail> tempList = equipService.getAllEquipmentdetail();
		for (Equipmentdetail equipdetail : tempList) {
			EquipCourse ec = new EquipCourse();
			ec.setId( String.valueOf( equipdetail.getEquipDetailid() ) );
			
			if(equipdetail.getEquipmentid() == -1) {  //如果该器材未知型号
				
				ec.setModelId( String.valueOf( -1 ) );
				ec.setModelName( "未知型号" );
				ec.setClassId( String.valueOf( -1 ) );
				ec.setClassName( "未知分类" );
				ec.setCode( String.valueOf( equipdetail.getEquipserial() ) );
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
					ec.setCode( String.valueOf( equipdetail.getEquipserial() ) );
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
					equipDetailCourse.add( ec );
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 视图资源映射
	 * 
	 */
	//设备管理首页
	public String gotoEquipManageAdmin() {
		showAllEquipDetailCourse();
		getAllEquipInfoCourse();
		
		return SUCCESS;
	}
	/**
	 * 设备管理-添加设备
	 * @return
	 */
	public String gotoEquipManageEquip() {
		getAllEquipInfoCourse();
		
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
		List<Equipmentclassification> ecList = equipService.getAllEquipmentclassifications();
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

	
	
	public String gotoEquipManageClassfiction() {
		
		List<Equipmentclassification> equipList =  equipService.getAllEquipmentclassifications();
		
		if(equipList != null) {
			classSum = String.valueOf( equipList.size() );
			
			for (Equipmentclassification equipmentclassification : equipList) {
				String name = equipmentclassification.getName();
				Integer parentId = equipmentclassification.getParentid();
				if( parentId == 0 ) {
					idNameMap.put(equipmentclassification.getClassificationid(), name);
				}
			}
			for (Equipmentclassification classfication : equipList) {
				ClassficationCourse cc = new ClassficationCourse();
				cc.setId( String.valueOf( classfication.getClassificationid() ) );
				cc.setName( classfication.getName() );
				cc.setModelCount( String.valueOf( equipService.getCountOfEquipByClassification(classfication.getClassificationid()) ) );
				cc.setEquipCount( String.valueOf( equipService.getCountOfEquipdetailByClassification(classfication.getClassificationid() ) ) );
				cc.setpId( String.valueOf( classfication.getParentid() ) );
				cc.setpName( idNameMap.get( classfication.getParentid() ) == null ? "无" : idNameMap.get( classfication.getParentid() ) );
				allClassCourse.add( cc );
				
				if(classfication.getParentid() == 0) {
					allParent.add( new ParentClassIdName( classfication.getClassificationid(), classfication.getName() ) );
				}
			}
		} else {
			classSum = "0";
		}
		return SUCCESS;
	}
}
