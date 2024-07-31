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
     * @return
     */

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
     */

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

}
