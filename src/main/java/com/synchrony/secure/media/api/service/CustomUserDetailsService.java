package com.synchrony.secure.media.api.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service implementation of the {@link UserDetailsService} interface.
 * This class is responsible for loading user-specific data during authentication.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Loads the user details by username.
     * This method is used by Spring Security to retrieve user information
     * for authentication and authorization purposes.
     *
     * @param username The username of the user whose details are to be loaded.
     * @return A {@link UserDetails} object containing the user's information.
     * @throws UsernameNotFoundException If the user with the given username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Returns a dummy user with the provided username, a hardcoded password, and no authorities.
        return new User(username, "password", new ArrayList<>());
    }
}