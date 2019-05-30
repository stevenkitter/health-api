package org.spring.springboot.service.impl;

import java.util.List;

import org.spring.springboot.dao.ArticleDao;
import org.spring.springboot.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl {
	
	@Autowired
	public ArticleDao articleDao;
	
	public List<Article> findByTitle(String title) {
		return articleDao.findByTitle(title);
	}

	public List<Article> findAll() {
		return articleDao.findAll();
	}

	public Article getContent(String id) {
		return articleDao.getContent(id);
	}
}
