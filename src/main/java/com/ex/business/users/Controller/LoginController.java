package com.ex.business.users.Controller;

import com.ex.business.users.Services.UserProfileLoginService;
import com.ex.business.users.Services.UserProfileSignUpService;
import com.ex.business.users.Repositories.UserRepository;
import com.ex.business.users.Services.UsersServiceImpl;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
//    private final UserProfileSignUpService userProfileSignUpService;
//    private final UserProfileLoginService userProfileLoginService;
//    private final UsersServiceImpl usersServiceImpl;
//    private final UserRepository userRepository;
//
//    public LoginController(UserProfileSignUpService userProfileSignUpService,
//                           UserProfileLoginService userProfileLoginService, UsersServiceImpl usersServiceImpl, UserRepository userRepository) {
//        this.userProfileSignUpService = userProfileSignUpService;
//        this.userProfileLoginService = userProfileLoginService;
//        this.usersServiceImpl = usersServiceImpl;
//        this.userRepository = userRepository;
//    }

    @GetMapping("/")
    String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
