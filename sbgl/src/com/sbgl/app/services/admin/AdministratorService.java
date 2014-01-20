package com.sbgl.app.services.admin;

import com.sbgl.app.entity.Administrator;

public interface AdministratorService {
	//新增
	public void addAdministrator(Administrator administrator);
	//修改
	public void updateAdministrator(Administrator administrator);
	//删除
	public void delAdministrator(Integer adminId);
	//根据id获得
	public Administrator getAdministratorById(Integer adminId);
}
