package com.sbgl.app.actions.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.app.services.user.UserGroupRelationService;

@Scope("prototype") 
@Controller("StudentAction")
public class StudentAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7591224502478563670L;

	@Resource
	private StudentService studentService;
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
	
	//保存和修改学生信息的参数对象
	private Student student;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
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
	 * 添加学生信息
	 * @return
	 */
	public String addStudent() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = studentService.isExistStudentCode( student.getStudentid() );
		
		if(!isExist) {
			int returnCode = studentService.addStudent( student );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加学生信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupid( group.getId() );
				ugr.setUserid( returnCode );
				userGroupRelationService.addUserGroupRelation( ugr );
				this.tag = "0";
				this.message = "添加学生信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的学生学号已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改学生信息
	 * @return
	 */
	public String alterStudent() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		int returnCode = studentService.alterStudent( student );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改学生信息失败！";
		} else {
			Usergrouprelation ugr = userGroupRelationService.getRelationByUserId( student.getId() );
			ugr.setGroupid( group.getId() );
			userGroupRelationService.alterUserGroupRelation( ugr );
			this.tag = "0";
			this.message = "修改学生信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除学生信息
	 * @return
	 */
	private String stuIds;
	public String getStuIds() {
		return stuIds;
	}
	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}
	
	public String deleteStudent() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = stuIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			Student stu = studentService.getStudentById( oneId );
			Usergrouprelation temp = userGroupRelationService.getRelationByUserId( stu.getId() );
			if(studentService.deleteStudent( oneId ) == 0 && userGroupRelationService.deleteUserGroupRelation( temp.getId() ) == 0) {
				this.message = "删除学生信息成功！";
				this.tag = "0";
			} else {
				this.message = "删除学生信息失败！";
				this.tag = "1";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部学生
	 * @return
	 */
	private List<Student> allStuList;
	public List<Student> getAllStuList() {
		return allStuList;
	}
	public String getAllStudent() {
		allStuList = studentService.getAllStudent();
		return SUCCESS;
	}
	
	/**
	 * 页面访问
	 */
	public String gotoUserManageUser() {
		
		return SUCCESS;
	}
	public String gotoUserManageUserAdd() {
		
		return SUCCESS;
	}
	
	private List<Usergroup> allGroupList;
	public List<Usergroup> getAllGroupList() {
		return allGroupList;
	}
	public void setAllGroupList(List<Usergroup> allGroupList) {
		this.allGroupList = allGroupList;
	}
	public String gotoUserManageUserGroup() {
		allGroupList = groupService.getAllUserGroup();
		return SUCCESS;
	}
}
