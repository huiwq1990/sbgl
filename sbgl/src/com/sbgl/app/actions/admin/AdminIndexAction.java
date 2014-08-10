package com.sbgl.app.actions.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sbgl.app.actions.common.BaseAction;
import com.sbgl.app.actions.common.CommonConfig;
import com.sbgl.app.actions.orderadmin.OrderCountFull;
import com.sbgl.app.common.computer.ComputerorderInfo;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.app.entity.Courseconfig;
import com.sbgl.app.entity.Loginuser;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.computer.ComputerorderService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.app.services.orderadmin.OrderAdminService;
import com.sbgl.app.services.teach.CourseconfigService;
import com.sbgl.app.services.user.StudentService;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.app.services.user.UserlogininfoService;
import com.sbgl.app.services.user.WorkerService;
import com.sbgl.common.DataError;
import com.sbgl.util.CardPassUtil;
import com.sbgl.util.CookiesUtil;


@Scope("prototype") 
@Controller("AdminIndexAction")
public class AdminIndexAction extends BaseAction{
	
	
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private WorkerService workerService;
	@Resource
	private UserlogininfoService loginInfoService;
	
	@Resource
	private ComputerorderService computerorderService;	
	private Computerorder computerorder = new Computerorder();//实例化一个模型
	private ComputerorderFull computerorderFull = new ComputerorderFull();//实例化一个模型
	List<Computerorder> computerorderList = new ArrayList<Computerorder>();

	
	@Resource
	private ComputerorderdetailService computerorderdetailService;	
	private Computerorderdetail computerorderdetail = new Computerorderdetail();//实例化一个模型
	private ComputerorderdetailFull computerorderdetailFull = new ComputerorderdetailFull();//实例化一个模型	
	List<Computerorderdetail> computerorderdetailList = new ArrayList<Computerorderdetail>();
	
	
	@Resource
	private OrderAdminService orderAdminService;
	

	private OrderCountFull orderCountFull;

	@Resource
	private CourseconfigService courseconfigService;	
	private Courseconfig courseconfig = new Courseconfig();//实例化一个模型
	
	int computerorderWaitAuditNum;
	
