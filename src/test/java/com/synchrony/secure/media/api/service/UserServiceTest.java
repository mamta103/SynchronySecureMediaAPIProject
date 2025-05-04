package com.synchrony.secure.media.api.service;

        import com.synchrony.secure.media.api.dto.RegisterRequest;
        import com.synchrony.secure.media.api.model.Users;
        import com.synchrony.secure.media.api.repository.UserRepository;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.ArgumentCaptor;

        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

        class UserServiceTest {

            private UserRepository userRepository;
            private UserService userService;

            @BeforeEach
            void setUp() {
                userRepository = mock(UserRepository.class);
                userService = new UserService();
                userService.setRepo(userRepository); // Use the setter to inject the mock
            }

            /**
             * Test the register method to ensure a user is correctly created and saved.
             */
            @Test
            void testRegister() {
                RegisterRequest request = new RegisterRequest("john_doe", "password123", "john@example.com");
                Users mockSavedUser = new Users();
                mockSavedUser.setUsername("john_doe");
                mockSavedUser.setPassword("password123");
                mockSavedUser.setEmail("john@example.com");

                when(userRepository.save(any(Users.class))).thenReturn(mockSavedUser);

                Users result = userService.register(request);

                assertEquals("john_doe", result.getUsername());
                assertEquals("password123", result.getPassword());
                assertEquals("john@example.com", result.getEmail());

                verify(userRepository, times(1)).save(any(Users.class));
            }

            /**
             * Test the authenticate method when credentials are valid.
             */
            @Test
            void testAuthenticate_Valid() {
                Users user = new Users();
                user.setUsername("john_doe");
                user.setPassword("password123");

                when(userRepository.findByUsernameAndPassword("john_doe", "password123"))
                        .thenReturn(Optional.of(user));

                Optional<Users> result = userService.authenticate("john_doe", "password123");

                assertTrue(result.isPresent());
                assertEquals("john_doe", result.get().getUsername());
            }

            /**
             * Test the authenticate method when credentials are invalid.
             */
            @Test
            void testAuthenticate_Invalid() {
                when(userRepository.findByUsernameAndPassword("john_doe", "wrongpass"))
                        .thenReturn(Optional.empty());

                Optional<Users> result = userService.authenticate("john_doe", "wrongpass");

                assertFalse(result.isPresent());
            }

            /**
             * Test the save method to ensure the repository is called.
             */
            @Test
            void testSave() {
                Users user = new Users();
                user.setUsername("alice");
                userService.save(user);

                verify(userRepository, times(1)).save(user);
            }
        }