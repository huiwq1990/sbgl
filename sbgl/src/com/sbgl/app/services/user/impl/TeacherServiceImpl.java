package com.sbgl.app.services.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.services.user.TeacherService;

@Scope("prototype") 
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addTeacher(Teacher teacher) {
		int id = baseDao.getCode("teacherId");
		teacher.setId( id );
		
		try {
			baseDao.saveEntity( teacher );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterTeacher(Teacher teacher) {
		int id = teacher.getId();
		Teacher storeTeacher = baseDao.getEntityById(Teacher.class, id);
		
		storeTeacher.setEmail( teacher.getEmail() );
		storeTeacher.setGender( teacher.getGender() );
		storeTeacher.setName( teacher.getName() );
		storeTeacher.setPassword( teacher.getPassword() );
		storeTeacher.setPhoto( teacher.getPhoto() );
		storeTeacher.setTeacherId( teacher.getTeacherId() );
		storeTeacher.setTelephone( teacher.getTelephone() );
		storeTeacher.setEmail( teacher.getEmail() );
		
		try {
			baseDao.updateEntity( storeTeacher );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteTeacher(int teacherId) {
		Teacher teacher = baseDao.getEntityById(Teacher.class, teacherId);
		
		try {
			baseDao.deleteEntity( teacher );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Teacher> getAllTeacher() {
		return baseDao.getAllEntity(Teacher.class);
	}

	@Override
	public boolean isExistTeacherCode(String teacherCode) {
		return baseDao.isExist(Teacher.class, "teacherId", teacherCode);
	}

	@Override
	public Teacher getTeacherById(int teacherId) {
		Teacher teacher = baseDao.getEntityById(Teacher.class, teacherId);
		return teacher;
	}
}
