package com.ex.business.users.Controller;

import com.ex.business.users.Entities.RegistrationForm;
import com.ex.business.users.Services.UserProfileSignUpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

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
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }
    //    public String registerUser(@RequestParam(name = "username", required = true) String userName,
//                               @Valid @RequestParam(name = "email", required = true) String userEmail,
//                               @RequestParam(name = "password", required = true) String userPassword,
//                               Model model) {

//    @ResponseStatus(HttpStatus.CREATED) // this does the job but stay and make register page blank
    @Transactional
    @PostMapping("/register")
    public String registerUser(RegistrationForm registrationForm, BindingResult bindingResult, Model model) {
        ResponseEntity<?> response = userProfileSignUpService.signUp(registrationForm.getUsername()
                , registrationForm.getEmail()
                , registrationForm.getPassword());

        if (bindingResult.hasErrors()) {
            // Validation failed, return to the registration page with error messages
            return "register"; // This is used to display an HTML page or a template
        }

//        && Objects.equals(response.getBody(), "User already exists.")
        if (response.getStatusCode() == HttpStatus.CONFLICT ) {
            model.addAttribute("exist",true);
            return "redirect:/register?exist=true";
        } else if (response.getStatusCode() == HttpStatus.OK) {
//            model.addAttribute("success",true);
            // such as sending a confirmation email, logging in the user, etc.
            return "/redirectingToLogin"; // Redirect first to redirectingToLogin endpoint wich again redirect
            // to the login page, and keeps the last endpoint
        } else {
            // Handle any other cases or errors that might occur during registration
            // You can redirect to an error page or another appropriate action.
            return "redirect:/error";
        }
    }
    @GetMapping("/redirectingToLogin")
    public String redirectToLogin(){
        return "redirect:/redirectingToLogin"; // redirect to a specific url in your application
        }
    }
