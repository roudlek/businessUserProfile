package com.ex.business.users.Services;

import com.ex.business.users.Entities.UserProfile;
import com.ex.business.users.Repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class UserProfileDetailsService implements UserDetailsService {
    private final UserRepository userProfileRepository;

    public UserProfileDetailsService(UserRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepository.findByName(username);
        if (userProfile == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserDetails loggedInUser = User
                .withUsername(userProfile.getName())
                .password(userProfile.getPassword())
                .roles(userProfile.getRole().toString())
                .build();
        return loggedInUser;
    }
}
