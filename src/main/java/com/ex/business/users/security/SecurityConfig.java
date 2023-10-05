package com.ex.business.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers(
                            // auth links are allowed by default

//                            "/oauth2/authorization/github"
//                            ,"/oauth2/authorization/google",
                            "/home","/"
                            ,"/api/home","/api/users"
                            ,"/login","/register","/css/**","/js/**","/favicon.ico").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin( form -> {
                    form.loginPage("/login");
//                    form.defaultSuccessUrl("/home");
                    form.loginProcessingUrl("/login");
                    form.failureUrl("/login?error=true");
                    form.permitAll(true);
                })

                .oauth2Login(
                        oauth2 -> {
//                            its ok to have them both have same path(/login)
                    oauth2.loginPage("/login").permitAll();
                }
                )
                .logout(logout ->
                        logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
        .build();
    }
}
