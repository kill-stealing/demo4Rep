package com.ibm.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 封装request
 * @author liumy
 *
 */
public class Request {
	//请求方式
	private String method;
	//请求资源
	private String url;
	//请求参数
	private Map<String, List<String>> map;
	
	public String getUrl() {
		return url;
	}

	//内部
	public static final String CRLF = "\r\n";
	private InputStream is;
	private String requestInfo;
	
	public Request(){
		method="";
		url="";
		map=new HashMap<String, List<String>>();
		requestInfo="";
	}
	
	public Request(InputStream is){
		this();
		this.is=is;
		byte[] data=new byte[20480];
		int len;
		try {
			len = is.read(data);
			requestInfo=new String(data,0,len);
		} catch (IOException e) {
			return ;
		}
		
		//分析头信息
		parseRequestInfo();
	}
	
	/**
	 * 分析请求信息
	 */
	
	/*	GET /index.html?userName=111&pwd=fdsafa HTTP/1.1
		Host: localhost:8888
		Connection: keep-alive
		Cache-Control: max-age=0
		Upgrade-Insecure-Requests: 1
		User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36
		Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp;q=0.8
		Accept-Encoding: gzip, deflate, sdch, br
		Accept-Language: zh-CN,zh;q=0.8
	*/

	private void parseRequestInfo(){
		if(null==requestInfo||(requestInfo=requestInfo.trim()).equals("")){
			return;
		}
		
		/**
		 * =========================================
		 * 从信息的首行分解出：请求方式 请求参数(get 可能存在)
		 * 如：GET /index.html?userName=111&pwd=fdsafa HTTP/1.1
		 * 
		 * 如果为post方式，请求参数可能在最后正文中
		 * 
		 * =========================================
		 */
		
		String paramsString="";//接收请求参数
		//1.获取请求方式
		String firstLine=requestInfo.substring(0,requestInfo.indexOf(CRLF));
		int idx=requestInfo.indexOf("/");// 第一个/的位置
		this.method=firstLine.substring(0, idx).trim();
		String urlStr=firstLine.substring(idx,firstLine.indexOf("HTTP/")).trim();
		if(this.method.equals("POST")){
			this.url=urlStr;
			
			paramsString=requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
			
		}else if(this.method.equals("GET")){
			//是否存在参数
			if(urlStr.contains("?")){
				String[] urlArr=urlStr.split("\\?");
				this.url=urlArr[0];
				paramsString=urlArr[1];
			}else{
				this.url=urlStr;
			}
		}
		
		if(paramsString.equals("")){
			return;
		}
		//2.将请求参数封装到Map中
		parseParams(paramsString);
	}
	
	private void parseParams(String paramsString){
		//分割
		
		StringTokenizer token=new StringTokenizer(paramsString,"&");
		while(token.hasMoreTokens()){
			String keyValue=token.nextToken();
			String[] keyValues=keyValue.split("=");
			if(keyValues.length==1){
				keyValues=Arrays.copyOf(keyValues, 2);
				keyValues[1]=null;
			}
			
			String key=keyValues[0].trim();
			String value= null==keyValues[1]?null:decode(keyValues[1].trim(), "utf-8");
			
			//转换成Map 分拣
			if(!map.containsKey(key)){
				map.put(key, new ArrayList<String>());
			}
			List<String> valList=map.get(key);
			valList.add(value);
		}
		System.out.println(map);
	}
	
	/**
	 * 根据页面的name 获取对应的值
	 * @return
	 */
	public String getParameter(String name){
		String[] values=getParameterValues(name);
		if(null==values){
			return null;
		}
		return values[0];
	}
	
	/**
	 * 根据页面的name 获取对应的多个值
	 * @return
	 */
	public String[] getParameterValues(String name){
		List<String> values=null;
		if((values=map.get(name))==null){
			return null;
		}else{
			return values.toArray(new String[0]);
		}
	}
	
	private void parseParamsByMyself(String paramsString){
		//分割
		String[] paramArr=paramsString.split("&");
		for(int i=0;i<paramArr.length;i++){
			String param=paramArr[i];
			String name=param.split("=")[0];
			String value=param.split("=")[1];
			if(null!=map.get(name)&&!"".equals(map.get(name))){
				List<String> valList=new ArrayList<String>();
				valList=map.get(name);
				valList.add(value);
				map.put(name,valList);
			}else{
				List<String> valList=new ArrayList<String>();
				valList.add(value);
				map.put(name,valList);
			}
		}
		System.out.println(map);
	}
	
	private String decode(String value,String code){
		try {
			return java.net.URLDecoder.decode(value,code);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
