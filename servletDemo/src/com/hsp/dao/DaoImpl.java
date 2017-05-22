package com.hsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hsp.entity.User;
import com.hsp.util.CloseUtil;
import com.hsp.util.GetDataSource;

public class DaoImpl implements Dao{

	@Override
	public User getUser(User user) {
		User user1=new User();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn=GetDataSource.getInstance().getConn();
			String sql="select * from atest_user where user_name=? and pwd=?";
			ps=conn.prepareStatement(sql);
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
		}finally{
			CloseUtil.close(rs, ps, conn);
		}
		return user1;
		
	}

	@Override
	public int ifExit(User user) {
		int flag=0;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn=GetDataSource.getInstance().getConn();
			String sql="select count(*) count from atest_user where user_name=? and pwd=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			rs=ps.executeQuery();
			while(rs.next()){
				flag=rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			CloseUtil.close(rs, ps, conn);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		DaoImpl dao=new DaoImpl();
		User u=new User("aaa", "bbb");
		int i=dao.ifExit();
		System.out.println(i);
		List<User> list=dao.getUser();
		System.out.println(list.toString());
		dao.doInsert();
		
	}
	
	@Override
	public List<User> getUser() {
		List<User> list=new ArrayList<User>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn=GetDataSource.getInstance().getConn();
			String sql="select * from atest_user";
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPwd(rs.getString("pwd"));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			CloseUtil.close(rs, ps, conn);
		}
		return list;
	}
	
	@Override
	public List<User> getUser(int pageNum,int pageSize) {
		List<User> list=new ArrayList<User>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=GetDataSource.getInstance().getConn();
			String sql="select * from atest_user limit ?,?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (pageNum-1)*pageSize);
			ps.setInt(2, pageSize);
			rs=ps.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPwd(rs.getString("pwd"));
				list.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			CloseUtil.close(rs, ps, conn);
		}
		
		return list;
	}
	
	public int ifExit() {
		int flag=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=GetDataSource.getInstance().getConn();
			String sql="select count(*) count from atest_user where user_name=? and pwd=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, "我是你爸爸");
			ps.setString(2, "123456");
			rs=ps.executeQuery();
			while(rs.next()){
				flag=rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			CloseUtil.close(rs, ps, conn);
		}
		return flag;
	}
	
	public int doInsert(){
		int i=0;
		try {
			Connection conn=GetDataSource.getInstance().getConn();
			String sql="insert into atest_user (user_id,user_name,pwd) values(?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=null;
			ps.setInt(1,222);
			ps.setString(2, "我是你爸爸1");
			ps.setString(3, "123456");
			i=ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return i;
	}

}
