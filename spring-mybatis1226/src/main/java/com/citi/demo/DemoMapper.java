package com.citi.demo;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface DemoMapper {
	
	@Select(value="select * from Cat where cat_name =#{name} order by id")
	List<Cat> likeName(String name);
	
	@Insert(value="insert into cat(cat_name,cat_age) values (#{catName},#{catAge})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") 
	void save(Cat cat);
}
