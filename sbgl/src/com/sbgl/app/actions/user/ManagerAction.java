package com.sbgl.app.actions.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.user.template.UserCourse;
import com.sbgl.app.entity.Administrator;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.ManagerService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.app.services.user.UserGroupRelationService;
import com.sbgl.app.services.user.WorkerService;


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
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private GroupService groupService;

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
		
		Boolean isExist = managerService.isExistManagerCode( manager.getAdministratorid() );
		
		if(!isExist) {
			int returnCode = managerService.addManager( manager );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加管理员信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupid( group.getId() );
				ugr.setUserid( returnCode );
				ugr.setGrouptype( group.getType() );
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
	//存储用户编号
	private String userCode;
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	//存储管理员管理组
	private String adminGroup;
	public String getAdminGroup() {
		return adminGroup;
	}
	public void setAdminGroup(String adminGroup) {
		this.adminGroup = adminGroup;
	}
	
	public String addManager2() {
		Administrator admin = new Administrator();
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Boolean isStu = false;
		Boolean isTea = false;
		Student stu = null;
		Teacher tea = null;
		
		Boolean isExist = managerService.isExistManagerCode( userCode.trim() );
		if(isExist) {
			this.tag = "1";
			this.message = "该用户已经是管理员！";
			returnJSON.put("tag", tag);
			returnJSON.put("msg", message);
			return SUCCESS;
		}
		
		isStu = studentService.isExistStudentCode( userCode.trim() );
		if(!isStu) {
			isTea = teacherService.isExistTeacherCode( userCode.trim() );
		}
		
		if(!isStu && !isTea) {
			this.tag = "2";
			this.message = "未找到相关的用户信息！";
			returnJSON.put("tag", tag);
			returnJSON.put("msg", message);
			return SUCCESS;
		} else if(isStu) {
			stu = studentService.getStudentByCode( userCode.trim() );
			admin.setId( stu.getId() );
			admin.setAdministratorid( stu.getStudentid() );
			admin.setPrivilege( Integer.parseInt(adminGroup) );
			admin.setName( stu.getName() );
		} else if(isTea) {
			tea = teacherService.getTeacherByCode( userCode.trim() );
			admin.setId( tea.getId() );
			admin.setAdministratorid( tea.getTeacherid() );
			admin.setPrivilege( Integer.parseInt(adminGroup) );
			admin.setName( tea.getName() );
		}
		
		int returnCode = managerService.addManager( admin );
		if(returnCode == -1) {
			this.tag = "3";
			this.message = "添加管理员信息失败！";
		} else {
			this.tag = "0";
			this.message = "管理员添加成功！";
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
//			Usergrouprelation ugr = userGroupRelationService.getRelationByType(manager.getId());
//			ugr.setGroupid( group.getId() );
//			userGroupRelationService.alterUserGroupRelation( ugr );
			this.tag = "0";
			this.message = "修改管理员信息成功！";
		}
		
		gotoUserManageAdmin();
		
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
	public String deleteManager() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = managerIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			Administrator mg = managerService.getManagerById( oneId );
			Usergrouprelation temp = userGroupRelationService.getRelationByType(mg.getId());
			if(managerService.deleteManager( oneId ) == 0 && userGroupRelationService.deleteUserGroupRelation( temp.getId() ) == 0) {
				this.message = "删除管理员信息成功！";
				this.tag = "0";
			} else {
				this.message = "删除管理员信息失败！";
				this.tag = "1";
			}
		}
		
		gotoUserManageAdmin();
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部管理员
	 * @return
	 */
	/*public String getAllTeacher() {
		allManagerList = managerService.getAllManager();
		return SUCCESS;
	}*/
	
	/**
	 * 页面访问
	 */
	private List<Usergroup> allGroupList;
	public List<Usergroup> getAllGroupList() {
		return allGroupList;
	}
	private List<UserCourse> allManagerList;
	public List<UserCourse> getAllManagerList() {
		return allManagerList;
	}
	public String gotoUserManageAdmin() {
		allManagerList = new ArrayList<UserCourse>();
		gotoUserManageAdminGroup();
		
		List<Administrator> allAdminList = managerService.getAllManager();
		for (Administrator admin : allAdminList) {
			Usergrouprelation ugr = userGroupRelationService.getRelationByType( admin.getId() );
			Usergroup ug = null;
			
			UserCourse uc = new UserCourse();
			uc.setUserName( admin.getName() );
			uc.setUserPass( admin.getPassword() );
			uc.setUserCode( admin.getAdministratorid() );
			uc.setGender( admin.getGender() );
			uc.setId( String.valueOf( admin.getId() ) );
			uc.setPhoto( admin.getPhoto() );
			uc.setTel( admin.getTelephone() );
			uc.setMail( admin.getEmail() );
			uc.setPrivilege( String.valueOf( admin.getPrivilege() ) );
			if(ugr != null) {
				ug = groupService.getUserGroupByid( ugr.getGroupid() );
				if(ug != null) {
					uc.setUserGroupId( String.valueOf(ugr.getId()) );
					uc.setUserGroupName( ug.getName() );
					uc.setUserGroupType( String.valueOf( ug.getType() ) );
				}
			}
			allManagerList.add( uc );
		}
		return SUCCESS;
	}
	public String gotoUserManageAdminAdd() {
		gotoUserManageAdminGroup();
		return SUCCESS;
	}
	
	public String gotoUserManageAdminGroup() {
		allGroupList = new ArrayList<Usergroup>();
		List<Usergroup> tempList = groupService.getAllUserGroup();
		for (Usergroup ug : tempList) {
			if(ug.getType() == 3) {
				allGroupList.add( ug );
			}
		}
		
		return SUCCESS;
	}
}
