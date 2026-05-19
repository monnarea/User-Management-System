package model.service;

import mapper.UserMapper;
import model.CreateUserDto;
import model.User;
import model.UserDao;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;

import java.util.*;

public class UserServiceImpl implements UserService {
    private final UserMapper userMapper = new UserMapper();
    private final UserDao userDao = new UserDao();

    @Override
    public List<UserResponseDto> getAllUser() {
        return userDao.findAll().stream()
                .map(userMapper::fromUserToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        return userDao.findAll().stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .map(userMapper::fromUserToUserResponseDto)
                .orElseThrow(() -> new RuntimeException("User not found with UUID: " + uuid));
    }

    @Override
    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto) {
        User user = userDao.findAll().stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with UUID: " + uuid));
        user.setName(updateRequestDto.name());
        user.setEmail(updateRequestDto.email());
        user.setPassword(updateRequestDto.password());
        user.setProfile(updateRequestDto.profile());
        userDao.update(user);
        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public int deleteUserByUuid(String uuid) {
        User user = userDao.findAll().stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with UUID: " + uuid));
        userDao.remove(user);
        return 1;
    }

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.fromCreateUserDtoToUser(createUserDto);
        userDao.save(user);
        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto searchUserByName(String name) {
        return userDao.findAll().stream()
                .filter(u -> u.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .map(userMapper::fromUserToUserResponseDto)
                .orElseThrow(() -> new RuntimeException("User not found with Name: " + name));
    }
}
