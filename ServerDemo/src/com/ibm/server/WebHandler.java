package com.ibm.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class WebHandler extends DefaultHandler{
	private List<Entity> entityList;
	private List<Mapping> mappingList;
	private Entity entity;
	private Mapping mapping;
	private String beginTag;
	private boolean isMap;

	@Override
	public void startDocument() throws SAXException {
		//文档解析开始
		entityList=new ArrayList<Entity>();
		mappingList=new ArrayList<Mapping>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		//开始元素
		if(null!=qName){
			beginTag=qName;
			if(qName.equals("servlet")){
				isMap=false;
				entity=new Entity();
			}else if(qName.equals("servlet-mapping")){
				isMap=true;
				mapping=new Mapping();
			}
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String str=new String(ch,start,length);
		if(null!=beginTag){
			if(isMap){
				if(beginTag.equals("servlet-name")){
					mapping.setName(str);
				}else if(beginTag.equals("url-pattern")){
					mapping.getUrlPattern().add(str);
				}
			}else{
				if(beginTag.equals("servlet-name")){
					entity.setName(str);
				}else if(beginTag.equals("servlet-class")){
					entity.setClz(str);
				}
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//结束元素
		if(null!=qName){
			if(qName.equals("servlet")){
				entityList.add(entity);
			}else if(qName.equals("servlet-mapping")){
				mappingList.add(mapping);
			}
		}
		beginTag=null;
	}

	@Override
	public void endDocument() throws SAXException {
		//文档解析结束
	}

	

	public List<Entity> getEntityList() {
		return entityList;
	}

	public List<Mapping> getMappingList() {
		return mappingList;
	}

	public Entity getEntity() {
		return entity;
	}

	public Mapping getMapping() {
		return mapping;
	}

	public String getBeginTag() {
		return beginTag;
	}

	public boolean isMap() {
		return isMap;
	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//1、获取解析工厂
		SAXParserFactory factory= SAXParserFactory.newInstance();
		//2、从解析工厂获取解析器
		SAXParser parse=factory.newSAXParser();
		//3.加载文档document 注册处理器
		//4.编写处理器
		WebHandler handler=new WebHandler();
		parse.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("WEB_INFO/web.xml"), handler);
		List<Entity> entity=handler.getEntityList();
		for(Entity e:entity){
			System.out.println(e.getName()+"-->"+e.getClz());
		}
		List<Mapping> mapping=handler.getMappingList();
		for(Mapping m:mapping){
			System.out.println(m.getName()+"  "+Arrays.toString(m.getUrlPattern().toArray()));
		}
	}

}
