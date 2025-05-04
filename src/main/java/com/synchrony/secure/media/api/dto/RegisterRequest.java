package com.synchrony.secure.media.api.dto;

/**
 * Data Transfer Object (DTO) for user registration requests.
 * This class is used to encapsulate the data provided by the user
 * during the registration process.
 *
 * The `RegisterRequest` is implemented as a Java record, which is a
 * concise way to define immutable data objects.
 *
 * @param username The username of the user registering.
 * @param password The password of the user registering.
 * @param email    The email address of the user registering.
 */
public record RegisterRequest(String username, String password, String email) {}