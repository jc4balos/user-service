package com.jc4balos.user_service.dto.user;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserDto {

    @NotNull(message = "First name is required.")
    private String firstName;

    private String motherSurname;

    @NotEmpty(message = "Father surnamme is required.")
    private String fatherSurname;

    private String husbandSurname;

    @NotEmpty(message = "Username is required.")
    private String username;

    @NotEmpty(message = "Password is required.")
    private String password;

    @NotEmpty(message = "Email is required.")
    @Email(message = "Email should be valid")
    private String email;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    @NotEmpty(message = "Contact Number is required.")
    private String contactNumber;

    @NotNull(message = "Birthdate is required.")
    private LocalDate birthDate;

    @NotEmpty(message = "Sex is required.")
    private String sex;

    @NotNull(message = "Please set the user status")
    private boolean isActive;
}
