package com.example.holiday.service;

import com.example.holiday.model.AvailableHolidays;
import com.example.holiday.model.DTO.HolidayDTO;
import com.example.holiday.model.DTO.HolidayStatusDTO;
import com.example.holiday.model.DTO.NewHolidayDTO;
import com.example.holiday.model.Holiday;
import com.example.holiday.model.User;
import com.example.holiday.repository.AvailableHolidaysRepository;
import com.example.holiday.repository.HolidayRepository;
import com.example.holiday.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AvailableHolidaysRepository availableHolidaysRepository;

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
        Holiday h = holidayRepository.findById(statusDTO.getId()).orElseThrow();

        if(h.getStatus() == statusDTO.getStatus()) return;

        int numOfDays = (int) DAYS.between(h.getStartDate(), h.getEndDate());
        System.out.println(numOfDays);

        AvailableHolidays ah = h.getUser().getAvailableHolidays();

        int availableDaysChange = availableHolidayNumChange(h.getStatus(), statusDTO.getStatus(), ah.getCount(), numOfDays);
        ah.setCount(availableDaysChange);
        h.setStatus(statusDTO.getStatus());
//        h.setStatus(statusDTO.getStatus());
//        availableHolidaysRepository.save(ah);
        holidayRepository.save(h);
    }

    private int availableHolidayNumChange(Holiday.status beforeStatus, Holiday.status afterStatus, int daysAvailable, int numOfDays) {
        if (beforeStatus == Holiday.status.pending || beforeStatus == Holiday.status.declined) {
            if (afterStatus == Holiday.status.approved) {
                return daysAvailable - numOfDays;
            } else {
                return daysAvailable;
            }
        } else {
            return daysAvailable + numOfDays;
        }
    }
}
