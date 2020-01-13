package com.example.holiday.service;

import com.example.holiday.model.DTO.UserDTO;
import com.example.holiday.model.User;
import com.example.holiday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            System.out.println("---> user is null ---> username: "+ userName);
        }
        return user;
    }

    public void createNew(User user) {
        userRepository.save(user);
    }

    public void setBoss(UserDTO userDTO) {
        User user = userRepository.findByName(userDTO.getName());
        User boss = userRepository.findById(userDTO.getBossId()).orElse(null);
        if (boss != null && user != null) {
            user.setBoss(boss);
            userRepository.save(user);
        }
    }
}
