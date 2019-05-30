package org.spring.springboot.service.impl;

import java.util.List;

import org.spring.springboot.dao.ArticleDao;
import org.spring.springboot.dao.PlanDao;
import org.spring.springboot.domain.Article;
import org.spring.springboot.domain.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl {
	@Autowired
	public PlanDao planDao;
	
	public List<Plan> findByUserId(String userId) {
		return planDao.findByUserId(userId);
	}

	public void insertPlan(Plan plan) {
		planDao.insertPlan(plan);
		
	}
	//更新超时的
	public void updateState() {
		planDao.updateState();
		
	}
	
	//自定义跟新
	public void update(String id, String state) {
		planDao.update(id,state);
			
	}

	public void delete(String id) {
		planDao.delete(id);
		
	}
}
