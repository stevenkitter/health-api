package org.spring.springboot.service;

import java.sql.Timestamp;
import java.util.List;

import org.spring.springboot.domain.StreamEntity;

public interface StreamService {
	
	void insert(StreamEntity stream);
	
	List<StreamEntity> currDay(String userId, Timestamp dateTime);
	
	List<StreamEntity> Search(String inOut, String field, String userId);
}
