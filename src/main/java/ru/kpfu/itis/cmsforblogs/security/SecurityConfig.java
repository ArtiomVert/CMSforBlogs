package ru.kpfu.itis.cmsforblogs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.kpfu.itis.cmsforblogs.dictionary.UserRole;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/signUp").anonymous()
                        .requestMatchers("/blogs/**").authenticated()
                        .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.toString())
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret"))
                .logout(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

