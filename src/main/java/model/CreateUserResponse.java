package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {
    private User user;
    private String accessToken;
    private String refreshToken;
    private Boolean success;
    private String message;
}
