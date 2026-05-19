package controller;

import model.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;
import model.service.UserService;
import model.service.UserServiceImpl;
import util.APIResponseTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImpl();

    public APIResponseTemplate<UserResponseDto> createUser(CreateUserDto createUserDto) {
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("User Created Successfully")
                .timeStamp(LocalDate.now())
                .data(userService.createUser(createUserDto))
                .build();
    }

    public APIResponseTemplate<List<UserResponseDto>> getAllUsers() {
        return APIResponseTemplate.<List<UserResponseDto>>builder()
                .status(200)
                .message("Get All Users Successfully")
                .timeStamp(LocalDate.now())
                .data(userService.getAllUser())
                .build();
    }

    public APIResponseTemplate<UserResponseDto> searchUserByUuid(String uuid) {
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("Found User")
                .timeStamp(LocalDate.now())
                .data(userService.getUserByUuid(uuid))
                .build();
    }

    public APIResponseTemplate<UserResponseDto> updateUser(String uuid, UpdateRequestDto updateRequestDto) {
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("Update User Successfully")
                .timeStamp(LocalDate.now())
                .data(userService.updateUserByUuid(uuid, updateRequestDto))
                .build();
    }

    public APIResponseTemplate<Integer> deleteUser(String uuid) {
        return APIResponseTemplate.<Integer>builder()
                .status(200)
                .message("Delete User Successfully")
                .timeStamp(LocalDate.now())
                .data(userService.deleteUserByUuid(uuid))
                .build();
    }

    public APIResponseTemplate<List<UserResponseDto>> searchByName(String name) {
        return APIResponseTemplate.<List<UserResponseDto>>builder()
                .status(200)
                .message("Found User")
                .timeStamp(LocalDate.now())
                .data(Collections.singletonList(userService.searchUserByName(name)))
                .build();
    }
}
