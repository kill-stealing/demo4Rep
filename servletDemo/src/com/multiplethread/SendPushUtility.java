package com.multiplethread;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.multiplethread.dao.Dao;


public class SendPushUtility implements Callable<PushInfo>{
	
	private static final Logger logger = Logger.getLogger(SendPushJob.class);
	
	private PushInfo info;
	private Dao dao ;
	
	public SendPushUtility(PushInfo info) {
		super();
		this.info = info;
		this.dao=info.getDao();
	}

	@Override
	public PushInfo call() throws Exception {
		// TODO Auto-generated method stub
		return sendPush(info);
	}
	
	private PushInfo sendPush(PushInfo info){
		try {
			logger.error(Thread.currentThread().getName()+"num :"+info.getNum());
//			logger.error("send push start postId:"+info.getPostId()+",userId:"+info.getUserId());
			QuarterJob job=new QuarterJob();
			job.invokeSQLAdapterGetValidAPPListByDeviceType1(info.getUserId(), info.getPostTitle(), info.getCounts());
//			logger.error("send push end postId:"+info.getPostId()+",userId:"+info.getUserId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return info;
	}
	
	private void doInsertPushHistory(PushInfo info){
		List<Map<String, Object>> list=null;
		String sql1="";
		try {
			//one post belongs to multiple categories. and one category will be saved as one push_history.
			
			sql1 +="select a1.id,a1.post_id,a2.post_title,a4.term_id,a5.user_id FROM nase_post_notification a1,"
					+ " nase_posts a2,nase_term_relationships a3, nase_term_taxonomy a4, nase_subscription_users as a5 where a1.post_id=a2.id"
					+ " and a3.object_id=a1.post_id and a3.term_taxonomy_id=a4.term_taxonomy_id and a5.subscription_id=a4.term_id"
					+ " and a1.post_id='"+info.getPostId()+"' and a5.user_id='"+info.getUserId()+"'";
			
			list = this.dao.executeQueryForList(sql1);
			if( list != null && !list.isEmpty() ){
				for(Map<String, Object> map:list){
					PushHistoryNew pushHistory=new PushHistoryNew();
					//a1.if_send,a1.id,a1.post_id,a2.post_title,a4.term_id,a6.name,a5.user_id
					pushHistory.setPushId((int)map.get("id")+"");
					pushHistory.setPostId((String)map.get("post_id"));
					pushHistory.setPushTime(new Date().toString());
					pushHistory.setMessage((String)map.get("post_title"));
					pushHistory.setSummary((String)map.get("post_title"));
					pushHistory.setUserId((String)map.get("user_id"));
					pushHistory.setSubscriptionId(((BigInteger)map.get("term_id")).intValue());
					pushHistory.setStatus(0);
					DataHandle.addPushHistoryNew(dao, pushHistory);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
