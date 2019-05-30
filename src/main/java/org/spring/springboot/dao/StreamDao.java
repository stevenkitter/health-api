package org.spring.springboot.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.StreamEntity;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StreamDao {
	public void insert(StreamEntity stream);

	public List<StreamEntity> currDay(@Param("userId")String userId,
			@Param("dateTime")Timestamp dateTime);

	public List<StreamEntity> Search(
			@Param("inOut")String inOut, 
			@Param("field")String field, 
			@Param("userId")String userId,
			@Param("value")Timestamp value);
}
