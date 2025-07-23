package ru.kpfu.itis.cmsforblogs.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreatePostDto {
    private String title;
    private String text;
    private MultipartFile[] files;

}
