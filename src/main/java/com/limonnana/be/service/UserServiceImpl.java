package com.limonnana.be.service;

import com.limonnana.be.repository.UserRepository;
import com.limonnana.be.dto.UserDto;
import com.limonnana.be.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User addUser(UserDto userDto) {
        User user = new User(userDto);
        user = userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public Optional<User> userLogin(UserDto userDto) {
        boolean isLogged = false;
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                response.add(String.valueOf(userOptional.get().getId()));
                isLogged = true;
            } else {
                response.add("Username or the Password is incorrect");
            }
        } else {
            response.add("Username or the Password is incorrect");
        }
        if(isLogged){
            return userOptional;
        }else{
            return null;
        }

    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
