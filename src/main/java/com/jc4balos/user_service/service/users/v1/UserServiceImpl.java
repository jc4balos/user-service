package com.jc4balos.user_service.service.users.v1;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.mapper.user_mapper.UserMapper;
import com.jc4balos.user_service.model.User;
import com.jc4balos.user_service.repository.UserRepository;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserMapper userMapper;

    private Logger logger;

    @Override
    @Transactional
    public Map<String, String> createUser(NewUserDto newUserDto) {
        User newUser = userMapper.newUserDto(newUserDto);
        userRepository.save(newUser);
        String message = "User " + newUser.getUsername() + " created successfully.";
        logger.info(message);
        return Map.of("message", message);
    }

}
