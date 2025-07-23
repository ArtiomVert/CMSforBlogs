package ru.kpfu.itis.cmsforblogs.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.cmsforblogs.dto.CreatePostDto;
import ru.kpfu.itis.cmsforblogs.security.details.UserDetailsImpl;
import ru.kpfu.itis.cmsforblogs.service.BlogService;
import ru.kpfu.itis.cmsforblogs.service.MinioService;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/blogs/create")
public class CreateBlogController {

    private final BlogService blogService;

    @GetMapping("")
    public String getCreatePage(Principal principal, Model model) {
        UserDetailsImpl user = (UserDetailsImpl) ((Authentication) principal).getPrincipal();
        model.addAttribute("user_login", user.getUsername());
        return "minio";
    }

    @PostMapping("")
    public String createBlog(Principal principal, CreatePostDto dto) {
        UserDetailsImpl user = (UserDetailsImpl) ((Authentication) principal).getPrincipal();
        blogService.createBlog(user.getUsername(), dto);
        return "redirect:/blogs";
    }
}
