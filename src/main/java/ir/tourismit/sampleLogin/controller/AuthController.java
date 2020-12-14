package ir.tourismit.sampleLogin.controller;

import ir.tourismit.sampleLogin.dto.JwtAuthenticationResponse;
import ir.tourismit.sampleLogin.dto.LoginRequest;
import ir.tourismit.sampleLogin.dto.SignUpRequest;
import ir.tourismit.sampleLogin.service.AuthServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthServiceImp authService;

    @Autowired
    public AuthController(AuthServiceImp authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    private JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public Long register(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

//    @GetMapping("/test/{value}")
//    @Cacheable({"topic"})
//    public int testCache(@PathVariable int value){
//        int result =logic(value);
//        return result;
//    }
//    public int logic(int val){
//        System.out.println("here!");
//        return val*3;
//    }

}
