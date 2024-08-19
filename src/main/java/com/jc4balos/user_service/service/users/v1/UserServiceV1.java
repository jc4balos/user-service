package com.jc4balos.user_service.service.users.v1;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;

@Service
public interface UserServiceV1 {
    //
    /**
     * @deprecated
     *             This method is deprecated due to it is not asynchronous (doesn't
     *             use threading)
     */
    @Deprecated
    Map<String, String> createUser(NewUserDto newUserDto);

    //
    /**
     * @deprecated
     *             This method is deprecated due to it is not asynchronous (doesn't
     *             use threading)
     */
    @Deprecated
    Map<String, Object> getAllUsers(int pageIndex, int itemsPerPage, String searchParam, String sortBy, String order);

    //
    /**
     * @deprecated
     *             This method is deprecated due to it is not asynchronous (doesn't
     *             use threading)
     */
    @Deprecated
    Map<String, String> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto);
}
