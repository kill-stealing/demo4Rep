package com.ibm.sorm;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnPool {
	private static List<Connection> pool;
	private static final int POOL_MAX_SIZE=100;
	private static final int POOL_MIN_SIZE=10;
	
	public DBConnPool(){
		initPool();
	}
	
	public void initPool(){
		if(pool==null){
			pool=new ArrayList<Connection>();
		}
		while(pool.size()<DBConnPool.POOL_MAX_SIZE){
			pool.add(DataSource.getConnection());
			System.out.println("初始化连接池："+pool.size());
		}
	}
	
	public synchronized Connection getConnection(){
		int lastIndex=pool.size()-1;
		Connection conn=pool.get(lastIndex);
		pool.remove(conn);
		return conn;
	}
	
	public synchronized void close(Connection conn){
		if(pool.size()<POOL_MIN_SIZE){
			pool.add(conn);
		}else{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
