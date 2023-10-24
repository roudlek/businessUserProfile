package com.ex.business.users.Services;

import com.ex.business.users.Repositories.UserRepository;
import com.ex.business.users.Entities.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileSignUpService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserProfileSignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> signUp(String userName, String userEmail, String userPassword) {
        UserProfile existingUser = userRepository.findByNameAndEmail(userName, userEmail);

        if (existingUser != null) {
            // User with the same name and email already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
        } else if (existingUser == null){
            // Create a new user profile
            UserProfile newUserProfile = new UserProfile();
            newUserProfile.setName(userName);
            newUserProfile.setEmail(userEmail);
            newUserProfile.setPassword(passwordEncoder.encode(userPassword));

            UserProfile savedUserProfile = userRepository.save(newUserProfile);

            // Registration successful
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserProfile);
        }
        else {
            // Handle the case where user creation fails
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed.");
        }
}

//    public ResponseEntity<?> signUp(String userName, String userEmail, String userPassword) {
//
//        UserProfile userProfile = userRepository.findByNameAndEmail(userName,userEmail);
//        if (userProfile != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
//        } else {
//            PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            UserProfile newUserProfile = new UserProfile();
//            newUserProfile.setName(userName);
//            newUserProfile.setEmail(userEmail);
//            newUserProfile.setPassword(bCryptPasswordEncoder.encode(userPassword));
//
//            userRepository.save(newUserProfile);
//
//            return new ResponseEntity<>(newUserProfile, HttpStatus.CREATED);
//        }
//    }
}
