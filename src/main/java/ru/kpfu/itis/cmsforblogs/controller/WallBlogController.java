package ru.kpfu.itis.cmsforblogs.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.itis.cmsforblogs.security.details.UserDetailsImpl;
import ru.kpfu.itis.cmsforblogs.service.BlogService;

import java.security.Principal;

@AllArgsConstructor
@Controller
@RequestMapping("/blogs")
public class WallBlogController {

    private final BlogService blogService;

    @GetMapping("")
    public String getAllBlogPage(Principal principal, Model model) {
        UserDetailsImpl user = (UserDetailsImpl) ((Authentication) principal).getPrincipal();
        model.addAttribute("user_login", user.getUsername());
        model.addAttribute("posts", blogService.getBlogs());
        return "blogs";
    }

    @GetMapping("/my")
    public String getMyBlogPage(Principal principal, Model model) {
        UserDetailsImpl user = (UserDetailsImpl) ((Authentication) principal).getPrincipal();
        model.addAttribute("user_login", user.getUsername());
        model.addAttribute("posts", blogService.getMyBlogs(user.getUsername()));
        return "blogs";
    }

}
