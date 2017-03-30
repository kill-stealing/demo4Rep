package com.ibm.sorm.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibm.sorm.DataSource;
import com.ibm.sorm.TypeConvertor;
import com.ibm.sorm.bean.ColumnInfo;
import com.ibm.sorm.bean.JavaFieldGetSet;
import com.ibm.sorm.bean.TableInfo;

public class FileUtils {
	public static void closeAll(Closeable ... io){
		for (Closeable closeable : io) {
			if(closeable!=null){
				try {
					closeable.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static JavaFieldGetSet createField(ColumnInfo columnInfo,TypeConvertor typeConvertor){
		JavaFieldGetSet jfgs=new JavaFieldGetSet();
		String type=typeConvertor.databaseType2JavaType(columnInfo.getDataType());
		String name=columnInfo.getName();
		String functionName=StringUtils.first2UpperCase(name);
		StringBuilder sb=new StringBuilder();
		if(name.equals("private")){
			name="privateNo";
			functionName="PrivateNo";
		}
		sb.append("\tprivate "+type+" "+name+" ;\n");
		jfgs.setFieldInfo(sb.toString());
		sb=new StringBuilder();
		//生成get方法
		sb.append("\tpublic "+type+" get"+functionName+" (){\n");
		sb.append("\t\treturn "+name+";\n");
		sb.append("\t}\n");
		jfgs.setGetInfo(sb.toString());
		//生成set方法
		sb=new StringBuilder();
		sb.append("\tpublic void set"+functionName+"("+type+" "+name+" ){\n");
		sb.append("\t\tthis."+name+"="+name+";\n");
		sb.append("\t}\n");
		jfgs.setSetInfo(sb.toString());
		return jfgs;
	}
	
	public static String  createJavaSrc(TableInfo tableInfo,TypeConvertor typeConvertor){
		Map<String,ColumnInfo> columns = tableInfo.getColumns();
		List<JavaFieldGetSet> fieldList=new ArrayList<JavaFieldGetSet>();
		for (ColumnInfo col : columns.values()) {
			fieldList.add(createField(col, typeConvertor));
		}
		StringBuilder sb=new StringBuilder();
		sb.append(" package "+DataSource.getConf().getPoPackage()+";\n\n");
		sb.append("import java.sql.*;\n");
		sb.append("import java.util.*;\n\n");
		sb.append("public class "+StringUtils.first2UpperCase(tableInfo.getTname())+" {\n\n");
		//生成属性列表
		for (JavaFieldGetSet javaFieldGetSet : fieldList) {
			sb.append(javaFieldGetSet.getFieldInfo());
		}
		sb.append("\n\n");
		//生成get方法列表
		for (JavaFieldGetSet javaFieldGetSet : fieldList) {
			sb.append(javaFieldGetSet.getGetInfo());
		}
		//生成set方法列表
		for (JavaFieldGetSet javaFieldGetSet : fieldList) {
			sb.append(javaFieldGetSet.getSetInfo());
		}
		sb.append("}\n");
		return sb.toString();
	}
}
