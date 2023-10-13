package com.ex.business.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Page<UserProfile> getUserProfilesByPagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<UserProfile> findUserById(Long id){
        return userRepository.findById(id);
    }

    public List<UserProfile> findAllByNameContains(String keyword) {
        return userRepository.findAllByNameContains(keyword);
    }

    public List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(String name){
        return userRepository.getAllUserProfilesWhereNameIsEqualTo(name);
    }

    public void addUserProfile(String name,Byte age,String email,String password){
        userRepository.addUserProfile(name,age,email,password);
    }
}
