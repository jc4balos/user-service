package com.jc4balos.user_service.service.users;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.request.user.ChangeEmailDto;
import com.jc4balos.user_service.dto.request.user.ChangePasswordDto;
import com.jc4balos.user_service.dto.request.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.request.user.NewUserDto;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface UserService {

    CompletableFuture<ResponseEntity<?>> changeEmail(Long userId, ChangeEmailDto changeEmailDto);

    CompletableFuture<ResponseEntity<?>> changePassword(Long userId, ChangePasswordDto changePasswordDto);

    CompletableFuture<ResponseEntity<?>> createUser(NewUserDto newUserDto);

    CompletableFuture<ResponseEntity<?>> getAllUsers(int pageIndex, int itemsPerPage, String searchParam,
            String sortBy, String order);

    CompletableFuture<ResponseEntity<?>> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto);

    CompletableFuture<ResponseEntity<?>> login(String authentication, HttpServletResponse response);

}
