package com.sbgl.app.services.user.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Student;
import com.sbgl.app.services.user.StudentService;

@Scope("prototype") 
@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addStudent(Student student) {
		int id = baseDao.getCode("userId");
		student.setId( id );
		student.setMakedate( new Date() );
		
		try {
			baseDao.saveEntity( student );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterStudent(Student student) {
		int id = student.getId();
		Student storeStu = baseDao.getEntityById(Student.class, id);
		
		storeStu.setClassid( student.getClassid() );
		storeStu.setCouldborrow( student.getCouldborrow() );
		storeStu.setEmail( student.getEmail() );
		storeStu.setGender( student.getGender() );
		storeStu.setName( student.getName() );
		storeStu.setPassword( student.getPassword() );
		storeStu.setPhoto( student.getPhoto() );
		storeStu.setStudentid( student.getStudentid() );
		storeStu.setTelephone( student.getTelephone() );
		storeStu.setModifydate( new Date() );
		
		try {
			baseDao.updateEntity( storeStu );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteStudent(int studentId) {
		Student stu = baseDao.getEntityById(Student.class, studentId);
		
		try {
			baseDao.deleteEntity( stu );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> resultList = baseDao.getAllEntity(Student.class);
		return resultList;
	}

	@Override
	public boolean isExistStudentCode(String stuCode) {
		return baseDao.isExist(Student.class, "studentId", stuCode);
	}

	@Override
	public Student getStudentById(int stuId) {
		Student stu = baseDao.getEntityById(Student.class, stuId);
		return stu;
	}

}
