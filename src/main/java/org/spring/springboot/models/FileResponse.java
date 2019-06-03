package org.spring.springboot.models;


import lombok.Data;

@Data
public class FileResponse {
    private String fileName;

    public FileResponse(String fileName) {
        this.fileName = fileName;
    }
}
