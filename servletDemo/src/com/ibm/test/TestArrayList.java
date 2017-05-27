package com.ibm.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestArrayList {
	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		list.add(null);
		list.add(null);
		list.add(null);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		Map map =new HashMap<>();
		map.put(null, null);
		System.out.println(map.get(null));
	}
}
