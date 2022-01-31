package by.kotik.please_work_jwt.pojo;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
