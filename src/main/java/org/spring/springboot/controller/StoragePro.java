package org.spring.springboot.controller;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "file")
public class StoragePro {
    private String location = "upload";
}
