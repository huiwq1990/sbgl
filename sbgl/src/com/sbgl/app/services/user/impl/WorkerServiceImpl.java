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
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.user.WorkerService;
import com.sbgl.util.CardPassUtil;

@Scope("prototype") 
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addWorker(Worker worker) {
		int id = baseDao.getCode("userId");
		worker.setId( id );
		worker.setPassword( CardPassUtil.encrypt(worker.getPassword()) );
		worker.setMakedate( new Date() );
		//如果没有上传图片，则使用默认图片
		if( worker.getPhoto() == null || "".equals( worker.getPhoto() ) ) {
			worker.setPhoto("avatar.jpg");
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
			baseDao.saveEntity( worker );
			baseDao.saveEntity( info );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterWorker(Worker worker) {
		int id = worker.getId();
		Worker storeWorker = baseDao.getEntityById(Worker.class, id);
		
		storeWorker.setEmail( worker.getEmail() );
		storeWorker.setGender( worker.getGender() );
		storeWorker.setName( worker.getName() );
		storeWorker.setPassword( CardPassUtil.encrypt(worker.getPassword()) );
		storeWorker.setPhoto( worker.getPhoto() );
		storeWorker.setTelephone( worker.getTelephone() );
		storeWorker.setWorkid( worker.getWorkid() );
		storeWorker.setModifydate( new Date() );
		
		try {
			baseDao.updateEntity( storeWorker );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteWorker(int workerId) {
		Worker worker = baseDao.getEntityById(Worker.class, workerId);
		
		try {
			baseDao.deleteEntity( worker );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<Worker> getAllWorker() {
		List<Worker> resultList = baseDao.getAllEntity(Worker.class);
		return resultList;
	}

	@Override
	public boolean isExistWorkerCode(String workerCode) {
		return baseDao.isExist(Worker.class, "workid", workerCode);
	}

	@Override
	public Worker getWorkerById(int workerId) {
		Worker worker = baseDao.getEntityById(Worker.class, workerId);
		return worker;
	}

	@Override
	public Integer getSumOfWorker() {
		List<Worker> resultList = getAllWorker();
		if(resultList != null) {
			return resultList.size();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllWkrShowList() {
		final StringBuilder sb = new StringBuilder();
		sb.append("select 4 as role, w.id, w.gender, w.workid, w.name, w.password, w.telephone, w.email, w.photo,");
		sb.append("g.id as gid, g.name as gname ");
		sb.append("from worker as w ");
		sb.append("left join usergrouprelation as r on w.id = r.userid ");
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
