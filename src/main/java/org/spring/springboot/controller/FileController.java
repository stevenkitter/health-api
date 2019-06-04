package org.spring.springboot.controller;

import org.spring.springboot.jpa.File;
import org.spring.springboot.models.FileResponse;
import org.spring.springboot.models.Response;
import org.spring.springboot.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

        File tkFile = new File();
        tkFile.setFileName(filename);
        tkFile.setUserId(userId);
        fileRepository.save(tkFile);
        return new FileResponse(filename, tkFile.getId());
    }

    @GetMapping("/downloadFile/**")
    public ResponseEntity<org.springframework.core.io.Resource> downloadFile(HttpServletRequest request) {
        String fileName = getExtractPath(request);
        System.out.printf("fileName %s", fileName);
        org.springframework.core.io.Resource resource = fileService.loadAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private String getExtractPath(final HttpServletRequest request) {
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        return new AntPathMatcher().extractPathWithinPattern(bestMatchPattern, path);
    }
}
