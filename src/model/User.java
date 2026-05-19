package model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class User {
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    private String password;
    private String profile;
}
