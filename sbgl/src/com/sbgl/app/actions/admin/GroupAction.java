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
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.services.user.GroupService;

@Scope("prototype") 
@Controller("GrpAction")
public class GroupAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1020480402800386089L;

	@Resource
	private GroupService grpService;
	
	@SuppressWarnings("unused")
	private Map<String, Object> session;
	private String tag;     		//返回执行结果 0-成功 1-失败
	private String message; 		//返回信息
	private int whichGroup = 0;		//0为用户组；1为管理员组
	public int getWhichGroup() {
		return whichGroup;
	}
	public void setWhichGroup(int whichGroup) {
		this.whichGroup = whichGroup;
	}
	
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
	
	//添加和修改分组的参数对象
	private Usergroup group;
	public Usergroup getGroup() {
		return group;
	}
	public void setGroup(Usergroup group) {
		this.group = group;
	}
	//筛选的用户组类型
	private String gourpType;
	public String getGourpType() {
		return gourpType;
	}
	public void setGourpType(String gourpType) {
		this.gourpType = gourpType;
	}
	/**
	 * 添加用户组信息
	 * @param group
	 * @return
	 */
	public String addGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		Boolean isExist = grpService.isExistGroupName( group.getName() );
		
		if(!isExist) {
			long returnCode = grpService.addUserGroup( group );
			if(returnCode == -1) {
				this.tag = "1";
				this.message = "添加组信息失败！";
			} else {
				this.tag = "0";
				this.message = "添加组信息成功！";
				
				if(group.getType() == 1 || group.getType() == 2 || group.getType() == 4) {
					whichGroup = 0;
				} else {
					whichGroup = 1;
				}
			}
		} else {
			this.tag = "2";
			this.message = "所添加的组信息已经存在！";
		}
		getGroupByPage();
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 修改用户组信息
	 * @return
	 */
	public String alterGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		
		long returnCode = grpService.alterUserGroup( group );
		
		if(returnCode == -1) {
			this.tag = "1";
			this.message = "修改组信息失败！";
		} else {
			this.tag = "0";
			this.message = "修改组信息成功！";
		}
		getGroupByPage();
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 按照id字符串删除用户组信息
	 * @return
	 */
	private String groupIds;
	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public String deleteGroup() {
		returnJSON = null;
		returnJSON = new HashMap<String,Object>();
		String[] ids = groupIds.split("_");
		
		for (String id : ids) {
			Integer oneId = Integer.valueOf( id );
			if(grpService.deleteUsergroup( oneId ) == 0) {
				this.message = "删除组成功！";
				this.tag = "0";

			} else {
				this.message = "删除组失败！";
				this.tag = "1";
			}
		}
		getGroupByPage();
		returnJSON.put("tag", tag);
		returnJSON.put("msg", message);
		return SUCCESS;
	}
	
	/**
	 * 获取全部用户组信息
	 * @return
	 */
	private List<Usergroup> allGroupList;
	public List<Usergroup> getAllGroupList() {
		return allGroupList;
	}
	public void setAllGroupList(List<Usergroup> allGroupList) {
		this.allGroupList = allGroupList;
	}
	private String crtPage;
	public String getCrtPage() {
		return crtPage;
	}
	public void setCrtPage(String crtPage) {
		this.crtPage = crtPage;
	}
	private String totPage;
	public String getTotPage() {
		return totPage;
	}
	public void setTotPage(String totPage) {
		this.totPage = totPage;
	}
	
	private String stuGroupNum;
	private String teaGroupNum;
	private String otrGroupNum;
	private String allGroupNum;
	public String getAllGroupNum() {
		return allGroupNum;
	}
	public String getStuGroupNum() {
		return stuGroupNum;
	}
	public String getTeaGroupNum() {
		return teaGroupNum;
	}
	public String getOtrGroupNum() {
		return otrGroupNum;
	}
	
	public String getGroupByPage() {
		allGroupList = new ArrayList<Usergroup>();
		List<Usergroup> tempList = grpService.getAllUserGroup();
		
		int _1 = 0, _2 = 0, _4 = 0;
		for (Usergroup ug : tempList) {
			if(ug.getType() == 1) {
				_1 ++;
			} else if(ug.getType() == 2) {
				_2 ++;
			} else if(ug.getType() == 4) {
				_4 ++;
			}
			
			if(gourpType != null && !"".equals(gourpType)) {
				if("0".equals(gourpType)) {
					allGroupList.add(ug);
				} else if("1".equals(gourpType) && ug.getType() == 1) {
					allGroupList.add(ug);
				} else if("2".equals(gourpType) && ug.getType() == 2) {
					allGroupList.add(ug);
				} else if("4".equals(gourpType) && ug.getType() == 4) {
					allGroupList.add(ug);
				}
			} else {
				allGroupList.add(ug);
			}
		}
		stuGroupNum = String.valueOf(_1);
		teaGroupNum = String.valueOf(_2);
		otrGroupNum = String.valueOf(_4);
		allGroupNum = String.valueOf(_1 + _2 + _4);
		
		int sum = allGroupList == null ? 0 : allGroupList.size();
		int crtPageNum = 1;
		
		if(crtPage == null || "".equals(crtPage)) {
			crtPageNum = 1;
			crtPage = "1";
		} else {
			crtPageNum = Integer.parseInt(crtPage);
		}
		
		int totPageNum = sum / 10;
		int lastPageNum = sum % 10;
		if(totPageNum == 0) {
			totPage = "1";
			totPageNum = 1;
		} else if(totPageNum > 0 && lastPageNum == 0) {
			totPage = String.valueOf(totPageNum);
		} else if(totPageNum > 0 && lastPageNum > 0) {
			totPage = String.valueOf(++totPageNum);
		}
		
		if(crtPageNum > totPageNum) {
			crtPageNum = totPageNum;
			crtPage = String.valueOf(totPageNum);
		}
		
		int startIndex = crtPageNum - 1;
		int endIndex = (startIndex + 1) * 10 > sum ? sum : (startIndex + 1) * 10;
		allGroupList = allGroupList == null ? new ArrayList<Usergroup>() : allGroupList.subList(startIndex*10, endIndex);
		
		return SUCCESS;
	}
}
