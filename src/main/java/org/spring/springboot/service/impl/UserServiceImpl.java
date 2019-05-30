package org.spring.springboot.service.impl;

import java.util.List;

import org.spring.springboot.Utile.HealthyUtile;
import org.spring.springboot.dao.UserDao;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public void register(String userName, String passWord) {
		userDao.insert(userName, passWord);
		
	}

	@Override
	public User findByNameAndWord(String userName, String passWord) {
		User user = userDao.findByNameAndWord(userName, passWord);
		return user;
	}

	@Override
	public User findByName(String userName) {
		return userDao.findByName(userName);
	}

	@Override
	public User findById(String userId) {
		return userDao.findById(userId);
	}

	@Override
	public void updatePassword(String userId, String passWord) {
		userDao.updatePassword(userId, passWord);;
	}

	@Override
	public void updateHW(User user) {
		userDao.updateHW(user);
	}

	@Override
	public String getHealthyInfo(String height, String weight, String yaowei, String tunwei, String xuetang) {
		
		return HealthyUtile.getHealthyInfo(height, weight, yaowei, tunwei, xuetang);
	}

}
