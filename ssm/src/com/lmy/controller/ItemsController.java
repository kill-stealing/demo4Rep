package com.lmy.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.mail.FetchProfile.Item;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lmy.exception.CustomException;
import com.lmy.pojo.Items;
import com.lmy.pojo.User;
import com.lmy.service.ItemsService;
import com.lmy.test.GetApplicationContext;
import com.lmy.vo.QueryVO;

@Controller
public class ItemsController {
	
	@Autowired
	@Qualifier("service")
	private ItemsService itemsService;
	
	@RequestMapping("/items")
	public ModelAndView getItemsById(String name) throws CustomException{
		System.out.println("start call getItemsById");
		List<Items> items=new ArrayList<>();
		items=itemsService.getItemsByName(name);
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
	
	@RequestMapping("/itemEdit/{id}")
	public String editItemsById(@PathVariable("id")Integer id,Model m){
		Items items=itemsService.getItemsById(id);
		m.addAttribute("item", items);
		return "editItem";
	}
	
	//springMVC 可以直接接收基本数据类型，包括string.springMVC可以帮你自动转换类型。
	@RequestMapping("/updateitem")
	public String updateItem(MultipartFile pictureFile, Items items,Model model)throws Exception{
		String name=pictureFile.getOriginalFilename();
		String newFileName=UUID.randomUUID().toString()+name.substring(name.lastIndexOf("."));
		pictureFile.transferTo(new File("C:/img/"+newFileName));
		items.setPic(newFileName);
		itemsService.updateItems(items);
//		model.addAttribute("id", items.getId());
		return "redirect:itemEdit/"+items.getId();
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
	@ResponseBody
	public List<User> testRequestBody(@RequestBody List<User> lists){
		System.out.println(lists);
		return lists;
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
	
	@RequestMapping("/insertItems")
	public void insertItems(Items items,HttpServletResponse response) throws IOException{
		
		PrintWriter out=response.getWriter();
		itemsService.insertItems(items);
		out.println("success");
	}
	
	@RequestMapping("/test/{id}")
	public String getString(@PathVariable int id){
		System.out.println(id);
		ItemsController test = GetApplicationContext
				.getApplicationContext().getBean(ItemsController.class);
		System.out.println(test.getString(id));
		return "test";
	}
}
