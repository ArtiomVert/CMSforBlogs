package ru.kpfu.itis.cmsforblogs.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.cmsforblogs.service.MinioService;

@AllArgsConstructor
@RestController
public class FileController {
    private final MinioService minioService;

    @GetMapping("/files")
    public ResponseEntity<byte[]> getImage(@RequestParam String name) {
        return new ResponseEntity<>(minioService.getFile(name), HttpStatus.OK);
    }
}
