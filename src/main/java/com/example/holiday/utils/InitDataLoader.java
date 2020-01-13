package com.example.holiday.utils;

import com.example.holiday.model.AvailableHolidays;
import com.example.holiday.model.User;
import com.example.holiday.repository.AvailableHolidaysRepository;
import com.example.holiday.repository.UserRepository;
import com.example.holiday.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
@Slf4j
public class InitDataLoader {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AvailableHolidaysRepository availableHolidaysRepository;
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        createUsers();
//        loadUsers();
        testShit();
    }

    public void testShit() {
        System.out.println(userService.getUserByName("jano").getName());
        System.out.println(userRepository.findByName("jano").getName());
    }

    public void loadUsers() {
        this.userRepository.saveAll(
                new Gson().fromJson(String.join("", getLines("users.json")),
                        new TypeToken<List<User>>() {
                        }.getType())
        );
    }


    private List<String> getLines(String s) {
        try {
            return Files.readAllLines(new File("src/main/resources/mocks/" + s).toPath());
        } catch (IOException e) {
            log.error("missing file: {}", s);
            return List.of();
        }
    }

    private void createUsers() {
        User u1 = new User("jano", "asd");
        User u2 = new User("kolompar", "asd");
        User u3 = new User("erno", "asd");
        User u4 = new User("lajos", "asd");
        AvailableHolidays a1 = new AvailableHolidays(u1);
        AvailableHolidays a2 = new AvailableHolidays(u2);
        AvailableHolidays a3 = new AvailableHolidays(u3);
        AvailableHolidays a4 = new AvailableHolidays(u4);
        u1.setAvailableHolidays(a1);
        u2.setAvailableHolidays(a2);
        u3.setAvailableHolidays(a3);
        u4.setAvailableHolidays(a4);
        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4));
    }

}
