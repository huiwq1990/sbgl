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
//import com.sbgl.app.entity.Equipment;
//import com.sbgl.app.entity.Notice;
//import com.sbgl.app.entity.Noticereply;
//import com.sbgl.app.entity.Noticestate;
//import com.sbgl.app.services.notice.NoticeService;
//
//@Scope("prototype") 
//@Service("noticeService")
//public class NoticeServiceImpl implements NoticeService {
//	@Resource
//	private BaseDao baseDao;
//	
//	@Override
//	public long addNotice(Notice notice) {
//		long id = baseDao.getCode("noticeId");
//		notice.setNoticeid( id );
//		try {
//			baseDao.saveEntity( notice );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public long alterNotice(Notice notice) {
//		// TODO Auto-generated method stub
//		long id = notice.getNoticeid();
//		Notice storeNotice = baseDao.getEntityById(Notice.class, id);
//		
//		storeNotice.setTitle( notice.getTitle() );
//		storeNotice.setContent( notice.getContent() );
//		storeNotice.setTime( notice.getTime() );
//		storeNotice.setUserid( notice.getUserid() );
//		storeNotice.setGroupingid( notice.getGroupingid() );
//		storeNotice.setTempgroup( notice.getTempgroup() );
//		
//		try {
//			baseDao.updateEntity( storeNotice );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//		
//	}
//
//	@Override
//	public boolean deleteNotice(long noticeId) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		Notice storeNotice = baseDao.getEntityById(Notice.class, noticeId);
//		try {
//			baseDao.deleteEntity( storeNotice );
//			flag = true;
//		} catch (RuntimeException re) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	@Override
//	public List<Notice> getAllReceivedNotices(long userId) {
//		// TODO Auto-generated method stub
//		List<Notice> resultList = baseDao.getAllEntity( Notice.class );
//		return resultList;
//	}
//
//	@Override
//	public List<Notice> getAllSendNotices(long userId) {
//		// TODO Auto-generated method stub
//		List<Notice> resultList = baseDao.getEntityByProperty("Notice", "userid", String.valueOf(userId));
//		return resultList;
//	}
//
//	@Override
//	public long addNoticereply(Noticereply noticereply) {
//		// TODO Auto-generated method stub
//		long id = baseDao.getCode("noticereplyId");
//		noticereply.setNoticeid( id );
//		try {
//			baseDao.saveEntity( noticereply );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public long alterNoticereply(Noticereply noticereply) {
//		// TODO Auto-generated method stub
//		long id = noticereply.getReplyid();
//		Noticereply storeNoticereply = baseDao.getEntityById(Noticereply.class, id);
//		
//		storeNoticereply.setUserid( noticereply.getUserid() );
//		storeNoticereply.setNoticeid( noticereply.getNoticeid() );
//		storeNoticereply.setCotent( noticereply.getCotent() );
//		storeNoticereply.setReplydate( noticereply.getReplydate() );
//		storeNoticereply.setReplytype( noticereply.getReplytype() );
//		
//		try {
//			baseDao.updateEntity( storeNoticereply );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public boolean deleteNoticereply(long noticereplyId) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		Noticereply storeNoticereply = baseDao.getEntityById(Noticereply.class, noticereplyId);
//		try {
//			baseDao.deleteEntity( storeNoticereply );
//			flag = true;
//		} catch (RuntimeException re) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	@Override
//	public Noticereply getReplyToNotice(long noticeId) {
//		// TODO Auto-generated method stub
//		List<Noticereply> resultList = baseDao.getEntityByProperty("Noticereply", "noticeid", String.valueOf(noticeId));
//		return resultList.get(0);
//	}
//
//	@Override
//	public Noticereply getReplyToReply(long noticereplyId) {
//		// TODO Auto-generated method stub
//		List<Noticereply> resultList = baseDao.getEntityByProperty("Noticereply", "replyid", String.valueOf(noticereplyId));
//		return resultList.get(0);
//	}
//
//	@Override
//	public long addNoticestate(Noticestate noticestate) {
//		// TODO Auto-generated method stub
//		long id = baseDao.getCode("noticestateId");
//		noticestate.setNoticeid( id );
//		try {
//			baseDao.saveEntity( noticestate );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public long alterNoticestate(Noticestate noticestate) {
//		// TODO Auto-generated method stub
//		long id = noticestate.getNoticestateid();
//		Noticestate storeNoticestate = baseDao.getEntityById(Noticestate.class, id);
//		
//		storeNoticestate.setNoticeid( noticestate.getNoticeid() );
//		storeNoticestate.setState( noticestate.getState() );
//		storeNoticestate.setUserid( noticestate.getUserid() );
//		
//		try {
//			baseDao.updateEntity( storeNoticestate );
//		} catch (RuntimeException re) {
//            id = -1;
//        }
//		return id;
//	}
//
//	@Override
//	public boolean deleteNoticestate(long noticestateId) {
//		// TODO Auto-generated method stub
//		boolean flag = false;
//		Noticestate storeNoticestate = baseDao.getEntityById(Noticestate.class, noticestateId);
//		try {
//			baseDao.deleteEntity( storeNoticestate );
//			flag = true;
//		} catch (RuntimeException re) {
//			flag = false;
//		}
//		return flag;
//	}
//
//	@Override
//	public Noticestate getNoticestate(long noticeId) {
//		// TODO Auto-generated method stub
//		List<Noticestate> resultList = baseDao.getEntityByProperty("Noticestate", "noticeid", String.valueOf(noticeId));
//		return resultList.get(0);
//	}
//
//	@Override
//	public List<Noticestate> getAllNotiecesatesByUser(long userId) {
//		List<Noticestate> resultList = baseDao.getEntityByProperty("Noticestate", "userid", String.valueOf(userId));
//		return resultList;
//	}
//
//}
