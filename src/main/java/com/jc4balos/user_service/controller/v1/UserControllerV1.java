package com.jc4balos.user_service.controller.v1;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jc4balos.user_service.dto.user.ChangeEmailDto;
import com.jc4balos.user_service.dto.user.ChangePasswordDto;
import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.exception.ApplicationExceptionHandler;
import com.jc4balos.user_service.service.users.v1.UserServiceV1;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserControllerV1 {

    @Autowired
    private UserServiceV1 userService;

    /**
     * @param newUserDto
     * @param bindingResult
     * @return response message
     * @deprecated this is deprecated since newer version uses async
     */
    @Deprecated
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(userService.createUser(newUserDto),
                        HttpStatus.OK);
            } else {
                return ApplicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return ApplicationExceptionHandler.handleCustomException(e);
        }

    }

    /**
     * @param pageIndex    page index of the users to get
     * @param itemsPerPage the number of records to be returned per page
     * @param searchParam  string that will match when searchng the records
     * @param sortBy       string from the database column name. The response will
     *                     be sorted by this.
     * @param order        sorting mechanism ("ASCENDING", "DESCENDING")
     * @return All users in the parameter
     * @deprecated this is deprecated since newer version uses async
     * 
     */
    @Deprecated
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(@RequestParam int pageIndex, @RequestParam int itemsPerPage,
            @RequestParam String searchParam, @RequestParam String sortBy, @RequestParam String order) {
        try {

            return new ResponseEntity<>(
                    userService.getAllUsers(pageIndex, itemsPerPage, searchParam, sortBy, order),
                    HttpStatus.OK);

        } catch (Exception e) {
            return ApplicationExceptionHandler.handleCustomException(e);
        }

    }

    /**
     * 
     * @param modifyUserInfoDto DTO of editable fields
     * @param bindingResult
     * @param userId            user id of user to be modified
     * @return response message
     * @deprecated this is deprecated since newer version uses async
     * 
     */
    @Deprecated
    @PutMapping("/modify/{userId}")
    public ResponseEntity<?> modifyUser(@Valid @RequestBody ModifyUserInfoDto modifyUserInfoDto,
            @PathVariable("userId") Long userId,
            BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(userService.modifyUserInfo(userId, modifyUserInfoDto),
                        HttpStatus.OK);
            } else {
                return ApplicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return ApplicationExceptionHandler.handleCustomException(e);
        }
    }

    @PatchMapping("/change-email/{userId}")
    @Async
    public CompletableFuture<ResponseEntity<?>> changeEmail(@PathVariable("userId") Long userId,
            @Valid @RequestBody ChangeEmailDto newEmail,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return userService.changeEmail(userId, newEmail).exceptionally(
                    e -> ApplicationExceptionHandler.handleCustomException(e));

        } else {
            return CompletableFuture.completedFuture(ApplicationExceptionHandler.handleBadRequest(bindingResult));
        }

    }

    @PatchMapping("/change-password/{userId}")
    @Async
    public CompletableFuture<ResponseEntity<?>> changePassword(@PathVariable("userId") Long userId,
            @Valid @RequestBody ChangePasswordDto passwordDto,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return userService.changePassword(userId, passwordDto).exceptionally(
                    e -> ApplicationExceptionHandler.handleCustomException(e));

        } else {
            return CompletableFuture.completedFuture(ApplicationExceptionHandler.handleBadRequest(bindingResult));
        }

    }

    // TODO: @PatchMapping("/change-contact-number/{userId}")

}
