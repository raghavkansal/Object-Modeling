package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@DisplayName("Tests for User Service")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    //act
    @Mock
    IUserRepository userRepositoryMock;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("addNewUser() Test")
    void addUserTest() {
        //setup
        User expectedUser = new User("1","Raghav");
        when(userRepositoryMock.insertUser(any(User.class))).thenReturn(expectedUser);

        //act
        User actualUser = userService.addNewUser("Raghav");

        //verification
        Assertions.assertEquals(expectedUser, actualUser);
        verify(userRepositoryMock,times(1)).insertUser(any(User.class));
    }
}
