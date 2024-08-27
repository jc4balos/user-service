package com.jc4balos.user_service.service.users;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.ChangeEmailDto;
import com.jc4balos.user_service.dto.user.ChangePasswordDto;
import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;

@Service
public interface UserService {

    CompletableFuture<ResponseEntity<?>> changeEmail(Long userId, ChangeEmailDto changeEmailDto);

    CompletableFuture<ResponseEntity<?>> changePassword(Long userId, ChangePasswordDto changePasswordDto);

    CompletableFuture<ResponseEntity<?>> createUser(NewUserDto newUserDto);

    CompletableFuture<ResponseEntity<?>> getAllUsers(int pageIndex, int itemsPerPage, String searchParam,
            String sortBy, String order);

    CompletableFuture<ResponseEntity<?>> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto);

}
