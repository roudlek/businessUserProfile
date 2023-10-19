package com.ex.business.users.Services;

import com.ex.business.users.Entities.UserProfile;
import com.ex.business.users.DTO.UserProfileDTO;
import com.ex.business.users.Mappers.UserMapper;
import com.ex.business.users.Repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserProfileDTO> getAllUsersUsingDTO() {
        List<UserProfile> userProfiles = findAllUserProfiles();
        List<UserProfileDTO> userProfileDTOS = new ArrayList<>();
        for (UserProfile userProfile : userProfiles) {
            UserProfileDTO userProfileDTO = UserMapper.mapFromUserProfileEntityToUserProfileDTO(userProfile);
            userProfileDTOS.add(userProfileDTO);
        }
        return userProfileDTOS;
    }
    @Override
    public Page<UserProfile> getUserProfilesByPaginationAndDTO(Pageable pageable) {
//        List<UserProfileDTO> userProfileDTOS = getAllUsersUsingDTO();
        //  create like a UserPageableDTO that will act like the Pageable class and add variables for it, like list of users, use set pagination methods to that class variables(getContent, getSize, getNumber ...)
        return userRepository.findAll(pageable);
    }

    @Override
    public List<UserProfile> getUsersByNameLikeAndAgeGreaterThan(String name, Byte age) {
        return userRepository.findByNameLikeAndAgeAbove(name, age);
    }

    @Override
    public List<UserProfile> getAllUsersWithAgeGreaterThan(Byte age) {
        return userRepository.getAllUserProfileTheirAge(age);
    }

    @Override
    public List<UserProfile> findAllUserProfiles() {
        return userRepository.findAll();
    }



    @Override
    public Optional<UserProfile> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserProfile> findAllByNameContains(String keyword) {
        return userRepository.findAllByNameContains(keyword);
    }

    @Override
    public List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(String name) {
        return userRepository.getAllUserProfilesWhereNameIsEqualTo(name);
    }

    @Override
    public void addUserProfile(String name, Byte age, String email, String password) {
        userRepository.addUserProfile(name, age, email, password);
    }

    @Override
    public UserProfileDTO addUserProfileUsingDTO(String name, Byte age, String email, String password) {
        UserProfileDTO userProfileDTO = new UserProfileDTO(name, age, email, password);
        UserProfile userProfile = new UserProfile();

        userProfile.setName(userProfileDTO.getName());
        userProfile.setAge(userProfileDTO.getAge());
        userProfile.setEmail(userProfileDTO.getEmail());
        userProfile.setPassword(userProfileDTO.getPassword());

        userRepository.save(userProfile);
        return userProfileDTO;
    }

    @Override
    public ResponseEntity<UserProfile> changeFullResource(String userName, Byte userAge, String userEmail, String userPassword, Long id) {
        Optional<UserProfile> user = findUserById(id);
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

    @Override
    public ResponseEntity<UserProfile> editUserProfile(Long id, String userPassword) {
        Optional<UserProfile> user = findUserById(id);
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
}
