package com.jc4balos.user_service.dto.user;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewUserDto {

    private Long userId;

    private String firstName;

    private String motherSurname;

    private String fatherSurname;

    private String husbandSurname;

    private String username;

    private String email;

    private String addressLine1;

    private String addressLine2;

    private Long addressBrgyId;

    private Long addressCityId;

    private Long addressProvinceId;

    private Long addressRegionId;

    private String contactNumber;

    private LocalDate birthDate;

    private String sex;

}
