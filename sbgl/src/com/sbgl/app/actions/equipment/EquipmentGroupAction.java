package com.sbgl.app.actions.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.equipment.template.EquipmentgroupFull;
import com.sbgl.app.actions.order.EquipmentFull;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.entity.Equipmentclassification;
import com.sbgl.app.entity.Equipmentgroup;
import com.sbgl.app.entity.Groupofequipment;
import com.sbgl.app.entity.GroupofequipmentId;
import com.sbgl.app.services.common.CommonService;
import com.sbgl.app.services.equipment.EquipGroupService;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.util.Page;
import com.sbgl.util.PropertyUtil;
import com.sbgl.util.StringUtil;

@Scope("prototype") 
@Controller("EquipmentGroupAction")
public class EquipmentGroupAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	
	private String groupChName;
	private String groupENName;
	private List<String> inputBasicEquipId;
	private List<String> inputBasicEquipNum;
	private String groupCNdetail;
	private String groupENdetail;
	private String brandid;
	private String tag;
	private String pageNo;
	private String ids;
	private Page page;
	private String imgGroupName;
	private String imgGroupRealName;
	private List<EquipmentgroupFull> equipmentgroupFullList;
	
	private List<Equipmentclassification> classification1List;
	
	private Map<Integer,List<EquipmentFull>> classequipmap;
	
	@Resource
	private EquipGroupService equipGroupService;
	
	@Resource
	private OrderAdminService orderAdminService;
	
	@Resource
	private CommonService commonService;
	
	//进入设备组管理
	public String equipManageGroup(){
		
		if(pageNo==null||pageNo.equals("")){
			pageNo = "1";
		}
		
		page = new Page(Integer.parseInt(pageNo),10);
		
		String lantype = (String) session.get(CommonConfig.sessionLanguagetype);
		if(lantype==null||lantype.equals("")){
			lantype = "0";
		}
		equipmentgroupFullList = equipGroupService.findEquipmentGroup(page);
		OrderCountFull orderCountFull = equipGroupService.findCountEquipmentGroup();
		page.setTotalCount(orderCountFull.getOrderCount1().intValue());
		int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
		int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
		if(temp==0){
			if(temp2==0){
				temp2=1;
			}
			page.setTotalpage(temp2);
		}else{
			page.setTotalpage(temp2+1);
		}
		classification1List = orderAdminService.findTopEquipmentclass(lantype);
		classequipmap = orderAdminService.fingclassequipMap(classification1List,-1);
		return SUCCESS;
	}
	
	//删除设备组
	public String deleteequipGroup(){
		if(ids!=null){
			ids = ids.substring(0,ids.length()-1);
			String[] temp1 = ids.split("_");
			equipGroupService.deleteequipGroup(temp1);
			if(pageNo==null||pageNo.equals("")){
				pageNo = "1";
			}
			
			page = new Page(Integer.parseInt(pageNo),10);
			
			String lantype = (String) session.get(CommonConfig.sessionLanguagetype);
			if(lantype==null||lantype.equals("")){
				lantype = "0";
			}
			equipmentgroupFullList = equipGroupService.findEquipmentGroup(page);
			OrderCountFull orderCountFull = equipGroupService.findCountEquipmentGroup();
			page.setTotalCount(orderCountFull.getOrderCount1().intValue());
			int temp = orderCountFull.getOrderCount1().intValue()%page.getPageSize();
			int temp2 = orderCountFull.getOrderCount1().intValue()/page.getPageSize();
			if(temp==0){
				if(temp2==0){
					temp2=1;
				}
				page.setTotalpage(temp2);
			}else{
				page.setTotalpage(temp2+1);
			}
		}

		return SUCCESS;
	}
	
	//增加设备组
	public String addEquipmentGroup(){
		try{
			Equipmentgroup equipmentgroup = new Equipmentgroup();
			equipmentgroup.setEquipmentgroupid(commonService.getCode("equipId"));
			equipmentgroup.setClassificationid(-2);
			equipmentgroup.setBrandid(StringUtil.parseInt(brandid));
			equipmentgroup.setLantype(CommonConfig.languagechStr);
			equipmentgroup.setEquipmentdetail(groupCNdetail);
			equipmentgroup.setEquipmentname(groupChName);
			equipmentgroup.setComid(commonService.getCode("equipComId"));
			equipmentgroup.setImgname(imgGroupName);
			equipmentgroup.setImgnamesaved(imgGroupRealName);
			Equipmentgroup equipmentENgroup = new Equipmentgroup();
			equipmentENgroup.setEquipmentgroupid(commonService.getCode("equipId"));
			equipmentENgroup.setClassificationid(-2);
			equipmentENgroup.setBrandid(StringUtil.parseInt(brandid));
			equipmentENgroup.setLantype(CommonConfig.languageenStr);
			equipmentENgroup.setEquipmentdetail(groupENdetail);
			equipmentENgroup.setEquipmentname(groupENName);
			equipmentENgroup.setComid(equipmentgroup.getComid());
			equipmentENgroup.setImgname(imgGroupName);
			equipmentENgroup.setImgnamesaved(imgGroupRealName);
			
			int size = inputBasicEquipId.size();
			List<Groupofequipment> groupofequipmentList = new ArrayList<Groupofequipment>();
			for(int i = 0;i < size;i++){
				Groupofequipment groupofequipment = new Groupofequipment();
				GroupofequipmentId groupofequipmentId = new GroupofequipmentId();
				groupofequipmentId.setEquipmentgroupid(equipmentgroup.getComid());
				groupofequipmentId.setEquipmentid(StringUtil.parseInt(inputBasicEquipId.get(i)));
				groupofequipment.setNum(StringUtil.parseInt(inputBasicEquipNum.get(i)));
				groupofequipment.setId(groupofequipmentId);
				groupofequipmentList.add(groupofequipment);
			}
			equipGroupService.addEquipmentGroup(equipmentgroup,equipmentENgroup,groupofequipmentList);
			tag = "1";
		}catch(Exception e){
			e.printStackTrace();
			tag = "2";
		}
		
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	public String getGroupChName() {
		return groupChName;
	}

	public void setGroupChName(String groupChName) {
		this.groupChName = groupChName;
	}

	public String getGroupENName() {
		return groupENName;
	}

	public void setGroupENName(String groupENName) {
		this.groupENName = groupENName;
	}

	public List<String> getInputBasicEquipId() {
		return inputBasicEquipId;
	}

	public void setInputBasicEquipId(List<String> inputBasicEquipId) {
		this.inputBasicEquipId = inputBasicEquipId;
	}

	public List<String> getInputBasicEquipNum() {
		return inputBasicEquipNum;
	}

	public void setInputBasicEquipNum(List<String> inputBasicEquipNum) {
		this.inputBasicEquipNum = inputBasicEquipNum;
	}

	public String getGroupCNdetail() {
		return groupCNdetail;
	}

	public void setGroupCNdetail(String groupCNdetail) {
		this.groupCNdetail = groupCNdetail;
	}

	public String getGroupENdetail() {
		return groupENdetail;
	}

	public void setGroupENdetail(String groupENdetail) {
		this.groupENdetail = groupENdetail;
	}

	public String getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Equipmentclassification> getClassification1List() {
		return classification1List;
	}

	public void setClassification1List(
			List<Equipmentclassification> classification1List) {
		this.classification1List = classification1List;
	}

	public Map<Integer, List<EquipmentFull>> getClassequipmap() {
		return classequipmap;
	}

	public void setClassequipmap(Map<Integer, List<EquipmentFull>> classequipmap) {
		this.classequipmap = classequipmap;
	}

	public List<EquipmentgroupFull> getEquipmentgroupFullList() {
		return equipmentgroupFullList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setEquipmentgroupFullList(
			List<EquipmentgroupFull> equipmentgroupFullList) {
		this.equipmentgroupFullList = equipmentgroupFullList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getImgGroupName() {
		return imgGroupName;
	}

	public void setImgGroupName(String imgGroupName) {
		this.imgGroupName = imgGroupName;
	}

	public String getImgGroupRealName() {
		return imgGroupRealName;
	}

	public void setImgGroupRealName(String imgGroupRealName) {
		this.imgGroupRealName = imgGroupRealName;
	}
	
}
