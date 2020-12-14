package ir.tourismit.sampleLogin.service;

import ir.tourismit.sampleLogin.dto.JwtAuthenticationResponse;
import ir.tourismit.sampleLogin.dto.LoginRequest;
import ir.tourismit.sampleLogin.dto.SignUpRequest;
import ir.tourismit.sampleLogin.exception.ConflictException;
import ir.tourismit.sampleLogin.model.User;
import ir.tourismit.sampleLogin.repository.UserRepository;
import ir.tourismit.sampleLogin.security.JwtTokenProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    private static final Logger logger = LogManager.getLogger();

    @Override
    public JwtAuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        logger.info("User with [username: {}] has logged in", user.getUsername());
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public long signUp(SignUpRequest signUpRequest) throws  ConflictException{
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent())
           throw new ConflictException("Username [username: " + signUpRequest.getUsername() + "] is already taken");
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent())
            throw new ConflictException("Email [email: " + signUpRequest.getEmail() + "] is already taken");
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setMobile(signUpRequest.getMobile());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        User savedUser = userRepository.save(user);
        logger.info(new StringBuilder("A new User with username '" + savedUser.getUsername() + "' and Id '" + savedUser.getId() + "' was registered!"));
        return savedUser.getId();
    }
}
