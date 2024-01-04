package com.limonnana.be.service;

import com.limonnana.be.dto.UserDto;
import com.limonnana.be.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {
    @Transactional
    User addUser(UserDto userDto);

    Optional<User> userLogin(UserDto userDto);

    List<User> getUsers();
}
