package com.example.holiday.rest;

import com.example.holiday.model.DTO.HolidayDTO;
import com.example.holiday.model.DTO.HolidayStatusDTO;
import com.example.holiday.model.DTO.NewHolidayDTO;
import com.example.holiday.model.Holiday;
import com.example.holiday.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping("/new")
    public void createNew(@RequestBody NewHolidayDTO holidayDTO) {
        holidayService.createNew(holidayDTO);
    }

    @PutMapping("/status")
    public void changeStatus(@RequestBody HolidayStatusDTO statusDTO) {
        holidayService.changeStatus(statusDTO);
    }

    @GetMapping
    public List<HolidayDTO> findHolidays(HolidayDTO holidayDTO) {
        return holidayService.findHolidays(holidayDTO);
    }

}
