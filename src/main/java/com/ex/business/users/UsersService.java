package com.ex.business.users;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository userRepository;
    public UsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserProfile> getUsersByNameLikeAndAgeGreaterThan(String name, Byte age){
        return userRepository.findByNameLikeAndAgeAbove(name,age);
    }
    public List<UserProfile> getAllUsersWithAgeGreaterThan(Byte age){
        return userRepository.getAllUserProfileTheirAge(age);
    }
    public List<UserProfile> findAll(){
        return userRepository.findAll();
    }

    public Optional<UserProfile> findUserById(Long id){
        return userRepository.findById(id);
    }


    public UserProfile findByNameContains(String keyword) {
        return userRepository.findByNameContains(keyword);
    }
}
