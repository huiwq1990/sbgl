package com.sbgl.app.actions.computer;

import java.util.Comparator;
import java.util.Date;

import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.DateUtil;

public class ComputerorderdetailFullComparator  implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		ComputerorderdetailFull a = (ComputerorderdetailFull)o1;
		ComputerorderdetailFull b = (ComputerorderdetailFull)o2;
		
		Date aD = a.getComputerorderdetailborrowday();
		Date bD = b.getComputerorderdetailborrowday();
		int aP = a.getComputerorderdetailborrowperiod();
		int bP = b.getComputerorderdetailborrowperiod();
		if(aD.after(bD)){
			return 1;
		}
		
		if(aD.equals(bD) && (aP > bP)){
			return 1;
		}
		
		return -1;
	}

}
