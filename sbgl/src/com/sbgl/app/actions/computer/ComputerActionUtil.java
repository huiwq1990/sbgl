package com.sbgl.app.actions.computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sbgl.app.common.computer.ComputerConfig;
import com.sbgl.app.entity.Computercategory;
import com.sbgl.app.entity.ComputercategoryFull;
import com.sbgl.app.entity.Computermodel;

public class ComputerActionUtil {
	
	public static HashMap<Integer, ArrayList<Computermodel>> categoryModelMap(List<Computermodel>  computermodelList){
		//model的分类信息，只显示中文的
//		String categorysqlch = " where a.languagetype=0 order by a.computercategorytype,a.languagetype";
//		List<ComputercategoryFull> tempComputercategoryFullList  = computercategoryService.selectComputercategoryFullByCondition(categorysqlch);
		HashMap<Integer, ArrayList<Computermodel>> computermodelByComputercategoryId = new HashMap<Integer,ArrayList<Computermodel>>();

		for(Computermodel computermodel : computermodelList){
			int computercategoryType = computermodel.getComputercategoryid();
			if(!computermodelByComputercategoryId.containsKey(computercategoryType)){		
				computermodelByComputercategoryId.put(computercategoryType, new ArrayList<Computermodel>());
			}
			computermodelByComputercategoryId.get(computercategoryType).add(computermodel);			
		}
		
		return computermodelByComputercategoryId;
	}

}
