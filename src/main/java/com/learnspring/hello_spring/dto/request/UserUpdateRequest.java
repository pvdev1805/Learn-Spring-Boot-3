package com.learnspring.hello_spring.dto.request;

import com.learnspring.hello_spring.validator.DobConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String password;
    String firstName;
    String lastName;

    @DobConstraint(min=18, message = "INVALID_DOB")
    LocalDate dateOfBirth;
    List<String> roles;
}
