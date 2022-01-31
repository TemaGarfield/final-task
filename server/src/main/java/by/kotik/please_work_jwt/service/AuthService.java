package by.kotik.please_work_jwt.service;

import by.kotik.please_work_jwt.pojo.JwtResponse;
import by.kotik.please_work_jwt.pojo.LoginRequest;
import by.kotik.please_work_jwt.pojo.SignupRequest;

public interface AuthService {
    JwtResponse signIn(LoginRequest loginRequest);
    Boolean signUp(SignupRequest signupRequest);
}
