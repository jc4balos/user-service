package com.jc4balos.user_service.service.users.v1;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.dto.user.ViewUserDto;

@Service
public interface UserService {
    Map<String, String> createUser(NewUserDto newUserDto);

    List<ViewUserDto> getAllUsers(int pageNumber, int itemsPerPage, String searchParam, String sortBy, String order);
}
