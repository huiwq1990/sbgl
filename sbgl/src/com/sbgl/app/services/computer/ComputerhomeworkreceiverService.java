package com.sbgl.app.services.computer;

import java.util.List;

import com.sbgl.app.entity.Computerhomeworkreceiver;
import com.sbgl.app.entity.ComputerhomeworkreceiverFull;
import com.sbgl.util.*;

public interface ComputerhomeworkreceiverService{
	
	public void addComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver);

	public void addComputerhomeworkreceiver(Computerhomeworkreceiver ch,Computerhomeworkreceiver en);
	public void addComputerhomeworkreceiverWithId(Computerhomeworkreceiver computerhomeworkreceiver);
		
	public void updateComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver);
	
//  根据id删除实体	
	public int deleteComputerhomeworkreceiver(Integer computerhomeworkreceiverId);

//  根据实体删除实体
	public int deleteComputerhomeworkreceiver(Computerhomeworkreceiver computerhomeworkreceiver);
	
//	根据id查询实体类		
	public Computerhomeworkreceiver selectComputerhomeworkreceiverById(Integer computerhomeworkreceiverId);

//  查询全部实体
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverAll();
	
//	根据id查询full类
	public ComputerhomeworkreceiverFull selectComputerhomeworkreceiverFullById(Integer computerhomeworkreceiverId);

//  查询全部full
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullAll();
		
//  查询数量
	public int countComputerhomeworkreceiverRow();
//  分页查询
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByPage(Page page);
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByPage(Page page);
		
		
		public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByCondition(String condition);

        public List<Computerhomeworkreceiver>  selectComputerhomeworkreceiverByConditionAndPage(String condition,final Page page);
	
	//条件查询full
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByCondition(String condition);
	
	
	// 查询实体full        
        public List<ComputerhomeworkreceiverFull>  selectComputerhomeworkreceiverFullByConditionAndPage(String condition,final Page page);
		
	
//根据关联查询实体	
	public List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByLoginuserId(Integer userid );
//根据关联查询实体full	
	public List<ComputerhomeworkreceiverFull> selectComputerhomeworkreceiverFullByLoginuserId(Integer userid );

	int execSql(String sql);

	List<Computerhomeworkreceiver> selectComputerhomeworkreceiverByUserAndOrder(
			int uid, int hasorder);

		
	
	
}
