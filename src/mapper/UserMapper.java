package mapper;

import model.CreateUserDto;
import model.User;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;
import util.APIResponseTemplate;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public User fromCreateUserDtoToUser(CreateUserDto createUserDto){
        return new User(new Random().nextInt(9999),
                UUID.randomUUID().toString(),
                createUserDto.name(),
                createUserDto.email(),
                createUserDto.password(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRy3ueXVQSJZxzx0sSm-zGrZt_kQugr5O4acw&s");
    }
    public UserResponseDto fromUserToUserResponseDto(User user){
        return new UserResponseDto(user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getProfile());
    }
}
