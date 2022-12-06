package com.example.demo.mappers;

import com.example.demo.dto.UserCreationDto;
import com.example.demo.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserCreationDto toUserDto(User user);

    User userToEntity(UserCreationDto userDto);
    List<UserCreationDto> toUserDtos(List<User> users);
    List<User> toEntities(List<UserCreationDto> userDtos);

}
