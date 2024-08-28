package com.jc4balos.user_service.dto.request.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {

    @NotEmpty(message = "Old Password is required")
    private String oldPassword;

    @NotEmpty(message = "New Password is required")
    private String newPassword;

    @NotEmpty(message = "Please re-enter your new password")
    private String confirmNewPassword;
}
