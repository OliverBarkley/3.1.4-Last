package com.example.demo.service.Impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error"));
    }

    @Override
    public User userEdit(User user, long id) {
        User exUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error edit Service"));
        exUser.setName(user.getName());
        exUser.setLastName(user.getLastName());
        exUser.setUsername(user.getUsername());
        if (user.getPassword() != null) {
            exUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        exUser.setRoles(user.getRoles());
        userRepository.save(exUser);
        return exUser;
    }

    @Override
    public void delete(long id) {
        User exUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error"));
        userRepository.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
