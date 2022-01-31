package by.kotik.please_work_jwt.pojo;

import by.kotik.please_work_jwt.model.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
