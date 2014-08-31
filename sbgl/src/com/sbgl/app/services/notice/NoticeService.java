//package com.sbgl.app.services.notice;
//
//import java.util.List;
//
//import com.sbgl.app.entity.Notice;
//import com.sbgl.app.entity.Noticereply;
//import com.sbgl.app.entity.Noticestate;
//
//public interface NoticeService {
//	//添加通知
//	public long addNotice(Notice notice);
//	//修改通知
//	public long alterNotice(Notice notice);
//	//删除通知
//	public boolean deleteNotice(long noticeId);
//	//查询用户接收的所有通知
//	public List<Notice> getAllReceivedNotices(long userId);
//	//查询用户发布的所有通知
//	public List<Notice> getAllSendNotices(long userId);
//	
//	//添加通知回复
//	public long addNoticereply(Noticereply noticereply);
//	//修改通知回复
//	public long alterNoticereply(Noticereply noticereply);
//	//删除通知回复
//	public boolean deleteNoticereply(long noticereplyId);
//	//查询对某公告的回复//
//	public Noticereply getReplyToNotice(long noticeId);
//	//查询对某回复的回复//
//	public Noticereply getReplyToReply(long noticereplyId);
//	
//	//添加通知状态//
//	public long addNoticestate(Noticestate noticestate);
//	//修改通知状态//
//	public long alterNoticestate(Noticestate noticestate);
//	//删除通知状态//
//	public boolean deleteNoticestate(long noticestateId);
//	//查找某一通知的状态//
//	public Noticestate getNoticestate(long noticeId);
//	//查找某一用户所有的通知状态//
//	public List<Noticestate> getAllNotiecesatesByUser(long userId);
//}
