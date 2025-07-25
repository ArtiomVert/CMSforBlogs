package ru.kpfu.itis.cmsforblogs.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.cmsforblogs.dictionary.PostTeg;

@Data
public class CreatePostDto {
    private String title;
    private String text;
    private MultipartFile[] files;
    private PostTeg[] tegs;

}
