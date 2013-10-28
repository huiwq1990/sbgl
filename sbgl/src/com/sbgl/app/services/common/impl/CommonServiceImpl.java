package com.sbgl.app.services.common.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Maxno;
import com.sbgl.app.services.common.CommonService;

@Scope("prototype") 
@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

	@Resource
	private BaseDao baseDao;
	
	@Override
	public Integer getCode(String codeType) {
		// TODO Auto-generated method stub
		Maxno maxno = new Maxno();
		Integer reCode;
		maxno = baseDao.getEntityById(Maxno.class, codeType);
		if(maxno==null){
			maxno = new  Maxno();
			maxno.setMaxno(1);
			maxno.setNotype(codeType);
			baseDao.saveEntity(maxno);
			reCode = maxno.getMaxno();
			
		}else{
			Integer sum = maxno.getMaxno();
			sum++;
			maxno.setMaxno(sum);
			baseDao.updateEntity(maxno);
			reCode = maxno.getMaxno();
		}
		return reCode;
	}
	
}
