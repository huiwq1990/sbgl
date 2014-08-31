//package com.sbgl.app.test.computer;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.time.DateUtils;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import com.sbgl.app.common.computer.BorrowperiodUtil;
//import com.sbgl.app.common.computer.ComputerConfig;
//import com.sbgl.app.entity.Borrowperiod;
//import com.sbgl.app.entity.Computermodel;
//import com.sbgl.app.entity.Computerorderdetail;
//import com.sbgl.app.services.computer.ComputermodelService;
//import com.sbgl.app.services.computer.ComputerorderdetailService;
//import com.sbgl.util.DateUtil;
//import com.sbgl.util.SpringUtil;
//
//public class TestOrder {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		ApplicationContext cxt=new FileSystemXmlApplicationContext(SpringUtil.getAppPath());
//		ComputermodelService computermodelService = (ComputermodelService)cxt.getBean("computermodelService");
//		
//		int computerorderTotalOrderDay = ComputerConfig.computerorderTotalOrderDay;
//		int computerorderTotalOrderPeriod = ComputerConfig.computerorderTotalOrderPeriod;
//		
//		//取得所有PC类型的当前库存数量
//		String currentlanguagetype = "0";
//		String getAllComputermodelTypeSql = " where languagetype="+currentlanguagetype+" ";
//		List<Computermodel> computermodelList = computermodelService.selectComputermodelByCondition(getAllComputermodelTypeSql);
//		for (int i = 0; i < computermodelList.size(); i++) {
//			System.out.println("当前可借数量id=" + computermodelList.get(i).getId() + "  " + " 名称："+ computermodelList.get(i).getName()
//					+ computermodelList.get(i).getAvailableborrowcountnumber());
//		}
//		
//		//所有可借时间段信息
//		Map<Integer,Borrowperiod> periodMap = BorrowperiodUtil.getBorrowperiodMap();
//		List<Borrowperiod> periodList =  BorrowperiodUtil.getBorrowperiodList();
//		//初始化每个型号每个时段可借数量数组，
//		HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> availableBorrowModelMap = new HashMap<Integer,HashMap<Integer,ArrayList<Integer>>> ();
//		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
//			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
//			HashMap<Integer,ArrayList<Integer>> periodDayAvailInfo = new HashMap<Integer,ArrayList<Integer>>();
//			for(int tempperiod=0; tempperiod < periodList.size(); tempperiod++){
//				Borrowperiod tempBorrowperiod = periodList.get(tempperiod);
//				ArrayList<Integer> dayInfo = new ArrayList<Integer>();
//				for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){				
//					dayInfo.add( tempmodel.getAvailableborrowcountnumber());
//				}				
//				periodDayAvailInfo.put(tempBorrowperiod.getId(), dayInfo);
//			}
//			availableBorrowModelMap.put(tempmodel.getComputermodeltype(), periodDayAvailInfo);
//		}
//		
//		System.out.println(availableBorrowModelMap.size());
//		
//		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
//			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
//			System.out.println(tempmodel.getName());
//			for(int tempperiod=0; tempperiod < periodList.size(); tempperiod++){
//				Borrowperiod tempBorrowperiod = periodList.get(tempperiod);
//				System.out.println(tempBorrowperiod.getPeroidname());
//				for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){				
//					
//					System.out.print(availableBorrowModelMap.get(tempmodel.getComputermodeltype()).get(tempBorrowperiod.getId()).get(tempday)+"  ");
//				}	
//				System.out.println();
////				periodDayAvailInfo.put(periodList.get(tempperiod).getId(), dayInfo);
//			}
//			System.out.println();
////			availableBorrowModelMap.put(modelList.get(tempmodel).getComputermodeltype(), periodDayAvailInfo);
//		}
//		
//		//pc模型1的可借数量
////		Integer[][] pcnumberArray = availableBorrowModelMap.get(1);
////		for(int tempperiod=0; tempperiod < computerorderTotalOrderPeriod; tempperiod++){
////			for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){				
////				System.out.print(pcnumberArray[tempperiod][tempday] + " ");
////			}	
////			System.out.println();
////		}
//	
//			
////		设置当前时间
//		String currentDay = "2013-10-01 00:00:00";
//		int currentPeriod = 2;
////		查询当前时间之后，预约的清单
//		System.out.println("预约订单：");
//		ComputerorderdetailService computerorderdetailService = (ComputerorderdetailService)cxt.getBean("computerorderdetailService");
//		 
//		List<Computerorderdetail> computerorderdetailList  = computerorderdetailService.selectComputerorderdetailAfterNow(currentDay, currentPeriod);
//		System.out.println(computerorderdetailList.size());
//		for(int i = 0; i <computerorderdetailList.size(); i++){
//			System.out.println("id="+computerorderdetailList.get(i).getId() + "  " +computerorderdetailList.get(i).getComputermodelid());
//		}
//
//		//根据预约清单计算当前可借数量			
//		for(Computerorderdetail od : computerorderdetailList){
//				int between = DateUtil.daysBetween(DateUtil.parseDate(currentDay),od.getBorrowday());
//				System.out.println(od.getId());
//				int newcount = availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).get(between) - od.getBorrownumber();
//				 availableBorrowModelMap.get(od.getComputermodelid()).get(od.getBorrowperiod()).set(between, newcount);
//		}
//
//	
//		for(int tempmodelindex=0;tempmodelindex<computermodelList.size();tempmodelindex++){
//			Computermodel tempmodel =  computermodelList.get(tempmodelindex);
//			for(int tempperiod=0; tempperiod < periodList.size(); tempperiod++){
//				Borrowperiod tempBorrowperiod = periodList.get(tempperiod);
//				for(int tempday=0; tempday < computerorderTotalOrderDay; tempday++){				
//					
//					System.out.print(availableBorrowModelMap.get(tempmodel.getComputermodeltype()).get(tempBorrowperiod.getId()).get(tempday)+"  ");
//				}	
//				System.out.println();
////				periodDayAvailInfo.put(periodList.get(tempperiod).getId(), dayInfo);
//			}
//			System.out.println();
////			availableBorrowModelMap.put(modelList.get(tempmodel).getComputermodeltype(), periodDayAvailInfo);
//		}
//	
//	}
//	
//	
//
//}
