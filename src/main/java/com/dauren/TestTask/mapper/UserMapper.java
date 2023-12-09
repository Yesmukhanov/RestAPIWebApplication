package com.dauren.TestTask.mapper;

import com.dauren.TestTask.dto.UserDto;
import com.dauren.TestTask.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);


    User toModel(UserDto userDto);
}
