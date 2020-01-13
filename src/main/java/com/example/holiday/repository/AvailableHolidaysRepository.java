package com.example.holiday.repository;

import com.example.holiday.model.AvailableHolidays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableHolidaysRepository extends JpaRepository<AvailableHolidays, Integer> {
}
