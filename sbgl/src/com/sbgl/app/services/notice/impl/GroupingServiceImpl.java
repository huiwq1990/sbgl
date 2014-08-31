//package com.sbgl.app.services.notice.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//
//import com.sbgl.app.dao.BaseDao;
//import com.sbgl.app.entity.Grouping;
//import com.sbgl.app.entity.Groupmember;
//import com.sbgl.app.entity.Notice;
//import com.sbgl.app.entity.Noticereply;
//import com.sbgl.app.entity.Noticestate;
//import com.sbgl.app.services.notice.GroupingService;
//
//@Scope("prototype") 
//@Service("groupingService")
//public class GroupingServiceImpl implements GroupingService {
//	@Resource
//	private BaseDao baseDao;
//	
//	@Override
//	public long addGrouping(Grouping grouping) {
//		// TODO Auto-generated method stub
//		long id = baseDao.getCode("groupingId");
//		grouping.setGroupingid( id );
//		try {
//			baseDao.saveEntity( grouping );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public long alterGrouping(Grouping grouping) {
//		// TODO Auto-generated method stub
//		long id = grouping.getGroupingid();
//		Grouping storeGrouping = baseDao.getEntityById(Grouping.class, id);
//		
//		storeGrouping.setGroupingname( grouping.getGroupingname() );
//		storeGrouping.setCreatedate( grouping.getCreatedate() );
//		
//		try {
//			baseDao.updateEntity( storeGrouping );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public boolean deleteGrouping(long groupingId) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		Grouping storeGrouping = baseDao.getEntityById(Grouping.class, groupingId);
//		try {
//			baseDao.deleteEntity( storeGrouping );
//			flag = true;
//		} catch (RuntimeException re) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	@Override
//	public List<Grouping> getAllGrouping() {
//		// TODO Auto-generated method stub
//		List<Grouping> resultList = baseDao.getAllEntity( Grouping.class );
//		return resultList;
//	}
//
//	@Override
//	public long addGroupmember(Groupmember groupmember) {
//		// TODO Auto-generated method stub
//		long id = baseDao.getCode("groupmemberId");
//		groupmember.setGroupmemberid( id );
//		try {
//			baseDao.saveEntity( groupmember );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public long alterGroupmember(Groupmember groupmember) {
//		// TODO Auto-generated method stub
//		long id = groupmember.getGroupmemberid();
//		Groupmember storeGroupmember = baseDao.getEntityById(Groupmember.class, id);
//		
//		storeGroupmember.setGroupingid( groupmember.getGroupingid() );
//		storeGroupmember.setMemberid( groupmember.getMemberid() );
//		
//		try {
//			baseDao.updateEntity( storeGroupmember );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public boolean deleteGroupingmember(long groupmemberId) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		Groupmember storeGroupmember = baseDao.getEntityById(Groupmember.class, groupmemberId);
//		try {
//			baseDao.deleteEntity( storeGroupmember );
//			flag = true;
//		} catch (RuntimeException re) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	@Override
//	public List<Groupmember> getAllGroupingmembers() {
//		// TODO Auto-generated method stub
//		List<Groupmember> resultList = baseDao.getAllEntity( Groupmember.class );
//		return resultList;
//	}
//
//}
