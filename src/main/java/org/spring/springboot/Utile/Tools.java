package org.spring.springboot.Utile;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Tools {

    public static String filePath(String fileName) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().scheme("http")
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return  fileDownloadUri;
    }
}
