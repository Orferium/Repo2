package ru.itmentor.spring.boot_security.demo.service;



import ru.itmentor.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();
    Role getRoleByName(String name);
    Set<Role> getSetOfRoles(String[] roleNames);
    void add(Role role);
    void edit(Role role);


    Role getById(Long id);
}
