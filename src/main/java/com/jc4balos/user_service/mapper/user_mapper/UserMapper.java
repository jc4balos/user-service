package com.jc4balos.user_service.mapper.user_mapper;

import org.springframework.stereotype.Component;

import com.jc4balos.user_service.dto.user.NewUserDto;
import com.jc4balos.user_service.model.User;
import com.jc4balos.user_service.utils.StringHasher;

@Component
public class UserMapper {

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
        user.setAddressBrgyId(newUserDto.getAddressBrgyId());
        user.setAddressCityId(newUserDto.getAddressCityId());
        user.setAddressProvinceId(newUserDto.getAddressProvinceId());
        user.setAddressRegionId(newUserDto.getAddressRegionId());
        user.setAddressLine1(newUserDto.getAddressLine1());
        user.setAddressLine2(newUserDto.getAddressLine2());
        user.setEmail(newUserDto.getEmail());
        user.setPassword(StringHasher.hashString(newUserDto.getPassword(), newUserDto.getEmail()));
        user.setIsActive(true);
        return user;
    }

}
