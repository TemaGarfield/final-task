package by.kotik.please_work_jwt.controller;

import by.kotik.please_work_jwt.config.jwt.JwtUtils;
import by.kotik.please_work_jwt.model.ERole;
import by.kotik.please_work_jwt.model.Role;
import by.kotik.please_work_jwt.model.User;
import by.kotik.please_work_jwt.pojo.JwtResponse;
import by.kotik.please_work_jwt.pojo.LoginRequest;
import by.kotik.please_work_jwt.pojo.MessageResponse;
import by.kotik.please_work_jwt.pojo.SignupRequest;
import by.kotik.please_work_jwt.repository.RoleRepository;
import by.kotik.please_work_jwt.repository.UserRepository;
import by.kotik.please_work_jwt.service.AuthService;
import by.kotik.please_work_jwt.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        authService.signUp(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User created"));
    }
}
