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
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.services.user.ClazzService;
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
	private ClazzService clazzService;
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
		
		Boolean isExist = studentService.isExistStudentCode( student.getStudentId() );
		
		if(!isExist) {
			int returnCode = studentService.addStudent( student );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加学生信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupId( group.getId() );
				ugr.setUserId( returnCode );
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
			ugr.setGroupId( group.getId() );
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
	
	private List<Clazz> clazzList;
	public List<Clazz> getClazzList() {
		return clazzList;
	}
	public String gotoUserManageUserAdd() {
		clazzList = clazzService.getAllClazz();
		allGroupList = new ArrayList<Usergroup>();
		List<Usergroup> tempList = groupService.getAllUserGroup();
		List<Usergroup> subList1 = new ArrayList<Usergroup>();
		List<Usergroup> subList2 = new ArrayList<Usergroup>();
		List<Usergroup> subList3 = new ArrayList<Usergroup>();
		for (Usergroup up : tempList) {
			if(up.getType() == 2) {
				subList1.add( up );
			} else if(up.getType() == 6) {
				subList2.add( up );
			} else if(up.getType() == -1) {
				subList3.add( up );
			}
		}
		allGroupList.addAll( subList1 );
		allGroupList.addAll( subList2 );
		allGroupList.addAll( subList3 );
		return SUCCESS;
	}
	
	private List<Usergroup> allGroupList;
	public List<Usergroup> getAllGroupList() {
		return allGroupList;
	}
	public String gotoUserManageUserGroup() {
		allGroupList = groupService.getAllUserGroup();
		return SUCCESS;
	}
	
	/**
	 * 判断该用户组所属的类别
	 */
	public String groupId;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String isStuGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		if(groupId != null && !"".equals(groupId)) {
			
		}
		Usergroup group = groupService.getUserGroupByid( Integer.valueOf( groupId.trim() ) );
		
		returnJSON.put("groupType", group.getType());
		return SUCCESS;
	}
}
