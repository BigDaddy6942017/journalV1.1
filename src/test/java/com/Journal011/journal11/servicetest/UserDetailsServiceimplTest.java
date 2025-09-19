package com.Journal011.journal11.servicetest;

import com.Journal011.journal11.entity.User;
import com.Journal011.journal11.repository.UserRepository;
import com.Journal011.journal11.service.UserDetailsServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceimplTest {

    @InjectMocks
    private UserDetailsServiceimpl userDetailsServiceimpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest() {
        // Create a mock UserEntity
        User mockUser = new User();
        mockUser.setUserName("yashna25");
        mockUser.setPassword("yugang27");
        mockUser.setRoles(Collections.singletonList("USER"));

        // Mock the repository call
        when(userRepository.findByUserName("yashna")).thenReturn(mockUser);

        // Call the service method
        UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername("yashna");

        // Assertions
        assertNotNull(userDetails);
        assertEquals("yashna25", userDetails.getUsername());
        assertEquals("yugang27", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
    }
}