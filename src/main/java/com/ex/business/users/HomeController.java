package com.ex.business.users;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class HomeController {

    private final UserProfileSignUpService userProfileSignUpService;
    private final UserProfileLoginService userProfileLoginService;
    private final UsersService usersService;
    private final UserRepository userRepository;

    public HomeController(UserProfileSignUpService userProfileSignUpService,
                          UserProfileLoginService userProfileLoginService, UsersService usersService, UserRepository userRepository) {
        this.userProfileSignUpService = userProfileSignUpService;
        this.userProfileLoginService = userProfileLoginService;
        this.usersService = usersService;
        this.userRepository = userRepository;
    }

//    private PasswordEncoder passwordEncoder;
//    @GetMapping("/user/{userId}")
//    public UserProfile getUserById(@PathVariable(name = "userId") Long id){
//        return usersService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return new ResponseEntity<>("Welcome to home", HttpStatus.OK);
    }

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
    @GetMapping("/users/pagination")
    public Page<UserProfile> getUserProfilesByPagination(@RequestParam(defaultValue = "0", name = "page") int page,
                                                         @RequestParam(defaultValue = "10", name = "size") int size,
                                                         @RequestParam(defaultValue = "id") String[] columnName){
        Pageable pageable = PageRequest.of(page, size, Sort.by(columnName));
        return usersService.getUserProfilesByPagination(pageable);
    }



    @PostMapping("/sign_up")
    public ResponseEntity<?> sign_up(
            @RequestParam(name = "name") String userName,
            @RequestParam(name = "age") Byte userAge,
            @Valid @RequestParam(name = "email") String userEmail,
            @RequestParam(name = "password") String userPassword) {
        return userProfileSignUpService.signUp(userName, userAge, userEmail, userPassword);
    }
    @PutMapping("/changeFullResource/{userId}")
    public ResponseEntity<UserProfile> editUserProfile(@PathVariable(name = "userId") Long id,
                                       @RequestParam(name = "name") String userName,
                                       @RequestParam(name = "age") Byte userAge,
                                       @RequestParam(name = "email") String userEmail,
                                       @RequestParam(name = "password") String userPassword) {
        Optional<UserProfile> user = usersService.findUserById(id);
        if (user.isPresent()) {
            UserProfile foundUser = user.get();
            if (userName != null) {
                foundUser.setName(userName);
            }
            if (userAge != null) {
                foundUser.setAge(userAge);
            }
            if (userEmail != null) {
                foundUser.setEmail(userEmail);
            }
            if (userPassword != null) {
                foundUser.setPassword(userPassword);
            }
            UserProfile updatedUser = userRepository.save(foundUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @PatchMapping("/edit/{userId}")
    public ResponseEntity<UserProfile> changeFullResource(@PathVariable(name = "userId") Long id,
                                                       @RequestParam(name = "password") String userPassword) {
        Optional<UserProfile> user = usersService.findUserById(id);
        if (user.isPresent()) {
            UserProfile foundUser = user.get();
            if (userPassword != null) {
                foundUser.setPassword(userPassword);
            }
            UserProfile updatedUser = userRepository.save(foundUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") Long id){
        Optional<UserProfile> user = usersService.findUserById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("user with id: " + id + " has been deleted", HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(
//            @RequestParam(name = "email") String userEmail,
//            @RequestParam(name = "password") String userPassword) {
//        return userProfileLoginService.login(userEmail, userPassword);
//    }

    @GetMapping("/user/filter/{keyword}")
    public List<UserProfile> findAllByNameContains(@PathVariable String keyword){
        return usersService.findAllByNameContains(keyword);
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

    @PostMapping("/upload")
    public String uploadFile(@RequestParam(name = "file") MultipartFile file){
        // Process the uploaded file
        if (!file.isEmpty() && file.getSize() < 50000) {
            // Save, process, or store the file as needed
            // You can access file properties like filename, content type, etc.
            String fileName = file.getOriginalFilename();
            // Save the file to a location, a database, or perform further processing
            // ...
            return "File uploaded successfully";
        } else {
            return "Failed to upload the file";
        }
    }

    @GetMapping("/getbyname")
    public List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(@RequestParam String name){
        return usersService.getAllUserProfilesWhereNameIsEqualTo(name);
    }


    @PostMapping("/adduser")
    public ResponseEntity<?> addUserProfile(@RequestParam String name,@RequestParam Byte age,
                               @RequestParam String email, @RequestParam String password){
        usersService.addUserProfile(name,age,email,password);
        return new ResponseEntity<>("user with name: " + name + " has been created", HttpStatus.CREATED);
    }
}