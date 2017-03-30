package com.ibm.sorm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ibm.sorm.bean.ColumnInfo;
import com.ibm.sorm.bean.TableInfo;
import com.ibm.sorm.utils.FileUtils;
import com.ibm.sorm.utils.StringUtils;

public class TableContext {

	/**
	 * 表名为key，表信息对象为value
	 */
	public static Map<String, TableInfo> tables = new HashMap<String, TableInfo>();

	/**
	 * 将po的class对象和表信息对象关联起来，便于重用！
	 */
	public static Map<Class, TableInfo> poClassTableMap = new HashMap<Class, TableInfo>();

	private TableContext() {

	}

	static {
		Connection conn = DataSource.getConnection();
		try {
			DatabaseMetaData dmData = conn.getMetaData();
			ResultSet rs = dmData.getTables(null, "%", "%",
					new String[] { "Table" });
			while (rs.next()) {
				String tableName = (String) rs.getObject("TABLE_NAME");
				TableInfo t = new TableInfo(tableName,
						new ArrayList<ColumnInfo>(),
						new HashMap<String, ColumnInfo>());
				tables.put(tableName, t);

				ResultSet rscolumn = dmData.getColumns(null, "%", tableName,
						"%");
				while (rscolumn.next()) {
					ColumnInfo col = new ColumnInfo(
							rscolumn.getString("COLUMN_NAME"),
							rscolumn.getString("TYPE_NAME"), 0);
					t.getColumns().put(rscolumn.getString("COLUMN_NAME"), col);
				}
				ResultSet rscolumn2 = dmData.getPrimaryKeys(null, "%", tableName);
				while(rscolumn2.next()){
					ColumnInfo col2=t.getColumns().get(rscolumn2.getString("COLUMN_NAME"));
					col2.setKeyType(1);
					t.getPriKeys().add(col2);
				}
				
				if(t.getPriKeys().size()>0){
					t.setOnlyPriKey(t.getPriKeys().get(0));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		updatePoStructure();
//		
		loadTables();
	}
	
	public static void loadTables(){
		for (TableInfo tableInfo : tables.values()) {
			String classPath=DataSource.getConf().getPoPackage();
			try {
				Class c=Class.forName(classPath+"."+StringUtils.first2UpperCase(tableInfo.getTname()));
				poClassTableMap.put(c, tableInfo);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void updatePoStructure(){
		int i=0;
		for (TableInfo tableInfo : tables.values()) {
			String srcPath=DataSource.getConf().getSrcPath()+"\\";
			String packagePath=DataSource.getConf().getPoPackage().replace(".", "/");
			File file=new File(srcPath+packagePath);
			if(!file.exists()){
				file.mkdirs();
			}
			file=new File(file.getAbsoluteFile()+"/"+StringUtils.first2UpperCase(tableInfo.getTname())+".java");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			BufferedWriter bw=null;
			try {
				bw=new BufferedWriter(new FileWriter(file));
				bw.write(FileUtils.createJavaSrc(tableInfo, new MySqlTypeConvertor()));
				bw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				FileUtils.closeAll(bw);
			}
			i++;
		}
	}
	
	public static void main(String[] args) {
//		Map<String, TableInfo> tables=TableContext.tables;
//		System.out.println(tables);
//		File file=new File("C:Users\\IBM_ADMIN\\workspace\\SormTest\\src\\com\\bjsxt\\po\\Nase_posts");
//		String srcPath=DataSource.getConf().getSrcPath()+"\\";
//		String packagePath=DataSource.getConf().getPoPackage().replace(".", "/");
//		File file=new File(srcPath+packagePath);
//		if(!file.exists()){
//			file.mkdirs();
//		}
		TableContext.updatePoStructure();
	}
}
