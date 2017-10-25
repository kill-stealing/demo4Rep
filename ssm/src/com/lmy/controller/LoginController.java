package com.lmy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	//跳转到登录页面
	@RequestMapping("/login")
	public String login() throws Exception{
		
		return "login";
	}
	
	@RequestMapping("/submit")
	public String submit(String username,String pwd,HttpServletRequest request)throws Exception{
		HttpSession session=request.getSession();
		if(username!=null&&!"".equals(username)&&pwd!=null&&!"".equals(pwd)){
			if(username.equals("abc")&&pwd.equals("abc")){
				session.setAttribute("username", username);
				session.setAttribute("pwd", pwd);
				return "redirect:/items";
			}
		}
		return "forward:/login/login";
		
	}
}
