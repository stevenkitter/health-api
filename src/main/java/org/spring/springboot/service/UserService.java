package org.spring.springboot.service;

import org.spring.springboot.domain.User;

public interface UserService {
	
	void register(String userName,String passWord);

	User findByNameAndWord(String userName, String passWord);

	User findByName(String userName);

	User findById(String userId);
	
	void updatePassword(String userId, String passWord);
	
	void updateHW(User user);

	String getHealthyInfo(String height, String weight, String yaowei, String tunwei, String xuetang);
}
