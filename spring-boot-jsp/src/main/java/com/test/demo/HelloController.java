package com.test.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/helloJsp")
	public String helloJsp(Map<String,Object>  map){
		map.put("name", "Andy");
		return "helloJsp";
	}
}
