package com.sbgl.app.test.computer;

import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sbgl.app.entity.Computermodel;
import com.sbgl.app.entity.Computerorderdetail;
import com.sbgl.app.services.computer.ComputermodelService;
import com.sbgl.app.services.computer.ComputerorderdetailService;
import com.sbgl.util.DateUtil;
import com.sbgl.util.SpringUtil;

public class TestOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
		
		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
		
		Computermodel computermodel = new Computermodel() ;
		
		 List<Computermodel> modelList = computermodelService.selectComputermodelAll();
			for(int i = 0; i < modelList.size(); i++){
						System.out.println("id="+modelList.get(i).getId() + "  " + modelList.get(i).getComputercount());
			}
		
			
	String currentDay = "2013-10-02 00:00:00";
	int currentPeriod = 2;
	ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
	List<Computerorderdetail> computerorderdetailList  = computerorderdetailService.selectComputerorderdetailAfterNow(currentDay, currentPeriod);
	for(int i = 0; i <computerorderdetailList.size(); i++){
		System.out.println("id="+computerorderdetailList.get(i).getId() + "  " +computerorderdetailList.get(i).getComputernumber());
}
	
	int modelId = 1;
	int[][] avaiablenum ;
	avaiablenum = new  int[3][14];
//	for(int i =0; i < avaiablenum.length;i++){
//		for(int j=0; j< avaiablenum[i].length;j++){
			for(Computerorderdetail od : computerorderdetailList){
				if(od.getComputerid() == modelId){
					int between = DateUtil.daysBetween(DateUtil.parseDate(currentDay),od.getBorrowday());
					avaiablenum[od.getBorrowperiod()][between] = avaiablenum[od.getBorrowperiod()][between]  -od.getComputernumber();
				}
			}
//		}
//	}
	
	for(int i =0; i < avaiablenum.length;i++){
		for(int j=0; j< avaiablenum[i].length;j++){
			System.out.print(avaiablenum[i][j]+" ");
		}
		System.out.println();
		}
	
	}
	
	

}
