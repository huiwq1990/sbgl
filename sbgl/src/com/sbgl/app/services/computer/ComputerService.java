package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computer;
import com.sbgl.app.entity.ComputerFull;
import com.sbgl.common.DataError;
import com.sbgl.util.*;

public interface ComputerService{
	



	
//  根据id删除实体	
	public int deleteComputer(Integer computerId);

//  根据实体删除实体
	public int deleteComputer(Computer computer);
	
//	根据id查询实体类		
	public Computer selectComputerById(Integer computerId);

//  查询全部实体
	public List<Computer> selectComputerAll();
	
//	根据id查询full类
	public ComputerFull selectComputerFullById(Integer computerId);

//  查询全部full
	public List<ComputerFull> selectComputerFullAll();
		
//  查询数量
	public int countComputerRow();
//  分页查询
	public List<Computer> selectComputerByPage(Page page);
	public List<ComputerFull> selectComputerFullByPage(Page page);
	
//根据关联查询实体	
	public List<Computer> selectComputerByComputermodelId(Integer computermodelid );
//根据关联查询实体full	
	public List<ComputerFull> selectComputerFullByComputermodelId(Integer computermodelid );

	void updateComputermodelTo(int originalComputermodelid,
			int toComputermodelid);


	List<ComputerFull> selectComputerFullByConditionAndPage(
			String conditionSql, Page page);

	List<Computer> selectComputerByCondition(String condition);

	List<Computer> selectComputerByConditionAndPage(String condition, Page page);

	List<ComputerFull> selectComputerFullByCondition(String condition);



	int deleteComputerByType(Integer type);




	

	int deleteComputerByType(List<Integer> delTypeList) throws DataError;


	void updateComputer(Computer ch, Computer en,
			int orignialComputerModelType, int nowAviBow, int orgAviBow) throws DataError;

	void addComputerAndSetNum(Computer ch, Computer en, int availBorrow,
			int originalTotalNum, int originalAvailBorrowNum);
					
	public Computer selectComputerByTypeAndLanguage(int computertype,
			int languagech);

	List<Computer> selByCategorytype(int categorytype, int language);

	List<ComputerFull> selFullByCategorytype(int categorytype, int language);

	List<Computer> selByModeltype(int modeltype, int language);

	List<ComputerFull> selFullByModeltype(int modeltype, int language);

	void selFullByPage(int computercategorytype, int computermodeltype,
			int computerstatusid, Page page, int languge,
			List<ComputerFull> computerFullListCh,
			List<ComputerFull> computerFullListEn);


	
}
