package model.service;

import model.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUser();
    UserResponseDto getUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid , UpdateRequestDto updateRequestDto);
    int deleteUserByUuid(String uuid);
    UserResponseDto createUser(CreateUserDto user);
    UserResponseDto searchUserByName(String name);


}
