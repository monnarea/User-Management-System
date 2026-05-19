package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public record UserResponseDto(
        String uuid,
        String name,
        String email,
        String profile
) { }
