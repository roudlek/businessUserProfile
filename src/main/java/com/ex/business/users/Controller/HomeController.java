package com.ex.business.users.Controller;

import com.ex.business.users.DTO.UserProfileDTO;
import com.ex.business.users.Repositories.UserRepository;
import com.ex.business.users.Services.UserProfileLoginService;
import com.ex.business.users.Services.UserProfileSignUpService;
import com.ex.business.users.Services.UsersServiceImpl;
import com.ex.business.users.Entities.UserProfile;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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
    private final UsersServiceImpl usersServiceImpl;
    private final UserRepository userRepository;

    public HomeController(UserProfileSignUpService userProfileSignUpService,
                          UserProfileLoginService userProfileLoginService, UsersServiceImpl usersServiceImpl, UserRepository userRepository) {
        this.userProfileSignUpService = userProfileSignUpService;
        this.userProfileLoginService = userProfileLoginService;
        this.usersServiceImpl = usersServiceImpl;
        this.userRepository = userRepository;
    }

//    private PasswordEncoder passwordEncoder;
//    @GetMapping("/user/{userId}")
//    public UserProfile getUserById(@PathVariable(name = "userId") Long id){
//        return usersService.findUserById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//    }

    @PostConstruct
    public void init() {
        System.out.println("from post construct");
//        for(int i = 0; i < 100 ; i++){
//            UserProfile userProfile = new UserProfile( "ahmad", (byte) 18, "ahmad@gmail.com", "Abdel123@@@@");
//            userRepository.save(userProfile);
//        }
    }

    @PreDestroy
    public void destroy() {
        System.out.println("from pre destroy");
    }

    @GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return new ResponseEntity<>("Welcome to home", HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserByHisId(@PathVariable(name = "userId") Long id) {
        //    UserProfile user = usersServiceImpl.findUserById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        Optional<UserProfile> user = usersServiceImpl.findUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(),HttpStatus.OK) ;
        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            return new ResponseEntity<>("user not found",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/users")
    public List<UserProfile> getAllUsers() {
        return usersServiceImpl.findAllUserProfiles();
    }

    @GetMapping("/usersDTO")
    public List<UserProfileDTO> getAllUsersUsingDTO() {
        return usersServiceImpl.getAllUsersUsingDTO();
    }

    @GetMapping("/users/pagination")
    public Page<UserProfile> getUserProfilesByPagination(@RequestParam(defaultValue = "0", name = "page") int pageNumber,
                                                         @RequestParam(defaultValue = "10", name = "size") int pageSize,
                                                         @RequestParam(defaultValue = "id") String[] columnName) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(columnName));
        return usersServiceImpl.getUserProfilesByPaginationAndDTO(pageable);
    }


    @PostMapping("/sign_up")
    @ResponseStatus(HttpStatus.CREATED) // this will add a status of 201 CREATED if everything was successfull
    public ResponseEntity<?> sign_up(
            @RequestParam(name = "name") String userName,
            @RequestParam(name = "age") Byte userAge,
            @Valid @RequestParam(name = "email") String userEmail,
            @RequestParam(name = "password") String userPassword) {
        return userProfileSignUpService.signUp(userName, userAge, userEmail, userPassword);
    }

    @PutMapping("/changeFullResource/{userId}")
    public ResponseEntity<UserProfile> changeFullResource(@PathVariable(name = "userId") Long id,
                                                          @RequestParam(name = "name") String userName,
                                                          @RequestParam(name = "age") Byte userAge,
                                                          @RequestParam(name = "email") String userEmail,
                                                          @RequestParam(name = "password") String userPassword) {
        return usersServiceImpl.changeFullResource(userName, userAge, userEmail, userPassword, id);
    }

    @PatchMapping("/edit/{userId}")
    public ResponseEntity<UserProfile> editUserProfile(@PathVariable(name = "userId") Long id,
                                                       @RequestParam(name = "password") String userPassword) {
        return usersServiceImpl.editUserProfile(id, userPassword);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable(name = "userId") Long id) {
        Optional<UserProfile> user = usersServiceImpl.findUserById(id);
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
    public List<UserProfile> findAllByNameContains(@PathVariable String keyword) {
        return usersServiceImpl.findAllByNameContains(keyword);
    }

    @GetMapping("/user/ageGreaterThan/{age}")
    public List<UserProfile> getAllUsersWithAgeGreaterThan(@PathVariable Byte age) {
        return usersServiceImpl.getAllUsersWithAgeGreaterThan(age);
    }

    @GetMapping("/user/filter/byNameLikeAndAgeGreaterThan")
    public List<UserProfile> getUsersByNameLikeAndAgeGreaterThan(
            @RequestParam(name = "name") String userName,
            @RequestParam(name = "age") Byte userAge) {
        return usersServiceImpl.getUsersByNameLikeAndAgeGreaterThan(userName, userAge);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam(name = "file") MultipartFile file) {
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
    public List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(@RequestParam String name) {
        return usersServiceImpl.getAllUserProfilesWhereNameIsEqualTo(name);
    }


    @PostMapping("/adduser")
    public ResponseEntity<?> addUserProfile(@RequestParam String name, @RequestParam Byte age,
                                            @RequestParam String email, @RequestParam String password) {
        usersServiceImpl.addUserProfile(name, age, email, password);
        return new ResponseEntity<>("user with name: " + name + " has been created", HttpStatus.CREATED);
    }

    @PostMapping("/addUserProfileUsingDTO")
    public ResponseEntity<?> addUserProfileUsingDTO(@RequestParam String name, @RequestParam Byte age,
                                                    @RequestParam String email, @RequestParam String password) {
        return new ResponseEntity<>(usersServiceImpl.addUserProfileUsingDTO(name, age, email, password), HttpStatus.CREATED);
    }
}