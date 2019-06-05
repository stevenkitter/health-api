package org.spring.springboot.controller;

import org.spring.springboot.Utile.DateOperation;
import org.spring.springboot.Utile.Tools;
import org.spring.springboot.jpa.Detection;
import org.spring.springboot.jpa.File;
import org.spring.springboot.jpa.Plan;
import org.spring.springboot.models.Conclusion;
import org.spring.springboot.models.SetPlanRequest;
import org.spring.springboot.models.DetectionRequest;

import org.spring.springboot.models.Response;
import org.spring.springboot.repository.DetectionRepository;
import org.spring.springboot.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @DeleteMapping("/detection")
    public Response<String> deleteDetection(@RequestHeader(value="Token") Long token, @RequestParam(value = "id") Long id) {
        detectionRepository.deleteById(id);
        return new Response<>(200, "删除成功", "");
    }


    @GetMapping("/detection/my")
    public Response<List<Detection>> myDetection(@RequestHeader(value="Token") Long token) throws ParseException {
        List<Detection> list = detectionRepository.findByUserId(token);
        for (Detection detection : list) {
            detection.setConclusion(detectionConclusion(detection));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
            Date date = format.parse(detection.getCreated_at().toString());
            detection.setCreated(DateOperation.format(date));
        }
        return new Response<>(200, "", list);
    }

    @GetMapping("/detectionPlan")
    public Response<List<Plan>> recommendPlan(@RequestParam("detectionId") Long detectionId) {
        Optional<Detection> optionalDetection = detectionRepository.findById(detectionId);
        if (!optionalDetection.isPresent()) {
            return new Response<>(400, "", null);
        }
        Detection detection = optionalDetection.get();
//        if (detection.getPlanId() != null) {
//            return new Response<>(400, "已经设置过计划了",null);
//        }

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

        for (Plan plan : plans) {
            File file = plan.getFile();
            String path = Tools.filePath(file.getFileName());
            file.setFileName(path);
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

    public Conclusion detectionConclusion(Detection detection) {
        Double bmi = getBmi(detection);
        Conclusion conclusion = new Conclusion();
        if(bmi<18.5) {
            conclusion.setDesc("体重偏瘦");
        }else if(bmi>18.5&&bmi<23.9){
            conclusion.setDesc("体重正常");
        }else if(bmi>24&&bmi<27.9){
            conclusion.setDesc("体重偏胖");
        }else if(bmi>28){
            conclusion.setDesc("体重重度肥胖");
        }
        if(detection.getWaist()<37) {
            conclusion.setWaist("腰力纤细");
        }else if(detection.getWaist()>37&&detection.getWaist()<43){
            conclusion.setWaist("腰身完美");
        }else if(detection.getWaist()>43){
            conclusion.setWaist("腰围圆滚滚");
        }
        if(detection.getBust()<52) {
            conclusion.setBust("臀部不明显");
        }else if(detection.getBust()>52&&detection.getBust()<60){
            conclusion.setBust("臀部完美");
        }else if(detection.getBust()>60){
            conclusion.setBust("臀部过大，注意减肥");
        }
        if(detection.getBlood()<3.9) {
            conclusion.setBlood("血糖过低");
        }else if(detection.getBlood()>3.9&&detection.getBlood()<6.1){
            conclusion.setBlood("血糖正常");
        }else if(detection.getBlood()>6.1){
            conclusion.setBlood("血糖过高");
        }
        return conclusion;
    }


}
