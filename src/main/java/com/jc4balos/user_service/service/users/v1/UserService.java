package com.jc4balos.user_service.service.users.v1;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.NewUserDto;

@Service
public interface UserService {
    Map<String, String> createUser(NewUserDto newUserDto);
}
