package com.ex.business.users.Controller;

import com.ex.business.users.UserProfileLoginService;
import com.ex.business.users.UserProfileSignUpService;
import com.ex.business.users.UserRepository;
import com.ex.business.users.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class LoginController {
    private final UserProfileSignUpService userProfileSignUpService;
    private final UserProfileLoginService userProfileLoginService;
    private final UsersService usersService;
    private final UserRepository userRepository;

    public LoginController(UserProfileSignUpService userProfileSignUpService,
                           UserProfileLoginService userProfileLoginService, UsersService usersService, UserRepository userRepository) {
        this.userProfileSignUpService = userProfileSignUpService;
        this.userProfileLoginService = userProfileLoginService;
        this.usersService = usersService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/home")
    String home() {
        return "home";
    }
    @GetMapping("/register")
    String register() {
        return "register";
    }
//    dont have to add that
//    @GetMapping("/oauth2/authorization/github")
//    String goToOauth2github() {
//        return "oauth2github";
//    }


//    @PostMapping("/login")
//    public String login(
//            @RequestParam(name = "email") String userEmail,
//            @RequestParam(name = "password") String userPassword) {
//        ResponseEntity<?> response = userProfileLoginService.login(userEmail, userPassword);
//
//        if (response.getStatusCode() == HttpStatus.OK && Objects.equals(response.getBody(),"Login successful")) {
//            // If login is successful, redirect to the home page
//            return "redirect:/api/home";
//        } else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED && Objects.equals(response.getBody(), "Incorrect password")) {
//            // If the password is incorrect, redirect to an error page or login page with an error message
//            return "redirect:/login?error=incorrect_password";
//        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND && Objects.equals(response.getBody(),"User not found")) {
//            // If the user does not exist, redirect to an error page or login page with an error message
//            return "redirect:/login?error=user_not_found";
//        } else {
//            // Handle other response statuses as needed (e.g., internal server error)
//            return "redirect:/error";
//        }
//
//    }

//    @GetMapping("/login")
//    public ModelAndView getLoginPage(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }
}
