package com.jc4balos.user_service.dto.user;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
@Data
public class NewUserDto {

    @NotEmpty(message = "First Name is required.")
    private String firstName;

    private String motherSurname;

    @NotEmpty(message = "Last Name is required.")
    private String fatherSurname;

    private String husbandSurname;

    @NotEmpty(message = "Username is required.")
    private String username;

    @NotEmpty(message = "Password is required.")
    private String password;

    @NotEmpty(message = "Email is required.")
    private String email;

    private String address_line_1;

    private String address_line_2;

    @NotNull(message = "Baranggay is required.")
    private Long addressBrgyId;

    @NotNull(message = "City/Municipality is required.")
    private Long addressCityId;

    @NotNull(message = "Province is required.")
    private Long addressProvinceId;

    @NotNull(message = "Province is required.")
    private Long addressRegionId;

    @NotNull(message = "Contact Number is required.")
    private String contactNumber;

}
