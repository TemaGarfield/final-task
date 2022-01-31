package by.kotik.please_work_jwt.service;

import by.kotik.please_work_jwt.model.Role;
import by.kotik.please_work_jwt.model.User;
import by.kotik.please_work_jwt.pojo.EditUserRequest;
import by.kotik.please_work_jwt.pojo.SignupRequest;
import by.kotik.please_work_jwt.pojo.UserResponse;

import java.util.List;
import java.util.Set;

public interface AdminService {
    List<UserResponse> getAllUsers();
    List<Role> getAllRoles();
    Set<Role> getUserRoles(Long id);
    void deleteUser(Long userId);
    Boolean saveUser(SignupRequest userRequest);
    Boolean editUser(EditUserRequest userRequest);
}
