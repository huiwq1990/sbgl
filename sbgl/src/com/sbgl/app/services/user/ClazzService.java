package com.sbgl.app.services.user;

import java.util.List;
import com.sbgl.app.entity.Clazz;

public interface ClazzService {
	//添加班级信息
	public int addClazz(Clazz clazz);
	//修改班级信息
	public int alterClazz(Clazz clazz);
	//删除班级信息
	public int deleteClazz(int clazzId);
	//获取全部班级信息
	public List<Clazz> getAllClazz();
}
