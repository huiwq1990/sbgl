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
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.app.services.user.GroupService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.app.services.user.UserGroupRelationService;
import com.sbgl.app.services.user.WorkerService;
import com.sbgl.util.CardPassUtil;

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
	private TeacherService teacherService;
	@Resource
	private WorkerService workerService;
	
	@Resource
	private UserGroupRelationService userGroupRelationService;
	
	@SuppressWarnings("unused")
	private Map<String, Object> session;
	private String tag;     //返回执行结果 0-成功 1-失败
	private String message; //返回信息
	
	private final int pageSize = 20;

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
				ugr.setGrouptype( group.getType() );
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
			Usergrouprelation ugr = userGroupRelationService.getRelationByType( student.getId() );
			if(ugr != null) {
				ugr.setGroupid( group.getId() );
				userGroupRelationService.alterUserGroupRelation( ugr );
			}
			
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
			Usergrouprelation temp = userGroupRelationService.getRelationByType( stu.getId() );
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
	private List<UserCourse> allUserList;
	private String sum;
	public String getSum() {
		return sum;
	}
	private String sum1;
	public String getSum1() {
		return sum1;
	}
	private String sum2;
	public String getSum2() {
		return sum2;
	}
	private String sum3;
	public String getSum3() {
		return sum3;
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

	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public List<UserCourse> getAllUserList() {
		return allUserList;
	}
	
	public String gotoUserManageUser() {
		gotoUserManageUserAdd();
		
		if(type == null) {
			type = "0";
		}
		
		allUserList = new ArrayList<UserCourse>();
		
		List<Object> allList = new ArrayList<Object>();
		List<Student> sList = null;
		List<Teacher> tList = null;
		List<Worker> wList = null;
		
		if("0".equals(type) || "1".equals(type)) {
			sList = studentService.getAllStudent();
			if(sList != null) {
				allList.addAll(sList);
			}
		}
		if("0".equals(type) || "2".equals(type)) {
			tList = teacherService.getAllTeacher();
			if(tList != null) {
				allList.addAll(tList);
			}
		}
		if( "0".equals(type) || "4".equals(type) ) {
			wList = workerService.getAllWorker();
			if(wList != null) {
				allList.addAll(wList);
			}
		}
		
		
		int allSize = allList.size();
		if(allList == null || allSize == 0) {
			totalPage = "1";
		} else if(allSize % pageSize != 0 && allSize > pageSize) {
			totalPage = String.valueOf( allSize / pageSize + 1 );
		} else if(allSize % pageSize != 0 && allSize < pageSize) {
			totalPage = "1";
		} else {
			totalPage = String.valueOf( allSize / pageSize );
		}
		if(curPage == "0" || curPage == "" || curPage == null) {
			curPage = "1";
		} else if(Integer.valueOf(curPage) > (allSize / pageSize)) {
			curPage = totalPage;
		} else if("-1".equals(curPage)) {
			curPage = totalPage;
		}
		
		//根据前台页面请求页码返回数据
		if(curPage != null && curPage != "") {
			int startIndex = Integer.valueOf( curPage.trim() ) - 1;
			int endIndex = (startIndex + 1) * pageSize > allSize ? allSize : (startIndex + 1) * pageSize;
			
			allList = allList.subList(startIndex*pageSize, endIndex);
			if(allSize > 0) {
				Usergrouprelation ugr = null;
				Usergroup ug = null;
				Clazz clazz = null;
				Student s = null;
				Teacher t = null;
				Worker w = null;
				for (Object o : allList) {
					if(o instanceof Student) {
						s = (Student) o;
						ugr = userGroupRelationService.getRelationByType( s.getId() );
						if(ugr != null) {
							ug = groupService.getUserGroupByid( ugr.getGroupid() );
						}
						if(s.getClassid() != -1) {
							clazz = clazzService.getClazzById( s.getClassid() );
						}
						
						UserCourse uc = new UserCourse(String.valueOf( s.getId() ),
													   s.getGender(),
													   s.getStudentid(),
													   s.getName(),
													   CardPassUtil.decrypt( s.getPassword() ),
													   String.valueOf( ugr == null ? "-1" : ug.getId() ),
													   ugr == null ? "无分组" : ug.getName(),
													   "1",
													   String.valueOf( s.getClassid() ),
													   s.getClassid() == -1 ? "无班级" : clazz.getClassname(),
													   s.getTelephone(),
													   s.getEmail(),
													   s.getCouldborrow(),
													   s.getPhoto(),
													   ""
													   );
						allUserList.add( uc );
					} else if(o instanceof Teacher) {
						t = (Teacher) o;
						if(ugr != null) {
							ug = groupService.getUserGroupByid( ugr.getGroupid() );
						}
						
						UserCourse uc = new UserCourse(String.valueOf( t.getId() ),
													   t.getGender(),
													   t.getTeacherid(),
													   t.getName(),
													   CardPassUtil.decrypt( t.getPassword() ),
													   String.valueOf( ugr == null ? "-1" : ug.getId() ),
													   ugr == null ? "无分组" : ug.getName(),
													   "2",
													   "",
													   "",
													   t.getTelephone(),
													   t.getEmail(),
													   "not",
													   t.getPhoto(),
													   ""
													   );
						allUserList.add( uc );
					} else if(o instanceof Worker) {
						w = (Worker) o;
						if(ugr != null) {
							ug = groupService.getUserGroupByid( ugr.getGroupid() );
						}
						
						UserCourse uc = new UserCourse(String.valueOf( w.getId() ),
													   w.getGender(),
													   w.getWorkid(),
													   w.getName(),
													   CardPassUtil.decrypt( w.getPassword() ),
													   String.valueOf( ugr == null ? "-1" : ug.getId() ),
													   ugr == null ? "无分组" : ug.getName(),
													   "4",
													   "",
													   "",
													   w.getTelephone(),
													   w.getEmail(),
													   "not",
													   w.getPhoto(),
													   ""
													   );
						allUserList.add( uc );
					}
				}
			}
		}
		
		int s1 = studentService.getSumOfStudent();
		int t1 = teacherService.getSumOfTeacher();
		int w1 = workerService.getSumOfWorker();
		
		sum1 = String.valueOf(s1);
		sum2 = String.valueOf(t1);
		sum3 = String.valueOf(w1);
		sum = String.valueOf(s1 + t1 + w1);
		
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
			if(up.getType() == 1) {
				subList1.add( up );
			} else if(up.getType() == 2) {
				subList2.add( up );
			} else if(up.getType() == 4) {
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
		allGroupList = new ArrayList<Usergroup>();
		List<Usergroup> tempList = groupService.getAllUserGroup();
		for (Usergroup ug : tempList) {
			if(ug.getType() == 1 || ug.getType() == 2 || ug.getType() == 4) {
				allGroupList.add( ug );
			}
		}
		
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
	
	/**
	 * 刷新人员管理主界面
	 */
	public String gotoIndex() {
		gotoUserManageUser();
		
		return SUCCESS;
	}
}
