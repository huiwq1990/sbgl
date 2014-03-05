package com.sbgl.app.actions.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.app.services.user.UserGroupRelationService;

@Scope("prototype") 
@Controller("TeacherAction")
public class TeacherAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4530418513044716330L;

	@Resource
	private TeacherService teacherService;
	
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
	
	//保存和修改教师使用的参数对象
	private Teacher teacher;
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	//用户保存用户分组信息的参数对象
	private Usergroup group;
	public Usergroup getGroup() {
		return group;
	}
	public void setGroup(Usergroup group) {
		this.group = group;
	}
	/**
	 * 添加教师信息
	 * @return
	 */
	public String addTeacher() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = teacherService.isExistTeacherCode( teacher.getTeacherid() );
		
		if(!isExist) {
			int returnCode = teacherService.addTeacher( teacher );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加教师信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupid( group.getId() );
				ugr.setUserid( returnCode );
				ugr.setGrouptype( group.getType() );
				userGroupRelationService.addUserGroupRelation( ugr );
				this.tag = "0";
				this.message = "添加教师信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的教师工号已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改教师信息
	 * @return
	 */
	public String alterTeacher() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		int returnCode = teacherService.alterTeacher( teacher );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改教师信息失败！";
		} else {
			Usergrouprelation ugr = userGroupRelationService.getRelationByType(teacher.getId());
			ugr.setGroupid( group.getId() );
			userGroupRelationService.alterUserGroupRelation( ugr );
			this.tag = "0";
			this.message = "修改教师信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除教师信息
	 * @return
	 */
	private String teacherIds;
	public String getTeacherIds() {
		return teacherIds;
	}
	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}
	public String deleteTeacher() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = teacherIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			Teacher tc = teacherService.getTeacherById( oneId );
			Usergrouprelation temp = userGroupRelationService.getRelationByType(tc.getId());
			if(teacherService.deleteTeacher( oneId ) == 0 && userGroupRelationService.deleteUserGroupRelation( temp.getId() ) == 0) {
				this.message = "删除教师信息成功！";
				this.tag = "0";
			} else {
				this.message = "删除教师信息失败！";
				this.tag = "1";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部教师
	 * @return
	 */
	private List<Teacher> allTeacherList;
	public List<Teacher> getAllTeacherList() {
		return allTeacherList;
	}
	public String getAllTeacher() {
		allTeacherList = teacherService.getAllTeacher();
		return SUCCESS;
	}
}
