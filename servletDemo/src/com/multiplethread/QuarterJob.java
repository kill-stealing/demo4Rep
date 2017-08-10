package com.multiplethread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.multiplethread.dao.Dao;
import com.multiplethread.dao.DaoImpl;


/*********************************************
 * 每隔15分钟执行一次定时执行此程序
 * @author Simon
 * @time   Oct 14, 2014 10:37:32 PM
 *********************************************/
public class QuarterJob implements Runnable {
	
	//private static final WorklightServerLogger logger = new WorklightServerLogger(DailyJob.class, WorklightServerLogger.MessagesBundles.CORE);
	//private final static Logger logger = Logger.getLogger(JobManager.class.getName()); 
	private Logger logger = Logger.getLogger(QuarterJob.class);
	
	
	
	//test
	//private String wikiUrl = "https://w3-connections.ibm.com/wikis/basic/anonymous/api/wiki/0616caab-d8c3-4bf3-85d3-52ecec780936/page/ab9cf114-2884-42ed-b21e-b741e5398599/feed?category=version";
	
	private Dao dao = new DaoImpl();
	private Dao worklightDao = new DaoImpl();
	
	private Integer times = 0;
	
	
	public QuarterJob(){
	
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:db2://9.51.101.218:50000/WRKLGHT:currentSchema=WRKLGHT;");
		ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		ds.setUsername("db2inst1");
		ds.setPassword("admin4dst");
			
		/*BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:db2://9.16.170.55:60000/WRKLGHT:currentSchema=WRKLGHT;");
		ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		ds.setUsername("db2inst1");
		ds.setPassword("gone4now");*/
		
		
		worklightDao.setDataSource( ds );
	}
	
	
	@Override
	public void run() {
		try {

			Class.forName("com.ibm.db2.jcc.DB2Driver");
			Class.forName("au.id.jericho.lib.html.Source");
			Class.forName("org.springframework.jdbc.core.JdbcTemplate");
			Class.forName("com.sun.xml.internal.stream.XMLInputFactoryImpl");
			
		} catch (ClassNotFoundException e) {
			
			JobManager.getInstance().stop();
			return;
		}
		
		{
			Long currentTime_ = parseDate().getTime();
//			Long currentTime_ = UtilDateFormat.getDate().getTime();
//			currentTime_ = currentTime_ + TimeZoneServlet.getTimezone();
			logger.info( UtilDateFormat.getDate( new Date(currentTime_), "yyyy-MM-dd HH:mm")+" QuarterJob Timer start...." + dao.hashCode() );
		}
		
		List<PushMessage> pushMessageList = new ArrayList<PushMessage>();
		
		this.times++;
		try{
			//1.先查询出push的数据
			List<Map<String, Object>> list = this.dao.executeQueryForList("select * from push_notification ");
			if( list != null && !list.isEmpty() ){
				for(Map<String, Object> map:list){
					PushMessage pushMessage=new PushMessage();
					try {
						pushMessage.setPushId((int)map.get("PUSH_ID")+"");
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						System.out.println(" i don't understand why it throws the exception ");
						pushMessage.setPushId((String)map.get("PUSH_ID")+"");
					}
					
					pushMessage.setPushTime((String)map.get("PUSH_TIME"));
					pushMessage.setMessage((String)map.get("MESSAGE"));
					pushMessage.setSummary((String)map.get("SUMMARY"));
					pushMessage.setStatus((int)map.get("STATUS"));
					pushMessage.setSubscriptionId((String)map.get("SUBSCRIPTION_ID"));
					pushMessageList.add(pushMessage);
				}
			}
			logger.info( "Get data successful." );
		}catch( Exception e ){
			logger.info( "Exception--> "+e.getMessage() );
			e.printStackTrace();
			StackTraceElement[] aa = e.getStackTrace();
			for (int i = 0; i < aa.length; i++) {
				logger.info( "Exception--> "+aa[i].toString() );
			}
		}
		
		try{
			//3.消息推送
			if( !pushMessageList.isEmpty() ){
				
				//save new data
				for (PushMessage pm : pushMessageList) {
				    int status=pm.getStatus();
					Long currentTime = parseDate().getTime();
					Long pushTime = UtilDateFormat.getDate( pm.getPushTime() , "yyyy-MM-dd HH:mm").getTime();
					System.out.println(" currentTime "+currentTime);
					System.out.println("pushTime "+pushTime);
//					currentTime = currentTime + TimeZoneServlet.getTimezone();
					
					//Frid's 8:40 prod's 7:40
					if( pushTime <= currentTime && pushTime >= (currentTime-1000*60*15) ){
//					if(pm.getPushId().equals("127")){
						//invokeSQLAdapterGetValidAPPListByDeviceType( null , null );
//						List<Map<String, Object>> deviceList = this.worklightDao.executeQueryForList("select MIN(friendly_name) AS USER_ID from WRKLGHT_2.DEVICES where STATUS = 0 group by friendly_name");
						List<Map<String, Object>> deviceList = this.worklightDao.executeQueryForList("select MIN(friendly_name) AS USER_ID from WRKLGHT_2.DEVICES where STATUS = 0 group by friendly_name");
						if( deviceList != null && !deviceList.isEmpty() ){
							for (Map<String, Object> map : deviceList) {
								String USER_ID = (String)map.get("USER_ID");
								String subscriptionId=pm.getSubscriptionId();
//								List<Map<String, Object>> generalList = this.dao.executeQueryForList("select * from topic_users "
//										+ "where id_user=(select intranet_no from users where intranet_id=?) and id_topic=445 ",new int[]{Types.VARCHAR} , paramterObjects1);
								//用户是否订阅General
								//yes
//								if(generalList!=null&&!generalList.isEmpty()){
									if(subscriptionId!=null&&!"".equals(subscriptionId)){
										//判断subscriptionID是否是多个
										//是多个
										if(subscriptionId.indexOf(",")!=-1){
											//用户是否订阅过这个subscription
											int flag=0;
											String[] subscriptionIdArray=subscriptionId.split(",");
											//判断subscription中是否有general，如果有，那发送给所有用户。
											if(subscriptionId.indexOf("445")!=-1){
												for(String id:subscriptionIdArray){
													flag=1;
													//根据push的status 证明是否发送过。发送过则不记录历史表
													if(status==0){
														//记录历史表 每个subscription记录一条
														PushHistory pushHistory=new PushHistory();
														pushHistory.setPushId(pm.getPushId());
														pushHistory.setPushTime(pm.getPushTime());
														pushHistory.setMessage(pm.getMessage());
														pushHistory.setSummary(pm.getSummary());
														pushHistory.setUserId(USER_ID);
														pushHistory.setSubscriptionId(Integer.parseInt(id));
														DataHandle.addPushHistory(dao, pushHistory);
													}
												}
											}else{
												for(String id:subscriptionIdArray){
													Object[] paramterObjects={USER_ID,id};
													List<Map<String, Object>> list = this.dao.executeQueryForList("select * from topic_users "
															+ "where id_user=(select intranet_no from users where intranet_id=?) and id_topic=? ",new int[]{Types.VARCHAR,Types.VARCHAR} , paramterObjects);
													if( list != null && !list.isEmpty()){
														flag=1;
														//根据push的status 证明是否发送过。发送过则不记录历史表
														if(status==0){
															//记录历史表 每个subscription记录一条
															PushHistory pushHistory=new PushHistory();
															pushHistory.setPushId(pm.getPushId());
															pushHistory.setPushTime(pm.getPushTime());
															pushHistory.setMessage(pm.getMessage());
															pushHistory.setSummary(pm.getSummary());
															pushHistory.setUserId(USER_ID);
															pushHistory.setSubscriptionId(Integer.parseInt(id));
															DataHandle.addPushHistory(dao, pushHistory);
															}
													}
												}
											}
											//只发送一回
											if(flag==1&&status==0){
												if( pm.getSummary() != null ){
													if( pm.getSummary().length() >= 256 ){
														//取前250个字符
														pm.setSummary( pm.getSummary().substring(0, 250) +"..." );
													}
													logger.info( "send message time :"+UtilDateFormat.getDate( new Date(currentTime), "yyyy-MM-dd HH:mm")+", Summary:...." +  pm.getSummary() );
													
													//给所有注册过设备的用户推送消息
													invokeSQLAdapterGetValidAPPListByDeviceType( USER_ID , pm.getSummary(),pm.getPushId());
												}
											}
										}
										else {
											Object[] paramterObjects={USER_ID,subscriptionId};
											List<Map<String, Object>> list = this.dao.executeQueryForList("select * from topic_users "
													+ "where id_user=(select intranet_no from users where intranet_id=?) and id_topic=? ",new int[]{Types.VARCHAR,Types.VARCHAR} , paramterObjects);
											if( (list != null && !list.isEmpty())||subscriptionId.equals("445") ){
												//根据push的status 证明是否发送过。发送过则不记录历史表
												if(status==0){
													//记录历史表 每个subscription记录一条
													PushHistory pushHistory=new PushHistory();
													pushHistory.setPushId(pm.getPushId());
													pushHistory.setPushTime(pm.getPushTime());
													pushHistory.setMessage(pm.getMessage());
													pushHistory.setSummary(pm.getSummary());
													pushHistory.setUserId(USER_ID);
													pushHistory.setSubscriptionId(Integer.parseInt(subscriptionId));
													DataHandle.addPushHistory(dao, pushHistory);
													
													if( pm.getSummary() != null ){
														if( pm.getSummary().length() >= 256 ){
															//取前250个字符
															pm.setSummary( pm.getSummary().substring(0, 250) +"..." );
														}
														
														logger.info( "send message time :"+UtilDateFormat.getDate( new Date(currentTime), "yyyy-MM-dd HH:mm")+", Summary:...." +  pm.getSummary() );
														//给所有注册过设备的用户推送消息
														invokeSQLAdapterGetValidAPPListByDeviceType( USER_ID , pm.getSummary(),pm.getPushId());
													}
												}
												
											}
										}
									}
//								}
							}
						}
						
						logger.info( "update push status :"+UtilDateFormat.getDate( new Date(currentTime), "yyyy-MM-dd HH:mm"));

						//修改push的status 证明是否发送过。发送过则不记录历史表
						DataHandle.updatePushStatus(dao, pm.getPushId());
					}
				}
			}
			
			
		}catch( Exception e ){
			logger.info( "error--> "+e.getMessage() );
			e.printStackTrace();
			StackTraceElement[] aa = e.getStackTrace();
			for (int i = 0; i < aa.length; i++) {
				logger.info( "error--> "+aa[i].toString() );
			}
			
		}catch(Throwable e){
			logger.info( "error--> "+e.getMessage() );
			e.printStackTrace();
			StackTraceElement[] aa = e.getStackTrace();
			for (int i = 0; i < aa.length; i++) {
				logger.info( "error--> "+aa[i].toString() );
			}
		}
		
	}

	
	public Integer getTimes() {
		return times;
	}
	
