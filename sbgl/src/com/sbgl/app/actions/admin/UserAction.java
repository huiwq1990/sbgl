package com.sbgl.app.actions.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.admin.template.UserInfoTemplate;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Managergroup;
import com.sbgl.app.entity.User;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.admin.ManagerGroupService;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.UserGroupRelationService;
import com.sbgl.app.services.user.UserService;

@Scope("prototype") 
@Controller("UserAction")
public class UserAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4453734626952294020L;
	@Resource
	private ClazzService clazzService;
	@Resource
	private GroupService grpService;
	@Resource
	private ManagerGroupService mgrGroupService;
	@Resource
	private UserService userService;
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
	
	private List<Usergroup> userGroupList;
	public List<Usergroup> getUserGroupList() {
		return userGroupList;
	}

	private List<Clazz> classList;
	public List<Clazz> getClassList() {
		return classList;
	}
	
	private String userType;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	private String classType;
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	
	public String gotoAddUser() {
		if(userType != null && !"".equals(userType)) {
			userGroupList = grpService.getUserGroupByType( Integer.parseInt(userType) );
		} else {
			userGroupList = grpService.getUserGroupByType( 1 );
		}
		classList = clazzService.getClazzByType(userGroupList.get(0).getId());
		return SUCCESS;
	}
	
	public String flushUserGroupForAdd() {
		if(userType != null && !"".equals(userType)) {
			userGroupList = grpService.getUserGroupByType( Integer.parseInt(userType) );
		} else {
			userGroupList = grpService.getUserGroupByType( 1 );
		}
		return SUCCESS;
	}
	
	public String flushStuClassForAdd() {
		if(classType != null && !"".equals(classType)) {
			classList = clazzService.getClazzByType( Integer.parseInt(classType) );
		}
		return SUCCESS;
	}
	
	public String addUser() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Boolean isExist;
		if("4".equals(user.getUsertype())) {
			isExist = userService.isExistUserIDCode( user.getCardnumber() );
		} else {
			isExist = userService.isExistUserCode( user.getUserid() );
		}
		
		if(!isExist) {
			int returnCode = userService.addUser(user);
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加用户信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupid( group.getId() );
				ugr.setUserid( returnCode );
				ugr.setGrouptype( group.getType() );
				userGroupRelationService.addUserGroupRelation( ugr );
				this.tag = "0";
				this.message = "添加用户信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的用户信息已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	private Usergroup group;
	public Usergroup getGroup() {
		return group;
	}
	public void setGroup(Usergroup group) {
		this.group = group;
	}
	
	private List<UserInfoTemplate> userInfoList;
	public List<UserInfoTemplate> getUserInfoList() {
		return userInfoList;
	}
	
	public String gotoUserIndex() {
		List<UserInfoTemplate> resultList = userService.getUserInfo();
		Managergroup mg = null;
		int type = 0;
		for (UserInfoTemplate u : resultList) {
			mg = mgrGroupService.getManagerGroupByUserId( u.getId() );
			if(mg != null) {
				type = mg.getManagertype();
				u.setManagerGroupid( mg.getId() );
				u.setManagerGroupType( type );
				if(type == 1) {
					u.setManaagerGroupname( "系统管理员" );
				} else if(type == 2) {
					u.setManaagerGroupname( "系秘" );
				} else if(type == 3) {
					u.setManaagerGroupname( "器材管理员" );
				} else if(type == 4) {
					u.setManaagerGroupname( "机房管理员" );
				} else {
					u.setManaagerGroupname( "无" );
				}
			} else {
				u.setManaagerGroupname( "无" );
			}
		}
		userInfoList = resultList;
		
		userGroupList = grpService.getAllUserGroup();
		if(userGroupList != null && userGroupList.size() > 0) {
			classList = clazzService.getClazzByType( userGroupList.get(0).getId() );
		}
		
		
		return SUCCESS;
	}
	
	
	public String alterUser() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		int returnCode = userService.alterUser(user);
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改用户信息失败！";
		} else {
			Usergrouprelation ugr = userGroupRelationService.getRelationByType( user.getId() );
			if(ugr != null) {
				ugr.setGroupid( group.getId() );
				userGroupRelationService.alterUserGroupRelation( ugr );
			}
			
			this.tag = "0";
			this.message = "修改用户信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	public String flushUserIndex() {
		gotoUserIndex();
		return SUCCESS;
	}
	
	
	public String getTag() {
		return tag;
	}
	public String getMessage() {
		return message;
	}
	

}