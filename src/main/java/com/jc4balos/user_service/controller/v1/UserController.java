package com.jc4balos.user_service.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.exception.ApplicationExceptionHandler;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<?> createUser(NewUserDto newUserDto, BindingResult bindingResult) {
        try {
            if (!bindingResult.hasErrors()) {
                return new ResponseEntity<>(/* TODO: insert create user service here */
                        HttpStatus.OK);
            } else {
                return ApplicationExceptionHandler.handleBadRequest(bindingResult);
            }
        } catch (Exception e) {
            return ApplicationExceptionHandler.handleCustomException(e);
        }

    }

}