	int totalUserNum;
	int studentNum;
	int teacherNum;
	int workerNum;
	
	
	public String adminIndex(){
		
//		统计待审核的预约数量
		computerorderList = computerorderService.selectComputerorderByCondition(" where status = "+ComputerorderInfo.ComputerorderStatusAduitWait);
		if(computerorderList == null){
			computerorderWaitAuditNum = 0;
		}else{
			computerorderWaitAuditNum = computerorderList.size();
		}
		orderCountFull = orderAdminService.findOrderCount("");
		
		//用户数量统计
		studentNum = studentService.getSumOfStudent();
		teacherNum = teacherService.getSumOfTeacher();
		workerNum = workerService.getSumOfWorker();
		totalUserNum = studentNum + teacherNum + workerNum;
		
		
//		学期信息设置
		try {
			courseconfig = courseconfigService.getCurrentCourseconfig();
		} catch (DataError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String firstSetup() {
		return SUCCESS;
	}
	
	private Map<String,Object> returnJSON;
	public Map<String,Object> getReturnJSON() {
		return returnJSON;
	}
	
	private String password;
	private String phoneNumber;
	private String email;
	private String pageLan;
	
	public String finishSetup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		Loginuser u = (Loginuser) session.get("loginUser");
		Integer id = u.getId();
		String userType = u.getRoletype();
		
		Integer res = null;
		if("1".equals(userType)) {
			Student s = studentService.getStudentById(id);
			s.setPassword(password);
			s.setTelephone(phoneNumber);
			s.setEmail(email);
			res = studentService.alterStudent(s);
		} else if("2".equals(userType)) {
			Teacher t = teacherService.getTeacherById(id);
			t.setPassword(password);
			t.setTelephone(phoneNumber);
			t.setEmail(email);
			res = teacherService.alterTeacher(t);
		} else if("4".equals(userType)) {
			Worker w = workerService.getWorkerById(id);
			w.setPassword(password);
			w.setTelephone(phoneNumber);
			w.setEmail(email);
			res = workerService.alterWorker(w);
		}
		
		u.setTelephone(phoneNumber);
		u.setEmail(email);
		u.setPassword( CardPassUtil.encrypt(password) );
		session.put(CommonConfig.sessionuser, u);
		
		if(res == null) {
			returnJSON.put("tag", -1);
			returnJSON.put("msg", "更新用户信息失败！");
		} else {
			Userlogininfo loginInfo = loginInfoService.getLoinInfoByUserId(id);
			loginInfo.setIsfirstlogin("false");
			loginInfo.setLastlogintime( new Date() );
			Integer count = loginInfo.getLogincount();
			count = count == null ? 0 : ++count;
			loginInfo.setLogincount(count);
			loginInfo.setPagelanguage(pageLan);
			loginInfoService.alterUserLoginInfo(loginInfo);
			
			session.put("isFirst", false);
			session.put(CommonConfig.sessionLanguagetype, pageLan);
			CookiesUtil.addLoginCookie("pageLan", loginInfo.getPagelanguage());
			
			if( "1".equals(u.getPrivilege()) ) {
				returnJSON.put("tag", 0);
				returnJSON.put("url", "./adminIndex.action");
			} else {
				returnJSON.put("tag", 0);
				returnJSON.put("url", "./index.action");
			}
		}
		
		return SUCCESS;
	}


	public ComputerorderService getComputerorderService() {
		return computerorderService;
	}


	public void setComputerorderService(ComputerorderService computerorderService) {
		this.computerorderService = computerorderService;
	}


	public Computerorder getComputerorder() {
		return computerorder;
	}


	public void setComputerorder(Computerorder computerorder) {
		this.computerorder = computerorder;
	}


	public ComputerorderFull getComputerorderFull() {
		return computerorderFull;
	}


	public void setComputerorderFull(ComputerorderFull computerorderFull) {
		this.computerorderFull = computerorderFull;
	}


	public List<Computerorder> getComputerorderList() {
		return computerorderList;
	}


	public void setComputerorderList(List<Computerorder> computerorderList) {
		this.computerorderList = computerorderList;
	}


	public ComputerorderdetailService getComputerorderdetailService() {
		return computerorderdetailService;
	}


	public void setComputerorderdetailService(
			ComputerorderdetailService computerorderdetailService) {
		this.computerorderdetailService = computerorderdetailService;
	}


	public Computerorderdetail getComputerorderdetail() {
		return computerorderdetail;
	}


	public void setComputerorderdetail(Computerorderdetail computerorderdetail) {
		this.computerorderdetail = computerorderdetail;
	}


	public ComputerorderdetailFull getComputerorderdetailFull() {
		return computerorderdetailFull;
	}


	public void setComputerorderdetailFull(
			ComputerorderdetailFull computerorderdetailFull) {
		this.computerorderdetailFull = computerorderdetailFull;
	}


	public List<Computerorderdetail> getComputerorderdetailList() {
		return computerorderdetailList;
	}


	public void setComputerorderdetailList(
			List<Computerorderdetail> computerorderdetailList) {
		this.computerorderdetailList = computerorderdetailList;
	}


	public int getComputerorderWaitAuditNum() {
		return computerorderWaitAuditNum;
	}


	public void setComputerorderWaitAuditNum(int computerorderWaitAuditNum) {
		this.computerorderWaitAuditNum = computerorderWaitAuditNum;
	}


	public int getTotalUserNum() {
		return totalUserNum;
	}


	public void setTotalUserNum(int totalUserNum) {
		this.totalUserNum = totalUserNum;
	}


	public int getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}


	public int getTeacherNum() {
		return teacherNum;
	}


	public void setTeacherNum(int teacherNum) {
		this.teacherNum = teacherNum;
	}


	public int getWorkerNum() {
		return workerNum;
	}


	public void setWorkerNum(int workerNum) {
		this.workerNum = workerNum;
	}


	public OrderCountFull getOrderCountFull() {
		return orderCountFull;
	}


	public void setOrderCountFull(OrderCountFull orderCountFull) {
		this.orderCountFull = orderCountFull;
	}


	public StudentService getStudentService() {
		return studentService;
	}


	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}


	public TeacherService getTeacherService() {
		return teacherService;
	}


	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}


	public WorkerService getWorkerService() {
		return workerService;
	}


	public void setWorkerService(WorkerService workerService) {
		this.workerService = workerService;
	}


	public OrderAdminService getOrderAdminService() {
		return orderAdminService;
	}


	public void setOrderAdminService(OrderAdminService orderAdminService) {
		this.orderAdminService = orderAdminService;
	}


	public CourseconfigService getCourseconfigService() {
		return courseconfigService;
	}


	public void setCourseconfigService(CourseconfigService courseconfigService) {
		this.courseconfigService = courseconfigService;
	}


	public Courseconfig getCourseconfig() {
		return courseconfig;
	}


	public void setCourseconfig(Courseconfig courseconfig) {
		this.courseconfig = courseconfig;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPageLan() {
		return pageLan;
	}

	public void setPageLan(String pageLan) {
		this.pageLan = pageLan;
	}
}
