package ir.tourismit.sampleLogin.controller;

import ir.tourismit.sampleLogin.dto.UserDTO;
import ir.tourismit.sampleLogin.service.UserServiceImp;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserServiceImp userService;

    @PostMapping("/signup")
    private long signUp(@RequestBody UserDTO userDTO) throws DuplicateMemberException {
        return userService.signUp(userDTO);
    }

    @PostMapping("/login")
    private String login(@RequestBody String username, String password){
        return "login kard masalan :)";
    }


    @GetMapping("/test/{value}")
    @Cacheable({"topic"})
    public int test(@PathVariable int value){
        int result =temp(value);
        return result;
    }
    public int temp(int val){
        System.out.println("here!");
        return val*3;
    }

}
