package com.example.holiday.service;

import com.example.holiday.model.DTO.HolidayDTO;
import com.example.holiday.model.DTO.HolidayStatusDTO;
import com.example.holiday.model.DTO.NewHolidayDTO;
import com.example.holiday.model.Holiday;
import com.example.holiday.model.User;
import com.example.holiday.repository.HolidayRepository;
import com.example.holiday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public void createNew(NewHolidayDTO holidayDTO) {
        User user = userService.getUserByName(holidayDTO.getUsername());
        holidayRepository.save(holidayDTO.generateHoliday(user));
    }

    public List<HolidayDTO> findHolidays(HolidayDTO holidayDTO) {
        Holiday queryHoliday;
        User user;
        if (holidayDTO.getUser() != null) {
            user = userService.getUserByName(holidayDTO.getUserName());
        } else {
            user = null;
        }
        queryHoliday = holidayDTO.generateHoliday(user);
        System.out.println(queryHoliday);
        List<Holiday> holidays = holidayRepository.findAll(Example.of(queryHoliday));
        return holidays.stream().map(HolidayDTO::fromHoliday).collect(Collectors.toList());
    }

    public void changeStatus(HolidayStatusDTO statusDTO) {
        Holiday h = holidayRepository.findById(statusDTO.getId()).orElse(null);
        if (h != null) {
            h.setStatus(statusDTO.getStatus());
            holidayRepository.save(h);
        }
    }
}
