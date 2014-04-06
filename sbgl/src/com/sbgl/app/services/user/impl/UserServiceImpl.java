package com.sbgl.app.services.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.sbgl.app.actions.admin.template.UserInfoTemplate;
import com.sbgl.app.dao.BaseDao;
import com.sbgl.app.entity.Managergroup;
import com.sbgl.app.entity.Student;
import com.sbgl.app.entity.User;
import com.sbgl.app.entity.Usergroup;
import com.sbgl.app.services.user.UserService;

@Scope("prototype") 
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private BaseDao baseDao;

	@Override
	public int addUser(User user) {
		int id = baseDao.getCode("userId");
		user.setId( id );
		user.setCreatedate( new Date() );
		
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
		
		storeUser.setModifydate( new Date() );
		storeUser.setCardnumber(user.getCardnumber());
//		storeUser.setClassid(user.getClassid());
		storeUser.setEmail(user.getEmail());
		storeUser.setGender(user.getGender());
		storeUser.setInitlan(user.getInitlan());
		storeUser.setName(user.getName());
		storeUser.setPassword(user.getPassword());
		storeUser.setPhoto(user.getPhoto());
		storeUser.setTelephone(user.getTelephone());
		storeUser.setUserid(user.getUserid());
		storeUser.setUsertype(user.getUsertype());
		
		try {
			baseDao.updateEntity( storeUser );
		} catch (RuntimeException re) {
            id = -1;
        }
		return id;
	}

	@Override
	public int deleteUser(int userId) {
		User user = baseDao.getEntityById(User.class, userId);
		
		try {
			baseDao.deleteEntity( user );
			return 0;
		} catch (RuntimeException re) {
			return -1;
		}
	}

	@Override
	public int deleteUser(String userIds) {
		String[] ids = userIds.split("_");
		try {
			Integer oneId;
			User user;
			for (String id : ids) {
				oneId = Integer.valueOf( id );
				user = baseDao.getEntityById(User.class, oneId);
				baseDao.deleteEntity( user );
			}
		} catch (RuntimeException re) {
			return -1;
		}
		return 0;
	}

	@Override
	public List<User> getAllUser() {
		List<User> resultList = baseDao.getAllEntity(User.class);
		return resultList;
	}

	@Override
	public boolean isExistUserIDCode(String userIDCode) {
		return baseDao.isExist(User.class, "cardnumber", userIDCode);
	}

	@Override
	public boolean isExistUserCode(String userCode) {
		return baseDao.isExist(User.class, "userid", userCode);
	}

	@Override
	public User getUserById(int userId) {
		return baseDao.getEntityById(User.class, userId);
	}

	@Override
	public Integer getSumOfUser() {
		Integer sum = baseDao.getRowCount(User.class);
		return sum == null ? 0 : sum;
	}

	@Override
	public List<UserInfoTemplate> getUserInfo() {
		UserInfoTemplate uif = null;
		List<UserInfoTemplate> uifList = new ArrayList<UserInfoTemplate>();
		final String hql = "select u.id, u.userid, u.name, u.gender, u.password, u.usertype, u.classid, u.email, u.telephone, u.cardnumber, u.initlan, u.photo, " +
				"c.classname, ugr.groupid " +   
				"from User as u, Clazz as c, Usergrouprelation as ugr " + 
				"where u.classid = c.classid and u.id = ugr.userid";
		Session session = baseDao.getHibernateTemplate().getSessionFactory().openSession();
		Query q = session.createQuery(hql);
		List<?> resultList = q.list();
		for (Object object : resultList) {
			uif = new UserInfoTemplate();
			Object[] o = (Object[]) object;
			uif.setId( (Integer) o[0] );
			uif.setName( (String) o[2] );
			uif.setGender( "0".equals((String) o[3]) ? "男" : "女" );
			uif.setPassword( (String) o[4] );
			uif.setUsertype( (Integer) o[5] );
			uif.setClassid( (Integer) o[6] );
			uif.setEmail( (String) o[7] );
			uif.setTelephone( (String) o[8] );
			uif.setCardnumber( (String) o[9] );
			uif.setInitlan( (Integer) o[10] );
			uif.setPhoto( (String) o[11] );
			uif.setClassname( (String) o[12] );
			Integer groupid = (Integer) o[13];
			uif.setGroupid( groupid );
			if(groupid != null) {
				Usergroup ug = baseDao.getEntityById(Usergroup.class, groupid);
				uif.setGroupname( ug.getName() );
			}
			if( 4 == (Integer) o[5] ) {
				uif.setUserid( (String) o[9] );
			} else {
				uif.setUserid( (String) o[1] );
			}
//			Integer mgTypeId = (Integer) o[14];
//			uif.setManaagerGroupname( mgTypeId == 1 ? "系统管理员" :
//									  mgTypeId == 2 ? "系秘" :
//									  mgTypeId == 3 ? "器材管理员" :
//									  mgTypeId == 4 ? "机房管理员" : "无"
//									);
			
			uifList.add(uif);
		}
		return uifList;
	}

	@Override
	public int addManagerPro(int userId, int managerType) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
