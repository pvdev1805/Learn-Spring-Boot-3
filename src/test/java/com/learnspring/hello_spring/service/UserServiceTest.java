package com.learnspring.hello_spring.service;

import com.learnspring.hello_spring.dto.request.UserCreationRequest;
import com.learnspring.hello_spring.dto.response.UserResponse;
import com.learnspring.hello_spring.entity.User;
import com.learnspring.hello_spring.exception.AppException;
import com.learnspring.hello_spring.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;

    @BeforeEach
    void initData(){
        dob = LocalDate.of(1997, 9, 2);

        request = UserCreationRequest.builder()
                .username("jayden14")
                .firstName("Doctor")
                .lastName("Strange")
                .password("123456789")
                .dateOfBirth(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("8b149b2d-2ddb-474e-9342-b20000cd49c7")
                .username("jayden14")
                .firstName("Doctor")
                .lastName("Strange")
                .dateOfBirth(dob)
                .build();

        user = User.builder()
                .id("8b149b2d-2ddb-474e-9342-b20000cd49c7")
                .username("jayden14")
                .firstName("Doctor")
                .lastName("Strange")
                .dateOfBirth(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        // GIVEN
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(userRepository.save(ArgumentMatchers.any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);

        // THEN
        Assertions.assertThat(response.getId()).isEqualTo("8b149b2d-2ddb-474e-9342-b20000cd49c7");
        Assertions.assertThat(response.getUsername()).isEqualTo("jayden14");
    }

    @Test
    void createUser_userExisted_failure(){
        // GIVEN
        Mockito.when(userRepository.existsByUsername(ArgumentMatchers.anyString())).thenReturn(true);

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    }

    @Test
    @WithMockUser(username = "jayden14")
    void getMyInfo_valid_success(){
        Mockito.when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Optional.of(user));

        var response = userService.getMyInfo();

        Assertions.assertThat(response.getUsername()).isEqualTo("jayden14");
        Assertions.assertThat(response.getId()).isEqualTo("8b149b2d-2ddb-474e-9342-b20000cd49c7");
    }

    @Test
    @WithMockUser(username = "jayden14")
    void getMyInfo_userNotFound_error(){
        Mockito.when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(null));

        // WHEN
        var exception = org.junit.jupiter.api.Assertions.assertThrows(AppException.class,
                () -> userService.getMyInfo());

        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
    }
}
