package com.synchrony.secure.media.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for authentication requests.
 * This class is used to encapsulate the username and password
 * provided by the user during the authentication process.
 *
 * The `AuthRequest` is implemented as a Java record, which is a
 * concise way to define immutable data objects.
 *
 * @param username The username of the user attempting to authenticate.
 * @param password The password of the user attempting to authenticate.
 */
public record AuthRequest(String username, String password) {}