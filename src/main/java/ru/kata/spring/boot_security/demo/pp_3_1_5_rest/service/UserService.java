package ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAll();
    void saveUser(User user);
    void deleteById(Long id);

    User findById(Long id);

    void updateUser(User user);
}
