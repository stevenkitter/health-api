package org.spring.springboot.models;


import lombok.Data;

@Data
public class AddCardRequest {
    private String content;
    private Long fileId;
}
