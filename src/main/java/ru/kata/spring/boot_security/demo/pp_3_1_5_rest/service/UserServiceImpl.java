package ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.model.User;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void saveUser(User user) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);
    }

    @Transactional
    public void updateUser(User user) {
        if (!user.getPassword().equals(Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }else {
            user.setPassword(Objects.requireNonNull(userRepository.findById(user.getId()).orElse(null)).getPassword());
        }

        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
