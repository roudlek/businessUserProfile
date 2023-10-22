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
    public UserRepository userRepository;

    public UserProfileSignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> signUp(String userName, Byte userAge, String userEmail, String userPassword) {

        UserProfile userProfile = userRepository.findByEmail(userEmail);
        if (userProfile != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
        } else {
            PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            UserProfile newUserProfile = new UserProfile();
            newUserProfile.setName(userName);
            newUserProfile.setAge(userAge);
            newUserProfile.setEmail(userEmail);
            newUserProfile.setPassword(bCryptPasswordEncoder.encode(userPassword));

            userRepository.save(newUserProfile);

            return new ResponseEntity<>(newUserProfile, HttpStatus.CREATED);
        }
    }
}
