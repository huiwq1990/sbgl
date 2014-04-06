package com.sbgl.app.actions.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.sbgl.app.actions.admin.template.ClassTemplate;
import com.sbgl.app.actions.admin.template.ClassTypeTemplate;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.services.user.ClazzService;
import com.sbgl.app.services.user.GroupService;

@Scope("prototype") 
@Controller("ClassAction")
public class ClazzAction extends ActionSupport implements SessionAware {
	@Resource
	private ClazzService clazzService;
	@Resource
	private GroupService grpService;

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
				getClazzByGroup();
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
			getClazzByGroup();
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
				getClazzByGroup();
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
	private String clazzType;
	public String getClazzType() {
		return clazzType;
	}
	public void setClazzType(String clazzType) {
		this.clazzType = clazzType;
	}
	
	private List<Clazz> allClazzList;
	public List<Clazz> getAllClazzList() {
		return allClazzList;
	}
	
	private List<ClassTemplate> allShowClazzList;
	public List<ClassTemplate> getAllShowClazzList() {
		return allShowClazzList;
	}
	
	private List<ClassTypeTemplate> stuGroupList;
	public List<ClassTypeTemplate> getStuGroupList() {
		return stuGroupList;
	}
	
	private String sum;
	public String getSum() {
		return sum;
	}
	
	private List<Usergroup> allGroup;
	public List<Usergroup> getAllGroup() {
		return allGroup;
	}
	
	public String getClazzByGroup() {
		allShowClazzList = new ArrayList<ClassTemplate>();
		int ct = 0;
		int oneType = -1;
		int twoType = -1;
		int threeType = -1;
		int count1 = 0, count2 = 0, count3 = 0;
		
		if(clazzType != null && !"-1".equals(clazzType)) {
			ct = Integer.parseInt(clazzType);
		}
		allGroup = grpService.getUserGroupByType(1);
		ClassTypeTemplate ctt = null;
		if(allGroup != null && allGroup.size() > 3) {
			stuGroupList = new ArrayList<ClassTypeTemplate>();
			ctt = new ClassTypeTemplate(allGroup.get(0));
			stuGroupList.add( ctt );
			oneType = allGroup.get(0).getId();
			
			ctt = new ClassTypeTemplate(allGroup.get(1));
			stuGroupList.add( ctt );
			twoType = allGroup.get(1).getId();
			
			ctt = new ClassTypeTemplate(allGroup.get(2));
			stuGroupList.add( ctt );
			threeType = allGroup.get(2).getId();
		} else if(allGroup != null) {
			oneType = allGroup.size() > 0 ? allGroup.get(0).getId() : -1;
			twoType = allGroup.size() > 1 ? allGroup.get(1).getId() : -1;
			threeType = allGroup.size() > 2 ? allGroup.get(2).getId() : -1;
			stuGroupList = new ArrayList<ClassTypeTemplate>();
			if(oneType != -1) {
				stuGroupList.add( new ClassTypeTemplate (allGroup.get(0)) );
			}
			if(twoType != -1) {
				stuGroupList.add( new ClassTypeTemplate (allGroup.get(1)) );
			}
			if(threeType != -1) {
				stuGroupList.add( new ClassTypeTemplate (allGroup.get(2)) );
			}
		} else {
			stuGroupList = new ArrayList<ClassTypeTemplate>();
			allGroup = new ArrayList<Usergroup>();
		}
		
		List<Clazz> clazzList = clazzService.getAllClazz();
		for(Clazz c : clazzList) {
			int t = c.getClasstype();
			if( t == oneType ) {
				count1 ++;
			} else if( t == twoType ) {
				count2 ++;
			} else if( t == threeType ) {
				count3 ++;
			}
		}
		
		for(ClassTypeTemplate t : stuGroupList) {
			if(t.getId().equals(oneType)) {
				t.setSum( String.valueOf(count1) );
			} else if(t.getId().equals(twoType)) {
				t.setSum( String.valueOf(count2) );
			} else if(t.getId().equals(threeType)) {
				t.setSum( String.valueOf(count3) );
			}
		}
		
		if(ct == 0) {
			allClazzList = clazzList;
		} else {
			allClazzList = clazzService.getClazzByType(ct);
		}
		
		sum = String.valueOf( clazzList.size() );
		
		for(Clazz c : allClazzList) {
			for(Usergroup u : allGroup) {
				if(c.getClasstype().equals( u.getId() )) {
					allShowClazzList.add( new ClassTemplate(c, u.getName()) );
					break;
				}
			}
		}
		
		return SUCCESS;
	}
	/**
	 * 页面访问
	 */
	public String gotoUserManageClass() {
		getClazzByGroup();
		return SUCCESS;
	}
}
