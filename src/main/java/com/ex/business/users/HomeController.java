package com.ex.business.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserProfileSignUpService userProfileSignUpService;
    private final UserProfileLoginService userProfileLoginService;

    public HomeController(UserProfileSignUpService userProfileSignUpService,
                          UserProfileLoginService userProfileLoginService) {

        this.userProfileSignUpService = userProfileSignUpService;
        this.userProfileLoginService = userProfileLoginService;
    }
//    private PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return new ResponseEntity<>("Welcome to home", HttpStatus.OK);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<?> sign_up(
            @RequestParam(name = "name") String userName,
            @RequestParam(name = "age") int userAge,
            @RequestParam(name = "email") String userEmail,
            @RequestParam(name = "password") String userPassword) {
        return userProfileSignUpService.signUp(userName, userAge, userEmail, userPassword);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam(name = "email") String userEmail,
            @RequestParam(name = "password") String userPassword) {
        return userProfileLoginService.login(userEmail, userPassword);
    }
}

