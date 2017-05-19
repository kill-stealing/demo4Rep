package com.hsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hsp.entity.User;
import com.hsp.util.GetDataSource;

public class DaoImpl implements Dao{

	@Override
	public User getUser(User user) {
		User user1=new User();
		try {
			Connection conn=GetDataSource.getConn();
			String sql="select * from atest_user where userName=? and pwd=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=null;
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			rs=ps.executeQuery();
			while(rs.next()){
				user1.setUserId(rs.getInt("user_id"));
				user1.setUserName(rs.getString("user_name"));
				user1.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user1;
		
	}

	@Override
	public int ifExit(User user) {
		int flag=0;
		try {
			Connection conn=GetDataSource.getConn();
			String sql="select count(*) from atest_user where userName=? and pwd=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=null;
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			rs=ps.executeQuery();
			while(rs.next()){
				flag=rs.getInt("count(*)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}
	
	

}
