package com.jc4balos.user_service.service.users.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jc4balos.user_service.dto.request.user.ChangeEmailDto;
import com.jc4balos.user_service.dto.request.user.ChangePasswordDto;
import com.jc4balos.user_service.dto.request.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.request.user.NewUserDto;
import com.jc4balos.user_service.dto.response.user.ViewUserDto;
import com.jc4balos.user_service.mapper.user_mapper.UserMapper;
import com.jc4balos.user_service.model.User;
import com.jc4balos.user_service.repository.UserRepository;
import com.jc4balos.user_service.service.users.UserService;
import com.jc4balos.user_service.utils.JWTService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTService jwtService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Transactional
    @Async
    public CompletableFuture<ResponseEntity<?>> createUser(NewUserDto newUserDto) {

        User newUser = userMapper.newUserDto(newUserDto);

        // TODO: make a guard clause for duplicate usernames and return username already
        // exists
        Optional.ofNullable(userRepository.findByUsername(newUserDto.getUsername()))
                .ifPresent(user -> {
                    throw new RuntimeException("Username already used.");
                });

        Optional.ofNullable(userRepository.findByEmail(newUserDto.getEmail()))
                .ifPresent(user -> {
                    throw new RuntimeException("Email already used.");
                });

        userRepository.save(newUser);
        String message = "User " + newUser.getUsername() + " created successfully.";
        logger.info(message);
        ResponseEntity<?> response = new ResponseEntity<>(Map.of("message", message), HttpStatus.CREATED);
        return CompletableFuture.completedFuture(response);

    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> getAllUsers(int pageIndex, int itemsPerPage, String searchParam,
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

        Map<String, Object> data = Map.of("pageIndex", users.getNumber(),
                "totalPages", users.getTotalPages(),
                "users", viewUserDtos);

        ResponseEntity<?> response = new ResponseEntity<>(data, HttpStatus.OK);

        return CompletableFuture.completedFuture(response);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<ResponseEntity<?>> modifyUserInfo(Long userId, ModifyUserInfoDto modifyUserInfoDto) {
        Optional<User> userToBeModified = Optional.of(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist")));

        User modifiedUser = userMapper.modifyUserInfoDto(modifyUserInfoDto, userToBeModified.get());
        userRepository.save(modifiedUser);
        Map<String, String> data = Map.of("message", modifiedUser.getUsername() + " successfully modified.");

        ResponseEntity<?> response = new ResponseEntity<>(data, HttpStatus.OK);
        return CompletableFuture
                .completedFuture(response);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<ResponseEntity<?>> changeEmail(Long userId, ChangeEmailDto changeEmailDto) {
        Optional<User> user = Optional.of(userRepository.findById(userId))
                .orElseThrow(() -> new RuntimeException("User doesn't exist."));

        User thisUser = user.get();

        thisUser.setEmail(changeEmailDto.getNewEmail());
        userRepository.save(thisUser);

        Map<String, String> data = Map.of("message",
                thisUser.getUsername() + " email was successfully modified.");
        ResponseEntity<?> response = new ResponseEntity<>(data, HttpStatus.OK);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<ResponseEntity<?>> changePassword(Long userId, ChangePasswordDto changePasswordDto) {
        Optional<User> user = Optional.of(userRepository.findById(userId))
                .orElseThrow(() -> new RuntimeException("User doesn't exist."));

        User thisUser = user.get();

        // check if old password matches the password in db
        Boolean isPasswordCorrect = bCryptPasswordEncoder.matches(changePasswordDto.getOldPassword(),
                thisUser.getPassword());
        if (!isPasswordCorrect) {
            throw new RuntimeException("Incorrect old Password. Please try again.");
        }

        // check if newpassword matches confirm new password
        if (!changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmNewPassword())) {
            throw new RuntimeException("New password and confirm password doesn't match. Please try again.");
        }

        String hashedNewPassword = bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword());

        thisUser.setPassword(hashedNewPassword);
        userRepository.save(thisUser);
        Map<String, String> data = Map.of("message", user.get().getUsername() + " password was successfully modified.");
        ResponseEntity<?> response = new ResponseEntity<>(data, HttpStatus.OK);
        return CompletableFuture.completedFuture(response);
    }

    @Override
    @Async
    public CompletableFuture<ResponseEntity<?>> login(String credentials, HttpServletResponse response) {
        String pair = new String(Base64.decodeBase64(credentials.substring(6)));
        String username = pair.split(":")[0];
        String password = pair.split(":")[1];

        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(username);
            response.addHeader("Authorization", "Bearer " + token);
            return CompletableFuture.completedFuture(new ResponseEntity<>("Login Sucess", HttpStatus.ACCEPTED));
        } else {
            return CompletableFuture.completedFuture(new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED));
        }

    }

}
