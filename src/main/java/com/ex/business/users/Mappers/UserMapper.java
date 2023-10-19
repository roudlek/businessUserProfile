package com.ex.business.users.Mappers;

import com.ex.business.users.DTO.UserProfileDTO;
import com.ex.business.users.Entities.UserProfile;

public class UserMapper {
    public static UserProfileDTO mapFromUserProfileEntityToUserProfileDTO(UserProfile userProfile){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setName(userProfile.getName());
        userProfileDTO.setAge(userProfile.getAge());
        userProfileDTO.setEmail(userProfile.getEmail());
        // very basic security layer // getPassword() should not return null
        userProfileDTO.setPassword("*".repeat(userProfile.getPassword().length()));
        return userProfileDTO;
    }

    public static UserProfile mapFromDTOToUserProfileEntity(UserProfileDTO userProfileDTO){
        UserProfile userprofile = new UserProfile();
        userprofile.setName(userProfileDTO.getName());
        userprofile.setAge(userProfileDTO.getAge());
        userprofile.setEmail(userProfileDTO.getEmail());
        userprofile.setPassword(userProfileDTO.getPassword());
        return userprofile;
    }
}
