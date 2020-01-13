package com.example.holiday.model.DTO;

import com.example.holiday.model.Holiday;
import com.example.holiday.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewHolidayDTO {
    private String username;
    private LocalDate startDate;
    private LocalDate endDate;
    private Holiday.type type;

    public Holiday generateHoliday(User user) {
        Holiday holiday = new Holiday();
        holiday.setUser(user);
        holiday.setStartDate(startDate);
        holiday.setEndDate(endDate);
        holiday.setType(type);
        holiday.setStatus(Holiday.status.pending);
        return holiday;
    }
}
