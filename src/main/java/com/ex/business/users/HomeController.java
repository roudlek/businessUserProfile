package com.ex.business.users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserProfileSignUpService userProfileSignUpService;
    private final UserProfileLoginService userProfileLoginService;
    private final UsersService usersService;

    public HomeController(UserProfileSignUpService userProfileSignUpService,
                          UserProfileLoginService userProfileLoginService, UsersService usersService) {
        this.userProfileSignUpService = userProfileSignUpService;
        this.userProfileLoginService = userProfileLoginService;
        this.usersService = usersService;
    }

//    private PasswordEncoder passwordEncoder;

//    @GetMapping("/user/{userId}")
//    public UserProfile getUserById(@PathVariable(name = "userId") Long id){
//        return usersService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @GetMapping("/user/{userId}")
    public UserProfile getUserByHisId(@PathVariable(name = "userId") Long id) {
        Optional<UserProfile> user = usersService.findUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @GetMapping("/users")
    public List<UserProfile> getAllUsers(){
        return usersService.findAll();
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return new ResponseEntity<>("Welcome to home", HttpStatus.OK);
    }

    @PostMapping("/sign_up")
    public ResponseEntity<?> sign_up(
            @RequestParam(name = "name") String userName,
            @RequestParam(name = "age") Byte userAge,
            @Valid @RequestParam(name = "email") String userEmail,
            @RequestParam(name = "password") String userPassword) {
        return userProfileSignUpService.signUp(userName, userAge, userEmail, userPassword);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam(name = "email") String userEmail,
            @RequestParam(name = "password") String userPassword) {
        return userProfileLoginService.login(userEmail, userPassword);
    }

    @GetMapping("/user/filter/{keyword}")
    public UserProfile findByNameContains(@PathVariable String keyword){
        return usersService.findByNameContains(keyword);
    }

    @GetMapping("/user/ageGreaterThan/{age}")
    public List<UserProfile> getAllUsersWithAgeGreaterThan(@PathVariable Byte age){
        return usersService.getAllUsersWithAgeGreaterThan(age);
    }

    @GetMapping("/user/filter/byNameLikeAndAgeGreaterThan")
    public List<UserProfile> getUsersByNameLikeAndAgeGreaterThan(
            @RequestParam(name = "name") String userName,
            @RequestParam(name = "age") Byte userAge){
        return usersService.getUsersByNameLikeAndAgeGreaterThan(userName,userAge);
    }
}

