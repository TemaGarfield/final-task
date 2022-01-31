package by.kotik.please_work_jwt.controller;

import by.kotik.please_work_jwt.model.Role;
import by.kotik.please_work_jwt.pojo.EditUserRequest;
import by.kotik.please_work_jwt.pojo.MessageResponse;
import by.kotik.please_work_jwt.pojo.SignupRequest;
import by.kotik.please_work_jwt.pojo.UserResponse;
import by.kotik.please_work_jwt.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/test/admin")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(adminService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/get_all_roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(adminService.getAllRoles());
    }

    @GetMapping("/get_user_roles/{id}")
    public ResponseEntity<Set<Role>> getUserRoles(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(adminService.getUserRoles(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            adminService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save_user")
    public ResponseEntity<?> saveUser(@RequestBody SignupRequest signupRequest) {
        adminService.saveUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User created"));
    }

    @PostMapping("/edit_user")
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest editUserRequest) {
        adminService.editUser(editUserRequest);
        return ResponseEntity.ok(new MessageResponse("User edited"));
    }
}
