package ru.kata.spring.boot_security.demo.pp_3_1_5_rest.service;

import ru.kata.spring.boot_security.demo.pp_3_1_5_rest.model.Role;

import java.util.Set;

public interface RoleService {

    Set<Role> findAll();

    Role findByName(String name);

    void save(Role role);

    Role findById(Long id);

}
