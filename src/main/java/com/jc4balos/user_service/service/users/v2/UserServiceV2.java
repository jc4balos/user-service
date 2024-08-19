package com.jc4balos.user_service.service.users.v2;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;

@Service
public interface UserServiceV2 {
    CompletableFuture<ResponseEntity<?>> createUser(NewUserDto newUserDto);

    CompletableFuture<ResponseEntity<?>> getAllUsers(int pageIndex, int itemsPerPage, String searchParam,
            String sortBy, String order);

    CompletableFuture<ResponseEntity<?>> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto);
}
