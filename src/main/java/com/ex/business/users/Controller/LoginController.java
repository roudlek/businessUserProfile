package com.ex.business.users.Controller;

import com.ex.business.users.Services.UserProfileLoginService;
import com.ex.business.users.Services.UserProfileSignUpService;
import com.ex.business.users.Repositories.UserRepository;
import com.ex.business.users.Services.UsersServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/successlogging")
    String successLogging() {
        return "successlogging";
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
