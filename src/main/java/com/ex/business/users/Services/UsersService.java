package com.ex.business.users.Services;

import com.ex.business.users.Entities.UserProfile;
import com.ex.business.users.DTO.UserProfileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserProfileDTO> getAllUsersUsingDTO();
    List<UserProfile> findAllUserProfiles();
    List<UserProfile> getUsersByNameLikeAndAgeGreaterThan(String name, Byte age);

    List<UserProfile> getAllUsersWithAgeGreaterThan(Byte age);

    Page<UserProfile> getUserProfilesByPaginationAndDTO(Pageable pageable);

    Optional<UserProfile> findUserById(Long id);

    List<UserProfile> findAllByNameContains(String keyword);

    List<UserProfile> getAllUserProfilesWhereNameIsEqualTo(String name);

    void addUserProfile(String name,Byte age,String email,String password);

    UserProfileDTO addUserProfileUsingDTO(String name, Byte age, String email, String password);

    ResponseEntity<UserProfile> changeFullResource(String userName, Byte userAge, String userEmail, String userPassword, Long id );
    ResponseEntity<UserProfile> editUserProfile(Long id, String userPassword);
}
