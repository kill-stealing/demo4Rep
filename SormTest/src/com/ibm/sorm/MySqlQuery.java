package com.ibm.sorm;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import com.bjsxt.po.Nase_postmeta;
import com.ibm.sorm.bean.ColumnInfo;
import com.ibm.sorm.bean.TableInfo;
import com.ibm.sorm.utils.StringUtils;

public class MySqlQuery implements Query{

	@Override
	public void insert(Object obj) {
		//obj-->表中。             insert into 表名  (id,uname,pwd) values (?,?,?)
		Class c=obj.getClass();
		try {
			TableInfo tableInfo=TableContext.poClassTableMap.get(c);
			StringBuilder sb=new StringBuilder();
			sb.append(" insert into "+tableInfo.getTname()+" (");
			Map<String, ColumnInfo> map=tableInfo.getColumns();
			int countColumn=0;
			for (ColumnInfo col : map.values()) {
				if(countColumn==0){
					sb.append(col.getName());
				}else{
					sb.append(","+col.getName());
				}
				countColumn++;
			}
			sb.append(") values (");
			for(int i=0;i<countColumn;i++){
				if(i==0){
					sb.append(" ?");
				}else{
					sb.append(",?");
				}
			}
			sb.append(" )");
			
			Connection conn=DataSource.getConnection();
			PreparedStatement ps=conn.prepareStatement(sb.toString());
			countColumn=1;
			for (ColumnInfo col : map.values()) {
				Method m=c.getDeclaredMethod("get"+StringUtils.first2UpperCase(col.getName()), null);
				ps.setObject(countColumn,m.invoke(obj, null));
				countColumn++;
			}
			int result=ps.executeUpdate();
			System.out.println("result "+result);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Map<String, TableInfo> tables=TableContext.tables;
		Nase_postmeta postmeta=new Nase_postmeta();
		postmeta.setMeta_id("1");
		MySqlQuery query=new MySqlQuery();
		query.insert(postmeta);
	}

}
