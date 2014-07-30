package com.sbgl.app.services.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Userlogininfo;
import com.sbgl.app.services.user.UserlogininfoService;

@Scope("prototype") 
@Service("loginInfoService")
public class UserlogininfoServiceImpl implements UserlogininfoService {
	@Resource
	private BaseDao baseDao;
	
	@Override
	public Userlogininfo getLoinInfoByUserId(int userId) {
		List<Userlogininfo> list = baseDao.getEntityByIntProperty(Userlogininfo.class.getName(), "userid", userId);
		if(list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Boolean getIsFirstLoginByUserId(int userId) {
		List<Userlogininfo> list = baseDao.getEntityByIntProperty(Userlogininfo.class.getName(), "userid", userId);
		if(list != null && list.size() == 1) {
			Userlogininfo info = list.get(0);
			return Boolean.valueOf( info.getIsfirstlogin() );
		}
		return null;
	}

	@Override
	public int addUserLoinInfo(Userlogininfo info) {
		int id = baseDao.getCode("loginInfoId");
		info.setId(id);
		
		try {
			baseDao.saveEntity( info );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterUserLoginInfo(Userlogininfo info) {
		int id = info.getId();
		Userlogininfo storeInfo = baseDao.getEntityById(Userlogininfo.class, id);
		
		storeInfo.setIsfirstlogin( info.getIsfirstlogin() );
		storeInfo.setLastlogintime( info.getLastlogintime() );
		storeInfo.setLogincount( info.getLogincount() );
		storeInfo.setRemark( info.getRemark() );
		storeInfo.setPagelanguage( info.getPagelanguage() );

		try {
			baseDao.updateEntity( storeInfo );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

}
