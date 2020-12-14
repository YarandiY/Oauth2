package ir.tourismit.sampleLogin.controller;

import ir.tourismit.sampleLogin.dto.UserSummary;
import ir.tourismit.sampleLogin.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping("getUser/{id}")
    public UserSummary getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }



}
