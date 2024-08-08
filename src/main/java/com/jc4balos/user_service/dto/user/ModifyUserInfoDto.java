package com.jc4balos.user_service.dto.user;

import java.time.LocalDate;

import com.jc4balos.user_service.values.UserSex;

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
public class ModifyUserInfoDto {

    @NotEmpty(message = "First name is required.")
    private String firstName;

    private String motherSurname;

    @NotEmpty(message = "Father surnamme is required.")
    private String fatherSurname;

    private String husbandSurname;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    @NotNull(message = "Birthdate is required.")
    private LocalDate birthDate;

    @NotEmpty(message = "Sex is required.")
    private UserSex sex;

}
