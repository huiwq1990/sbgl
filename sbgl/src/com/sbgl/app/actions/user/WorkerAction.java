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
import com.sbgl.app.entity.Usergrouprelation;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.user.UserGroupRelationService;
import com.sbgl.app.services.user.WorkerService;

@Scope("prototype") 
@Controller("WorkerAction")
public class WorkerAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7759632650510859924L;

	@Resource
	private WorkerService workerService;
	
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
	
	//保存和修改其他人员使用的参数对象
	private Worker worker;
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
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
	 * 添加其他用户信息
	 * @return
	 */
	public String addWorker() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = workerService.isExistWorkerCode( worker.getWorkid() );
		
		if(!isExist) {
			int returnCode = workerService.addWorker( worker );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加其他人员信息失败！";
			} else {
				Usergrouprelation ugr = new Usergrouprelation();
				ugr.setGroupid( group.getId() );
				ugr.setUserid( returnCode );
				userGroupRelationService.addUserGroupRelation( ugr );
				this.tag = "0";
				this.message = "添加其他人员信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的其他人员编号已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改其他人员信息
	 * @return
	 */
	public String alterWorker() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		int returnCode = workerService.alterWorker( worker );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改其他人员信息失败！";
		} else {
			Usergrouprelation ugr = userGroupRelationService.getRelationByUserId( worker.getId() );
			ugr.setGroupid( group.getId() );
			userGroupRelationService.alterUserGroupRelation( ugr );
			this.tag = "0";
			this.message = "修改其他人员信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除其他人员信息
	 * @return
	 */
	private String workerIds;
	public String getWorkerIds() {
		return workerIds;
	}
	public void setWorkerIds(String workerIds) {
		this.workerIds = workerIds;
	}
	public String deleteWorker() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = workerIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			Worker wk = workerService.getWorkerById( oneId );
			Usergrouprelation temp = userGroupRelationService.getRelationByUserId( wk.getId() );
			if(workerService.deleteWorker( oneId ) == 0 && userGroupRelationService.deleteUserGroupRelation( temp.getId() ) == 0) {
				this.message = "删除其他人员信息成功！";
				this.tag = "0";
			} else {
				this.message = "删除其他人员信息失败！";
				this.tag = "1";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部其他人员
	 * @return
	 */
	private List<Worker> allWorkerList;
	public List<Worker> getAllWorkerList() {
		return allWorkerList;
	}
	public String getAllTeacher() {
		allWorkerList = workerService.getAllWorker();
		return SUCCESS;
	}
}
