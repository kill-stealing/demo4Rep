package com.ibm.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.ibm.server.servlet.Servlet;

public class WebApp {
	private static ServletContext context;
	static{
		
		//1、获取解析工厂
		SAXParserFactory factory= SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse;
		List<Entity> entity=null;
		List<Mapping> mapping=null;
		try {
			parse = factory.newSAXParser();
			//3.加载文档document 注册处理器
			//4.编写处理器
			WebHandler handler=new WebHandler();
			parse.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("WEB_INFO/web.xml"), handler);
			entity=handler.getEntityList();
			mapping=handler.getMappingList();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		context=new ServletContext();
		
		Map<String, String> mapping1=context.getMapping();
		Map<String, String> servlet1=context.getServlet();
		for(Entity e:entity){
			servlet1.put(e.getName(), e.getClz());
		}
		for(Mapping m:mapping){
			List<String> url=m.getUrlPattern();
			for(String u:url){
				mapping1.put(u, m.getName());
			}
			
		}
		/*mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/reg", "register");
		
		
		servlet.put("login", "com.ibm.server.demo3.LoginServlet");
		servlet.put("register", "com.ibm.server.demo3.RegisterServlet");*/
	}
	
	public static Servlet getServlet(String url) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(null==url||(url.trim()).equals("")){
			return null;
		}
		//return context.getServlet().get(context.getMapping().get(url));
		//根据字符串（完整路径）创建对象
		String name=context.getServlet().get(context.getMapping().get(url));
		return (Servlet)Class.forName(name).newInstance();
	}
}
