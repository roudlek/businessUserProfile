package com.ex.business.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserProfileSignUpService {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
//    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public UserRepository userRepository;

    public UserProfileSignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> signUp(String userName, Byte userAge, String userEmail, String userPassword) {

        UserProfile userProfile = userRepository.findByEmail(userEmail);
        if (userProfile != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.");
        }
        else if (userAge < 18 && userAge > 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied for age under 18.");
        } else if (!userPassword.matches(PASSWORD_PATTERN)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password does not meet the right format.");
        }
//       else if (!Objects.equals(userPassword, userConfirmPassword)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match");}
//        else if (!userEmail.matches(EMAIL_PATTERN)) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format.");
//        }
        else{
            UserProfile newUserProfile  = new UserProfile();
            newUserProfile.setName(userName);
            newUserProfile.setAge(userAge);
            newUserProfile.setEmail(userEmail);
            newUserProfile.setPassword(userPassword);

            // Save the user profile
            userRepository.save(newUserProfile);

            return new ResponseEntity<>(newUserProfile, HttpStatus.CREATED);
        }
    }
}
