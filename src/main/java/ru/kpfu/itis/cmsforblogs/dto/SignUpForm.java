package ru.kpfu.itis.cmsforblogs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpForm {
    @NotBlank(message = "Имя не может быть пустым")
    private String username;
    @Size(min = 4, max = 8, message = "Пароль должен содержать от 4 до 8 символов")
    private String password;
    @Size(min = 4, max = 8, message = "Пароль должен содержать от 4 до 8 символов")
    private String confirmPassword;

}
