package com.sbgl.app.services.user;

import java.util.List;
import java.util.Map;

import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Student;

public interface ClazzService {
	//添加班级信息
	public int addClazz(Clazz clazz);
	//修改班级信息
	public int alterClazz(Clazz clazz);
	//删除班级信息
	public int deleteClazz(int clazzId);
	//获取全部班级信息
	public List<Clazz> getAllClazz();
	//判断是否存在该班级名称
	public boolean isExistClazzName(String clazzName);
	//根据id获取班级
	public Clazz getClazzById(int clazzId);
	//获取全部班级的具体人员信息
	public List<Map<Clazz, List<Student>>> getAllClazzDetail();
	//根据班级类型获取班级
	public List<Clazz> getClazzByType(int typeId);
}
