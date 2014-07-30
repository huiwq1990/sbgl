package com.sbgl.app.services.user.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.user.WorkerService;

@Scope("prototype") 
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addWorker(Worker worker) {
		int id = baseDao.getCode("userId");
		worker.setId( id );
		worker.setMakedate( new Date() );
		
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
		storeWorker.setPassword( worker.getPassword() );
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

}
