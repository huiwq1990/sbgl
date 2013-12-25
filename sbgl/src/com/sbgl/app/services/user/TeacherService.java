package com.sbgl.app.services.user;

import java.util.List;

import com.sbgl.app.entity.Teacher;

public interface TeacherService {
	//添加教师
	public int addTeacher(Teacher teacher);
	//修改教师
	public int alterTeacher(Teacher teacher);
	//删除教师
	public int deleteTeacher(int teacherId);
	//获取全部教师
	public List<Teacher> getAllTeacher();
	//判断此教师编号是否存在
	public boolean isExistTeacherCode(String teacherCode);
	//根据ID获取教师
	public Teacher getTeacherById(int teacherId);
}
