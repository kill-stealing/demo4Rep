package com.ibm.sorm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.ibm.sorm.bean.Configuration;

public class DataSource {

	private static Configuration conf;

	static {
		Properties pro = new Properties();
		try {
			pro.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		conf = new Configuration(pro.getProperty("driver"),
				pro.getProperty("url"), pro.getProperty("user"),
				pro.getProperty("pwd"),pro.getProperty("usingDB"),pro.getProperty("srcPath"),pro.getProperty("poPackage"));

	}

	public static Connection getConnection(){
		try {
			Class.forName(conf.getDriver());
			Connection conn=DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPwd());
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Configuration getConf(){
		return conf;
	}
}
