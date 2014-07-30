package com.sbgl.app.services.user.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.Userlogininfo;
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
		
		Userlogininfo info = new Userlogininfo();
		info.setId( baseDao.getCode("loginInfoId") );
		info.setIsfirstlogin( new Boolean(true).toString() );
		info.setLastlogintime(null);
		info.setLogincount(0);
		info.setRemark(null);
		info.setUserid(id);
		info.setPagelanguage("0");
		
		try {
			baseDao.saveEntity( student );
			baseDao.saveEntity( info );
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
		return baseDao.isExist(Student.class, "studentid", stuCode);
	}

	@Override
	public Student getStudentById(int stuId) {
		Student stu = baseDao.getEntityById(Student.class, stuId);
		return stu;
	}

	@Override
	public Integer getSumOfStudent() {
		List<Student> resultList = getAllStudent();
		if(resultList != null) {
			return resultList.size();
		}
		return 0;
	}

	@Override
	public Student getStudentByCode(String stuCode) {
		List<Student> resultList = baseDao.getEntityByProperty(Student.class.getName(), "studentid", stuCode);
		return resultList != null ? resultList.get(0) : null;
	}

}
