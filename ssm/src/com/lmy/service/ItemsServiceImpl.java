package com.lmy.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lmy.dao.ItemsMapper;
import com.lmy.exception.CustomException;
import com.lmy.exception.CustomException404;
import com.lmy.pojo.Items;
import com.lmy.pojo.ItemsExample;
import com.lmy.pojo.ItemsExample.Criteria;

@Service(value="service")
public class ItemsServiceImpl implements ItemsService{
	@Resource
	private ItemsMapper itemsMapper;
	
	@Override
	public Items getItemsById(int id){
		Items items=itemsMapper.selectByPrimaryKey(id);
		return items;
	}

	@Override
	public List<Items> getItemsByName(String name) {
		ItemsExample ex=new ItemsExample();
		Criteria cr=ex.createCriteria();
		List<Integer> list1=new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		cr.andIdIn(list1);
		List<Items> items=itemsMapper.selectByExample(ex);
		if(items.isEmpty()){
			try {
				throw new CustomException("there is no results",11);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return items;
	}

	@Override
	public void updateItems(Items items) {
		itemsMapper.updateByPrimaryKeyWithBLOBs(items);
	}

	//抛出自定义异常
	@Override
	public List<Items> getItemsByName1(String name){
		List<Items> items=new ArrayList<>();
		throw new CustomException("Hello",11);
	}
}