	/**
	 * 调用adapter进行数据推送
	 * @param deviceType
	 * @param deviceOS
	 * @param countryCode
	 * @return
	 */
	public void invokeSQLAdapterGetValidAPPListByDeviceType( String userId, String notificationText,String pushId){		
		
		if(notificationText!=null && !"".equals(notificationText)){

			notificationText = notificationText.replace("'", "!@#$%");
			//String url = "http://lexbz1218.lexington.ibm.com:9080/NAeFridaysMobileApp/invoke";
			String url = "http://lexbz1218.lexington.ibm.com:9080/NAeFridaysMobileApp/invoke";
//			String url = "http://cv01a114.w3-969.ibm.com:9080/NAeFridaysMobileApp/invoke";
			//http://cv01a114.w3-969.ibm.com/NAeFridaysMobileApp/invoke?adapter=PushAdapter&procedure=submitNotification&parameters=[%22dllism%40cn.ibm.com%22%2C%22aa%22] 
			Object[] paramterObjects={userId};
			int counts=0;
			try {
				counts = this.dao.executeQueryForInt("select count(*)   from ( "+
							" select distinct push_id from push_history where user_id=? and status_1=0) as a ",new int[]{Types.VARCHAR} , paramterObjects);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			logger.info(" counts of the push message :"+counts);
				
			Map<String,String> params = new HashMap<String,String>();
			params.put("adapter", "PushAdapter");
			params.put("procedure", "submitNotification");
			params.put("parameters", "['" + userId + "','" + 	notificationText + "','"+counts+"']");
			
			String result = doPost( url , params , "UTF-8" );
			logger.info("call push adapter : " + result);
		}
		else{
			logger.error("notificationText is null");
		}
	}   
	
	/**
	 * 调用adapter进行数据推送
	 * @param deviceType
	 * @param deviceOS
	 * @param countryCode
	 * @return
	 */
	public void invokeSQLAdapterGetValidAPPListByDeviceType1( String userId, String notificationText,int counts){		
		
		if(notificationText!=null && !"".equals(notificationText)){

			notificationText = notificationText.replace("'", "!@#$%");
			//String url = "http://lexbz1218.lexington.ibm.com:9080/NAeFridaysMobileApp/invoke";
			String url = "http://lexbz1218.lexington.ibm.com:9080/NAeFridaysMobileApp/invoke";
//			String url = "http://cv01a114.w3-969.ibm.com:9080/NAeFridaysMobileApp/invoke";
			//http://cv01a114.w3-969.ibm.com/NAeFridaysMobileApp/invoke?adapter=PushAdapter&procedure=submitNotification&parameters=[%22dllism%40cn.ibm.com%22%2C%22aa%22] 
//			logger.info(" counts of the push message :"+counts);
				
			Map<String,String> params = new HashMap<String,String>();
			params.put("adapter", "PushAdapter");
			params.put("procedure", "submitNotification");
			params.put("parameters", "['" + userId + "','" + 	notificationText + "','"+counts+"']");
			
			String result = doPost( url , params , "UTF-8" );
			logger.info("call push adapter : " + result+" userId:"+userId);
		}
		else{
			logger.error("notificationText is null"+" userId:"+userId);
		}
	}   
	
	/**
     * <pre>
     * 发送带参数的POST的HTTP请求
     * </pre>
     *
     * @param reqUrl HTTP请求URL
     * @param parameters 参数映射表
     * @return HTTP响应的字符串
     */
    @SuppressWarnings("rawtypes")
	public String doPost(String reqUrl, Map<String,String> parameters,String recvEncoding){
        HttpURLConnection url_con = null;
        String responseContent = null;
        try
        {
            StringBuffer params = new StringBuffer();
            for (Iterator iter = parameters.entrySet().iterator(); iter.hasNext();)
            {
            	java.util.Map.Entry element = (java.util.Map.Entry) iter.next();
                params.append(element.getKey().toString());
                params.append("=");
                params.append(URLEncoder.encode(element.getValue().toString(),"UTF-8"));
                params.append("&");
            }

            if (params.length() > 0)
            {
                params = params.deleteCharAt(params.length() - 1);
            }

            URL url = new URL(reqUrl);
            url_con = (HttpURLConnection) url.openConnection();
            url_con.setRequestMethod("POST");
            System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(5000));// （单位：毫秒）jdk1.4换成这个,连接超时
            System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(10000)); // （单位：毫秒）jdk1.4换成这个,读操作超时
            // url_con.setConnectTimeout(5000);//（单位：毫秒）jdk
            // 1.5换成这个,连接超时
            // url_con.setReadTimeout(5000);//（单位：毫秒）jdk 1.5换成这个,读操作超时
            url_con.setDoOutput(true);
            byte[] b = params.toString().getBytes();
            url_con.getOutputStream().write(b, 0, b.length);
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();

            InputStream in = url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,
                    recvEncoding));
            String tempLine = rd.readLine();
            StringBuffer tempStr = new StringBuffer();
            String crlf=System.getProperty("line.separator");
            while (tempLine != null)
            {
                tempStr.append(tempLine);
                tempStr.append(crlf);
                tempLine = rd.readLine();
            }
            responseContent = tempStr.toString();
            rd.close();
            in.close();
        }
        catch (IOException e)
        {
        	logger.error( "网络故障" , e );
        }
        finally
        {
            if (url_con != null)
            {
                url_con.disconnect();
            }
        }
        return responseContent;
    }
    
    public Date parseDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EST"));   
	    String utcTime =simpleDateFormat.format(new Date());
	    Date d=null;
	    try {
			d=simpleDateFormat1.parse(utcTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return d;
    }
    
    
    
    public static void main(String[] args) {
    	/*QuarterJob j=new QuarterJob();
		Date d=j.parseDate();
		System.out.println(d);
    	
    	TimeZone time = TimeZone.getTimeZone("GMT+8"); //设置为东八区
    	time = TimeZone.getDefault();// 这个是国际化所用的
    	System.out.println(time);
    	TimeZone.setDefault(time);// 设置时区
    	Calendar calendar = Calendar.getInstance();// 获取实例
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//构造格式化模板
    	Date date = calendar.getTime(); //获取Date对象
    	String str = new String();
    	str = format1.format(date);//对象进行格式化，获取字符串格式的输出
    	System.out.println(str);*/
    	QuarterJob job=new QuarterJob();
    	job.invokeSQLAdapterGetValidAPPListByDeviceType1("dllmyliu@cn.ibm.com", "test notifications", 5);
    	
	}
	
}
