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
import com.sbgl.app.actions.user.template.GroupCourse;
import com.sbgl.app.actions.user.template.UserCourse2;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.User;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
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
	@Resource
	private UserService userService;
	@Resource
	private ClazzService clazzService;
	@Resource
	private GroupService groupService;
	@Resource
	private UserGroupRelationService userGroupRelationService;
	
	private Map<String, Object> session;
	
	private static final long serialVersionUID = -3856779529100525958L;

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	
	/**
	 * Ajax调用返回封装的JSON对象
	 * @return
	 */
	private Map<String,Object> returnJSON;
	public Map<String,Object> getReturnJSON() {
		return returnJSON;
	}
	
	private String tag;     //返回执行结果 0-成功 1-失败
	private String msg;		//返回信息
	
	
	//参数对象user，用于保存和修改
	private User user;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//用户保存用户分组信息的参数对象
	private Usergroup group;
	public Usergroup getGroup() {
		return group;
	}
	public void setGroup(Usergroup group) {
		this.group = group;
	}

	//添加用户
	public String addUser() {
		Boolean isExist = userService.isExistUserInfo( user.getUsernumber(), user.getEmail() );
		if( !isExist ) {
			returnJSON = null;
			returnJSON = new HashMap<String,Object>();
			
			Loginuser sessionUser = (Loginuser) session.get("loginUser");
			user.setCreaterid( sessionUser == null ? -1 : sessionUser.getId() );	//从session获取当前登录的用户，使用其id为创建者id
			
			int returnCode = userService.addUser( user );
			if(returnCode == -1) {
				this.tag = "1";
				if(group.getType() == 1) {
					this.msg = "添加学生信息失败！";
				} else if(group.getType() == 2) {
					this.msg = "添加教师信息失败！";
				} else if(group.getType() == 4) {
					this.msg = "添加其他人员信息失败！";
				}
				
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupid( group.getId() );
				ugr.setUserid( returnCode );
				ugr.setGrouptype( group.getType() );
				userGroupRelationService.addUserGroupRelation( ugr );
				
				this.tag = "0";
				if(group.getType() == 1) {
					this.msg = "添加学生信息成功！";
				} else if(group.getType() == 2) {
					this.msg = "添加教师信息成功！";
				} else if(group.getType() == 4) {
					this.msg = "添加其他人员信息成功！";
				}
			}
			
		} else {
			this.tag = "2";
			this.msg = "邮箱或用户编号已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", msg);
		
		return SUCCESS;
	}
	
	//修改用户
	public String alterUser() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		int type = user.getRoletype();
		
		int returnCode = userService.alterUser( user );
		
		if( returnCode == -1 ) {
			this.tag = "1";
			if(type == 1) {
				this.msg = "修改学生信息失败！";
			} else if(type == 2) {
				this.msg = "修改教师信息失败！";
			} else if(type == 4) {
				this.msg = "修改其他人员信息失败！";
			}
		} else {
			Usergrouprelation ugr = userGroupRelationService.getRelationByType( user.getId() );
			if(ugr != null) {
				ugr.setGroupid( group.getId() );
				userGroupRelationService.alterUserGroupRelation( ugr );
			}
			
			this.tag = "0";
			if(type == 1) {
				this.msg = "修改学生信息成功！";
			} else if(type == 2) {
				this.msg = "修改教师信息成功！";
			} else if(type == 4) {
				this.msg = "修改其他人员信息成功！";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", msg);
				
		return SUCCESS;
	}
	
	//根据id串删除用户
	private String userIds;
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	public String deleteUsers() {
		if(userIds != null && !"".equals(userIds) ) {
			returnJSON = null;
			returnJSON = new HashMap<String,Object>();
			String[] ids = userIds.split("_");
			
			try {
				for (String id : ids) {
					Integer oneId = Integer.valueOf( id );
					Usergrouprelation temp = userGroupRelationService.getRelationByType( oneId );
					if(userService.deleteUser( oneId ) == 0 && userGroupRelationService.deleteUserGroupRelation( temp.getId() ) == 0) {
						this.tag = "0";
						this.msg = "删除用户信息成功！";
					} else {
						this.msg = "删除用户信息失败！";
						this.tag = "1";
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				this.msg = "后台异常，请联系管理员！";
				this.tag = "2";
			}
		}
		
		return SUCCESS;
	}
	
	//进入人员管理首页
	private List<UserCourse2> userCourseList;
	public List<UserCourse2> getUserCourseList() {
		return userCourseList;
	}
	private List<GroupCourse> groupCourseList;
	public List<GroupCourse> getGroupCourseList() {
		return groupCourseList;
	}
	
	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String gid;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}

	private String totalPage;
	public String getTotalPage() {
		return totalPage;
	}

	private String curPage;
	public String getCurPage() {
		return curPage;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	
	public String gotoIndex() {
		groupCourseList = new ArrayList<GroupCourse>();
		
		List<Usergroup> allGroupList = null;
		List<Clazz> allClazzList = null;
		
		if( type != null &&  !"0".equals(type) ) {
			allGroupList = groupService.getAllUserGroup();
		} else {
			allGroupList = groupService.getUserGroupByType( Integer.valueOf(type) );
		}
		if("0".equals(type) || "1".equals(type)) {
			allClazzList = clazzService.getAllClazz();
		}
		
		if(allGroupList != null) {
			GroupCourse gcourse = null;
			for(Usergroup gc : allGroupList) {
				gcourse = null;
				gcourse = new GroupCourse();
				gcourse.setGroupId( gc.getId() );
				gcourse.setGroupName( gc.getName() );
				gcourse.setUsersNum( 0 );
				groupCourseList.add( gcourse );
			}
		}
		
		List<User> allUserList = userService.getAllUsers();
		
		if(allUserList != null) {
			UserCourse2 ucourse = null;
			userCourseList = new ArrayList<UserCourse2>();
			for(User u : allUserList) {
				if( "0".equals(type) || type.equals(u.getRoletype()) ) {
					ucourse = null;
					ucourse = new UserCourse2();
					ucourse.setId( u.getId().toString() );
					ucourse.setUserCode( u.getUsernumber() );
					ucourse.setUserName( u.getUsername() );
					ucourse.setGender( u.getGender() );
					ucourse.setInitPageLan( u.getInitpagelan().toString() );
					ucourse.setMail( u.getEmail() );
					ucourse.setTel( u.getPhonenum() );
					ucourse.setPrivilege( u.getPrivilege().toString() );
					ucourse.setRoleType( u.getRoletype().toString() );
					ucourse.setPhoto( u.getPhoto() );
					
					if(u.getRoletype() == 1) {	//是学生
						if(u.getClassbelong() != -1) {
							for(Clazz c : allClazzList) {
								if( c.getClassId().equals(u.getClassbelong()) ) {
									ucourse.setClazzId( c.getClassId().toString() );
									ucourse.setClazzName( c.getClassname() );
									break;
								}
							}
							
						} else {
							ucourse.setClazzId( "-1" );
							ucourse.setClazzName( "无班级" );
						}
					}
					
					Usergrouprelation temp = userGroupRelationService.getRelationByType( u.getId() );
					if(temp != null) {
						for(GroupCourse gc : groupCourseList) {
							if( gc.getGroupId().equals(temp.getGroupid()) ) {
								ucourse.setUserGroupId( gc.getGroupId().toString() );
								ucourse.setUserGroupName( gc.getGroupName() );
								gc.setUsersNum( gc.getUsersNum() + 1 );
							}
						}
					} else {
						ucourse.setUserGroupId( "-1" );
						ucourse.setUserGroupName( "无分组" );
					}
					
					userCourseList.add( ucourse );
				}
			}
		}
		//减去如果筛选了组号
		if( userCourseList.size() > 0 && "-1".equals(gid) ) {
			for (UserCourse2 temp : userCourseList) {
				if( !gid.equals(temp.getId()) ) {
					userCourseList.remove( temp );
				}
			}
		}
		
		if(userCourseList == null || userCourseList.size() == 0) {
			totalPage = "1";
		} else if(userCourseList.size() % 10 != 0 && userCourseList.size() > 10) {
			totalPage = String.valueOf( userCourseList.size() / 10 + 1 );
		} else if(userCourseList.size() % 10 != 0 && userCourseList.size() < 10) {
			totalPage = "1";
		} else {
			totalPage = String.valueOf( userCourseList.size() / 10 );
		}
		if(curPage == "0" || curPage == "" || curPage == null) {
			curPage = "1";
		} else if(Integer.valueOf(curPage) > (userCourseList.size() / 10)) {
			curPage = totalPage;
		} else if("-1".equals(curPage)) {
			curPage = totalPage;
		}
		
		//根据前台页面请求页码返回数据
		if(curPage != null && curPage != "") {
			int startIndex = Integer.valueOf( curPage.trim() ) - 1;
			int endIndex = (startIndex + 1) * 10 > userCourseList.size() ? userCourseList.size() : (startIndex + 1) * 10;
			userCourseList = userCourseList.subList(startIndex*10, endIndex);
		}
		
		return SUCCESS;
	}
	
}
