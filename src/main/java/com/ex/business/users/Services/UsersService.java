package com.ex.business.users.Services;

import com.ex.business.users.Entities.UserProfile;
import com.ex.business.users.DTO.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserProfile> getUsersByNameLikeAndAgeGreaterThan(String name, Byte age);

    List<UserProfile> getAllUsersWithAgeGreaterThan(Byte age);

    List<UserProfile> findAll();

    Page<UserProfile> getUserProfilesByPagination(Pageable pageable);

    Optional<UserProfile> findUserById(Long id);

    List<UserProfile> findAllByNameContains(String keyword);

    List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(String name);

    void addUserProfile(String name,Byte age,String email,String password);

    void addUserUsingDTO();

    UserProfileDTO addUserProfileUsingDTO(String name, Byte age, String email, String password);
}
