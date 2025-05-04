package com.synchrony.secure.media.api.repository;

import com.synchrony.secure.media.api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing `Users` entities.
 * This interface provides CRUD operations and custom query methods
 * for interacting with the `Users` table in the database.
 *
 * It extends the `JpaRepository` interface, which provides generic
 * methods for database operations.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    /**
     * Finds a user by their username and password.
     * This method is used to authenticate a user by matching their
     * credentials against the database records.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return An `Optional` containing the `Users` entity if a match is found,
     *         or an empty `Optional` if no match is found.
     */
    Optional<Users> findByUsernameAndPassword(String username, String password);

}