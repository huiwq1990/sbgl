package com.sbgl.app.services.user.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Teacher;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.services.user.TeacherService;
import com.sbgl.util.CardPassUtil;

@Scope("prototype") 
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addTeacher(Teacher teacher) {
		int id = baseDao.getCode("userId");
		teacher.setId( id );
		teacher.setPassword( CardPassUtil.encrypt(teacher.getPassword()) );
		teacher.setMakedate( new Date() );
		//如果没有上传图片，则使用默认图片
		if( teacher.getPhoto() == null || "".equals( teacher.getPhoto() ) ) {
			teacher.setPhoto("photo.jpg");
		}
		
		Userlogininfo info = new Userlogininfo();
		info.setId( baseDao.getCode("loginInfoId") );
		info.setIsfirstlogin( new Boolean(true).toString() );
		info.setLastlogintime(null);
		info.setLogincount(0);
		info.setRemark(null);
		info.setUserid(id);
		info.setPagelanguage("0");
		
		try {
			baseDao.saveEntity( teacher );
			baseDao.saveEntity( info );
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
		storeTeacher.setPassword( CardPassUtil.encrypt(teacher.getPassword()) );
		storeTeacher.setPhoto( teacher.getPhoto() );
		storeTeacher.setTeacherid( teacher.getTeacherid() );
		storeTeacher.setTelephone( teacher.getTelephone() );
		storeTeacher.setEmail( teacher.getEmail() );
		storeTeacher.setModifydate( new Date() );
		
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
		return baseDao.isExist(Teacher.class, "teacherid", teacherCode);
	}

	@Override
	public Teacher getTeacherById(int teacherId) {
		Teacher teacher = baseDao.getEntityById(Teacher.class, teacherId);
		return teacher;
	}

	@Override
	public Integer getSumOfTeacher() {
		List<Teacher> resultList = getAllTeacher();
		if(resultList != null) {
			return resultList.size();
		}
		return 0;
	}

	@Override
	public Teacher getTeacherByCode(String teaCode) {
		List<Teacher> resultList = baseDao.getEntityByProperty(Teacher.class.getName(), "teacherid", teaCode);
		return resultList != null ? resultList.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllTeaShowList() {
		final StringBuilder sb = new StringBuilder();
		sb.append("select 2 as role, t.id, t.gender, t.teacherid, t.name, t.password, t.telephone, t.email, t.photo,");
		sb.append("g.id as gid, g.name as gname ");
		sb.append("from teacher as t ");
		sb.append("left join usergrouprelation as r on t.id = r.userid ");
		sb.append("left join usergroup as g on r.groupid = g.id");
		
		HibernateTemplate tmpl = baseDao.getHibernateTemplate();  
		return tmpl.execute(new HibernateCallback<List<Object[]>>() {  
			@Override  
		    public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
		        SQLQuery query = session.createSQLQuery( sb.toString() );  
		        
		        return (List<Object[]>) query.list();  
		    }  
		});
	}
}
