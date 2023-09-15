package com.ex.business.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserProfileLoginService {

    private final UserRepository userRepository;

    public UserProfileLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> login(String userEmail, String userPassword){
        UserProfile userProfile = userRepository.findByEmail(userEmail);

        if (userProfile != null) {
            String userProfilePassword = userProfile.getPassword();
            if (Objects.equals(userPassword, userProfilePassword)) {
                // You can generate a token or session here if needed.
                return new ResponseEntity<>("Login successful", HttpStatus.OK);
            }
            else {
                // Passwords do not match, indicating a failed login attempt.
                return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            // User with the provided email does not exist.
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
