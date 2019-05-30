package org.spring.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Article;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ArticleDao {
	
	public List<Article> findByTitle(@Param("title")String title);

	public List<Article> findAll();

	public Article getContent(@Param("id")String id);
	
}
