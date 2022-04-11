package com.example.demo.service.Impl;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role getRoleById(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Error"));
    }

    @Override
    public Role addRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public Set<Role> getRoles(long[] ides) {
        HashSet<Role> roles = new HashSet<>();
       for (int i = 0; i<ides.length;i++){
           roles.add(roleRepository.getById(ides[i]));
       }return roles;
    }

    @Override
    public List<Role> getAllRoles() {
        return null;
    }

    @Override
    public Role save(String name) {
       if (roleRepository.findByName(name) == null) {
           Role role = new Role(name);
           return role;
       } return roleRepository.findByName(name);
    }


}
