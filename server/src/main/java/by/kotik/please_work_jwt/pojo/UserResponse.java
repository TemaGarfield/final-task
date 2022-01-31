package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;

    public UserResponse(Long id, String username, String email, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
