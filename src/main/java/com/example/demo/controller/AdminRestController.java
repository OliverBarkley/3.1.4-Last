package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/admin")
public class AdminRestController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public List<User> userList(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id_var}")
    public ResponseEntity<User> getByUserById(@PathVariable("id_var") long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<User> addUser (User user ){
        Set <Role> roles = new HashSet<>();
        for (Role r : user.getRoles()){
            roles.add( roleService.save(r.getName()));
        }
        user.setRoles(roles);

        return new ResponseEntity<>(userService.add(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id_var}", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<User> update (@PathVariable(name = "id_var")long id, @RequestBody @Valid User user){
        return new ResponseEntity<>(userService.userEdit(user, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id_var}")
    public ResponseEntity<String> delete(@PathVariable(name = "id_var")long id){
        userService.delete(id);
        return new ResponseEntity<>("User Deleted", HttpStatus.OK);
    }




}
