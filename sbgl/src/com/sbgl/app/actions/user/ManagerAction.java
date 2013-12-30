package com.sbgl.app.actions.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Administrator;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.user.ManagerService;
import com.sbgl.app.services.user.UserGroupRelationService;


@Scope("prototype") 
@Controller("ManagerAction")
public class ManagerAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3016805895007792306L;

	@Resource
	private ManagerService managerService;

	@Resource
	private UserGroupRelationService userGroupRelationService;
	
	@SuppressWarnings("unused")
	private Map<String, Object> session;
	private String tag;     //返回执行结果 0-成功 1-失败
	private String message; //返回信息

	@Override
	public void setSession(Map<String, Object> session) {
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
	
	//用于保存和修改管理员的参数对象
	private Administrator manager;
	public Administrator getManager() {
		return manager;
	}
	public void setManager(Administrator manager) {
		this.manager = manager;
	}
	//用户保存用户分组信息的参数对象
	private Usergroup group;
	public Usergroup getGroup() {
		return group;
	}
	public void setGroup(Usergroup group) {
		this.group = group;
	}
	
	public String addManager() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = managerService.isExistManagerCode( manager.getAdministratorId() );
		
		if(!isExist) {
			int returnCode = managerService.addManager( manager );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加管理员信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupId( group.getId() );
				ugr.setUserId( returnCode );
				userGroupRelationService.addUserGroupRelation( ugr );
				this.tag = "0";
				this.message = "添加管理员信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的管理员编号已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改管理员信息
	 * @return
	 */
	public String alterManager() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		int returnCode = managerService.alterManager( manager );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改管理员信息失败！";
		} else {
			Usergrouprelation ugr = userGroupRelationService.getRelationByUserId( manager.getId() );
			ugr.setGroupId( group.getId() );
			userGroupRelationService.alterUserGroupRelation( ugr );
			this.tag = "0";
			this.message = "修改管理员信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除管理员信息
	 * @return
	 */
	private String managerIds;
	public String getManagerIds() {
		return managerIds;
	}
	public void setManagerIds(String managerIds) {
		this.managerIds = managerIds;
	}
	public String deleteTeacher() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = managerIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			Administrator mg = managerService.getManagerById( oneId );
			Usergrouprelation temp = userGroupRelationService.getRelationByUserId( mg.getId() );
			if(managerService.deleteManager( oneId ) == 0 && userGroupRelationService.deleteUserGroupRelation( temp.getId() ) == 0) {
				this.message = "删除管理员信息成功！";
				this.tag = "0";
			} else {
				this.message = "删除管理员信息失败！";
				this.tag = "1";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部管理员
	 * @return
	 */
	private List<Administrator> allManagerList;
	public List<Administrator> getAllManagerList() {
		return allManagerList;
	}
	public String getAllTeacher() {
		allManagerList = managerService.getAllManager();
		return SUCCESS;
	}
	
	/**
	 * 页面访问
	 */
	public String gotoUserManageAdmin() {
		
		return SUCCESS;
	}
	public String gotoUserManageAdminAdd() {
		
		return SUCCESS;
	}
	public String gotoUserManageAdminGroup() {
		
		return SUCCESS;
	}
}
