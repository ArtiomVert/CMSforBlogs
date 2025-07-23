package ru.kpfu.itis.cmsforblogs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.cmsforblogs.dictionary.UserRole;
import ru.kpfu.itis.cmsforblogs.dto.SignUpForm;
import ru.kpfu.itis.cmsforblogs.entity.Post;
import ru.kpfu.itis.cmsforblogs.entity.User;
import ru.kpfu.itis.cmsforblogs.exception.UserAlreadyExistException;
import ru.kpfu.itis.cmsforblogs.repository.UserRepository;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void createUser(SignUpForm form) {
        userRepository.findByUsername(form.getUsername())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException("User already exist");
                });

        String encode = passwordEncoder.encode(form.getPassword());
        User user = User.builder()
                .username(form.getUsername())
                .hashPassword(encode)
                .role(UserRole.USER)
                .posts(new ArrayList<Post>())
                .build();
        userRepository.save(user);
    }
}