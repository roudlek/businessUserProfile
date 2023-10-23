package com.ex.business.users.Controller;

import com.ex.business.users.Services.UserProfileSignUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@Controller
public class RegistrationController {

    private final UserProfileSignUpService userProfileSignUpService;

    public RegistrationController(UserProfileSignUpService userProfileSignUpService) {
        this.userProfileSignUpService = userProfileSignUpService;
    }
//    private final UserProfileLoginService userProfileLoginService;
//    private final UsersServiceImpl usersServiceImpl;
//    private final UserRepository userRepository;
//
//    public RegistrationController(UserProfileSignUpService userProfileSignUpService, UserProfileLoginService userProfileLoginService, UsersServiceImpl usersServiceImpl, UserRepository userRepository) {
//        this.userProfileSignUpService = userProfileSignUpService;
//        this.userProfileLoginService = userProfileLoginService;
//        this.usersServiceImpl = usersServiceImpl;
//        this.userRepository = userRepository;
//    }

    @GetMapping("/register")
    String goToRegisterPage(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam(name = "username") String userName,
                               @Valid @RequestParam(name = "email") String userEmail,
                               @RequestParam(name = "password") String userPassword,
                               Model model) {
        ResponseEntity<?> response = userProfileSignUpService.signUp(userName, userEmail, userPassword);

        if (response.getStatusCode() == HttpStatus.CONFLICT && Objects.equals(response.getBody(), "User already exists.")) {
            model.addAttribute("exist",true);
            return "redirect:/register?exist=true";
        } else if (response.getStatusCode() == HttpStatus.CREATED) {
//            model.addAttribute("success",true);
            // such as sending a confirmation email, logging in the user, etc.
            return "/redirectingToLogin"; // Redirect to the login page
        } else {
            // Handle any other cases or errors that might occur during registration
            // You can redirect to an error page or another appropriate action.
            return "redirect:/error";
        }
    }
    @GetMapping("/redirectingToLogin")
    public String redirectToLogin(){
        return "redirect:/redirectingToLogin";
    }
}
