package ru.kata.spring.boot_security.demo.pp_3_1_5_rest.userinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.model.Role;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.model.User;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service.RoleService;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service.UserService;
import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit {


    private UserServiceImpl userService;
    private RoleServiceImpl roleService;
    @Autowired
    public DataInit(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.save(role1);
        roleService.save(role2);

        List<Role> roleAdmin = new ArrayList<>();
        List<Role> roleUser = new ArrayList<>();

        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("admin", "admin", "admin@gmail.com", "admin", roleAdmin);
        User user2 = new User("user", "user", "user@gmail.com", "user", roleUser);

        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
