package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role getRoleById(long id);
    Role addRole(Role role);
    Set<Role> getRoles(long [] ides);
    List<Role> getAllRoles();
    Role save(String name);

}
