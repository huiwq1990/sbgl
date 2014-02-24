package com.sbgl.app.services.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Clazz;
import com.sbgl.app.entity.Student;
import com.sbgl.app.services.user.ClazzService;

@Scope("prototype") 
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addClazz(Clazz clazz) {
		int id = baseDao.getCode("clazzId");
		clazz.setClassId( id );
		
		try {
			baseDao.saveEntity( clazz );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterClazz(Clazz clazz) {
		int id = clazz.getClassId();
		Clazz storeClazz = baseDao.getEntityById(Clazz.class, id);
		
		storeClazz.setClassname( clazz.getClassname() );
		
		try {
			baseDao.updateEntity( storeClazz );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteClazz(int clazzId) {
		Clazz clazz = baseDao.getEntityById(Clazz.class, clazzId);
		
		try {
			baseDao.deleteEntity( clazz );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Clazz> getAllClazz() {
		List<Clazz> resultList = baseDao.getAllEntity( Clazz.class );
		return resultList;
	}

	@Override
	public boolean isExistClazzName(String clazzName) {
		return baseDao.isExist(Clazz.class, "classname", clazzName);
	}

	@Override
	public Clazz getClazzById(int clazzId) {
		return baseDao.getEntityById(Clazz.class, clazzId);
	}

	@Override
	public List<Map<Clazz, List<Student>>> getAllClazzDetail() {
		List<Map<Clazz, List<Student>>> resultList = new ArrayList<Map<Clazz, List<Student>>>();
		Map<Clazz, List<Student>> clazzMap = new HashMap<Clazz, List<Student>>();
		
		List<Clazz> allClazz = baseDao.getAllEntity(Clazz.class);
		List<Student> allStu = null;
		for (Clazz c : allClazz) {
			allStu = baseDao.getEntityByIntProperty(Student.class.getName(), "classid", c.getClassId());
			if(allStu != null && allStu.size() > 0) {
				clazzMap.put(c, allStu);
				resultList.add(clazzMap);
			}
			
		}
		
		return resultList.size() > 0 ? resultList : null;
	}

}
