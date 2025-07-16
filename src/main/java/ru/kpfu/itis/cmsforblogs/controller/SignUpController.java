package ru.kpfu.itis.cmsforblogs.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.cmsforblogs.dto.SignUpForm;
import ru.kpfu.itis.cmsforblogs.exception.UserAlreadyExistException;
import ru.kpfu.itis.cmsforblogs.service.SignUpService;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String createUser(@Valid SignUpForm signUpForm, BindingResult bindingResult) {
        if (!signUpForm.getPassword().equals(signUpForm.getConfirmPassword())) {
            bindingResult.addError(new ObjectError("signUpForm", "Пароли должны совпадать"));
        }
        try{
            signUpService.createUser(signUpForm);
        } catch (UserAlreadyExistException e) {
            bindingResult.addError(new ObjectError("signUpForm", "Пользователь с таким именем уже существует"));
        }
        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        return "redirect:/login";
    }


}
