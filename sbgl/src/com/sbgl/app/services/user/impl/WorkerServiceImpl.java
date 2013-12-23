package com.sbgl.app.services.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Worker;
import com.sbgl.app.services.user.WorkerService;

@Scope("prototype") 
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addWorker(Worker worker) {
		int id = baseDao.getCode("workerId");
		worker.setId( id );
		
		try {
			baseDao.saveEntity( worker );
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

}
