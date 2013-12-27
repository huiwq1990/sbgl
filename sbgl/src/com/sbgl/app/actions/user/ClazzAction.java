package com.sbgl.app.actions.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.services.user.ClazzService;

@Scope("prototype") 
@Controller("ClazzAction")
public class ClazzAction extends ActionSupport implements SessionAware {
	@Resource
	private ClazzService clazzService;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6021088828615474440L;
	
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
	
	//添加和修改班级信息的接收参数对象
	private Clazz clazz;
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
	/**
	 * 添加班级信息
	 * @param clazz
	 * @return
	 */
	public String addClazz() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = clazzService.isExistClazzName( clazz.getClassname() );
		
		if(!isExist) {
			long returnCode = clazzService.addClazz( clazz );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加班级信息失败！";
			} else {
				getAllClazz();
				this.tag = "0";
				this.message = "添加班级信息成功！";
			}
		} else {
			this.tag = "2";
			this.message = "所添加的班级信息已经存在！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改班级信息
	 * @param clazz
	 * @return
	 */
	public String alterClazz() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		long returnCode = clazzService.alterClazz( clazz );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改班级信息失败！";
		} else {
			getAllClazz();
			this.tag = "0";
			this.message = "修改班级信息成功！";
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除班级信息
	 * @return
	 */
	private String clazzIds;
	public String getClazzIds() {
		return clazzIds;
	}
	public void setClazzIds(String clazzIds) {
		this.clazzIds = clazzIds;
	}
	
	public String deleteClazz() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = clazzIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			if(clazzService.deleteClazz(oneId) == 0) {
				this.message = "删除班级成功！";
				this.tag = "0";
				getAllClazz();
			} else {
				this.message = "删除班级失败！";
				this.tag = "1";
			}
		}
		
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部班级信息
	 * @return
	 */
	private List<Clazz> allClazzList;
	public List<Clazz> getAllClazzList() {
		return allClazzList;
	}
	
	public String getAllClazz() {
		allClazzList = clazzService.getAllClazz();
		return SUCCESS;
	}
	/**
	 * 页面访问
	 */
	public String gotoUserManageClass() {
		getAllClazz();
		return SUCCESS;
	}
}
