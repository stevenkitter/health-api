package org.spring.springboot.controller;


import org.spring.springboot.Utile.Tools;
import org.spring.springboot.jpa.File;
import org.spring.springboot.jpa.MyPlan;
import org.spring.springboot.models.Response;
import org.spring.springboot.models.SetPlanRequest;
import org.spring.springboot.repository.MyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RestController
public class MyPlanController {

    @Autowired
    private MyPlanRepository myPlanRepository;

    @GetMapping("/myPlan")
    public Response<MyPlan> getMyPlan(@RequestHeader(value="Token") Long token) throws ParseException {
        Optional<MyPlan> optionalMyPlan = myPlanRepository.findByUserId(token);
        if (!optionalMyPlan.isPresent()) {
            return new Response<>(400, "无数据", null);
        }
        MyPlan myPlan = optionalMyPlan.get();
        File file = myPlan.getPlan().getFile();
        file.setFileName(Tools.filePath(file.getFileName()));
        myPlan.getPlan().setFile(file);

        Date started = new Date(myPlan.getUpdated_at().getTime());
        Integer days = daysBetween(started, new Date());
        myPlan.setDays(days);
        float per = days * 100/myPlan.getPlan().getPeriod();
        myPlan.setPercent(per);
        return new Response<>(200, "", myPlan);
    }


    @PostMapping("/plan/set")
    public Response<String> setMyPlan(@RequestHeader(value="Token") Long token, @RequestBody SetPlanRequest request) {
        MyPlan myPlan = myPlanRepository.findByUserId(token).orElse(new MyPlan());
        myPlan.setUserId(token);
        myPlan.setPlanId(request.getPlanId());
        myPlanRepository.save(myPlan);
        return new Response<>(200, "选择成功", "");
    }


    public Integer daysBetween(Date smdate, Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days)) + 1;
    }

}
