package com.multiplethread;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.multiplethread.dao.Dao;
import com.multiplethread.dao.DaoImpl;

public class SendPushJob extends MultipleThreadJobTemplate<PushInfo> implements SchedulerJob{
	
	private static final Logger logger = Logger.getLogger(SendPushJob.class);

	private Dao dao = new DaoImpl();
	
	@Override
	public boolean checkJobActive() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void prepareTask() throws Exception {
		List<SendPushUtility> taskList = null;
		List<PushInfo> pushInfoList = this.getSendList();
		if(pushInfoList != null) {
			taskList = new ArrayList<SendPushUtility>();
			for (PushInfo info : pushInfoList) {
				taskList.add(new SendPushUtility(info));
			}
			this.setTaskList(taskList);
		}
	}
	
	@Override
	public void doJob() throws Exception {
		try {
			logger.error("********* Send Push Job start ***********");
			super.doJob();
			List<PushInfo> pushInfoList = this.getResultList();
			
//			for (PushInfo info:pushInfoList) {
//				//update the status of push if_send=1
//				DataHandle.updatePushStatusNew(dao, info.getId()+"");
//			}
			logger.error("********* Send Push Job end ***********");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private List<PushInfo> getSendList(){
		List<Map<String, Object>> list=null;
		List<PushInfo> pushInfoList=new ArrayList<PushInfo>();
		String sql="";
		try {
			sql +=" select a1.if_send,a1.id,a1.post_id,a2.post_title,a4.term_id,a6.name,a5.user_id,"
				+ " (select count(distinct push_id) from push_history where user_id=a5.user_id and status_1=0) counts "
				+ " FROM nase_post_notification a1,"
				+ " nase_posts a2,nase_term_relationships a3,nase_term_taxonomy a4, nase_subscription_users as a5,nase_terms a6 "
				+ " where a1.post_id=a2.id and a3.object_id=a1.post_id and a3.term_taxonomy_id=a4.term_taxonomy_id"
				+ " and a5.subscription_id=a4.term_id and a6.term_id=a5.subscription_id and a1.if_send=0 group by a5.user_id,a1.post_id";
			list = this.dao.executeQueryForList(sql);
			if( list != null && !list.isEmpty() ){
				for(int i=0;i<list.size();i++){
					Map<String, Object> map=list.get(i);
					PushInfo pushInfo=new PushInfo();
					//a1.if_send,a1.id,a1.post_id,a2.post_title,a4.term_id,a6.name,a5.user_id
					pushInfo.setId((int)map.get("id"));
					pushInfo.setIfSend((int)map.get("if_send"));
					pushInfo.setPostId(Integer.parseInt((String)map.get("post_id")));
					pushInfo.setPostTitle((String)map.get("post_title"));
					pushInfo.setTermId(((BigInteger)map.get("term_id")).intValue());
					pushInfo.setName((String)map.get("name"));
					pushInfo.setUserId((String)map.get("user_id"));
					pushInfo.setDao(dao);
					pushInfo.setCounts((int)(long)map.get("counts"));
					pushInfo.setNum(i);
					pushInfoList.add(pushInfo);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pushInfoList;
	}
	
	public static void main(String[] args) {
		SendPushJob job=new SendPushJob();
		try {
			job.doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long a=3213213123213213l;
		System.out.println((int)a);
		
	}
}
