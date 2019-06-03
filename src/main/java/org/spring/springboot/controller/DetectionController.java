package org.spring.springboot.controller;

import org.spring.springboot.jpa.Detection;
import org.spring.springboot.jpa.Plan;
import org.spring.springboot.models.DetectionRequest;

import org.spring.springboot.models.Response;
import org.spring.springboot.repository.DetectionRepository;
import org.spring.springboot.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class DetectionController {

    @Autowired
    private DetectionRepository detectionRepository;

    @Resource
    private PlanRepository planRepository;

    @PostMapping("/detection")
    public Response<String> addDetection(@RequestHeader(value="Token") Long token, @RequestBody DetectionRequest request) {
        Detection detection = new Detection();
        detection.setHeight(request.getHeight());
        detection.setWeight(request.getWeight());
        detection.setWaist(request.getWaist());
        detection.setBlood(request.getBlood());
        detection.setBust(request.getBust());
        detection.setUserId(token);
        detectionRepository.save(detection);
        return new Response<>(200, "创建成功", "");
    }

    @GetMapping("/detectionPlan")
    public Response<List<Plan>> recommendPlan(@RequestParam("detectionId") Long detectionId) {
        Optional<Detection> optionalDetection = detectionRepository.findById(detectionId);
        if (!optionalDetection.isPresent()) {
            return new Response<>(400, "", null);
        }
        Detection detection = optionalDetection.get();
        if (detection.getPlanId() != null) {
            return new Response<>(400, "已经设置过计划了",null);
        }


        List<Plan> plans = new ArrayList<>();
        // 推荐计划
        Double bmi = getBmi(detection);
        if (bmi < 18.5) {
            //瘦子 搞一个
            Long id = 6L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        } else if ( bmi>18.5 && bmi<23.9) {
            Long id = 8L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else if ( bmi>24 && bmi<27.9) {
            Long id = 7L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        } else {
            Long id = 5L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }

        if (detection.getWaist() < 37) {
            Long id = 1L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else if(detection.getWaist()>37&&detection.getWaist()<43){
            Long id = 3L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else {
            Long id = 4L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }

        if(detection.getBust()<52) {
            Long id = 6L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else if(detection.getBust()>52&&detection.getBust()<60){
            Long id = 3L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else if(detection.getBust()>60){
            Long id = 5L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }


        if(detection.getBlood()<3.9) {
            Long id = 2L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else if(detection.getBlood()>3.9&&detection.getBlood()<6.1){
            Long id = 1L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }else {
            Long id = 7L;
            if (planRepository.findById(id).isPresent()) {
                plans.add(planRepository.findById(id).get());
            }
        }
        return new Response<>(200, "", getSingle(plans));
    }


    public static List<Plan> getSingle(List<Plan> list){
        List<Plan> newList = new ArrayList<>();
        for (Plan plan : list) {
            if(!(newList.contains(plan))){       //如果新集合中不包含老集合中的元素
                newList.add(plan);               //将该元素进行添加
            }
        }
        return newList;
    }


    public Double getBmi(Detection detection) {
        double bmi=(double) ((detection.getWeight()/detection.getHeight())/detection.getHeight())*10000;
        return bmi;
    }
}
