package org.spring.springboot.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.spring.springboot.Utile.TimeUtile;
import org.spring.springboot.dao.StreamDao;
import org.spring.springboot.domain.StreamEntity;
import org.spring.springboot.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StreamServiceImpl implements StreamService{
	@Autowired
	private StreamDao streamDao;
	
	public void insert(StreamEntity stream){
		
		streamDao.insert(stream);
	}
	
	public List<StreamEntity> currDay(String userId, Timestamp dateTime){
		
		return streamDao.currDay(userId,dateTime);
	}

	public List<StreamEntity> Search( String inOut, String field, String userId) {
		
		if("week".equals(field)){
			Timestamp one = TimeUtile.getWeekStartDate();
			List<StreamEntity> weekList = new ArrayList<StreamEntity>();
			for(int i=0;i<7;i++){
				List<StreamEntity> list = streamDao.Search(inOut, field, userId, one);
				System.out.println(one);
				StreamEntity streamEntity = new StreamEntity();
				BigDecimal amount = new BigDecimal("0");
				for(StreamEntity stream:list){
					amount = new BigDecimal(stream.getAmount()).add(amount);
				}
				streamEntity.setAmount(amount.toString());
				streamEntity.setInOut(inOut);
				streamEntity.setTimeStr(one.toString());
				weekList.add(streamEntity);
				one = TimeUtile.addOneDate(one);
			}
			return weekList;
		}else if("month".equals(field)){
			Timestamp one = TimeUtile.getMonthStartDate();
			List<StreamEntity> weekList = new ArrayList<StreamEntity>();
			for(int i=0;i<TimeUtile.getDayOfMonth();i++){
				List<StreamEntity> list = streamDao.Search(inOut, field, userId, one);
				System.out.println(one);
				StreamEntity streamEntity = new StreamEntity();
				BigDecimal amount = new BigDecimal("0");
				for(StreamEntity stream:list){
					amount = new BigDecimal(stream.getAmount()).add(amount);
				}
				streamEntity.setAmount(amount.toString());
				streamEntity.setInOut(inOut);
				streamEntity.setTime(one);
				weekList.add(streamEntity);
				one = TimeUtile.addOneDate(one);
			}
			return weekList;
		}
		
		
		return null;
	}
}
