package com.example.holiday.model.DTO;

import com.example.holiday.model.Holiday;
import lombok.Data;

@Data
public class HolidayStatusDTO {
    private int id;
    private Holiday.status status;
}
