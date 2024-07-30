package com.jc4balos.user_service.service.users.v1;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.dto.user.ViewUserDto;
import com.jc4balos.user_service.mapper.user_mapper.UserMapper;
import com.jc4balos.user_service.model.User;
import com.jc4balos.user_service.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    public Map<String, String> createUser(NewUserDto newUserDto) {
        User newUser = userMapper.newUserDto(newUserDto);
        userRepository.save(newUser);
        String message = "User " + newUser.getUsername() + " created successfully.";
        logger.info(message);
        return Map.of("message", message);
    }

    @Override
    public List<ViewUserDto> getAllUsers(int pageNumber, int itemsPerPage, String searchParam, String sortBy,
            String order) {
        // TODO: implement getAllUsers service\
        Sort sort;

        if (order == "ASCENDING") {
            sort = Sort.by(sortBy).ascending();
        } else if (order == "DESCENDING") {
            sort = Sort.by(sortBy).descending();
        } else {
            String message = "Cannot fetch users. Unknown sorting order.";
            logger.error(message);
            throw new RuntimeException(message);
        }

        Pageable pageAndSort = PageRequest.of(pageNumber, itemsPerPage, sort);
        Page<User> users = userRepository.findBySearchParam(searchParam, pageAndSort);

        // TODO: map users to dto for security
        return null;
    }
}
