package ir.tourismit.sampleLogin.service;

import ir.tourismit.sampleLogin.dto.JwtAuthenticationResponse;
import ir.tourismit.sampleLogin.dto.LoginRequest;
import ir.tourismit.sampleLogin.dto.SignUpRequest;
import ir.tourismit.sampleLogin.dto.UserDTO;
import ir.tourismit.sampleLogin.exception.ConflictException;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    JwtAuthenticationResponse login(LoginRequest loginRequest);
    long signUp(SignUpRequest signUpRequest) throws ConflictException;
}
