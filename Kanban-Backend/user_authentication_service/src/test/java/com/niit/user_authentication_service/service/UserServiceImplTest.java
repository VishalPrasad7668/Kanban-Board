package com.niit.user_authentication_service.service;

import com.niit.user_authentication_service.domain.User;
import com.niit.user_authentication_service.exception.UserAlreadyExitException;
import com.niit.user_authentication_service.repositroy.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user1;

    @BeforeEach
    void setUp() {
        user1 =new User("ks@gmail.com","ks123");
    }

    @AfterEach
    void tearDown() {
        user1=null;
    }
    @Test
    public void saveUserSuccess() throws UserAlreadyExitException {
        when(userRepository.findById(user1.getEmailId())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(any())).thenReturn(user1);
        assertEquals(user1,userService.saveUser(user1));
        verify(userRepository,times(1)).save(any());
        verify(userRepository,times(1)).findById(any());
    }

    @Test
    public void saveUserFailure(){
        when(userRepository.findById(user1.getEmailId())).thenReturn(Optional.ofNullable(user1));
        assertThrows(UserAlreadyExitException.class,()->userService.saveUser(user1));
        verify(userRepository,times(0)).save(any());
        verify(userRepository,times(1)).findById(any());
    }
}
