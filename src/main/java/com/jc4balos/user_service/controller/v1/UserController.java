package com.jc4balos.user_service.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.exception.ApplicationExceptionHandler;
import com.jc4balos.user_service.service.users.v1.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
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

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(@RequestParam int pageNumber, @RequestParam int itemsPerPage,
            @RequestParam String searchParam) {
        try {

            return new ResponseEntity<>(// TODO: insert service here,
                    HttpStatus.OK);

        } catch (Exception e) {
            return ApplicationExceptionHandler.handleCustomException(e);
        }

    }

}
