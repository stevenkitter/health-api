package org.spring.springboot.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spring.springboot.domain.StreamEntity;
import org.spring.springboot.service.impl.StreamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StreamController {
	@Autowired
	private StreamServiceImpl streamService;
	/**
	 * 插入数据
	 * @param type
	 * @param inOut
	 * @param amount
	 * @param desc
	 * @return
	 */
	@RequestMapping("/insert")
    public String insert(@RequestParam(value = "type", required = true) String type,
    		@RequestParam(value = "inOut", required = true) String inOut,
    		@RequestParam(value = "amount", required = true) String amount,
    		@RequestParam(value = "desc") String desc,
    		@RequestParam(value = "userId") String userId){
		StreamEntity stream = new StreamEntity();
		stream.setType(type);
		stream.setInOut(inOut);
		stream.setAmount(amount);
		stream.setTime(new Timestamp(System.currentTimeMillis()));
		stream.setDesc(desc);
		stream.setUserId(userId);
		streamService.insert(stream);
		return "success insert";
    }
	/**
	 * 查询当天流水
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/index")
    public Map<String, Object> index(@RequestParam(value = "userId") String userId,
    		@RequestParam(value = "dateTime") String dateTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(dateTime);
		List<StreamEntity> streamList =  streamService.currDay(userId,new Timestamp(date.getTime()));
		BigDecimal in = new BigDecimal(0);
		BigDecimal out = new BigDecimal(0);
		for(StreamEntity stream:streamList){
			if("in".equals(stream.getInOut())){
				in = in.add(new BigDecimal(stream.getAmount()));
				stream.setAmount("+"+stream.getAmount());
			}else if("out".equals(stream.getInOut())){
				out = out.add(new BigDecimal(stream.getAmount()));
				stream.setAmount("-"+stream.getAmount());
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("in", in.toString());
		map.put("out", out.toString());
		map.put("streamList", streamList);
		return map;
		
    }
	
	/**
	 * 按条件查询流水
	 * @return
	 */
	@RequestMapping("/search")
    public List<StreamEntity> Search(
    		@RequestParam(value = "inOut") String inOut,
    		@RequestParam(value = "field") String field,
    		@RequestParam(value = "userId") String userId){
		return streamService.Search( inOut, field, userId);
		
    }
	
}
