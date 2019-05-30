package org.spring.springboot.task;

import java.time.LocalDateTime;

import org.spring.springboot.service.impl.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class PlanTask {
	
	@Autowired
	public PlanServiceImpl planService;
	//修改计划状态(每天凌晨一点更新过期任务状态)
    //@Scheduled(cron = "0 0 1 * * ?")
    
    @Scheduled(fixedRate=5000)
    private void configureTasks() {
    	planService.updateState();
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
