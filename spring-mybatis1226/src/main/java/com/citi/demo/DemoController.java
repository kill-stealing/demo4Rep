package com.citi.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

@RestController
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping("/likeName")
	public List<Cat> likeName(String name){
		PageHelper.startPage(1,10);
		return demoService.likeName(name);
	}
	
	@RequestMapping("/save")
	public Cat save(String name,int age){
		Cat cat =new Cat();
		cat.setCatName(name);
		cat.setCatAge(age);
		return demoService.save(cat);
	}
}
