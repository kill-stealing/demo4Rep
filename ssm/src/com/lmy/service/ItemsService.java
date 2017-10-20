package com.lmy.service;

import java.util.List;

import com.lmy.exception.CustomException;
import com.lmy.pojo.Items;

public interface ItemsService {
	Items getItemsById(int id);
	List<Items> getItemsByName(String name);
	List<Items> getItemsByName1(String name);
	void updateItems(Items items);
	
	void insertItems(Items items);
}
