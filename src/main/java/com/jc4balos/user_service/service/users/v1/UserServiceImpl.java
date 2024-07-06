package com.jc4balos.user_service.service.users.v1;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.NewUserDto;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Transactional
    public Map<String, String> createUser(NewUserDto newUserDto) {
        // TODO: add create user implementation here
        return null;
    }

}
