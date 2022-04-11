package com.example.demo.DbInit;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DbInit implements InitializingBean {
    private final UserService userService;
    private final RoleService roleService;

    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @Override
    public void afterPropertiesSet() throws Exception {try {
        HashSet<Role> roles = new HashSet<>();
        roleService.addRole(new Role( "ROLE_ADMIN"));
        roleService.addRole(new Role( "ROLE_USER"));
        Role role = roleService.getRoleById(1L);
        roles.add(role);
        User user = new User("Alexander", "Kondratyev", "info@ivawood.com", "admin",roles);
        userService.add(user);

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("При попытке заполнить базу данных возникла ошибка!");
    }
    }
}
