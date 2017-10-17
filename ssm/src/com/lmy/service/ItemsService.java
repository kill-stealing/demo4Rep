package com.lmy.service;

import java.util.List;

import com.lmy.pojo.Items;

public interface ItemsService {
	Items getItemsById(int id);
	List<Items> getItemsByName(String name);
	void updateItems(Items items);
}
