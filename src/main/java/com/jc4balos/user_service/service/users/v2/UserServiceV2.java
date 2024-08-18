package com.jc4balos.user_service.service.users.v2;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;

@Service
public interface UserServiceV2 {
    CompletableFuture<Map<String, String>> createUser(NewUserDto newUserDto);

    CompletableFuture<Map<String, Object>> getAllUsers(int pageIndex, int itemsPerPage, String searchParam,
            String sortBy, String order);

    CompletableFuture<Map<String, String>> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto);
}
