package com.multiplethread;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.multiplethread.dao.Dao;
import com.multiplethread.dao.DaoImpl;


/*********************************************
 * 
 * @author Simon
 * @time   Nov 20, 20147:36:33 PM
 *********************************************/
public class DataHandle {
	
	private static Logger logger = Logger.getLogger(DataHandle.class);
	
	/**
	 * TODO 清空掉所有的category
	 * @return
	 */
	public static boolean deleteCategory(){
		
		Dao dao = new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);

		try {
			//1.删除类别表数据
			dao.executeUpdate("delete from CATEGORY");
			
			//2.删除类别关联表数据
			dao.executeUpdate("delete from EVENT_CATEGORY");

			transactionManager.commit(status);	
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "delete category error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "delete category error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} 
	}
	
	/**
	 * TODO 清空掉所有的采集到的数据
	 * @return
	 */
	public static boolean deleteAll(){
		
		Dao dao = new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);

		try {
			
			//1.删除每月的所有文件
			dao.executeUpdate("delete from file_list");
		
			//2.删除每月的文件跳转链接
			dao.executeUpdate("delete from file_links");
		
			//3.删除每月的所有会议
			dao.executeUpdate("delete from event_session");
		
			//4.删除每日会议表
			dao.executeUpdate("delete from dayly_schedule");
		
			//5.删除月份主表数据
			dao.executeUpdate("delete from MONTHLY_SCHEDULE");
			
			//6.删除类别表数据
			//dao.executeUpdate("delete from CATEGORY");
			
			//7.删除类别关联表数据
			//dao.executeUpdate("delete from EVENT_CATEGORY");

			transactionManager.commit(status);	
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "delete all data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "delete all data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} 
	}
	/**
	 * TODO 删除指定月份的数据（级联删除）
	 * @param request_year
	 * @param request_month
	 */
	public static boolean deleteMonthlySession( String request_year , String request_month ){
		
		Dao dao = new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);
		try {
			int[] types = new int[]{ Types.VARCHAR , Types.VARCHAR };
			Object[] values = new Object[]{ request_year , request_month };
			
			{
				//1.删除每月的所有文件
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from file_list where ref_link_no in ( ");
				sb.append(" 	select link_no from file_links where ref_session_no in ( ");
				sb.append(" 		select session_no from event_session where ref_day_no in( ");
				sb.append(" 			select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 				select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 			) ");
				sb.append(" 		) ");
				sb.append(" 	) ");
				sb.append(" ) ");
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			
			{
				//2.删除每月的文件跳转链接
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from file_links where ref_session_no in ( ");
				sb.append(" 	select session_no from event_session where ref_day_no in( ");
				sb.append(" 		select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 			select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 		) ");
				sb.append(" 	) ");
				sb.append(" ) ");
				
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			{
				//3.删除每月的所有会议
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from event_session where ref_day_no in( ");
				sb.append(" 	select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 		select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 	) ");
				sb.append(" ) ");
				
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			{
				//4.删除每日会议表
				String sql = "delete from dayly_schedule where ref_monthly_no in(select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=?)";
				dao.executeUpdate(sql, types, values);
			}
			
			{
				//5.删除月份主表数据
				String sql = "delete from MONTHLY_SCHEDULE where request_year=? and request_month=?";
				dao.executeUpdate(sql, types, values);
			}
			
			
			transactionManager.commit(status);	
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "delete "+request_year+" "+request_month+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "delete "+request_year+" "+request_month+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "delete "+request_year+" "+request_month+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "delete "+request_year+" "+request_month+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		}
	}
	
	
	/**
	 * TODO 更新指定月份的数据（先删除后增加）
	 * @param request_year
	 * @param request_month
	 */
	public static boolean updateMonthlySession( MonthlySchedule monthlySchedule ){
		
		if( monthlySchedule == null ){
			System.out.println("Data hava a issue , don't update.");
			return false;
		}
		
		if( monthlySchedule.getError() == true ){
			System.out.println("Data hava a error , don't update.");
			return false;
		}
		
		Dao dao = new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);
		try {
			int[] types = new int[]{ Types.VARCHAR , Types.VARCHAR };
			Object[] values = new Object[]{ monthlySchedule.getRequestYear() , monthlySchedule.getRequestMonth() };
			
			{
				//1.删除每月的所有文件
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from file_list where ref_link_no in ( ");
				sb.append(" 	select link_no from file_links where ref_session_no in ( ");
				sb.append(" 		select session_no from event_session where ref_day_no in( ");
				sb.append(" 			select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 				select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 			) ");
				sb.append(" 		) ");
				sb.append(" 	) ");
				sb.append(" ) ");
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			
			{
				//2.删除每月的文件跳转链接
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from file_links where ref_session_no in ( ");
				sb.append(" 	select session_no from event_session where ref_day_no in( ");
				sb.append(" 		select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 			select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 		) ");
				sb.append(" 	) ");
				sb.append(" ) ");
				
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			{
				//2.1 删除会议对应的category
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from event_category where session_no in ( ");
				sb.append(" 	select session_no from event_session where ref_day_no in( ");
				sb.append(" 		select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 			select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 		) ");
				sb.append(" 	) ");
				sb.append(" ) ");
				
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			{
				//3.删除每月的所有会议
				StringBuffer sb = new StringBuffer();
				sb.append(" delete from event_session where ref_day_no in( ");
				sb.append(" 	select day_no from dayly_schedule where ref_monthly_no in( ");
				sb.append(" 		select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=? ");
				sb.append(" 	) ");
				sb.append(" ) ");
				
				dao.executeUpdate(sb.toString(), types, values);
			}
			
			{
				//4.删除每日会议表
				String sql = "delete from dayly_schedule where ref_monthly_no in(select monthly_no from MONTHLY_SCHEDULE where request_year=? and request_month=?)";
				dao.executeUpdate(sql, types, values);
			}
			
			{
				//5.删除月份主表数据
				String sql = "delete from MONTHLY_SCHEDULE where request_year=? and request_month=?";
				dao.executeUpdate(sql, types, values);
			}
			

			//入库
			if ( monthlySchedule != null ) {
				logger.info( monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" data into database.");
				saveMonthSessionsInDB( monthlySchedule );
			}
			
			
			transactionManager.commit(status);	

			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "update "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "update "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "update "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "update "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		}
	}
	
	
	/**
	 * TODO 保存采集到的数据
	 * @param monthSessions
	 * @return
	 */
	public static boolean saveMonthSessionsInDB( MonthlySchedule monthlySchedule ) {
		
		if( monthlySchedule == null ){
			System.out.println("Data hava a issue , don't update.");
			return false;
		}
		
		if( monthlySchedule.getError() == true ){
			System.out.println("Data hava a error , don't update.");
			return false;
		}
		
		Dao dao = new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			//1.保存月份数据
			{
				//检测字段是否超长
				monthlySchedule.checkFields();
				
				String sql = "insert into MONTHLY_SCHEDULE(MONTHLY_NO,MONTHLY_ID,MONTHLY_TITLE,REQUEST_YEAR,REQUEST_MONTH,AUTHOR_USER_ID,AUTHOR_NAME,AUTHOR_EMAIL,MODIFIER_USER_ID,MODIFIER_NAME,MODIFIER_EMAIL,SUMMARY,CREATE_TIME,UPDATE_TIME,CREATE_TIME_SPIDER,UPDATE_TIME_SPIDER,TOTAL_MEDIA_SIZE,IS_NEED_UPDATE,REPLIES_URL,HTML_RUL)"
						+ " values(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				int[] types = new int[19];
				types[0] = Types.VARCHAR;
				types[1] = Types.VARCHAR;
				types[2] = Types.VARCHAR;
				types[3] = Types.VARCHAR;
				types[4] = Types.VARCHAR;
				types[5] = Types.VARCHAR;
				types[6] = Types.VARCHAR;
				types[7] = Types.VARCHAR;
				types[8] = Types.VARCHAR;
				types[9] = Types.VARCHAR;
				types[10] = Types.VARCHAR;
				types[11] = Types.TIMESTAMP;
				types[12] = Types.TIMESTAMP;
				types[13] = Types.TIMESTAMP;
				types[14] = Types.TIMESTAMP;
				types[15] = Types.VARCHAR;
				types[16] = Types.INTEGER;
				types[17] = Types.VARCHAR;
				types[18] = Types.VARCHAR;
				
				Object[] values = new Object[19];
				values[0] = monthlySchedule.getMonthId()==null?"":monthlySchedule.getMonthId();
				values[1] = monthlySchedule.getMonthTitle()==null?"":monthlySchedule.getMonthTitle();
				values[2] = monthlySchedule.getRequestYear()==null?"":monthlySchedule.getRequestYear();
				values[3] = monthlySchedule.getRequestMonth()==null?"":monthlySchedule.getRequestMonth();
				values[4] = monthlySchedule.getAuthorUserId()==null?"":monthlySchedule.getAuthorUserId();
				values[5] = monthlySchedule.getAuthorName()==null?"":monthlySchedule.getAuthorName();
				values[6] = monthlySchedule.getAuthorEmail()==null?"":monthlySchedule.getAuthorEmail();
				
				values[7] = monthlySchedule.getModifierUserId()==null?"":monthlySchedule.getModifierUserId();
				values[8] = monthlySchedule.getModifierName()==null?"":monthlySchedule.getModifierName();
				values[9] = monthlySchedule.getModifierEmail()==null?"":monthlySchedule.getModifierEmail();
				
				values[10] = monthlySchedule.getSummary()==null?"":monthlySchedule.getSummary();
				values[11] = new Timestamp(monthlySchedule.getCreateTime().getTime());
				values[12] = new Timestamp(monthlySchedule.getUpdateTime().getTime());
				values[13] = new Timestamp(monthlySchedule.getCreateTimeSpider().getTime());
				values[14] = new Timestamp(UtilDateFormat.getDate(monthlySchedule.getRequestYear(), String.valueOf(MonthUtil.getMonth(monthlySchedule.getRequestMonth())+2), "12").getTime());
				values[15] = monthlySchedule.getTotalMediaSize();
				
				values[16] = monthlySchedule.getIsNeedUpdate()==null?"":monthlySchedule.getIsNeedUpdate();
				values[17] = monthlySchedule.getRepliesUrl()==null?"":monthlySchedule.getRepliesUrl();
				values[18] = monthlySchedule.getHtmlUrl()==null?"":monthlySchedule.getHtmlUrl();
				
				Long monthNo = dao.executeUpdateGetKey(sql, types, values);
				
				monthlySchedule.setMonthNo( monthNo );
			}
				
			//2.保存每日的会议数据
			List<DaylySchedule> daylyScheduleList = monthlySchedule.getDaylyScheduleList();
			for (DaylySchedule daylySchedule : daylyScheduleList) {
				
				
				daylySchedule.setMonthNo( monthlySchedule.getMonthNo() );
				daylySchedule.setMonthlySchedule(monthlySchedule);
				//检测字段是否超长
				daylySchedule.checkFields();
				//保存某一日的会议数据
				{
					String sql = "insert into DAYLY_SCHEDULE(DAY_NO,DAY_TITLE,MONTH,DAY,SUMMARY,REMARK,ORDER,REF_MONTHLY_NO) values(default,?,?,?,?,?,?,?)";
					
					int[] types = new int[7];
					types[0] = Types.VARCHAR;
					types[1] = Types.VARCHAR;
					types[2] = Types.TIMESTAMP;
					types[3] = Types.VARCHAR;
					types[4] = Types.VARCHAR;
					types[5] = Types.INTEGER;
					types[6] = Types.INTEGER;
					
					
					Object[] values = new Object[7];
					values[0] = daylySchedule.getDayTitle()==null?"":daylySchedule.getDayTitle();
					values[1] = daylySchedule.getMonth()==null?"":daylySchedule.getMonth();
					values[2] = new Timestamp(UtilDateFormat.getDate(monthlySchedule.getRequestYear(), String.valueOf(MonthUtil.getMonth(daylySchedule.getMonth())+2), daylySchedule.getDay()).getTime());
					values[3] = daylySchedule.getSummary()==null?"":daylySchedule.getSummary();
					values[4] = daylySchedule.getRemark()==null?"":daylySchedule.getRemark();
					values[5] = daylySchedule.getOrder();
					values[6] = daylySchedule.getMonthNo().intValue();
					
					Long dayNo = dao.executeUpdateGetKey(sql, types, values);
					daylySchedule.setDayNo(dayNo);
				}
				
				
				
				//3.保存每一条会议数据
				List<EventSession> eventSessionList = daylySchedule.getEventSessionList();
				for (EventSession eventSession : eventSessionList) {
					
					eventSession.setDaylySchedule(daylySchedule);
					//检测字段是否超长
					eventSession.checkFields();
					
					{
						String sql = "insert into event_Session(SESSION_NO,SESSION_TITLE,SESSION_TIME,DETAILS,DETAIL_TITLE,PROFILED_FOR,DESCRIPTION,SPEAKERS,CALL_DETAILS,TOLL_FREE,PASSCODE,SMART_CLOUD,CLOUD_PASSCODE,SESSION_REMARK,ORDER,REF_DAY_NO,ENROLL_INSTRUCTION,ENROLL_URL,CALL_HOST)"
								+ " values(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						
						int[] types = new int[18];
						types[0] = Types.VARCHAR;
						types[1] = Types.VARCHAR;
						types[2] = Types.VARCHAR;
						types[3] = Types.VARCHAR;
						types[4] = Types.VARCHAR;
						types[5] = Types.VARCHAR;
						types[6] = Types.VARCHAR;
						types[7] = Types.VARCHAR;
						types[8] = Types.VARCHAR;
						types[9] = Types.VARCHAR;
						types[10] = Types.VARCHAR;
						types[11] = Types.VARCHAR;
						types[12] = Types.VARCHAR;
						types[13] = Types.INTEGER;
						types[14] = Types.INTEGER;
						types[15] = Types.VARCHAR;
						types[16] = Types.VARCHAR;
						types[17] = Types.VARCHAR;
						
						Object[] values = new Object[18];
						values[0] = eventSession.getSessionTitle()==null?"":eventSession.getSessionTitle().trim();
						values[1] = eventSession.getSessionTime()==null?"":eventSession.getSessionTime();
						
						values[2] = eventSession.getDetails()==null?"":eventSession.getDetails();
						values[3] = eventSession.getDetail_title()==null?"":eventSession.getDetail_title();
						values[4] = eventSession.getProfiledFor()==null?"":eventSession.getProfiledFor();
						values[5] = eventSession.getDescription()==null?"":eventSession.getDescription();
						values[6] = eventSession.getSpeakers()==null?"":eventSession.getSpeakers();
						values[7] = eventSession.getCallDetails()==null?"":eventSession.getCallDetails();
						values[8] = eventSession.getTollFree()==null?"":eventSession.getTollFree();
						values[9] = eventSession.getPasscode()==null?"":eventSession.getPasscode();
						values[10] = eventSession.getSmartCloud()==null?"":eventSession.getSmartCloud();
						values[11] = eventSession.getSmartCloudPass()==null?"":eventSession.getSmartCloudPass();
						values[12] = eventSession.getAttention()==null?"":eventSession.getAttention();
						
						values[13] = eventSession.getOrder();
						values[14] = daylySchedule.getDayNo().intValue();
						
						values[15] = eventSession.getEnrollInstruction()==null?"":eventSession.getEnrollInstruction();
						values[16] = eventSession.getEnrollUrl()==null?"":eventSession.getEnrollUrl();
						
						values[17] = eventSession.getCallHost()==null?"":eventSession.getCallHost();
						
						Long sessionNo = dao.executeUpdateGetKey(sql, types, values);
						eventSession.setSessionNo(sessionNo);
					}
					
					//3.1 保存category与event_session的信息
					{
						String categoryString=eventSession.getCategoryType();
						if(categoryString!=null&&!"".equals(categoryString)){
							if(categoryString.indexOf("#")!=-1){
								String[] mStrings=categoryString.split("#");
								for(int mm=1;mm<mStrings.length;mm++){
									String sql2 = "insert into event_category values(default,?,?)";
									dao.executeUpdate(sql2, new int[]{Types.INTEGER , Types.VARCHAR} , new Object[]{eventSession.getSessionNo(),mStrings[mm]});
								}
							}
						}
						
					}
					
					//4.保存链接数据
					List<FileLinks> fileLinks = eventSession.getFileLinks();
					for (FileLinks fileLink : fileLinks) {
						
						
						fileLink.setEventSession(eventSession);
						//检测字段是否超长
						fileLink.checkFields();
						{
							String sql = "insert into file_links(LINK_NO,LINK_TITLE,LINK_URL,FILE_FOLDER,FILE_ID,FOLDER_ID,ORDER,REF_SESSION_NO)"
									+ " values(default,?,?,?,?,?,?,?)";
							
							int[] types = new int[7];
							types[0] = Types.VARCHAR;
							types[1] = Types.VARCHAR;
							types[2] = Types.INTEGER;
							types[3] = Types.VARCHAR;
							types[4] = Types.VARCHAR;
							types[5] = Types.INTEGER;
							types[6] = Types.INTEGER;
							
							Object[] values = new Object[7];
							values[0] = fileLink.getLinkTitle()==null?"":fileLink.getLinkTitle();
							values[1] = fileLink.getLinkUrl()==null?"":fileLink.getLinkUrl();
							values[2] = fileLink.getFileOrFolder();
							values[3] = fileLink.getFileId();
							values[4] = fileLink.getFolderId();
							values[5] = fileLink.getOrder();
							values[6] = eventSession.getSessionNo().intValue();
							
							Long linkNo = dao.executeUpdateGetKey(sql, types, values);
							fileLink.setLinkNo(linkNo);
						}
						
						
						//5.保存链接数据
						List<FileList> fileLists = fileLink.getFileLists();
						for (FileList fileList : fileLists) {
							
							
							fileList.setFileLinks(fileLink);
							//检测字段是否超长
							fileLink.checkFields();
							
							String sql = "insert into file_list(FILE_NO,FILE_ID,FOLDER_ID,FILE_NAME,AUTHOR_USER_ID,AUTHOR_NAME,AUTHOR_EMAIL,MODIFIER_USER_ID,MODIFIER_NAME,MODIFIER_EMAIL,SUMMARY,TOTAL_MEDIA_SIZE,CREATE_TIME,UPDATE_TIME,FILE_URL,ORDER,REF_LINK_NO)"
									+ " values(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							
							int[] types = new int[16];
							types[0] = Types.VARCHAR;
							types[1] = Types.VARCHAR;
							types[2] = Types.VARCHAR;
							types[3] = Types.VARCHAR;
							types[4] = Types.VARCHAR;
							types[5] = Types.VARCHAR;
							types[6] = Types.VARCHAR;
							types[7] = Types.VARCHAR;
							types[8] = Types.VARCHAR;
							types[9] = Types.VARCHAR;
							types[10] = Types.VARCHAR;
							types[11] = Types.TIMESTAMP;
							types[12] = Types.TIMESTAMP;
							types[13] = Types.VARCHAR;
							types[14] = Types.INTEGER;
							types[15] = Types.INTEGER;
							
							Object[] values = new Object[16];
							values[0] = fileList.getFileId()==null?"":fileList.getFileId();
							values[1] = fileList.getFolderId()==null?"":fileList.getFolderId();
							values[2] = fileList.getFileName()==null?"":fileList.getFileName();
							
							values[3] = fileList.getAuthorUserId()==null?"":fileList.getAuthorUserId();
							values[4] = fileList.getAuthorName()==null?"":fileList.getAuthorName();
							values[5] = fileList.getAuthorEmail()==null?"":fileList.getAuthorEmail();
							
							values[6] = fileList.getModifierUserId()==null?"":fileList.getModifierUserId();
							values[7] = fileList.getModifierName()==null?"":fileList.getModifierName();
							values[8] = fileList.getModifierEmail()==null?"":fileList.getModifierEmail();
							
							values[9] = fileList.getSummary()==null?0:fileList.getSummary();
							values[10] = fileList.getTotalMediaSize()==null?0:fileList.getTotalMediaSize();
							values[11] = fileList.getCreateTime();
							values[12] = fileList.getUpdateTime();
							values[13] = fileList.getFileUrl()==null?"":fileList.getFileUrl();
							values[14] = fileList.getOrder()==null?"":fileList.getOrder();
							values[15] = fileLink.getLinkNo().intValue();
							
							dao.executeUpdate(sql, types, values);
							
//							//tony add 
							FileList been = new FileList();
							been.setFileId(fileList.getFileId());
							been.setFileUrl(fileList.getFileUrl());
							queue.putMsg(been);
						}
						
					}
					
				}
				
			}
		

			//数据提交
			transactionManager.commit(status);	
			new Thread( new FileTransForm(queue)).start();
			//
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "save "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "save "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "save "+monthlySchedule.getRequestYear()+" "+monthlySchedule.getRequestMonth()+" month's data error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;
		}
	}
	
	/**
	 * TODO 保存解析数据的错误信息
	 * @param parseLog
	 * @return
	 */
	public static int addParseLog( ParseLog parseLog ){
		try {
			Dao dao = new DaoImpl();
			if( parseLog.getException().length() > 2000 ){
				parseLog.setException( parseLog.getException().substring(0,2000) );
			}
			if( parseLog.getDescription().length() > 250 ){
				parseLog.setDescription( parseLog.getDescription().substring(0,250) );
			}
		
		
			String sql3 = "insert into parse_log(log_no,create_time,exception,description,solved) values(default,?,?,?,?)";
			
			int[] types = new int[4];
			types[0] = Types.TIMESTAMP;
			types[1] = Types.VARCHAR;
			types[2] = Types.VARCHAR;
			types[3] = Types.INTEGER;
			
			Object[] values = new Object[4];
			values[0] = parseLog.getCreateTime();
			values[1] = parseLog.getException() ;
			values[2] = parseLog.getDescription();
			values[3] = parseLog.getSolved();
			
			return dao.executeUpdate(sql3, types, values);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;	
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		}
	}
	
	
	
	/**
	 * TODO 保存采集到的推送消息
	 * @param pushMessage
	 * @return
	 */
	public static int addNotification( Dao dao , PushMessage pushMessage ){
		try {
			
			if( pushMessage.getPushId().length() > 48 ){
				pushMessage.setPushId( pushMessage.getPushId().substring(0,47) );
			}
			if( pushMessage.getPushTime().length() > 36 ){
				pushMessage.setPushTime( pushMessage.getPushTime().substring(0,35) );
			}
			if( pushMessage.getMessage().length() > 256 ){
				pushMessage.setMessage( pushMessage.getMessage().substring(0,255) );
			}
			if( pushMessage.getSummary().length() > 512 ){
				pushMessage.setSummary( pushMessage.getSummary().substring(0,510) );
			}
			
			String sql3 = "insert into PUSH_NOTIFICATION(PUSH_ID,PUSH_TIME,MESSAGE,SUMMARY,STATUS) values(?,?,?,?,?)";
			
			int[] types = new int[5];
			types[0] = Types.VARCHAR;
			types[1] = Types.VARCHAR;
			types[2] = Types.VARCHAR;
			types[3] = Types.VARCHAR;
			types[4] = Types.INTEGER;
			
			Object[] values = new Object[5];
			values[0] = pushMessage.getPushId();
			values[1] = pushMessage.getPushTime();
			values[2] = pushMessage.getMessage();
			values[3] = pushMessage.getSummary();
			values[4] = 0 ;
			
			return dao.executeUpdate(sql3, types, values);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;	
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		}
	}
	
	/**
	 * TODO 保存采集到的历史推送消息
	 * @param PushHistory
	 * @return
	 */
	public static int addPushHistory( Dao dao , PushHistory pushHistory ){
		try {
			
			if( pushHistory.getPushId().length() > 48 ){
				pushHistory.setPushId( pushHistory.getPushId().substring(0,47) );
			}
			if( pushHistory.getPushTime().length() > 36 ){
				pushHistory.setPushTime( pushHistory.getPushTime().substring(0,35) );
			}
			if( pushHistory.getMessage().length() > 256 ){
				pushHistory.setMessage( pushHistory.getMessage().substring(0,255) );
			}
			if( pushHistory.getSummary().length() > 512 ){
				pushHistory.setSummary( pushHistory.getSummary().substring(0,510) );
			}
			
			String sql3 = "insert into push_history(PUSH_ID,PUSH_TIME,MESSAGE,SUMMARY,USER_ID,SUBSCRIPTION_ID,STATUS,STATUS_1) values(?,?,?,?,?,?,?,0)";
			
			int[] types = new int[7];
			types[0] = Types.VARCHAR;
			types[1] = Types.VARCHAR;
			types[2] = Types.VARCHAR;
			types[3] = Types.VARCHAR;
			types[4] = Types.VARCHAR;
			types[5] = Types.VARCHAR;
			types[6] = Types.INTEGER;
			
			Object[] values = new Object[7];
			values[0] = pushHistory.getPushId();
			values[1] = pushHistory.getPushTime();
			values[2] = pushHistory.getMessage();
			values[3] = pushHistory.getSummary();
			values[4] = pushHistory.getUserId();
			values[5] = pushHistory.getSubscriptionId();
			values[6] = 0 ;
			
			return dao.executeUpdate(sql3, types, values);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;	
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		}
	}
	
	public static void updatePushStatus( Dao dao ,String id){
		try {
			int[] types = new int[1];
			types[0] = Types.VARCHAR;
			Object[] values = new Object[1];
			values[0] = id;
			dao.executeUpdate("update push_notification set status=1 where push_id=?",types,values);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * TODO 保存用户订阅关系
	 * @param user_id,topic_id
	 * @return
	 */
	public static boolean saveSubscription( String userId,String topicId ){
		Dao dao=new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);
		String[] topicArrStrings={};
		try {
			String sql3 = "insert into topic_users (id_topic, id_user) values (?, (select intranet_no from users where intranet_id=?))";
			int[] types = new int[2];
			types[0] = Types.INTEGER;
			types[1] = Types.VARCHAR;
			Object[] values = new Object[2];
			values[1] = userId;
			if(topicId.indexOf(",")!=-1){
				topicArrStrings=topicId.split(",");
				for(String topic:topicArrStrings){
					values[0] = Integer.parseInt(topic);
					dao.executeUpdate(sql3, types, values);
				}
			}else{
				values[0] = Integer.parseInt(topicId);
				dao.executeUpdate(sql3, types, values);
			}
			transactionManager.commit(status);	
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;	
		} 
	}
	
	/**
	 * TODO 删除用户订阅关系
	 * @param user_id,topic_id
	 * @return
	 */
	public static boolean deleteSubscription( String userId,String topicId ){
		Dao dao=new DaoImpl();
		DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager( dao.getDataSource() );
		TransactionStatus status = transactionManager.getTransaction(definition);
		String[] topicArrStrings={};
		try {
			String sql3 = "delete from topic_users where id_user in (select intranet_no from users where intranet_id=?) and id_topic=?";
			int[] types = new int[2];
			types[0] = Types.VARCHAR;
			types[1] = Types.INTEGER;
			Object[] values = new Object[2];
			values[0] = userId;
			if(topicId.indexOf(",")!=-1){
				topicArrStrings=topicId.split(",");
				for(String topic:topicArrStrings){
					values[1] = Integer.parseInt(topic);
					dao.executeUpdate(sql3, types, values);
				}
			}else{
				values[1] = Integer.parseInt(topicId);
				dao.executeUpdate(sql3, types, values);
			}
			transactionManager.commit(status);	
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			transactionManager.rollback(status);
			return false;	
		} 
	}
	
	/**
	 * TODO 保存采集到的历史推送消息
	 * @param PushHistory mysql
	 * @return
	 */
	public static int addPushHistoryNew( Dao dao , PushHistoryNew pushHistory ){
		try {
			
			if( pushHistory.getPushId().length() > 48 ){
				pushHistory.setPushId( pushHistory.getPushId().substring(0,47) );
			}
			if( pushHistory.getPushTime().length() > 36 ){
				pushHistory.setPushTime( pushHistory.getPushTime().substring(0,35) );
			}
			if( pushHistory.getMessage().length() > 256 ){
				pushHistory.setMessage( pushHistory.getMessage().substring(0,255) );
			}
			if( pushHistory.getSummary().length() > 512 ){
				pushHistory.setSummary( pushHistory.getSummary().substring(0,510) );
			}
			
			String sql3 = "insert into push_history(PUSH_ID,PUSH_TIME,MESSAGE,SUMMARY,USER_ID,SUBSCRIPTION_ID,STATUS,STATUS_1,post_id) values(?,?,?,?,?,?,?,0,?)";
			
			int[] types = new int[8];
			types[0] = Types.VARCHAR;
			types[1] = Types.VARCHAR;
			types[2] = Types.VARCHAR;
			types[3] = Types.VARCHAR;
			types[4] = Types.VARCHAR;
			types[5] = Types.VARCHAR;
			types[6] = Types.INTEGER;
			types[7] = Types.VARCHAR;
			
			Object[] values = new Object[8];
			values[0] = pushHistory.getPushId();
			values[1] = pushHistory.getPushTime();
			values[2] = pushHistory.getMessage();
			values[3] = pushHistory.getSummary();
			values[4] = pushHistory.getUserId();
			values[5] = pushHistory.getSubscriptionId();
			values[6] = 0 ;
			values[7] = pushHistory.getPostId() ;
			
			return dao.executeUpdate(sql3, types, values);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;	
		} catch (SQLException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info( "save parse data log error , data rollback."+e.getMessage() );
			return 0;
		}
	}
	
	public static void updatePushStatusNew( Dao dao ,String id){
		try {
			int[] types = new int[1];
			types[0] = Types.VARCHAR;
			Object[] values = new Object[1];
			values[0] = id;
			dao.executeUpdate("update nase_post_notification set if_send=1,send_date=now() where id=?",types,values);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
