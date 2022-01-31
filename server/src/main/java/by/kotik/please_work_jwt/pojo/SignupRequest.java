package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
