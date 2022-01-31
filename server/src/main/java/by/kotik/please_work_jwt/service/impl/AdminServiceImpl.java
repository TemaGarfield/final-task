package by.kotik.please_work_jwt.service.impl;

import by.kotik.please_work_jwt.model.Role;
import by.kotik.please_work_jwt.model.User;
import by.kotik.please_work_jwt.pojo.EditUserRequest;
import by.kotik.please_work_jwt.pojo.SignupRequest;
import by.kotik.please_work_jwt.pojo.UserResponse;
import by.kotik.please_work_jwt.repository.RoleRepository;
import by.kotik.please_work_jwt.repository.UserRepository;
import by.kotik.please_work_jwt.service.AdminService;
import by.kotik.please_work_jwt.service.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ServiceUtils serviceUtils;

    @Autowired
    public AdminServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            ServiceUtils serviceUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.serviceUtils = serviceUtils;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            users.add(new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getRoles()
                    ));
        });

        return users;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> getUserRoles(Long id) {
        return userRepository.getById(id).getRoles();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Boolean saveUser(SignupRequest userRequest) {
        if (serviceUtils.checker(userRequest.getUsername(), userRequest.getEmail())) {
            return false;
        }

        User user = new User(
                userRequest.getUsername(),
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()),
                serviceUtils.rolesGenerator(userRequest.getRoles())
                );

        userRepository.save(user);

        return true;
    }

    @Override
    public Boolean editUser(EditUserRequest userRequest) {
        User user = userRepository.getById(userRequest.getId());
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        if (userRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        user.setRoles(serviceUtils.rolesGenerator(userRequest.getRoles()));

        userRepository.save(user);

        return true;
    }
}
