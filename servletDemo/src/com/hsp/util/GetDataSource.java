package com.hsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataSource {
	
	private static GetDataSource instance=new GetDataSource();
	private List<Connection> list=new ArrayList<Connection>();
	
	private static final int MAX_SIZE=50;
	private static final int MIN_SIZE=10;
	
	private Connection createConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver") ;
			String url="jdbc:mysql://localhost:3306/test1?characterEncoding=utf-8";
			String userName="servlet0519";
			String pwd="123456";
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
	
	public static GetDataSource getInstance(){
		return instance;
	}
	
	public Connection getConn(){
		int lastIndex=list.size()-1;
		Connection conn=list.get(lastIndex);
		list.remove(conn);
		System.out.println(lastIndex);
		return conn;
	}
	
	public void close(Connection conn){
		if(list.size()<MIN_SIZE){
			list.add(conn);
		}else{
			if(null!=conn){
				try {
					conn.close();
				} catch (SQLException e) {//
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		//GetDataSource.getConn();
		for (int i = 0; i < 100; i++) {
			Connection conn=GetDataSource.getInstance().getConn();
			if(conn!=null){
				GetDataSource.getInstance().close(conn);
			}
		}
		
		/*try {
			Class.forName("com.mysql.jdbc.Driver") ;
			String url="jdbc:mysql://localhost:3306/test1";
			String userName="servlet0519";
			String pwd="123456";
			Connection con=DriverManager.getConnection(url, userName, pwd);
			System.out.println(con);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}

}
