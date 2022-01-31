package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class EditUserRequest {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<String> roles;

    public EditUserRequest() {}
}
