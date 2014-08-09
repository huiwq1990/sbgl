package com.sbgl.app.services.computer;

import java.util.Date;
import java.util.List;

import com.sbgl.app.actions.computer.ComputerorderEntity;
import com.sbgl.app.entity.Borrowperiod;
import com.sbgl.app.entity.Computerorder;
import com.sbgl.app.entity.ComputerorderFull;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

public interface ComputerorderService{
	
	public void addComputerorder(Computerorder computerorder);

//	public void addComputerorder(Computerorder ch,Computerorder en);
//	public void addComputerorderWithId(Computerorder computerorder);
		
	public void updateComputerorder(Computerorder computerorder);
	
//  根据id删除实体	
	public int deleteComputerorder(Integer computerorderId);

//  根据实体删除实体
	public int deleteComputerorder(Computerorder computerorder);
	
//	根据id查询实体类		
	public Computerorder selectComputerorderById(Integer computerorderId);

//  查询全部实体
	public List<Computerorder> selectComputerorderAll();
	
//	根据id查询full类
	public ComputerorderFull selectComputerorderFullById(Integer computerorderId);

//  查询全部full
	public List<ComputerorderFull> selectComputerorderFullAll();
		
//  查询数量
	public int countComputerorderRow();
//  分页查询
	public List<Computerorder> selectComputerorderByPage(Page page);
	public List<ComputerorderFull> selectComputerorderFullByPage(Page page);
		
		
		public List<Computerorder> selectComputerorderByCondition(String condition);

        public List<Computerorder>  selectComputerorderByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerorderFull> selectComputerorderFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerorderFull>  selectComputerorderFullByConditionAndPage(String condition,final Page page);

		int execSql(String sql);

		boolean auditComputerorder(Computerorder cr);

		void addComputerorder(Computerorder computerorder,
				int computerordertype, int reorder, int uid,
				List<Computerorderdetail> computerorderdetailList) throws Exception;

		boolean vaildComputerorderForm(
				List<Computerorderdetail> orderComputerorderdetailList,
				Date currentDate, int currentPeriod, Date endDate,
				int endPeriod, int currentLanguage,
				List<Borrowperiod> borrowperiodList,
				int computeroderadvanceorderday);

		int deleteComputerorder(List<Integer> orderidList) throws DataError;

		List<Computerorderdetail> adminForceGetComputer(
				List<Computerorderdetail> newOrderComputerorderdetailList,
				Date currentDate, int currentPeriod, Date endDate,
				int endPeriod, int currentLanguage,
				List<Borrowperiod> borrowperiodList,
				int computeroderadvanceorderday);

		List<ComputerorderEntity> getUnderwayComputerorder(int userid);

		List<ComputerorderFull> selFullByStatus(int uid, int orderstatus);

		List<ComputerorderFull> selFullByStatus(int orderstatus);
		
	
//根据关联查询实体	
		
	
	
}
