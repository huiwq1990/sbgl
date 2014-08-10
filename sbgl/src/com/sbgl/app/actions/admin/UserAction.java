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
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Managergroup;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.entity.User;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.admin.ManagerGroupService;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.app.services.user.UserGroupRelationService;
import com.sbgl.app.services.user.UserService;
import com.sbgl.app.services.user.UserlogininfoService;
import com.sbgl.app.services.user.WorkerService;
import com.sbgl.util.CardPassUtil;
import com.sbgl.util.CookiesUtil;

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
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private WorkerService workerService;
	@Resource
	private UserlogininfoService loginInfoService;
	
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
	
	private String newPassword;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String alterPassword() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		if( !"".equals(newPassword) ) {
			Integer res = null; 
			if("1".equals(userType)) {
				Student s = studentService.getStudentById( Integer.valueOf(userId) );
				s.setPassword(newPassword);
				res = studentService.alterStudent(s);
			} else if("2".equals(userType)) {
				Teacher t = teacherService.getTeacherById( Integer.valueOf(userId) );
				t.setPassword(newPassword);
				res = teacherService.alterTeacher(t);
			} else if("4".equals(userType)) {
				Worker w = workerService.getWorkerById( Integer.valueOf(userId) );
				w.setPassword(newPassword);
				res = workerService.alterWorker(w);
			}
			
			if(res != null) {
				Loginuser u = (Loginuser) session.get("loginUser");
				u.setPassword(newPassword);
				session.put("loginUser", u);
				returnJSON.put("tag", 0);
				returnJSON.put("msg", "密码修改成功！");
			} else {
				returnJSON.put("tag", 2);
				returnJSON.put("msg", "密码修改失败！");
			}
			
		} else {
			returnJSON.put("tag", 1);
			returnJSON.put("msg", "密码不能为空！");
		}
		
		return SUCCESS;
	}
	
	private String pageLan;
	public String getPageLan() {
		return pageLan;
	}
	public void setPageLan(String pageLan) {
		this.pageLan = pageLan;
	}
	public String alterPageLan() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		if( !"".equals(pageLan) ) {
			Integer res = null; 
			Userlogininfo loginInfo = loginInfoService.getLoinInfoByUserId( Integer.valueOf(userId) );
			if(loginInfo != null) {
				loginInfo.setPagelanguage(pageLan);
				res = loginInfoService.alterUserLoginInfo(loginInfo);
				
				if(res != null) {
					returnJSON.put("tag", 0);
					returnJSON.put("msg", "界面语言修改成功！");
				} else {
					returnJSON.put("tag", 2);
					returnJSON.put("msg", "界面语言修改失败！");
				}
			}
			session.put(CommonConfig.sessionLanguagetype, pageLan);
			CookiesUtil.removeCookie("pageLan");
			CookiesUtil.addLoginCookie("pageLan", pageLan);
		} else {
			returnJSON.put("tag", -1);
			returnJSON.put("msg", "后台故障！");
		}
		
		return SUCCESS;
	}
	
	private String newGender;
	
	public String getNewGender() {
		return newGender;
	}
	public void setNewGender(String newGender) {
		this.newGender = newGender;
	}
	public String alterGender() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		if( !"".equals(newGender) ) {
			Integer res = null; 
			if("1".equals(userType)) {
				Student s = studentService.getStudentById( Integer.valueOf(userId) );
				s.setGender(newGender);
				s.setPassword( CardPassUtil.decrypt(s.getPassword()) );
				res = studentService.alterStudent(s);
			} else if("2".equals(userType)) {
				Teacher t = teacherService.getTeacherById( Integer.valueOf(userId) );
				t.setGender(newGender);
				t.setPassword( CardPassUtil.decrypt(t.getPassword()) );
				res = teacherService.alterTeacher(t);
			} else if("4".equals(userType)) {
				Worker w = workerService.getWorkerById( Integer.valueOf(userId) );
				w.setGender(newGender);
				w.setPassword( CardPassUtil.decrypt(w.getPassword()) );
				res = workerService.alterWorker(w);
			}
			
			Loginuser u = (Loginuser) session.get("loginUser");
			u.setGender(newGender);
			session.put("loginUser", u);
			
			if(res != null) {
				returnJSON.put("tag", 0);
				returnJSON.put("msg", "性别修改成功！");
			} else {
				returnJSON.put("tag", 2);
				returnJSON.put("msg", "性别修改失败！");
			}
			
		} else {
			returnJSON.put("tag", 1);
			returnJSON.put("msg", "性别信息有误！");
		}
		
		return SUCCESS;
	}
	
	private String phoneNum;
	private String newEmail;
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getNewEmail() {
		return newEmail;
	}
	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}
	
	public String alterConnInfo() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		if( !"".equals(phoneNum) && !"".equals(newEmail) ) {
			Integer res = null; 
			if("1".equals(userType)) {
				Student s = studentService.getStudentById( Integer.valueOf(userId) );
				s.setTelephone(phoneNum);
				s.setEmail(newEmail);
				s.setPassword( CardPassUtil.decrypt(s.getPassword()) );
				res = studentService.alterStudent(s);
			} else if("2".equals(userType)) {
				Teacher t = teacherService.getTeacherById( Integer.valueOf(userId) );
				t.setTelephone(phoneNum);
				t.setEmail(newEmail);
				t.setPassword( CardPassUtil.decrypt(t.getPassword()) );
				res = teacherService.alterTeacher(t);
			} else if("4".equals(userType)) {
				Worker w = workerService.getWorkerById( Integer.valueOf(userId) );
				w.setTelephone(phoneNum);
				w.setEmail(newEmail);
				w.setPassword( CardPassUtil.decrypt(w.getPassword()) );
				res = workerService.alterWorker(w);
			}
			
			Loginuser u = (Loginuser) session.get("loginUser");
			u.setTelephone(phoneNum);
			u.setEmail(newEmail);
			session.put("loginUser", u);
			
			if(res != null) {
				returnJSON.put("tag", 0);
				returnJSON.put("msg", "修改联系方式成功！");
			} else {
				returnJSON.put("tag", 2);
				returnJSON.put("msg", "修改联系方式失败！");
			}
			
		} else {
			returnJSON.put("tag", 1);
			returnJSON.put("msg", "联系信息有误！");
		}
		return SUCCESS;
	}
	
	
	public String getTag() {
		return tag;
	}
	public String getMessage() {
		return message;
	}

}
