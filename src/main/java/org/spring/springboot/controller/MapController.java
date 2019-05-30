package org.spring.springboot.controller;

import java.util.Map;

import org.spring.springboot.service.impl.MapServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.json.JSONArray;

@RestController
public class MapController {
	@Autowired
	private MapServiceImpl mapServiceImpl;
	
	@RequestMapping(value = "/near", method = RequestMethod.GET)
    public Map<String, JSONArray> findNear(@RequestParam(value = "longitude") String longitude,
    		@RequestParam(value = "latitude") String latitude) {
         Map<String, JSONArray> map = mapServiceImpl.findNear(longitude, latitude);
        
		return map;
    }
}
