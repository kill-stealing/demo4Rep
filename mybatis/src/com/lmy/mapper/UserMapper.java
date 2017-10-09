package com.lmy.mapper;

import java.util.List;

import com.lmy.pojo.QueryVo;
import com.lmy.pojo.User;

public interface UserMapper {
	
	User findUserById(int id);
	
	List<User> findUserByVo(QueryVo vo);
}
