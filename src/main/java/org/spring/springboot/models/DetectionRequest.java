package org.spring.springboot.models;

import lombok.Data;

@Data
public class DetectionRequest {
    private Integer height;
    private Integer weight;
    private Integer waist;
    private Integer bust;
    private Integer blood;
}
