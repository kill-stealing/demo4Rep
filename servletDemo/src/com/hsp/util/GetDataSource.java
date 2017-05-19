package com.hsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataSource {
	private static List<Connection> list=new ArrayList<Connection>();
	
	private static final int MAX_SIZE=100;
	private static final int MIN_SIZE=10;
	
	private static Connection createConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			String url="jdbc:mysql://lexbz1218.lexington.ibm.com:3306/nase";
			String userName="efriday";
			String pwd="efriday123";
			Connection con=DriverManager.getConnection(url, userName, pwd);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	private GetDataSource() {
		for (int i = 0; i < MAX_SIZE; i++) {
			Connection con=createConn();
			list.add(con);
		}
	}
	
	public static Connection getConn(){
		int lastIndex=list.size()-1;
		Connection conn=null;
		conn=list.get(lastIndex);
		list.remove(conn);
		return conn;
	}
	
	public static void close(Connection conn){
		if(list.size()<MIN_SIZE){
			list.add(conn);
		}else{
			if(null!=conn){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
