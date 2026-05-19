package model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public record UpdateRequestDto(
        String name,
        String email,
        String password,
        String profile
) {


}
