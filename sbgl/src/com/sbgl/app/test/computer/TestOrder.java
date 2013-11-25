package com.sbgl.app.test.computer;

import java.util.HashMap;
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
		
		int computerorderTotalOrderDay = 14;
		int computerorderTotalOrderPeriod = 3;
		
		//去得当前库存数量
		List<Computermodel> modelList = computermodelService.selectComputermodelAll();
		for (int i = 0; i < modelList.size(); i++) {
			System.out.println("id=" + modelList.get(i).getId() + "  "
					+ modelList.get(i).getAvailableborrowcountnumber());
		}
		
		//初始化每个型号可借数量数组，
		HashMap<Integer,Integer[][]> availableBorrowModelMap = new HashMap<Integer,Integer[][]> ();
		for(int tempmodel=0;tempmodel<modelList.size();tempmodel++){
			Integer[][] pcnumberArray = new Integer[computerorderTotalOrderPeriod][computerorderTotalOrderDay];
			for(int tempperiod=0; tempperiod < computerorderTotalOrderPeriod; tempperiod++){
				for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){				
					pcnumberArray[tempperiod][tempday] = modelList.get(tempmodel).getComputercount();
				}				
			}
			availableBorrowModelMap.put(modelList.get(tempmodel).getId(), pcnumberArray);
		}
		
			
//		设置当前时间
		String currentDay = "2013-10-02 00:00:00";
		int currentPeriod = 2;
//		查询当前时间之后，预约的清单
		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
		List<Computerorderdetail> computerorderdetailList  = computerorderdetailService.selectComputerorderdetailAfterNow(currentDay, currentPeriod);
		for(int i = 0; i <computerorderdetailList.size(); i++){
			System.out.println("id="+computerorderdetailList.get(i).getId() + "  " +computerorderdetailList.get(i).getComputermodelid());
		}

		//根据预约清单计算当前可借数量			
		for(Computerorderdetail od : computerorderdetailList){
				int between = DateUtil.daysBetween(DateUtil.parseDate(currentDay),od.getBorrowday());
				availableBorrowModelMap.get(od.getComputerid())[od.getBorrowperiod()][between]  -= od.getBorrownumber();

		}

	
//		输出	
		for(int tempmodel=0;tempmodel<modelList.size();tempmodel++){//			
				
			for(int tempperiod=0; tempperiod < computerorderTotalOrderPeriod; tempperiod++){
					for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){	
//						System.out.println(tempperiod+"  "+tempday);
						System.out.print(availableBorrowModelMap.get(modelList.get(tempmodel).getId())[tempperiod][tempday] + "   ");
					}			
					System.out.println();
				}
				System.out.println("-------------------------------------------------");
				System.out.println("-------------------------------------------------");
			}
	
	}
	
	

}
