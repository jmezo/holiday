package com.example.holiday.rest;

import com.example.holiday.model.DTO.UserDTO;
import com.example.holiday.model.User;
import com.example.holiday.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public void createNew(@RequestBody User user) {
        userService.createNew(user);
    }

    @PutMapping("boss")
    public void setBoss(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        userService.setBoss(userDTO);
    }


}
