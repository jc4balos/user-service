package com.jc4balos.user_service.controller.v1;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.jc4balos.user_service.dto.request.user.ChangeEmailDto;
import com.jc4balos.user_service.dto.request.user.ChangePasswordDto;
import com.jc4balos.user_service.dto.request.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.request.user.NewUserDto;
import com.jc4balos.user_service.exception.ApplicationExceptionHandler;
import com.jc4balos.user_service.service.users.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param newUserDto
     * @param bindingResult
     * @return response message
     */

    @PostMapping("/register")
    @Async
    CompletableFuture<ResponseEntity<?>> createUser(@Valid @RequestBody NewUserDto newUserDto,
            BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            return userService.createUser(newUserDto)
                    .exceptionally(e -> ApplicationExceptionHandler.handleCustomException(e));
        } else {
            return CompletableFuture.completedFuture(ApplicationExceptionHandler.handleBadRequest(bindingResult));
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
     */

    @GetMapping("/get-all")
    @Async
    public CompletableFuture<ResponseEntity<?>> getAllUsers(@RequestParam int pageIndex, @RequestParam int itemsPerPage,
            @RequestParam String searchParam, @RequestParam String sortBy, @RequestParam String order) {

        return userService.getAllUsers(pageIndex, itemsPerPage, searchParam, sortBy, order)
                .exceptionally(
                        e -> ApplicationExceptionHandler.handleCustomException(e));

    }

    /**
     * 
     * @param modifyUserInfoDto DTO of editable fields
     * @param bindingResult
     * @param userId            user id of user to be modified
     * @return response message
     */
    @PutMapping("/modify/{userId}")
    @Async
    public CompletableFuture<ResponseEntity<?>> modifyUser(@Valid @RequestBody ModifyUserInfoDto modifyUserInfoDto,
            BindingResult bindingResult,
            @PathVariable("userId") Long userId) {

        if (!bindingResult.hasErrors()) {
            return userService.modifyUserInfo(userId, modifyUserInfoDto).exceptionally(
                    e -> ApplicationExceptionHandler.handleCustomException(e));

        } else {
            return CompletableFuture.completedFuture(ApplicationExceptionHandler.handleBadRequest(bindingResult));
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
