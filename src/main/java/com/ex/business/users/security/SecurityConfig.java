package com.ex.business.users.security;

import com.ex.business.users.Services.UserProfileDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserProfileDetailsService userProfileDetailsService;

    public SecurityConfig(UserProfileDetailsService userProfileDetailsService) {
        this.userProfileDetailsService = userProfileDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .csrf(csrf -> csrf.disable()) // csrf prootection is enabled by default, there is no enable method
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "/", "/api/users", "/login", "/register",
                            "/css/**", "/js/**", "/favicon.ico", "/webjars/**").permitAll();
//                            .hasRole("USER");
//                  auth.permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> {
                    form.loginPage("/login");
//                    form.usernameParameter("username");
//                    form.passwordParameter("password");
                    form.defaultSuccessUrl("/templates/users");
//                    form.loginProcessingUrl("/login/process");
                    form.failureUrl("/login?failed=true");
                    form.permitAll();
                })
                .oauth2Login(
                        oauth2 -> {
//                            its ok to have them both have same path(/login)
                            oauth2.loginPage("/login").permitAll();
                            oauth2.defaultSuccessUrl("/templates/users");
                        }
                )
                // form login with sample username and password wont work if auth is also implemented

                .logout(logout ->
                        logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userProfileDetailsService)
                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(githubClientRegistration());
//    }

//    private ClientRegistration githubClientRegistration() {
//        return ClientRegistration.withRegistrationId("github")
//                .clientId("a0d21afc82e627c62373")
//                .clientSecret("70971a1612b3b18b3d5a4d5241bfc6daf4088ccd")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("localhost:8080/templates/users")
////                .redirectUri("localhost:8080/login/oauth2/code/{registrationId}")
////                .scope("read:user")
//                .authorizationUri("http://localhost:8080/login/oauth2/code/github")
////                .tokenUri("https://github.com/login/oauth/access_token")
////                .userInfoUri("https://api.github.com/user")
////                .userNameAttributeName("login")
//                .clientName("GitHub")
//                // Add other GitHub OAuth2 details
//                .build();
//    }


//    @Bean
//    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
//        return new DefaultOAuth2UserService();
//    }


//    @Bean
//    public OAuth2AuthorizationRequestResolver authorizationRequestResolver(
//            ClientRegistrationRepository clientRegistrationRepository
////            ,OAuth2AuthorizationRequestRedirectFilter filter
//    ) {
//        return new DefaultOAuth2AuthorizationRequestResolver(
//                clientRegistrationRepository, "/oauth2/authorization");
////        /oauth2/authorization/github
//    }
}
