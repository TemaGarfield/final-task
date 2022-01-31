package by.kotik.please_work_jwt.service.utils;

import by.kotik.please_work_jwt.model.ERole;
import by.kotik.please_work_jwt.model.Role;
import by.kotik.please_work_jwt.repository.RoleRepository;
import by.kotik.please_work_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ServiceUtils {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public ServiceUtils(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public Boolean checker(String username, String email) {
        return userRepository.existsByUsername(username) || userRepository.existsByEmail(email);
    }

    public Set<Role> rolesGenerator(Set<String> roles) {
        Set<Role> userRoles = new HashSet<>();

        if (roles == null || roles.isEmpty()) {
            userRoles.add(
                    roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Role is not found")));
            return userRoles;
        }

        roles.forEach(role -> {
            userRoles.add(roleRepository.findByName(ERole.valueOf(role))
                    .orElseThrow(() -> new RuntimeException("Role is not found")));
        });

        return userRoles;
    }
}
