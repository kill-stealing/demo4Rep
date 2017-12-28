package com.citi.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {
	@Autowired
	private DemoMapper demoMapper;
	
	public List<Cat> likeName(String name){
		return demoMapper.likeName(name);
	}
	
	@Transactional
	public Cat save(Cat cat){
		demoMapper.save(cat);
		return cat;
	}
}
