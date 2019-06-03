package org.spring.springboot.controller;

import org.spring.springboot.jpa.File;
import org.spring.springboot.models.FileResponse;
import org.spring.springboot.models.Response;
import org.spring.springboot.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @Resource
    private FileRepository fileRepository;

    @PostMapping("/uploadOneFile")
    public Response<FileResponse> uploadOneFile(@RequestHeader(value="Token") Long token, @RequestParam("file") MultipartFile file) {
        FileResponse uploadFileResponse = uploadFile(file, token);
        return new Response<>(200, "", uploadFileResponse);
    }

    @PostMapping("/uploadFile")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file, Long userId) {
        String filename = fileService.store(file);
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().scheme("https")
//                .path("/downloadFile/")
//                .path(filename)
//                .toUriString();
        File tkFile = new File();
        tkFile.setFileName(filename);
        tkFile.setUserId(userId);
        fileRepository.save(tkFile);
        return new FileResponse(filename);
    }
}
