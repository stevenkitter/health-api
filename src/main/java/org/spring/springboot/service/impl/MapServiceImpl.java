package org.spring.springboot.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.spring.springboot.Utile.HttpUtile;
import org.springframework.stereotype.Service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;


@Service
public class MapServiceImpl {
	private static final String url = "https://apis.map.qq.com/ws/place/v1/search";
	public Map<String, JSONArray> findNear(String longitude, String latitude){
		
		String[] keyword = {"药店","健身房","医院"};
		String[] keywordEN = {"yd","jsf","yy"};
		Map<String, JSONArray> map = new HashMap<>();
		for(int i=0; i<keyword.length; i++){
			String boundary = "nearby("+latitude+","+longitude+",1000)";
			//String filter = "";
			String orderby = "_distance";
			String page_size = "5";
			String page_index = "1";
			String key = "K7TBZ-IM4C6-P44SF-M6DGJ-CSZS5-WZF5G";
			//String output = "";
			//String callback = "";
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("keyword", keyword[i]);
			params.put("boundary", boundary);
			params.put("page_size", page_size);
			params.put("page_index", page_index);
			params.put("key", key);
			params.put("orderby", orderby);
			
			String callString = "";
			try {
				callString = HttpUtile.get(url, params);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			JSONObject json = new JSONObject(callString);
			map.put(keywordEN[i], (JSONArray)json.get("data"));
		}
		return map;
	}
}
