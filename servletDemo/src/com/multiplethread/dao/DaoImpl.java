package com.multiplethread.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


/***********************************************************
 * 使用Spring模板的实现类
 * @author 	Simon
 * @time	2014-10-20
 ***********************************************************/
public class DaoImpl extends JdbcTemplate implements Dao {
	
	public DaoImpl(){
		
/*
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:db2://9.115.26.28:50002/FRIDAY:currentSchema=EF;");
		ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		ds.setUsername("db2inst1");
		ds.setPassword("3edcvfr4");
		this.setDataSource(ds);
*/
		
	
		/*BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:db2://9.51.101.218:50000/NAEFRIDB:currentSchema=EF;");
		ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		ds.setUsername("db2inst1");
		ds.setPassword("admin4dst");
		this.setDataSource(ds);*/


		/*BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:db2://9.16.170.55:60000/NAEFRIDB:currentSchema=EF;");
		ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
		ds.setUsername("db2inst1");
		ds.setPassword("gone4now");
		this.setDataSource(ds);*/
		
		BasicDataSource pool = new BasicDataSource();// 连接池
		pool.setUsername("efriday");
		pool.setPassword("efriday123");
		pool.setDriverClassName("com.mysql.jdbc.Driver");
		pool.setUrl("jdbc:mysql://9.51.101.218:3306/nase");
		this.setDataSource(pool);

		/*BasicDataSource pool = new BasicDataSource();// 连接池
		pool.setUsername("nase");
		pool.setPassword("nase123!");
		pool.setDriverClassName("com.mysql.jdbc.Driver");
		pool.setUrl("jdbc:mysql://9.16.170.55:3306/nase");
		this.setDataSource(pool);*/
	}
	
	
	
    /**
     * log4j
     */
    //private static Logger logger = Logger.getLogger(DaoImpl.class);
	
	public int executeQueryForInt(String sql) throws ClassNotFoundException, SQLException{
		//logger.info("query count:"+sql);
		try{
			return super.queryForInt(sql);
		}catch( EmptyResultDataAccessException e ){
			return 0;
		}
	}

	public int executeQueryForInt(String sql, int[] types, Object[] values)throws ClassNotFoundException, SQLException {
		//logger.info("query count:"+sql);
		//this.print(values);
		try{
			return super.queryForInt(sql, values, types);
		}catch( EmptyResultDataAccessException e ){
			return 0;
		}
	}

	public List<Map<String, Object>> executeQueryForList(String sql)throws ClassNotFoundException, SQLException {
		//logger.info("query sql:"+sql);
		try{
			return super.queryForList(sql);
		}catch( EmptyResultDataAccessException e ){
			return new ArrayList<Map<String, Object>>();
		}catch( CannotGetJdbcConnectionException e ){
			return new ArrayList<Map<String, Object>>();
		}
	}
	

	public List<Map<String, Object>> executeQueryForList(String sql,int[] types, Object[] values) throws ClassNotFoundException,SQLException {
		//logger.info("query sql:"+sql);
		//this.print(values);
		try{
			return  super.queryForList(sql, values, types);
		}catch( EmptyResultDataAccessException e ){
			return new ArrayList<Map<String, Object>>();
		}
	}

	public Map<String, Object> executeQueryForMap(String sql)throws ClassNotFoundException, SQLException {
		//logger.info("query one:"+sql);
		try{
			return super.queryForMap(sql);
		}catch( EmptyResultDataAccessException e ){
			return null;
		}
	}

	public Map<String, Object> executeQueryForMap(String sql, int[] types,Object[] values) throws ClassNotFoundException, SQLException {
		//logger.info("query one:"+sql);
		//this.print(values);
		try{
			return super.queryForMap(sql, values, types);
		}catch( EmptyResultDataAccessException e ){
			return null;
		}
	}

	public int executeUpdate(String sql) throws ClassNotFoundException,SQLException {
		//logger.info("update:"+sql);
		return super.update(sql);
	}

	public int executeUpdate(String sql, int[] types, Object[] values)throws ClassNotFoundException, SQLException, FileNotFoundException,IOException {
		//logger.info("update:"+sql);
		//this.print(values);
		return super.update(sql, values, types);
	}

	public long executeUpdateGetKey(final String sql , final int[] types,final Object[] values) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
		//logger.info("update:"+sql);
		//this.print(values);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		super.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				
				//String sql_sms = "insert into  sms(title,content,date_s,form,sffs,by1,by2,by3) values (?,?,'"+dates+"',?,?,?,?,?)"; 
                PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                
                for (int i = 0; i < values.length; i++) {
                	switch (types[i]) {
					case Types.VARCHAR:
						 ps.setString((i+1), (String)values[i]);
						break;
					case Types.INTEGER:
						 ps.setInt((i+1), (Integer)values[i]);
						break;
					case Types.TIMESTAMP:
						 ps.setTimestamp((i+1), (Timestamp)values[i]);
						break;
					case Types.DATE:
						 ps.setDate((i+1), (Date)values[i]);
						break;
					case Types.DOUBLE:
						 ps.setDouble((i+1), (Double)values[i]);
						break;
					default:
						ps.setString((i+1), (String)values[i]);
						break;
					}
				}
                return ps;
			}
		}, keyHolder);
		
		return keyHolder.getKey().longValue(); 
	}
	
	public List<?> executeQueryForList(String sql, Class<?> cla)throws ClassNotFoundException, SQLException {
		//logger.info("query sql:"+sql);
		try{
			return super.query(sql, new BeanPropertyRowMapper( cla ) );
		}catch( EmptyResultDataAccessException e ){
			return new ArrayList<Map<String, Object>>();
		}
	}
	public List<?> executeQueryForList(String sql, int[] types,Object[] values, Class<?> cla) throws ClassNotFoundException,SQLException {
		//logger.info("query sql:"+sql);
		//this.print(values);
		try{
			return super.query(sql, values, types, new BeanPropertyRowMapper( cla ) );
		}catch( EmptyResultDataAccessException e ){
			return new ArrayList<Map<String, Object>>();
		}
	}
	public Object executeQueryForObject(String sql, Class<?> cla)throws ClassNotFoundException, SQLException {
		//logger.info("query one:"+sql);
		try{
			return super.queryForObject(sql, new BeanPropertyRowMapper( cla ) );
		}catch( EmptyResultDataAccessException e ){
			return null;
		}
	}

	public Object executeQueryForObject(String sql, int[] types,Object[] values, Class<?> cla) throws ClassNotFoundException,SQLException {
		//logger.info("query one:"+sql);
		//this.print(values);
		try{
			return super.queryForObject(sql, values, types, new BeanPropertyRowMapper( cla ) );
		}catch( EmptyResultDataAccessException e ){
			return null;
		}
	}
	
	/**
	 * 打印出所有的参数值
	 * @param values
	 */
	private void print(Object[] values){
		/*if( values == null ) return;
			logger.info("params:---------------------");
		for (int i = 0; i < values.length; i++) {
			logger.info( "\t["+i+"]=["+values[i]+"]" );
		}
		logger.info("\t---------------------");
*/	}

}
