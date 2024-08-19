package com.jc4balos.user_service.mapper.user_mapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jc4balos.user_service.dto.user.ModifyUserInfoDto;
import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.dto.user.ViewUserDto;
import com.jc4balos.user_service.model.User;
import com.jc4balos.user_service.utils.PasswordHasher;

@Component
public class UserMapper {

    // TODO: use builder for better readability

    public User newUserDto(NewUserDto newUserDto) {
        User user = new User();
        user.setFirstName(newUserDto.getFirstName());
        user.setMotherSurname(newUserDto.getMotherSurname());
        user.setFatherSurname(newUserDto.getFatherSurname());
        user.setHusbandSurname(newUserDto.getHusbandSurname());
        user.setBirthDate(newUserDto.getBirthDate());
        user.setSex(newUserDto.getSex());
        user.setUsername(newUserDto.getUsername());
        user.setContactNumber(newUserDto.getContactNumber());
        user.setAddressLine1(newUserDto.getAddressLine1());
        user.setAddressLine2(newUserDto.getAddressLine2());
        user.setAddressLine3(newUserDto.getAddressLine3());
        user.setEmail(newUserDto.getEmail());

        Map<String, String> passwordDetails = new HashMap<>(PasswordHasher.createPassword(newUserDto.getPassword()));
        user.setPassword(passwordDetails.get("password"));
        user.setSalt(passwordDetails.get("salt"));
        user.setIsActive(true);

        return user;
    }

    public ViewUserDto viewUserDto(User user) {
        ViewUserDto viewUserDto = new ViewUserDto();
        viewUserDto.setAddressLine1(user.getAddressLine1());
        viewUserDto.setAddressLine2(user.getAddressLine2());
        viewUserDto.setAddressLine3(user.getAddressLine3());
        viewUserDto.setBirthDate(user.getBirthDate());
        viewUserDto.setContactNumber(user.getContactNumber());
        viewUserDto.setEmail(user.getEmail());
        viewUserDto.setFatherSurname(user.getFatherSurname());
        viewUserDto.setFirstName(user.getFirstName());
        viewUserDto.setHusbandSurname(user.getHusbandSurname());
        viewUserDto.setMotherSurname(user.getMotherSurname());
        viewUserDto.setSex(user.getSex());
        viewUserDto.setUserId(user.getUserId());
        viewUserDto.setUsername(user.getUsername());
        return viewUserDto;
    }

    public User modifyUserInfoDto(ModifyUserInfoDto modifyUserDto, User currentUser) {
        currentUser.setAddressLine1(modifyUserDto.getAddressLine1());
        currentUser.setAddressLine2(modifyUserDto.getAddressLine2());
        currentUser.setAddressLine3(modifyUserDto.getAddressLine3());
        currentUser.setBirthDate(modifyUserDto.getBirthDate());
        currentUser.setFatherSurname(modifyUserDto.getFatherSurname());
        currentUser.setFirstName(modifyUserDto.getFirstName());
        currentUser.setMotherSurname(modifyUserDto.getMotherSurname());
        currentUser.setHusbandSurname(modifyUserDto.getHusbandSurname());
        currentUser.setSex(modifyUserDto.getSex());
        return currentUser;
    }

}
