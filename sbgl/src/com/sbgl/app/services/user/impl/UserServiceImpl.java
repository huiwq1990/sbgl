package com.sbgl.app.services.user.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.dao.QueryResult;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.User;
import com.sbgl.app.services.user.UserService;
import com.sbgl.common.HQLOption;
import com.sbgl.util.Page;

@Scope("prototype") 
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private BaseDao baseDao;

	@Override
	public int addUser(User user) {
		int id = baseDao.getCode("userId");
		user.setId( id );
		user.setCreatetime( new Date() );	//用户创建者id在Action中获取
		
		try {
			baseDao.saveEntity( user );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int alterUser(User user) {
		int id = user.getId();
		User storeUser = baseDao.getEntityById(User.class, id);
		
		storeUser.setBirthday( user.getBirthday() );
		storeUser.setClassbelong( user.getClassbelong() );
		storeUser.setEmail( user.getEmail() );
		storeUser.setGender( user.getGender() );
		storeUser.setModifytime( new Date() );
		storeUser.setPhonenum( user.getPhonenum() );
		storeUser.setPhoto( user.getPhoto() );
		storeUser.setPrivilege( user.getPrivilege() );
		storeUser.setRoletype( user.getRoletype() );
		storeUser.setUsername( user.getUsername() );
		storeUser.setUsernumber( user.getUsernumber() );
		storeUser.setUserpass( user.getUsername() );
		storeUser.setInitpagelan( user.getInitpagelan() );
		
		try {
			baseDao.updateEntity( storeUser );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteUser(int userId) {
		User user = baseDao.getEntityById( User.class, userId );
		
		try {
			baseDao.deleteEntity( user );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> resultList = baseDao.getAllEntity( User.class );
		return resultList;
	}

	@Override
	public boolean isExistUserInfo(String userNum, String email) {
		final String hql = "from User as u where u.usernumber='" + userNum + "' or u.email='" + email + "'";
		List<User> userList = baseDao.executeHQL( hql );
		
		return userList == null;
	}

	@Override
	public User getUserByNumberAndPass(String number, String userpass) {
		final String hql = "from User as u where u.usernumber='" + number + "' or u.userpass='" + userpass + "'";
		List<User> userList = baseDao.executeHQL( hql );
		
		if(userList != null) {
			return userList.get( 0 );
		}
		return null;
	};

	@Override
	public User getUserByEmailAndPass(String email, String userpass) {
		final String hql = "from User as u where u.email='" + email + "' or u.userpass='" + userpass + "'";
		List<User> userList = baseDao.executeHQL( hql );
		
		if(userList != null) {
			return userList.get( 0 );
		}
		return null;
	};
	
	@Override
	public List<User> getManager() {
		final String hql = "from User as u where u.privilege<>-1";
		List<User> userList = baseDao.executeHQL( hql );
		
		if(userList != null) {
			return userList;
		}
		return null;
	}

	@Override
	public QueryResult getUsersByPageWithOpts(List<HQLOption> hqlOptionList,
			Page page) {
		return baseDao.getEntityByPageWithOptions( User.class, hqlOptionList, page );
	}

}
