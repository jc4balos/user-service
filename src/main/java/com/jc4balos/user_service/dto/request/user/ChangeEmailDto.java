package com.jc4balos.user_service.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeEmailDto {
    @Email(message = "Email is not valid.")
    @NotBlank(message = "Email is not valid.")
    private String newEmail;
}
