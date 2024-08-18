package com.jc4balos.user_service.controller.v2;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.exception.AsyncApplicationExceptionHandler;
import com.jc4balos.user_service.service.users.v2.UserServiceV2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v2/users")
@RequiredArgsConstructor
public class UserControllerV2 {

    @Autowired
    private UserServiceV2 userService;

    /**
     * @param newUserDto
     * @param bindingResult
     * @return response message
     */

    @PostMapping("/create")
    @Async
    CompletableFuture<ResponseEntity<?>> createUser(@Valid @RequestBody NewUserDto newUserDto,
            BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                return userService.createUser(newUserDto).thenApply(response -> new ResponseEntity<>(response,
                        HttpStatus.OK));
            } else {
                return AsyncApplicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return AsyncApplicationExceptionHandler.handleCustomException(e);
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
        try {

            return userService.getAllUsers(pageIndex, itemsPerPage, searchParam, sortBy, order)
                    .thenApply(response -> new ResponseEntity<>(response,
                            HttpStatus.OK));

        } catch (Exception e) {
            return AsyncApplicationExceptionHandler.handleCustomException(e);
        }

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
        try {
            if (!bindingResult.hasErrors()) {
                return userService.modifyUserInfo(userId, modifyUserInfoDto)
                        .thenApply(response -> new ResponseEntity<>(response,
                                HttpStatus.OK));
            } else {
                return AsyncApplicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return AsyncApplicationExceptionHandler.handleCustomException(e);
        }
    }

    // TODO: @PatchMapping("/change-email/{userId}")

    // TODO: @PatchMapping("/change-password/{userId}")

    // TODO: @PatchMapping("/change-contact-number/{userId}")

}
