package com.sbgl.app.actions.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.services.user.GroupService;

@Scope("prototype") 
@Controller("GroupAction")
public class GroupAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1020480402800386089L;

	@Resource
	private GroupService groupService;
	
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
	
	//添加和修改分组的参数对象
	private Usergroup group;
	public Usergroup getGroup() {
		return group;
	}
	public void setGroup(Usergroup group) {
		this.group = group;
	}
	
	/**
	 * 添加用户组信息
	 * @param group
	 * @return
	 */
	public String addGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = groupService.isExistGroupName( group.getName() );
		
		if(!isExist) {
			long returnCode = groupService.addUserGroup( group );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加用户组信息失败！";
			} else {
				getAllGroup();
				this.tag = "0";
				this.message = "添加用户组信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的用户组信息已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改用户组信息
	 * @return
	 */
	public String alterGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		long returnCode = groupService.alterUserGroup( group );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改用户组信息失败！";
		} else {
			getAllGroup();
			this.tag = "0";
			this.message = "修改用户组信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除用户组信息
	 * @return
	 */
	private String groupIds;
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String deleteGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = groupIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			if(groupService.deleteUsergroup( oneId ) == 0) {
				getAllGroup();
				this.message = "删除用户组成功！";
				this.tag = "0";
			} else {
				this.message = "删除用户组失败！";
				this.tag = "1";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部用户组信息
	 * @return
	 */
	private List<Usergroup> allGroupList;
	public List<Usergroup> getAllGroupList() {
		return allGroupList;
	}
	public void setAllGroupList(List<Usergroup> allGroupList) {
		this.allGroupList = allGroupList;
	}
	
	public String getAllGroup() {
		allGroupList = groupService.getAllUserGroup();
		return SUCCESS;
	}
}
