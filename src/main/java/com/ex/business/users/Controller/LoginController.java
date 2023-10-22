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
    private final UserProfileSignUpService userProfileSignUpService;
    private final UserProfileLoginService userProfileLoginService;
    private final UsersServiceImpl usersServiceImpl;
    private final UserRepository userRepository;

    public LoginController(UserProfileSignUpService userProfileSignUpService,
                           UserProfileLoginService userProfileLoginService, UsersServiceImpl usersServiceImpl, UserRepository userRepository) {
        this.userProfileSignUpService = userProfileSignUpService;
        this.userProfileLoginService = userProfileLoginService;
        this.usersServiceImpl = usersServiceImpl;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }


//    @GetMapping("/loginwithauth")
//    String loginWithAuth() {
//        return "/loginwithauth";
//    }

    @GetMapping("/home")
    String home() {
        return "home";
    }
    @GetMapping("/register")
    String goToRegisterPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "username") String userName,
                               @RequestParam(name = "age") Byte userAge,
                               @Valid @RequestParam(name = "email") String userEmail,
                               @RequestParam(name = "password") String userPassword) {
        userProfileSignUpService.signUp(userName, userAge, userEmail, userPassword);
        // Handle user registration logic, save user to the database, etc.
        // You can access form fields through the registrationForm object ( I should have thought about that)
        return "redirect:/login"; // Redirect to a login page or another appropriate page
    }
}
