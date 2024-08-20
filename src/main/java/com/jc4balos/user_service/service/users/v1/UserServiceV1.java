package com.jc4balos.user_service.service.users.v1;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.ChangeEmailDto;
import com.jc4balos.user_service.dto.user.ChangePasswordDto;
import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;

@Service
public interface UserServiceV1 {

    @Deprecated
    Map<String, String> createUser(NewUserDto newUserDto);

    @Deprecated
    Map<String, Object> getAllUsers(int pageIndex, int itemsPerPage, String searchParam, String sortBy, String order);

    @Deprecated
    Map<String, String> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto);

    CompletableFuture<ResponseEntity<?>> changeEmail(Long userId, ChangeEmailDto changeEmailDto);

    CompletableFuture<ResponseEntity<?>> changePassword(Long userId, ChangePasswordDto changePasswordDto);

}
