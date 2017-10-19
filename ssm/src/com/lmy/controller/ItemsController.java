package com.lmy.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lmy.pojo.Items;
import com.lmy.pojo.User;
import com.lmy.service.ItemsService;
import com.lmy.vo.QueryVO;

@Controller
public class ItemsController {
	
	@Autowired
	private ItemsService itemsService;
	
	@RequestMapping("/items")
	public ModelAndView getItemsById(){
		List<Items> items=new ArrayList<>();
		items=itemsService.getItemsByName("");
		ModelAndView m=new ModelAndView();
		m.addObject("itemList", items);
		m.setViewName("itemList1");
		return m;
	}
	
	/*@RequestMapping("/itemEdit")
	public ModelAndView editItemsById(HttpServletRequest request){
		int id=Integer.parseInt(request.getParameter("id"));
		Items items=itemsService.getItemsById(id);
		ModelAndView m=new ModelAndView();
		m.addObject("item", items);
		m.setViewName("editItem");
		return m;
	}*/
	
	@RequestMapping("/itemEdit")
	public String editItemsById(HttpServletRequest request,Model m){
		int id=Integer.parseInt(request.getParameter("id"));
		Items items=itemsService.getItemsById(id);
		m.addAttribute("item", items);
		return "editItem";
	}
	
	//springMVC 可以直接接收基本数据类型，包括string.springMVC可以帮你自动转换类型。
	@RequestMapping("/updateitem")
	public String updateItem(Items items){
		itemsService.updateItems(items);
		return "success";
	}
	
	@RequestMapping("/testGetRequest")
	public String testGetRequest(String name){
		try {
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(name);
		return "itemList";
	}
	
	@RequestMapping(value="/testRequestBody",method=RequestMethod.POST)
	public String testRequestBody(@RequestBody List<User> lists){
		System.out.println(lists);
		return "itemList";
	}
	
	@RequestMapping("/delAll")
	public String delAll(Integer[] ids){
		System.out.println(ids);
		return  "";
	}
	
	@RequestMapping("/updateAll")
	public String updateALl(QueryVO vo){
		System.out.println(vo.getItemsList());
		return "";
	}
}
