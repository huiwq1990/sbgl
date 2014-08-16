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
	//判断学生学号是否存在
	public boolean isExistStudentCode(String stuCode);
	//根据ID获取学生信息
	public Student getStudentById(int stuId);
	//获取学生总数
	public Integer getSumOfStudent();
	//根据学号获取学生信息
	public Student getStudentByCode(String stuCode);
	//获取全部学生信息用于界面显示
	public List<Object[]> getAllStuShowList();
}
