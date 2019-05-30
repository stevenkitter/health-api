package org.spring.springboot.controller;

import java.sql.Date;
import java.util.List;

import org.spring.springboot.Utile.TimeUtile;
import org.spring.springboot.domain.Plan;
import org.spring.springboot.service.impl.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {
	
	@Autowired
	public PlanServiceImpl planService;
	
	@RequestMapping(value = "/planList", method = RequestMethod.GET)
    public List<Plan> findPlan(@RequestParam(value = "userId", required = true) String userId) {
		List<Plan> planList = planService.findByUserId(userId);
        return planList;
    }
	
	@RequestMapping(value = "/updatePlan", method = RequestMethod.GET)
    public void updatePlan(@RequestParam(value = "id", required = true) String id,
    		@RequestParam(value = "state", required = true) String state) {
		if("3".equals(state)){
			planService.delete(id);
		}else{
			
			planService.update(id, state);
		}
    }
	
	
	@RequestMapping(value = "/insertPlan", method = RequestMethod.GET)
    public String insertPlan(@RequestParam(value = "userId", required = true) String userId,
    		@RequestParam(value = "planName", required = true) String planName,
    		@RequestParam(value = "startTime", required = true) String startTime,
    		@RequestParam(value = "endTime", required = true) String endTime,
    		//@RequestParam(value = "createTime", required = true) String createTime,
    		//@RequestParam(value = "state", required = true) String state,
    		@RequestParam(value = "desc", required = false) String desc) {
		Plan plan = new Plan();
		plan.setUserId(userId);
		plan.setPlanName(planName);
		plan.setStartTime(TimeUtile.get(startTime));
		plan.setEndTime(TimeUtile.get(endTime));
		plan.setCreateTime(new Date(System.currentTimeMillis()));
		plan.setState("0");
		plan.setDesc(desc);
		planService.insertPlan(plan);
		return "success insert";
    }
}
