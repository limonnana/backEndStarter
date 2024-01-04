package com.limonnana.be.controller;

import com.limonnana.be.dto.UserDto;
import com.limonnana.be.entity.User;
import com.limonnana.be.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public User addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public Optional<User> userLogin(@RequestBody UserDto userDto){
        Optional<User> u = userService.userLogin(userDto);
        if(u.isPresent()){
            log.info(" User: " + u.get().getUsername());
        }else{
            log.info("wrong username and password");
        }
        return u;
    }
}
