package org.spring.springboot.controller;

import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(@RequestParam(value = "userName", required = true) String userName,
    		@RequestParam(value = "passWord", required = true) String passWord) {
    	if(!StringUtils.isEmpty(userName)&&!StringUtils.isEmpty(passWord)){
    		User user = userService.findByName(userName);
    		if(user == null){
    			userService.register(userName,passWord);
    			return "{\"state\":\"success\",\"desc\":\"\"}";
    		}
    		return "{\"state\":\"faile\",\"desc\":\"用户名已存在\"}";
    	}
        return "{\"state\":\"faile\",\"desc\":\"用户名或密码不能为空\"}";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "userName", required = true) String userName,
    		@RequestParam(value = "passWord", required = true) String passWord) {
        User user = userService.findByNameAndWord(userName,passWord);
        if(user!=null){
        	return "{\"state\":\"success\",\"desc\":\""+user.getId()+"\"}";
        }
        return "{\"state\":\"faile\",\"desc\":\"用户名或密码有误\"}";
    }
    
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public User userInfo(@RequestParam(value = "userId", required = true) String userId) {
        User user = userService.findById(userId);
//        if(user.getFirstTime()!=null){
//        	user.setFirstDate(user.getFirstTime().toString().substring(0, 10));
//        }
        return user;
    }
    
    @RequestMapping(value = "/updatePassWord", method = RequestMethod.GET)
    public String updatePassWord(@RequestParam(value = "userId", required = true) String userId,
    		@RequestParam(value = "passWord", required = true) String passWord) {
    	userService.updatePassword(userId, passWord);
        return "{\"state\":\"success\",\"desc\":\"\"}";
    }
    
    @RequestMapping(value = "/healthyInfo", method = RequestMethod.GET)
    public String healthyInfo(@RequestParam(value = "userId", required = true) String userId,
    		@RequestParam(value = "sex", required = true) String sex,
    		@RequestParam(value = "height", required = true) String height,
    		@RequestParam(value = "weight", required = true) String weight,
    		@RequestParam(value = "yaowei", required = true) String yaowei,
    		@RequestParam(value = "tunwei", required = true) String tunwei,
    		@RequestParam(value = "xuetang", required = true) String xuetang) {
    	
    	User user = new User(userId, sex, height, weight, yaowei, tunwei, xuetang);
    	
    	userService.updateHW(user);
    	return userService.getHealthyInfo(height, weight, yaowei, tunwei, xuetang);
        
    }
}
