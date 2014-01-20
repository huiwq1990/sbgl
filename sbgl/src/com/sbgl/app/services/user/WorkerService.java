package com.sbgl.app.services.user;

import java.util.List;
import com.sbgl.app.entity.Worker;

public interface WorkerService {
	//添加其他用户信息
	public int addWorker(Worker worker);
	//修改其他用户信息
	public int alterWorker(Worker worker);
	//删除其他用户信息
	public int deleteWorker(int workerId);
	//获取全部其他用户信息
	public List<Worker> getAllWorker();
	//判断其他用户编号是否存在
	public boolean isExistWorkerCode(String workerCode);
	//根据ID查找其他用户
	public Worker getWorkerById(int workerId);
}
