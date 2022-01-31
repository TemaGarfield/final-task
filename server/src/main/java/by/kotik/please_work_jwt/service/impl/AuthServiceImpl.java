package by.kotik.please_work_jwt.service.impl;

import by.kotik.please_work_jwt.config.jwt.JwtUtils;
import by.kotik.please_work_jwt.model.User;
import by.kotik.please_work_jwt.pojo.JwtResponse;
import by.kotik.please_work_jwt.pojo.LoginRequest;
import by.kotik.please_work_jwt.pojo.SignupRequest;
import by.kotik.please_work_jwt.repository.RoleRepository;
import by.kotik.please_work_jwt.repository.UserRepository;
import by.kotik.please_work_jwt.service.AuthService;
import by.kotik.please_work_jwt.service.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final ServiceUtils serviceUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, ServiceUtils serviceUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.serviceUtils = serviceUtils;
    }


    @Override
    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new JwtResponse(token, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    @Override
    public Boolean signUp(SignupRequest signupRequest) {
        if (serviceUtils.checker(signupRequest.getUsername(), signupRequest.getEmail())) {
            return false;
        }

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                serviceUtils.rolesGenerator(signupRequest.getRoles())
        );

        userRepository.save(user);

        return true;
    }

}
