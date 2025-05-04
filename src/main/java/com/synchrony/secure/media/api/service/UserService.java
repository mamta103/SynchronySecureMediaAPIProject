package com.synchrony.secure.media.api.service;

import com.synchrony.secure.media.api.dto.RegisterRequest;
import com.synchrony.secure.media.api.model.Users;
import com.synchrony.secure.media.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing user-related operations.
 * This class provides methods for user registration, authentication, and saving user data.
 */
@Service
public class UserService {

    /**
     * Repository for performing CRUD operations on the `Users` entity.
     * This field is automatically injected by Spring.
     */
    @Autowired
    private UserRepository repo;

    /**
     * Sets the `UserRepository` instance.
     * This method is primarily used for testing purposes.
     *
     * @param repo The `UserRepository` instance to be set.
     */
    public void setRepo(UserRepository repo) {
        this.repo = repo;
    }

    /**
     * Registers a new user in the system.
     * This method creates a new `Users` entity from the provided registration request
     * and saves it to the database.
     *
     * @param request The registration request containing the user's details.
     * @return The saved `Users` entity.
     */
    public Users register(RegisterRequest request) {
        Users user = new Users();
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.email());
        return repo.save(user);
    }

    /**
     * Authenticates a user by their username and password.
     * This method checks the database for a user matching the provided credentials.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return An `Optional` containing the `Users` entity if authentication is successful,
     *         or an empty `Optional` if authentication fails.
     */
    public Optional<Users> authenticate(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }

    /**
     * Saves the user entity to the database.
     * This method is used to update or persist user data.
     *
     * @param user The `Users` entity to be saved.
     */
    public void save(Users user) {
        repo.save(user);
    }
}