package com.lmy.jdbc;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lmy.pojo.Items;

@Repository
public class ItemsDaoImpl implements ItemsDao{

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public void insertItems(Items items) {
		String sql="insert into items (name,price,detail,createtime) values("
				+ " 'aaa',1.1,'aaa',now())";
		jdbcTemplate.execute(sql);
//		throw new CustomException("aaaa");
	}

}
