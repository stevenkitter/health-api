package org.spring.springboot.controller;

import java.util.List;

import org.spring.springboot.domain.Article;
import org.spring.springboot.service.impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {
	@Autowired
	public ArticleServiceImpl articleService;
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
    public List<Article> findArticle(@RequestParam(value = "title") String title) {
        return articleService.findByTitle(title);
    }
	
	@RequestMapping(value = "/allArticle", method = RequestMethod.GET)
    public List<Article> allArticle() {
        return articleService.findAll();
    }
	
	
	@RequestMapping(value = "/getContent", method = RequestMethod.GET)
    public Article getContent(@RequestParam(value = "id") String id) {
        return articleService.getContent(id);
    }
}
