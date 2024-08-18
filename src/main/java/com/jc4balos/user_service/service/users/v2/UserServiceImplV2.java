package com.jc4balos.user_service.service.users.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.dto.user.ViewUserDto;
import com.jc4balos.user_service.mapper.user_mapper.UserMapper;
import com.jc4balos.user_service.model.User;
import com.jc4balos.user_service.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplV2 implements UserServiceV2 {

    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImplV2.class);

    @Override
    @Transactional
    @Async
    public CompletableFuture<Map<String, String>> createUser(NewUserDto newUserDto) {
        User newUser = userMapper.newUserDto(newUserDto);

        // TODO: make a guard clause for duplicate usernames and return username already
        // e

        userRepository.save(newUser);
        String message = "User " + newUser.getUsername() + " created successfully.";
        logger.info(message);
        return CompletableFuture.completedFuture(Map.of("message", message));
    }

    @Override
    public CompletableFuture<Map<String, Object>> getAllUsers(int pageIndex, int itemsPerPage, String searchParam,
            String sortBy,
            String order) {

        List<ViewUserDto> viewUserDtos = new ArrayList<>();
        Sort sort;

        System.out.println("SORTING ORDER: " + order);

        if (order.equals("ASCENDING")) {
            sort = Sort.by(sortBy).ascending();
        } else if (order.equals("DESCENDING")) {
            sort = Sort.by(sortBy).descending();
        } else {
            String message = "Cannot fetch users. Unknown sorting order.";
            logger.error(message);
            throw new RuntimeException(message);
        }

        Pageable pageAndSort = PageRequest.of(pageIndex, itemsPerPage, sort);
        Page<User> users = userRepository.findBySearchParam(searchParam, pageAndSort);

        for (User user : users) {
            ViewUserDto mappedUser = userMapper.viewUserDto(user);
            viewUserDtos.add(mappedUser);
        }

        Map<String, Object> response = Map.of("pageIndex", users.getNumber(),
                "totalPages", users.getTotalPages(),
                "users", viewUserDtos);

        return CompletableFuture.completedFuture(response);
    }

    @Override
    @Transactional
    public CompletableFuture<Map<String, String>> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto) {
        Optional<User> userToBeModified = Optional.of(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist")));

        User modifiedUser = userMapper.modifyUserInfoDto(modifyUserInfoDto, userToBeModified.get());
        userRepository.save(modifiedUser);
        return CompletableFuture
                .completedFuture(Map.of("message", modifiedUser.getUsername() + " successfully modified."));
    }

}
