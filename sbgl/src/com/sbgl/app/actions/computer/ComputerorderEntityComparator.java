package com.sbgl.app.actions.computer;

import java.util.Comparator;
import java.util.Date;

import com.sbgl.app.entity.ComputerorderdetailFull;
import com.sbgl.util.DateUtil;

public class ComputerorderEntityComparator  implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		ComputerorderEntity a = (ComputerorderEntity)o1;
		ComputerorderEntity b = (ComputerorderEntity)o2;
		
		Date aD = a.getStarttime();
		Date bD = b.getStarttime();
		if(aD.after(bD)){
			return -1;
		}
		
		if(bD.after(aD)){
			return 1;
		}
		
		
		return 0;
	}

}
