package com.example.holiday.model.DTO;

import com.example.holiday.model.Holiday;
import com.example.holiday.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO {
    String userName;
    User user;
    LocalDate startDate;
    LocalDate endDate;
    Holiday.type type;
    Holiday.status status;

    public static HolidayDTO fromHoliday(Holiday holiday) {
        return HolidayDTO.builder()
                .userName(holiday.getUser().getName())
                .startDate(holiday.getStartDate())
                .endDate(holiday.getEndDate())
                .type(holiday.getType())
                .status(holiday.getStatus()).build();
    }

    public Holiday generateHoliday(User user) {
        return Holiday.builder()
                .user(user)
                .startDate(startDate)
                .endDate(endDate)
                .type(type)
                .status(status).build();
    }
}
