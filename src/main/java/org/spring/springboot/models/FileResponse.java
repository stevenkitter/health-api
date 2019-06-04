package org.spring.springboot.models;


import lombok.Data;

@Data
public class FileResponse {
    private String fileName;
    private Long fileId;
    public FileResponse(String fileName, Long fileId) {
        this.fileName = fileName;
        this.fileId = fileId;
    }
}
