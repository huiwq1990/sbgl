package com.sbgl.app.services.user;

import java.util.List;
import com.sbgl.app.entity.Student;

public interface StudentService {
	//添加用户分组信息
	public int addStudent(Student student);
	//修改用户分组信息
	public int alterStudent(Student student);
	//删除用户分组信息
	public int deleteStudent(int studentId);
	//获取全部用户分组信息
	public List<Student> getAllStudent();
}
