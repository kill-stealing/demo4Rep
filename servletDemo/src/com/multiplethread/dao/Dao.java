package com.multiplethread.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/************************************
 * 数据库连接对象
 * @author 	Simon
 * @time	2014-10-20
 ************************************/
public interface Dao {
	
	public DataSource getDataSource();
	/**
     * 根据sql查询列表数据(查询一条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Object executeQueryForObject(String sql,Class<?> cla) throws ClassNotFoundException, SQLException;
	/**
	 * 根据sql查询列表数据(查询一条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Object executeQueryForObject(String sql , int[] types,Object[] values,Class<?> cla) throws ClassNotFoundException, SQLException ;
	/**
     * 根据sql查询列表数据(查询多条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<?> executeQueryForList(String sql,Class<?> cla) throws ClassNotFoundException, SQLException;
	/**
	 * 根据sql查询列表数据(查询多条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<?> executeQueryForList(String sql , int[] types,Object[] values,Class<?> cla) throws ClassNotFoundException, SQLException ;
    
    
	/**
     * 根据sql查询列表数据(查询一条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Map<String, Object> executeQueryForMap(String sql) throws ClassNotFoundException, SQLException;

    
	/**
	 * 根据sql查询列表数据(查询一条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Map<String, Object> executeQueryForMap(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException ;
    
	/**
     * 根据sql查询列表数据(查询多条)，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<Map<String, Object>> executeQueryForList(String sql) throws ClassNotFoundException, SQLException;
    
	/**
	 * 根据sql查询列表数据(查询多条)，支持预编译的方式
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Map<String, Object>> executeQueryForList(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException ;

	
    /**
     * 执行 增、删、改、等的操作，不支持预编译的方式
     * @param sql
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
	public int executeUpdate(String sql) throws ClassNotFoundException, SQLException ;
	/**
	 * 预编译sql操作，   支持insert ， update  ， delete  语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public int executeUpdate(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
	
	/**
	 * 预编译sql操作，   支持insert ， update  ,返回自增主键值
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public long executeUpdateGetKey(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException ;
	
	/**
	 * 预编译sql操作，   支持  select 语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForInt(String sql ) throws ClassNotFoundException, SQLException ;
	/**
	 * 预编译sql操作，   支持  select 语句
	 * @param sql
	 * @param types
	 * @param ObjectValues
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public int executeQueryForInt(String sql , int[] types,Object[] values) throws ClassNotFoundException, SQLException ;

	
	/**
	 * 更改连库的数据源
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource);
	
	
}
